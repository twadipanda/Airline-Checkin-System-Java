package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Flight;
import models.FlightModel;
import observers.FlightObserver;

/**
 * Customized JPanel component for displaying flight information.
 */
public class FlightView extends JPanel implements FlightObserver {

	JLabel flightname;
	JLabel flightDetails;
	Flight flight;
	
	public FlightView(FlightModel model) {
		model.addFlightObserver(this);
		this.flight = model.getFlight();
		init();
	}
	
	void init() {
		setBackground(new Color(255, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {152};
		gridBagLayout.rowHeights = new int[] {14};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		
		flightname = new JLabel("");
		flightname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.ipady = 5;
		gbc_lblNewLabel_1.ipadx = 5;
		gbc_lblNewLabel_1.weighty = 1.0;
		gbc_lblNewLabel_1.weightx = 1.0;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		add(flightname, gbc_lblNewLabel_1);
		
		flightDetails = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipady = 5;
		gbc_lblNewLabel.ipadx = 5;
		gbc_lblNewLabel.weighty = 1.0;
		gbc_lblNewLabel.weightx = 1.0;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(flightDetails, gbc_lblNewLabel);
		
		this.flightDetails.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.flightname.setText("<html><b>"+flight.getCode()+" "+flight.getDestinationAirport()+"<b/></html>");
		int percentageFull = (int)(flight.getPassengers()/flight.getCapacity()) * 100;
		String details = String.format("%d checked in of %d \n Hold is %d%% full", flight.getPassengers(),
				(int) flight.getCapacity(), percentageFull);
		this.flightDetails.setText(details);
	}

	@Override
	public void update(Flight flight) {
		this.flightname.setText("<html><b>"+flight.getCode()+" "+flight.getDestinationAirport()+"<b/></html>");
		float percentageFull = (flight.getPassengers()/flight.getCapacity()) * 100;
		String details = String.format("%d checked in of %d \n Hold is %d%% full", flight.getPassengers(),
				(int) flight.getCapacity(), Math.round(percentageFull));
		this.flightDetails.setText(details);
		
	}


}
