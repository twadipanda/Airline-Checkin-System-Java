package views;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Customized JSlider component for controlling simulation speed.
 */
public class SliderView extends JSlider {
	public SliderView(){
		setValue(2);
		setSnapToTicks(true);
		setPaintTicks(true);
		setMajorTickSpacing(1);
		setMaximum(5);
	}
	
	public void setListener(ChangeListener listener) {
		addChangeListener(listener);
	}
}
