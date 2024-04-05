package views;

import javax.swing.JLabel;

import models.PassengerNumberModel;
import observers.PassengerNumberObserver;
import observers.PassengerObserver;

/**
 * Customized JLabel component for displaying the total number of passengers in the queue.
 */
public class PassengerNumberView extends JLabel implements PassengerNumberObserver {

	int numberInQueue = 0;
	
	public PassengerNumberView(PassengerNumberModel passengerNumberModel) {
		passengerNumberModel.attachObserver(this);
	}

	@Override
	public void update(int n) {
		this.numberInQueue +=n;
		setText("Total number in queue: "+this.numberInQueue);
	}

}
