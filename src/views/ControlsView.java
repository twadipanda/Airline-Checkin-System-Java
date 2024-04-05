package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.SliderController;

/**
 * Custom JPanel component for controlling simulation settings.
 */
public class ControlsView extends JPanel {
	public JTextField economycheckInDeskDurationTextField;
	public JTextField businesscheckInDeskDurationTextField;
	public JTextField feeCalculationWeightTextField;
	public JTextField feeCalculationVolumeTextField;
	public JButton btnAddEconomyDesk;
	public JButton btnRemoveEconomyDesk;
	public JButton btnAddBusinessDesk;
	public JButton btnRemoveBusinessDesk;

	public ControlsView() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel simulationSpeedControlsContainer = new JPanel();
		add(simulationSpeedControlsContainer);
		
		
		JPanel checkInDeskDurationPanel = new JPanel();
		add(checkInDeskDurationPanel);
		checkInDeskDurationPanel.setLayout(new GridLayout(1, 2));
		
		JPanel economycheckInDeskDurationPanel = new JPanel();
		checkInDeskDurationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkInDeskDurationPanel.add(economycheckInDeskDurationPanel);
		
		JPanel businesscheckInDeskDurationPanel = new JPanel();
		checkInDeskDurationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		checkInDeskDurationPanel.add(businesscheckInDeskDurationPanel);
		
		JPanel feeCalculationDetailsContainer = new JPanel();
		feeCalculationDetailsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		CustomInputVerifier inputVerifier = new CustomInputVerifier();
		
		JLabel lblcalculationWeightFee = new JLabel("Excees fee ratio for weight amount:");
		feeCalculationDetailsContainer.add(lblcalculationWeightFee);
		
		feeCalculationWeightTextField = new JTextField();
		feeCalculationDetailsContainer.add(feeCalculationWeightTextField);
		feeCalculationWeightTextField.setColumns(10);
		
		JLabel lblcalculationVolumnFee = new JLabel("Excees fee ratio for weight amount:");
		feeCalculationDetailsContainer.add(lblcalculationVolumnFee);
		
		feeCalculationVolumeTextField = new JTextField();
		feeCalculationDetailsContainer.add(feeCalculationVolumeTextField);
		feeCalculationVolumeTextField.setColumns(10);
		
		add(feeCalculationDetailsContainer);
		
		JLabel lblcheckInEconomyDeskDuration = new JLabel("Economy Check In Desk Duration:");
		economycheckInDeskDurationPanel.add(lblcheckInEconomyDeskDuration);
		
		JLabel lblcheckInBusinessDeskDuration = new JLabel("Business Check In Desk Duration:");
		businesscheckInDeskDurationPanel.add(lblcheckInBusinessDeskDuration);
		
		economycheckInDeskDurationTextField = new JTextField();
		economycheckInDeskDurationPanel.add(economycheckInDeskDurationTextField);
		economycheckInDeskDurationTextField.setColumns(10);
		
		economycheckInDeskDurationTextField.setInputVerifier(inputVerifier);
		
		businesscheckInDeskDurationTextField = new JTextField();
		businesscheckInDeskDurationPanel.add(businesscheckInDeskDurationTextField);
		businesscheckInDeskDurationTextField.setColumns(10);
		
		businesscheckInDeskDurationTextField.setInputVerifier(inputVerifier);
		
		JPanel checkInDeskButtonsPanel = new JPanel();
		add(checkInDeskButtonsPanel);
		checkInDeskButtonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAddEconomyDesk = new JButton("Add Economy CheckIn Desk");
		btnAddEconomyDesk.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkInDeskButtonsPanel.add(btnAddEconomyDesk);
		
		btnRemoveEconomyDesk = new JButton("Remove Economy CheckIn Desk");
		btnRemoveEconomyDesk.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkInDeskButtonsPanel.add(btnRemoveEconomyDesk);
		
		btnAddBusinessDesk = new JButton("Add Business CheckIn Desk");
		btnAddBusinessDesk.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkInDeskButtonsPanel.add(btnAddBusinessDesk);
		
		btnRemoveBusinessDesk = new JButton("Remove Business CheckIn Desk");
		btnRemoveBusinessDesk.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkInDeskButtonsPanel.add(btnRemoveBusinessDesk);
		
		JLabel displayLabel = new JLabel("Simulation Speed:");
		displayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		displayLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		simulationSpeedControlsContainer.add(displayLabel);
		
		SliderView speedSlider = new SliderView();
		new SliderController(speedSlider);
		displayLabel.setLabelFor(speedSlider);
		simulationSpeedControlsContainer.add(speedSlider);
		
		JLabel speedDisplay = new JLabel("Slider value increases speed");
		speedDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
		speedDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		simulationSpeedControlsContainer.add(speedDisplay);
	}
	
	private class CustomInputVerifier extends InputVerifier{

		@Override
		public boolean verify(JComponent input) {
			// TODO Auto-generated method stub
			JTextField textField = (JTextField) input;
			String text = textField.getText();
			
			boolean isValid = !text.isEmpty();
			try {
				Integer.parseInt(text);
				return true;
			}catch(NumberFormatException exception) {
				if(!text.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
					textField.setBackground(Color.PINK);
					return false;
				}
				return true;
			}		
		}
		
	}
}
