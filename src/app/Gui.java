package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants_and_singleton_classes.Enums.BookingClass;
import models.CheckInDeskModel;
import models.Flight;
import models.FlightModel;
import models.PassengerNumberModel;
import models.PassengersTableModel;
import views.CheckInDeskView;
import views.ControlsView;
import views.FlightView;
import views.PassengerNumberView;

/**
 * The Gui class represents the graphical user interface of the application.
 */
public class Gui extends JFrame {
	
	Controller controller;
	ArrayList<FlightModel> flightModels = new ArrayList<FlightModel>();
	ArrayList<Flight> flights;
	PassengerQueueController economyQueueController;
	PassengerQueueController businessQueueController;
	PassengersTableModel businessTableModel;
	PassengersTableModel economyTableModel;
	PassengerNumberModel passengerBusinessNumberModel;
	PassengerNumberModel passengerEconomyNumberModel;
	JPanel economyCheckInDeskContainer;
	JPanel businessCheckInDeskContainer;
	private JPanel displayPanel;
	private JTable businessPassengerQueue;
	private JTable economyPassengerQueue;
	private JPanel flightsContainer;
	
	/**
     * Constructs a GUI object.
     * 
     * @param economyQueueController the controller for the economy passenger queue
     * @param businessQueueController the controller for the business passenger queue
     * @param flights the list of flights
     * @param controlsView the controls view
     */
	public Gui(PassengerQueueController economyQueueController, PassengerQueueController businessQueueController, ArrayList<Flight> flights, ControlsView controlsView ) {
		this.economyQueueController = economyQueueController;
		this.businessQueueController = businessQueueController;
		this.flights = flights;
		setSize(new Dimension(900, 0));
		setTitle("Airline CheckIn Simulation");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 500);
		setVisible(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		displayPanel = new JPanel();
		tabbedPane.addTab("Display", null, displayPanel, null);
		tabbedPane.addTab("Controls", null, controlsView, "Controls");
		
		
		JPanel numberViewContainer = new JPanel();
		numberViewContainer.setLayout(new GridLayout(1, 2, 2, 0));
		
		passengerBusinessNumberModel = new PassengerNumberModel(businessQueueController);
		PassengerNumberView passengerBusinessNumberView = new PassengerNumberView(passengerBusinessNumberModel);
		
		passengerEconomyNumberModel = new PassengerNumberModel(economyQueueController);
		PassengerNumberView passengerEconomyNumberView = new PassengerNumberView(passengerEconomyNumberModel);
		
		numberViewContainer.add(passengerEconomyNumberView);
		numberViewContainer.add(passengerBusinessNumberView);
		
		JScrollPane flightsContainerScrollPane = new JScrollPane();
		flightsContainerScrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel checkInDeskContainer = new JPanel();
		
		JPanel passengerQueueContainer = new JPanel();
		GroupLayout groupLayout_contentPane = new GroupLayout(displayPanel);
		groupLayout_contentPane.setHorizontalGroup(
			groupLayout_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout_contentPane.createSequentialGroup()
							.addComponent(checkInDeskContainer, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(groupLayout_contentPane.createSequentialGroup()
								.addComponent(numberViewContainer, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(groupLayout_contentPane.createSequentialGroup()
							.addComponent(flightsContainerScrollPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
							.addGap(5))))
				.addGroup(groupLayout_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(passengerQueueContainer, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addGap(5))
		);
		groupLayout_contentPane.setVerticalGroup(
			groupLayout_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(numberViewContainer, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(passengerQueueContainer, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(checkInDeskContainer, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(flightsContainerScrollPane, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(5))
		);
		passengerQueueContainer.setLayout(new GridLayout(1, 2, 10, 0));
		
		economyTableModel = new PassengersTableModel(economyQueueController);
		
		economyPassengerQueue = new JTable(economyTableModel);
		economyPassengerQueue.setTableHeader(null);
		economyPassengerQueue.setFillsViewportHeight(true);
		JScrollPane economyPassengerQueueScrollPane = new JScrollPane(economyPassengerQueue);
		passengerQueueContainer.add(economyPassengerQueueScrollPane);
		
		JPanel economyHeaderPanel = new JPanel(new BorderLayout());
		economyHeaderPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		economyHeaderPanel.add(new JLabel("Economy Passengers"), BorderLayout.CENTER);
		economyPassengerQueueScrollPane.setColumnHeaderView(economyHeaderPanel);
		
		JPanel businessHeaderPanel = new JPanel(new BorderLayout());
		businessHeaderPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		businessHeaderPanel.add(new JLabel("Business Passengers"), BorderLayout.CENTER);
		
		businessTableModel = new PassengersTableModel(businessQueueController);
		
		businessPassengerQueue = new JTable(businessTableModel);
		businessPassengerQueue.setFillsViewportHeight(true);
		JScrollPane businessPassengerQueueScrollPane = new JScrollPane(businessPassengerQueue);
		passengerQueueContainer.add(businessPassengerQueueScrollPane);
		businessPassengerQueueScrollPane.setColumnHeaderView(businessHeaderPanel);
		
		GridBagLayout gridbag_checkInDeskContainer = new GridBagLayout();
		gridbag_checkInDeskContainer.columnWidths = new int[] {100, 100};
		gridbag_checkInDeskContainer.rowHeights = new int[]{4, 0};
		gridbag_checkInDeskContainer.columnWeights = new double[]{0.0, 0.0};
		gridbag_checkInDeskContainer.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		checkInDeskContainer.setLayout(new GridLayout(1, 2));
		
		JScrollPane economyCheckInDeskContainer_scrollPane = new JScrollPane();
		economyCheckInDeskContainer_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		economyCheckInDeskContainer_scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.EAST;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		checkInDeskContainer.add(economyCheckInDeskContainer_scrollPane, gbc_scrollPane);
		
		economyCheckInDeskContainer = new JPanel();
		economyCheckInDeskContainer_scrollPane.setViewportView(economyCheckInDeskContainer);
		economyCheckInDeskContainer.setLayout(new BoxLayout(economyCheckInDeskContainer, BoxLayout.X_AXIS));
		economyCheckInDeskContainer_scrollPane.setBorder(new TitledBorder(null, "Economy CheckIn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane businessCheckInDeskContainer_scrollpane = new JScrollPane();
		businessCheckInDeskContainer_scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		businessCheckInDeskContainer_scrollpane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.weighty = 1.0;
		gbc_scrollPane_2.weightx = 1.0;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 0;
		checkInDeskContainer.add(businessCheckInDeskContainer_scrollpane, gbc_scrollPane_2);
		
		businessCheckInDeskContainer = new JPanel();
		businessCheckInDeskContainer_scrollpane.setViewportView(businessCheckInDeskContainer);
		businessCheckInDeskContainer.setLayout(new BoxLayout(businessCheckInDeskContainer, BoxLayout.X_AXIS));
		businessCheckInDeskContainer_scrollpane.setBorder(new TitledBorder(null, "Business CheckIn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		flightsContainer = new JPanel();
		flightsContainerScrollPane.setViewportView(flightsContainer);
		flightsContainer.setLayout(new BoxLayout(flightsContainer, BoxLayout.X_AXIS));
		
		for(int i=0; i<flights.size(); i++) {
			FlightModel flightModel = new FlightModel(flights.get(i));
			this.flightModels.add(flightModel);
			FlightView flightView = new FlightView(flightModel);
			flightsContainer.add(flightView);
		}
		
		groupLayout_contentPane.setAutoCreateGaps(true);
		groupLayout_contentPane.setAutoCreateContainerGaps(true);
		
		displayPanel.setLayout(groupLayout_contentPane);
		
	}
	
	/**
     * Creates a new check-in desk and adds it to the GUI.
     * 
     * @param checkInController the check-in controller
     * @param type              the booking class type
     */
	public void createNewCheckInDesk(CheckInController checkInController, BookingClass type) {
		if(type == BookingClass.BUISINESS) {
			checkInController.addPassengerObserver(businessTableModel);
			checkInController.addPassengerObserver(passengerBusinessNumberModel);
			CheckInDeskModel checkindeskmodel = new CheckInDeskModel("Desk "+checkInController.getDeskNumber(),checkInController);
			CheckInDeskView checkInDeskView = new CheckInDeskView(checkindeskmodel);
			businessCheckInDeskContainer.add(checkInDeskView);
			revalidate();
			repaint();
		}
		else {
			checkInController.addPassengerObserver(economyTableModel);
			checkInController.addPassengerObserver(passengerEconomyNumberModel);
			CheckInDeskModel checkindeskmodel = new CheckInDeskModel("Desk "+checkInController.getDeskNumber(),checkInController);
			CheckInDeskView checkInDeskView = new CheckInDeskView(checkindeskmodel);
			economyCheckInDeskContainer.add(checkInDeskView);
			revalidate();
			repaint();
		}
	}
	
	/**
     * Removes the check-in desk from the GUI.
     * 
     * @param type the booking class type
     */
	public void removeCheckInDesk(BookingClass type) {
		if(type == BookingClass.BUISINESS) {
			businessCheckInDeskContainer.remove(businessCheckInDeskContainer.getComponentCount()-1);
			revalidate();
			repaint();
		}else {
			economyCheckInDeskContainer.remove(economyCheckInDeskContainer.getComponentCount()-1);
			revalidate();
			repaint();
		}
	}
	
	/**
     * Attaches the check-in desk to the flight.
     * 
     * @param checkInController the check-in controller
     */
	public void attachCheckInDeskToFlight(CheckInController checkInController) {
		for(int i=0; i<flightModels.size(); i++) {
			checkInController.addFlightObserver(flightModels.get(i));
		}	
	}		
}
