package views;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Customized JTable component for displaying passenger queue.
 */
public class PassengersQueueView extends JTable {

	public PassengersQueueView() {
		// TODO Auto-generated constructor stub
		setMinimumSize(new Dimension(300, 100));
		setPreferredSize(new Dimension(300, 100));
	}
}
