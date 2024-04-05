package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import constants_and_singleton_classes.Enums;
import constants_and_singleton_classes.Logger;
import constants_and_singleton_classes.SimulationControls;
import constants_and_singleton_classes.Enums.BookingClass;
import controllers.ControlsController;
import exceptions.InvalidBookingReference;
import models.Passenger;
import observers.CheckInObserver;
import views.ControlsView;

/**
 * The Manager class manages the application's components and functionalities.
 */
public class Manager implements CheckInObserver {
	
	ArrayList<Thread> economyCheckInThreads = new ArrayList<Thread>();
	ArrayList<Thread> businessCheckInThreads = new ArrayList<Thread>();
	Map<Thread, CheckInController> economyThreadsToCheckInDeskMap = new HashMap<>();
	Map<Thread, CheckInController> businessThreadsToCheckInDeskMap = new HashMap<>();
	
	Gui gui;
	Queue<Passenger> economyQueue;
    Queue<Passenger> businessQueue;
    Controller controller;
    
    
    /**
     * Constructs a Manager object and initializes application components.
     * 
     * @throws InvalidBookingReference if the booking reference is invalid
     * @throws IOException if an I/O error occurs
     */
    
	public Manager() throws InvalidBookingReference, IOException {
	
	    Logger.getLogger().log("Application started", Enums.Severity.INFO);
	    
	    Data reader = new Data("bookings.csv", "flights.csv");
	    controller = reader.read();
	    
	    this.economyQueue = new LinkedList<Passenger>();
	    this.businessQueue = new LinkedList<Passenger>();
	    
	    PassengerQueueController economyQueueController = new PassengerQueueController(economyQueue, controller.getBookings(), BookingClass.ECONOMY);
	    PassengerQueueController businessQueueController = new PassengerQueueController(businessQueue, controller.getBookings(), BookingClass.BUISINESS);
	    
	    ControlsView controlsView = new ControlsView();
	    ControlsController controlsController = new ControlsController(controlsView);
	    controlsController.addObserver(this);
	    
	    gui = new Gui(economyQueueController, businessQueueController, controller.getFlights() ,controlsView);
	    Thread passengerQueueThread = new Thread(economyQueueController);
	    passengerQueueThread.start();
	    Thread buisinessQueueThread = new Thread(businessQueueController);
	    buisinessQueueThread.start(); 
	    for (int i = 0; i < SimulationControls.getInstance().getEconomyCheckInDeskNumber(); i++) {
	    	createCheckInDesk(economyQueue,economyCheckInThreads,SimulationControls.getInstance().getEconomyCheckInDeskTime(),
	    			economyThreadsToCheckInDeskMap,BookingClass.ECONOMY);
	    }
	    
	    for (int i = 0; i < SimulationControls.getInstance().getBusinessCheckInDeskNumber(); i++) {
	    	createCheckInDesk(businessQueue,businessCheckInThreads,SimulationControls.getInstance().getBusinessCheckInDeskTime(),
	    			businessThreadsToCheckInDeskMap,BookingClass.BUISINESS);
	    }
	  }

	/**
     * Creates a check-in desk for a given queue of passengers.
     * 
     * @param passengerQueue the queue of passengers
     * @param checkInThreads the list of check-in threads
     * @param checkInDeskTime the check-in desk time
     * @param threadsToCheckInDeskMap the map of threads to check-in desks
     * @param bc the booking class
     */
	  public void createCheckInDesk(Queue<Passenger> passengerQueue, ArrayList<Thread> checkInThreads, long checkInDeskTime, 
			  Map<Thread, CheckInController> threadsToCheckInDeskMap, BookingClass bc) {
		  synchronized(this) {
	    CheckInController checkInController = new CheckInController(passengerQueue, controller.getBookings(),checkInDeskTime);
	    checkInController.setDeskNumber(checkInThreads.size()+1);
	    gui.createNewCheckInDesk(checkInController, bc);
	    gui.attachCheckInDeskToFlight(checkInController);
	    Thread thread = new Thread(checkInController);
	    checkInThreads.add(thread);
	    threadsToCheckInDeskMap.put(thread, checkInController);
	    checkInThreads.get(checkInThreads.size() -1 ).start();
		  }
	  }
	@Override
	public void add(BookingClass bc) {
		if(bc == BookingClass.BUISINESS) {
			createCheckInDesk(businessQueue, businessCheckInThreads ,SimulationControls.getInstance().getBusinessCheckInDeskTime(),
					businessThreadsToCheckInDeskMap, bc);
		}
		else {
			createCheckInDesk(economyQueue, economyCheckInThreads ,SimulationControls.getInstance().getEconomyCheckInDeskTime(),
					economyThreadsToCheckInDeskMap, bc);
		}
	}
	@Override
	public void remove(BookingClass bc) {
		if(bc == BookingClass.BUISINESS) {
			CheckInController checkInDesk = businessThreadsToCheckInDeskMap.remove(businessCheckInThreads.get(businessCheckInThreads.size()-1));
			checkInDesk.stopThread();
			businessCheckInThreads.remove(businessCheckInThreads.size()-1);
			gui.removeCheckInDesk(bc);
		}
		else {
			CheckInController checkInDesk = economyThreadsToCheckInDeskMap.remove(economyCheckInThreads.get(economyCheckInThreads.size()-1));
			checkInDesk.stopThread();
			economyCheckInThreads.remove(economyCheckInThreads.size()-1);
			gui.removeCheckInDesk(bc);
		}
	}
	
}
