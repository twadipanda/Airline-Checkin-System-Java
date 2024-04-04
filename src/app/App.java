package app;
import exceptions.BookingNotFound;
import exceptions.CheckInFailed;
import exceptions.InvalidBookingReference;
import observers.CheckInObserver;
import views.ControlsView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import app.Enums.BookingClass;
import app.Enums.FlightClass;


public class App {
		
  public static void main(String[] args) throws InvalidBookingReference, CheckInFailed, BookingNotFound, IOException {
	  new Manager();
  }
//    int ECONOMY_CHECK_IN_DESKS = 3;
//    int BUISINESS_CHECK_IN_DESKS = 2;
//    long ECONOMY_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15L, TimeUnit.SECONDS);
//    long BUSINESS_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(30L, TimeUnit.SECONDS);
//    ArrayList<Thread> checkInThreads = new ArrayList<Thread>();
//    ArrayList<CheckInController> checkInControllers = new ArrayList<CheckInController>();
//    Logger.getLogger().log("Application started", Enums.Severity.INFO);
//    Queue<Passenger> economyQueue = new LinkedList<Passenger>();
//    Queue<Passenger> businessQueue = new LinkedList<Passenger>();
//    Data reader = new Data("src/bookings.csv", "src/flights.csv");
//    Controller controller = reader.read();
//    
//    
//    PassengerQueueController economyQueueController = new PassengerQueueController(economyQueue, controller.getBookings(), BookingClass.ECONOMY);
//    PassengerQueueController buisinessQueueController = new PassengerQueueController(businessQueue, controller.getBookings(), BookingClass.BUISINESS);
//    
//    ControlsView controlsView = new ControlsView();
//    new ControlsController(controlsView);
//    
//    Gui gui = new Gui(checkInControllers, economyQueueController, buisinessQueueController, controlsView);
//    Thread passengerQueueThread = new Thread(economyQueueController);
//    passengerQueueThread.start();
//    Thread buisinessQueueThread = new Thread(buisinessQueueController);
//    buisinessQueueThread.start();   
//    
//    for (int i = 0; i < SimulationControls.getInstance().getEconomyCheckInDeskNumber(); i++) {
//    	CheckInController checkInController = new CheckInController(economyQueue, controller.getBookings(), SimulationControls.getInstance().getEconomyCheckInDeskTime());
//    	checkInController.setN(i+1);
//    	gui.createNewCheckInDesk(checkInController, BookingClass.ECONOMY);
//    	gui.attachCheckInDeskToFlight(checkInController);
//    	checkInThreads.add(new Thread(checkInController));
//    	checkInThreads.get(i).start();
//    }
//
//    for (int i = 0; i < SimulationControls.getInstance().getBusinessCheckInDeskNumber(); i++) {
//    	CheckInController checkInController = new CheckInController(businessQueue, controller.getBookings(), SimulationControls.getInstance().getBusinessCheckInDeskTime());
//    	checkInController.setN(i+ECONOMY_CHECK_IN_DESKS+1);
//    	gui.createNewCheckInDesk(checkInController, BookingClass.BUISINESS);
//    	gui.attachCheckInDeskToFlight(checkInController);
//    	checkInThreads.add(new Thread(checkInController));
//    }  
//  }
//
//  public void initializeCheckInDesk(int i, Gui gui, ArrayList<Thread> checkInThreads, Queue<Passenger> passengerQueue, Controller controller, long CHECK_IN_DESKS_END_TIME) {
//    CheckInController checkInController = new CheckInController(passengerQueue, controller.getBookings(), CHECK_IN_DESKS_END_TIME);
//    checkInController.setN(i+1);
//    gui.createNewCheckInDesk(checkInController, BookingClass.ECONOMY);
//    gui.attachCheckInDeskToFlight(checkInController);
//    checkInThreads.add(new Thread(checkInController));
//    checkInThreads.get(i).start();
//  }

}
