package models;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import app.CheckInController;
import app.PassengerQueueController;
import observers.PassengerObserver;

/**
 * The PassengersTableModel class represents a table model for displaying passenger information in a table.
 * It extends AbstractTableModel to provide the necessary functionality for a table model.
 */
public class PassengersTableModel extends AbstractTableModel implements PassengerObserver {
	
	/** The list of passengers to be displayed in the table. */
	List<Passenger> passengerList = new ArrayList();
	
	/** The controller responsible for managing check-ins. */
	CheckInController checkInController;
	
	 /**
     * Constructs a PassengersTableModel object with the specified PassengerQueueController.
     * 
     * @param ctrl The PassengerQueueController to observe for changes in the passenger queue.
     */
	public PassengersTableModel(PassengerQueueController ctrl) {
		ctrl.addPassengerObserver(this);
	}

	@Override
	public int getRowCount() {
		return passengerList.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
     * Returns the value at the specified row and column in the table.
     * 
     * @param rowIndex The row index of the cell.
     * @param columnIndex The column index of the cell.
     * @return The value at the specified cell.
     */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(passengerList.size() != 0 && rowIndex>=0) {
			switch(columnIndex) {
			case 0:
				return passengerList.get(rowIndex).getBookingReference();
			case 1:
				return passengerList.get(rowIndex).getFirstName()+" "+passengerList.get(rowIndex).getLastName();
			case 2:
				return passengerList.get(rowIndex).getTotalBaggageWeight()+"kg";
			case 3:
				return String.valueOf(passengerList.get(rowIndex).getTotalBaggageVolume());
			default:
				return passengerList.get(rowIndex).getBookingReference();
			}
		}
		return null;
	}

	/**
     * Adds a passenger to the table and updates the table data.
     * 
     * @param passenger The passenger to be added.
     */
	@Override
	public void addPassenger(Passenger passenger) {
		passengerList.add(passenger);
		fireTableDataChanged();
	}

	 /**
     * Removes a passenger from the table and updates the table data.
     * 
     * @param passenger The passenger to be removed.
     */
	@Override
	public void removePassenger(Passenger passenger) {
		if(passenger != null) {
			passengerList.remove(passenger);
			fireTableDataChanged();
		}
	}

	/**
     * Updates passenger information (not used in this model).
     * 
     * @param passenger The passenger to be updated.
     */
	@Override
	public void updatePassenger(Passenger passenger) {
		// Not used in this model
	}
	
}
