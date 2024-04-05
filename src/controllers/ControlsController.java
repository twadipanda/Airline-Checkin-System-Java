package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

import constants_and_singleton_classes.SimulationControls;
import constants_and_singleton_classes.Enums.BookingClass;
import observers.CheckInObserver;
import views.ControlsView;

/**
 * The ControlsController class manages user interactions with controls in the application,
 * such as adding/removing check-in desks and updating simulation parameters.
 */
public class ControlsController {
	
	CheckInObserver observer; 
	
	/**
     * Constructs a new ControlsController with the specified ControlsView.
     *
     * @param view The ControlsView instance to associate with this controller.
     */
	public ControlsController(ControlsView view) {
		view.btnAddBusinessDesk.addActionListener(new AddBusinessCheckinDeskButtonListener());
		view.btnRemoveBusinessDesk.addActionListener(new RemoveBusinessCheckinDeskButtonListener());
		view.btnAddEconomyDesk.addActionListener(new AddEconomyCheckinDeskButtonListener());
		view.btnRemoveEconomyDesk.addActionListener(new ReomveEconomyCheckinDeskButtonListener());
		
		view.businesscheckInDeskDurationTextField.addKeyListener(new BusinessTextChangedListener());
		view.economycheckInDeskDurationTextField.addKeyListener(new EconomyTextChangedListener());
		
		view.feeCalculationWeightTextField.addKeyListener(new WeightRatioTextChangedListener());
		view.feeCalculationVolumeTextField.addKeyListener(new VolumeRatioTextChangedListener());
	}
	
	/**
     * Sets the observer for the ControlsController.
     *
     * @param observer The CheckInObserver to set.
     */
	 public void  addObserver(CheckInObserver observer){
		this.observer = observer;
	}
	
	 /**
	     * Notifies the observer to add a check-in desk of the specified booking class.
	     *
	     * @param bc The booking class of the check-in desk to add.
	     */
	 void notifyObseverCheckInObserverAdd(BookingClass bc) {
		observer.add(bc);
	}
	
	 
	 /**
	     * Notifies the observer to remove a check-in desk of the specified booking class.
	     *
	     * @param bc The booking class of the check-in desk to remove.
	     */
	 void notifyObserverCheckInDeskRemove(BookingClass bc) {
		observer.remove(bc);
	}
	
	 /**
	     * ActionListener implementation for adding an economy check-in desk.
	     */
	public class AddEconomyCheckinDeskButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObseverCheckInObserverAdd(BookingClass.ECONOMY);
		}
		
	}
	
	/**
     * ActionListener implementation for removing an economy check-in desk.
     */
	public class ReomveEconomyCheckinDeskButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObserverCheckInDeskRemove(BookingClass.ECONOMY);
		}
		
	}
	
	/**
     * ActionListener implementation for adding a business check-in desk.
     */
	public class AddBusinessCheckinDeskButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObseverCheckInObserverAdd(BookingClass.BUISINESS);
		}
		
	}
	
	/**
     * ActionListener implementation for removing a business check-in desk.
     */
	public class RemoveBusinessCheckinDeskButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObserverCheckInDeskRemove(BookingClass.BUISINESS);
		}
		
	}
	
	/**
     * KeyListener implementation for handling changes in the economy check-in desk duration text field.
     */
	public class EconomyTextChangedListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField textField = (JTextField) e.getSource();
			try {
				int value = Integer.valueOf(textField.getText());
				SimulationControls.getInstance().setEconomyCheckInDeskTime(value);
			}catch(NumberFormatException exception) {
			}
			
		}
		
	}
	
	/**
     * KeyListener implementation for handling changes in the business check-in desk duration text field.
     */
	public class BusinessTextChangedListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField textField = (JTextField) e.getSource();
			try {
				int value = Integer.valueOf(textField.getText());
				SimulationControls.getInstance().setBusinessCheckInDeskTime(value);
			}catch(NumberFormatException exception) {
			}
		}
		
	}
	
	 /**
     * KeyListener implementation for handling changes in the weight ratio text field for fee calculation.
     */
	public class WeightRatioTextChangedListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField textField = (JTextField) e.getSource();
			try {
				int value = Integer.valueOf(textField.getText());
				SimulationControls.getInstance().setVolumeFeeRatio(value);
			}catch(NumberFormatException exception) {
			}
			
		}
		
	}
	
	/**
     * KeyListener implementation for handling changes in the volume ratio text field for fee calculation.
     */
	public class VolumeRatioTextChangedListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField textField = (JTextField) e.getSource();
			try {
				int value = Integer.valueOf(textField.getText());
				SimulationControls.getInstance().setVolumeFeeRatio(value);
			}catch(NumberFormatException exception) {
			}
			
		}
		
	}
	
}
