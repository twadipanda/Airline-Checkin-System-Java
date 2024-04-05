package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import constants_and_singleton_classes.SimulationControls;

/**
 * This class contains JUnit test cases for the Simulation Control related calculation functionality.
 */
public class TestSimulationControls {
	private SimulationControls controls;

    @Before
    public void setUp() {
        controls = SimulationControls.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        SimulationControls anotherControls = SimulationControls.getInstance();
        assertNotNull(controls);
        assertEquals(controls, anotherControls);
    }

    @Test
    public void testSpeedAdjustment() {
        controls.setSpeed(2);
        assertEquals(2, controls.getSpeed());
    }


    @Test
    public void testCheckInDeskNumber() {
        assertEquals(3, controls.getEconomyCheckInDeskNumber());
        assertEquals(2, controls.getBusinessCheckInDeskNumber());
    }

    @Test
    public void testAdjustSpeed() {
        int baseSpeed = 100;
        controls.setSpeed(2);
        int adjustedSpeed = controls.adjustSpeed(baseSpeed);
        assertEquals(66, adjustedSpeed);
    }

    @Test
    public void testFeeRatio() {
        controls.setWeightFeeRatio(0.07f);
        controls.setVolumeFeeRatio(0.09f);
        assertEquals(0.07f, controls.getWeightFeeRatio(), 0);
        assertEquals(0.09f, controls.getVolumeFeeRatio(), 0);
    }
}
