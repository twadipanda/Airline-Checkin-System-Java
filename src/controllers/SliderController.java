package controllers;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import constants_and_singleton_classes.SimulationControls;
import views.SliderView;

/**
 * The SliderController class manages the interaction between the SliderView and SimulationControls.
 * It listens for changes in the JSlider component and updates the speed of the simulation accordingly.
 */
public class SliderController {
	
	/**
     * Constructs a new SliderController with the specified SliderView.
     *
     * @param sliderview The SliderView instance to associate with this controller.
     */
	public SliderController(SliderView sliderview) {
		sliderview.setListener(new Listener());
	}
	
	 /**
     * Inner class implementing the ChangeListener interface to handle state changes in the JSlider.
     */
	private  class Listener implements ChangeListener {
		/**
         * Invoked when the state of the JSlider changes.
         *
         * @param e The ChangeEvent representing the state change.
         */
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider slider = (JSlider) e.getSource();
			int value = slider.getValue();
			SimulationControls.getInstance().setSpeed(value);
		}
	}

}
