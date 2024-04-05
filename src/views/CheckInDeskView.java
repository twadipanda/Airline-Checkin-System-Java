package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants_and_singleton_classes.FeeController;
import models.CheckInDeskModel;
import models.Passenger;
import observers.PassengerObserver;

/**
 * Custom JPanel component representing a check-in desk view.
 */
public class CheckInDeskView extends JPanel implements PassengerObserver {
	CheckInDeskModel model;
	JLabel deskname;
	JLabel passengerDetails;
	Passenger passenger;

	public CheckInDeskView(CheckInDeskModel model) {
		model.addPassengerObserver(this);
		init();
		this.deskname.setText("<html><b style=\"text-align:center;\">"+model.getDeskName()+"</b></html>");
		this.model = model;
	}
	
	private void init() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(new Color(255, 255, 255));
		setPreferredSize(getParent() == null? new Dimension(200,100) :new Dimension(200,getParent().getHeight()));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {20};
		gridBagLayout.rowHeights = new int[] {14};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		setLayout(gridBagLayout);
		
		deskname = new JLabel("");
		deskname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.weighty = 0.0;
		gbc_lblNewLabel_1.weightx = 1.0;
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(1, 1, 1, 1);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		add(deskname, gbc_lblNewLabel_1);
		
		passengerDetails = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weighty = 1.0;
		gbc_lblNewLabel.weightx = 0.0;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(1, 1, 1, 1);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(passengerDetails, gbc_lblNewLabel);
		
	}

	@Override
	public void addPassenger(Passenger passenger) {
		
	}

	@Override
	public void removePassenger(Passenger passenger) {
		
	}

	@Override
	public void updatePassenger(Passenger passenger) {
		if(passenger != null && model.getPassengerFlight() != null) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>")
		.append(passenger.getFirstName()+" ")
		.append(passenger.getLastName())
		.append(" is dropping off 1 bag of ")
		.append(passenger.getTotalBaggageWeight()+"kg.")
		.append("A bagggage fee of ")
		.append("£")
		.append(FeeController.calculateFee(passenger.getTotalBaggageWeight(), passenger.getTotalBaggageVolume(), 
				model.getPassengerFlight()))
		.append(" is due.")
		.append("</html>");
		this.passengerDetails.setText(sb.toString());
		}
		else {
			this.passengerDetails.setText("No more passengers left to serve...");
		}
	}

}
