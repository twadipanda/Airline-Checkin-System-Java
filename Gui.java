package app;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import exceptions.BookingNotFound;
import exceptions.CheckInFailed;
import exceptions.InvalidBookingReference;

/**
 * The UI class of the application
 * 
 */
public class Gui extends JFrame{
	
	private JTextField referenceCodeTextField;
	private JTextField lastNameTextField;
	private Controller controller;
	private JPanel baggagePanel;
	private JLabel nameLabel;
	private JLabel bookingReferenceCodeLabel;
	private Booking passengerBooking;
	private JTextField heightTextField;
	private JTextField widthTextField;
	private JTextField lengthTextField;
	private JTextField weightTextField;
	private JLabel excessBaggageFeeLabel;

	/**
     * Constructor of the GUI class
     * @param Controller controller
     * calls the initiate method which creates the UI components
     */
	public Gui(Controller controller) {
		this.controller = controller;
		initialize();
  	}
	
	/**
     * Initiliaze method
     * Create the UI elements
     */
  	private void initialize() {
  		setTitle("Airport Check In System");
		setAlwaysOnTop(true);
		setBounds(100, 100, 720, 517);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		// Add a window listener that listens for a user exit and generate the report		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to close the application?",
						"Close Application?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {					
					controller.report();
					System.exit(0);
				}
			}
		});
		
		JPanel bookingPanel = new JPanel();
		bookingPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Booking Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bookingPanel.setBounds(10, 11, 685, 165);
		getContentPane().add(bookingPanel);
		bookingPanel.setLayout(null);
		
		JLabel referenceCodeLabel = new JLabel("Reference Code:");
		referenceCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		referenceCodeLabel.setBounds(21, 36, 96, 35);
		bookingPanel.add(referenceCodeLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lastNameLabel.setBounds(21, 96, 96, 35);
		bookingPanel.add(lastNameLabel);
		
		referenceCodeTextField = new JTextField();
		referenceCodeTextField.setBounds(127, 36, 238, 35);
		bookingPanel.add(referenceCodeTextField);
		referenceCodeTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(127, 96, 238, 35);
		bookingPanel.add(lastNameTextField);
		
		referenceCodeTextField.setNextFocusableComponent(lastNameTextField);
		JButton findButton = new JButton("FIND");
		
		lastNameTextField.setNextFocusableComponent(findButton);
		findButton.setNextFocusableComponent(referenceCodeTextField);
		
		/*
		 * Add an action listener to the find button to allow the user to search for a booking
		 * */
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookingReference = referenceCodeTextField.getText();
				String lastName = lastNameTextField.getText();
				 passengerBooking = controller.getBooking(lastName, bookingReference);
				 if(passengerBooking != null) {
					  updateBaggageDetails();
				 }
				 else {
					 JOptionPane.showMessageDialog(bookingPanel, "Your details don't exist");
				 }
			}
		});
		findButton.setBounds(416, 68, 89, 40);
		bookingPanel.add(findButton);
		
		baggagePanel = new JPanel();
		baggagePanel.setBorder(new TitledBorder(null, "Baggage Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		baggagePanel.setLayout(null);
		baggagePanel.setBounds(10, 187, 685, 280);
		getContentPane().add(baggagePanel);
		baggagePanel.setVisible(false);
		
		JLabel weightLabel = new JLabel("Weight (kg):");
		weightLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		weightLabel.setBounds(48, 14, 96, 35);
		baggagePanel.add(weightLabel);
		
		JLabel heightLabel = new JLabel("Height (cm):");
		heightLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		heightLabel.setBounds(48, 63, 96, 35);
		baggagePanel.add(heightLabel);
		
		JButton checkInButton = new JButton("CHECK IN");
		checkInButton.setBounds(563, 22, 89, 40);
		baggagePanel.add(checkInButton);
		
		heightTextField = new JTextField();
		heightTextField.setText("0");
		heightTextField.setBounds(154, 70, 67, 20);
		baggagePanel.add(heightTextField);
		
		JLabel widthLabel = new JLabel("Width (cm):");
		widthLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		widthLabel.setBounds(223, 63, 74, 35);
		baggagePanel.add(widthLabel);
		
		widthTextField = new JTextField();
		widthTextField.setText("0");
		widthTextField.setBounds(307, 70, 67, 20);
		baggagePanel.add(widthTextField);
		
		JLabel lengthLabel = new JLabel("Length (cm):");
		lengthLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lengthLabel.setBounds(379, 63, 76, 35);
		baggagePanel.add(lengthLabel);
		
		lengthTextField = new JTextField();
		lengthTextField.setText("0");
		lengthTextField.setBounds(465, 70, 67, 20);
		baggagePanel.add(lengthTextField);
		
		weightTextField = new JTextField();
		weightTextField.setText("0");
		weightTextField.setBounds(154, 21, 67, 20);
		baggagePanel.add(weightTextField);
		
		JLabel nameTextLabel = new JLabel("Name:");
		nameTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		nameTextLabel.setBounds(35, 155, 109, 35);
		baggagePanel.add(nameTextLabel);
		
		nameLabel = new JLabel("");
		nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		nameLabel.setBounds(154, 155, 257, 35);
		baggagePanel.add(nameLabel);
		
		JLabel bookingReferenceCodeTextLabel = new JLabel("Reference Code:");
		bookingReferenceCodeTextLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		bookingReferenceCodeTextLabel.setBounds(35, 201, 109, 35);
		baggagePanel.add(bookingReferenceCodeTextLabel);
		
		bookingReferenceCodeLabel = new JLabel("");
		bookingReferenceCodeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		bookingReferenceCodeLabel.setBounds(154, 201, 257, 35);
		baggagePanel.add(bookingReferenceCodeLabel);
		
		excessBaggageFeeLabel = new JLabel("");
		excessBaggageFeeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		excessBaggageFeeLabel.setBounds(154, 109, 143, 35);
		baggagePanel.add(excessBaggageFeeLabel);
		
		JLabel lblexcessBaggageFee = new JLabel("Excess Baggage Fees:");
		lblexcessBaggageFee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblexcessBaggageFee.setHorizontalAlignment(SwingConstants.TRAILING);
		lblexcessBaggageFee.setBounds(13, 109, 131, 35);
		baggagePanel.add(lblexcessBaggageFee);
		
		/*
		 * Add an action listener to the check-In button to allow the user to checkIn for a flight
		 * */
		checkInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float weight = Float.parseFloat(weightTextField.getText());
					float length = Float.parseFloat(lengthTextField.getText());
					float width  = Float.parseFloat(widthTextField.getText());
					float height = Float.parseFloat(heightTextField.getText());
					
					float volume = length + width + height;
					
					String lastName = passengerBooking.getLName();
					String reference = passengerBooking.getReference();
					controller.checkIn(lastName, reference, weight, volume);
					JOptionPane.showMessageDialog(getContentPane(), "Check In Successful");
					passengerBooking = null;
					updateBaggageDetails();
				} 
				catch (CheckInFailed | BookingNotFound | InvalidBookingReference | NumberFormatException  e1) {
					JOptionPane.showMessageDialog(Gui.this,
							"Something went wrong try again","Check-In Unsuccessful, You might have been checked in before.",
							JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(Gui.this,"Something went wrong try again","Check-In Unsuccessful",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		/*
		 * Add an input verifier anounymous class to prevent the user from typing any character other than a number 
		 * for the weight, height, width and length text fields
		 * */
		InputVerifier inp = new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField tf = (JTextField) input;
				try {
					float val = Float.parseFloat(tf.getText());
					if(val < 0) {
						return false;
					}
					return true;
				}
				catch(NumberFormatException e) {
					return false;
				}
			}
		};
		
		weightTextField.setInputVerifier(inp);
		lengthTextField.setInputVerifier(inp);
		widthTextField.setInputVerifier(inp);
		heightTextField.setInputVerifier(inp);
		
		
		/*
		 * Add an key listener to the textfields to make them reactive to changes in input
		 * to compute the excess baggage fees
		 * */
		weightTextField.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyReleased(KeyEvent e) {
				calculateExcessBaggageFee();
			}
		});
		
		lengthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculateExcessBaggageFee();
			}
		});
		
		widthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculateExcessBaggageFee();
			}
		});
		
		heightTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				calculateExcessBaggageFee();
			}
		});
		
		setVisible(true);
		
  	}
  	
  	/*
	 * updateBaggageDetails method which triggers the BaggagePanel display
	 * based on whether the user has a valid passenger booking
	 * 
	 * */

	void updateBaggageDetails() {
		if(passengerBooking != null) {
			if(!baggagePanel.isVisible()) {
				baggagePanel.setVisible(true);
				bookingReferenceCodeLabel.setText(passengerBooking.getReference());
				nameLabel.setText(passengerBooking.getFName()+" "+passengerBooking.getLName());
		 }
			else {
				bookingReferenceCodeLabel.setText(passengerBooking.getReference());
				nameLabel.setText(passengerBooking.getFName()+" "+passengerBooking.getLName());
			}
			calculateExcessBaggageFee();
		}
		else {
			referenceCodeTextField.setText("");
			lastNameTextField.setText("");
			if(baggagePanel.isVisible()) {
				bookingReferenceCodeLabel.setText("");
				nameLabel.setText("");
				baggagePanel.setVisible(false);
			}
		}
	}

	/*
	 * Calculates the fee for the excess baggage of the user
	 * */
	void calculateExcessBaggageFee() {
		String weightText = weightTextField.getText().trim();
		String lengthText = lengthTextField.getText().trim(); 
		String widthText = widthTextField.getText().trim(); 
		String heightText = heightTextField.getText().trim(); 
		
		float weight = 0;
		float length = 0;
		float width  = 0;
		float height = 0;
		try {
			weight = Float.parseFloat(weightText);
			length = Float.parseFloat(lengthText);
			width = Float.parseFloat(widthText);
			height = Float.parseFloat(heightText);
		}
		catch(NumberFormatException fe) {
		}
		finally {
			float volume = length + width + height;
			Flight flight = passengerBooking.getFlight();
			float fee = controller.calculateFee(weight, volume, flight);
			excessBaggageFeeLabel.setText(String.valueOf(fee));
		}
	}
}
