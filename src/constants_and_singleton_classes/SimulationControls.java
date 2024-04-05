package constants_and_singleton_classes;

import java.util.concurrent.TimeUnit;

/**
 * The SimulationControls class manages various controls and settings for the simulation.
 * It is implemented as a singleton to ensure only one instance exists throughout the application.
 */
public class SimulationControls {
    private static SimulationControls instance;
    private int speed;
    
    private int ECONOMY_CHECK_IN_DESKS = 3;
    private int BUSINESS_CHECK_IN_DESKS = 2;
    private volatile long ECONOMY_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(10L, TimeUnit.SECONDS);
    private volatile long BUSINESS_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15L, TimeUnit.SECONDS);
    private float WEIGHT_FEE_RATIO = 0.05f;
    private float VOLUME_FEE_RATIO = 0.08f;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private SimulationControls() {
        speed = 3;
    }

    /**
     * Returns the instance of the SimulationControls class, creating it if it doesn't exist.
     *
     * @return The SimulationControls instance.
     */
    public synchronized static SimulationControls getInstance() {
    		if (instance == null) {
                instance = new SimulationControls();
            }
            return instance;
    }

    /**
     * Returns the current speed of the simulation.
     *
     * @return The speed of the simulation.
     */
    public synchronized int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the simulation.
     *
     * @param speed The speed to set for the simulation.
     */
    public synchronized void setSpeed(int speed) {
        this.speed = speed;
    }
    
    // Methods for getting and setting check-in desk time and number
     
    public synchronized long getEconomyCheckInDeskTime() {
    	return this.ECONOMY_CHECK_IN_DESKS_END_TIME;
    }
    
    public synchronized long getBusinessCheckInDeskTime() {
    	return this.BUSINESS_CHECK_IN_DESKS_END_TIME;
    }
    
    public synchronized int getEconomyCheckInDeskNumber() {
    	return this.ECONOMY_CHECK_IN_DESKS;
    }
    
    public synchronized int getBusinessCheckInDeskNumber() {
    	return this.BUSINESS_CHECK_IN_DESKS;
    }
    
    public synchronized void setEconomyCheckInDeskTime(long ECONOMY_CHECK_IN_DESKS_END_TIME) {
    	this.ECONOMY_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(ECONOMY_CHECK_IN_DESKS_END_TIME, TimeUnit.SECONDS);
    }
    
    public synchronized void setBusinessCheckInDeskTime(long BUSINESS_CHECK_IN_DESKS_END_TIME) {
    	this.BUSINESS_CHECK_IN_DESKS_END_TIME = System.nanoTime() + TimeUnit.NANOSECONDS.convert(BUSINESS_CHECK_IN_DESKS_END_TIME, TimeUnit.SECONDS);
    }
    
    public synchronized void setEconomyCheckInDeskNumber(int ECONOMY_CHECK_IN_DESKS) {
    	this.ECONOMY_CHECK_IN_DESKS = ECONOMY_CHECK_IN_DESKS;
    }
    
    public synchronized void setBusinessCheckInDeskNumber(int BUSINESS_CHECK_IN_DESKS) {
    	this.BUSINESS_CHECK_IN_DESKS = BUSINESS_CHECK_IN_DESKS;
    }
    
    // Methods for adjusting speed and setting fee ratios
    
    // Other methods for getting and setting simulation controls
    
    public synchronized int adjustSpeed(int baseSpeed) {
        double factor = (double) getSpeed() / 3;
        return (int) (baseSpeed * factor);
    }
    
    public synchronized void setWeightFeeRatio(float weightFeeRatio) {
    	this.WEIGHT_FEE_RATIO = weightFeeRatio;
    }
    
    public synchronized float getWeightFeeRatio() {
    	return this.WEIGHT_FEE_RATIO;
    }
    
    public synchronized void setVolumeFeeRatio(float volumeFeeRatio) {
    	this.VOLUME_FEE_RATIO = volumeFeeRatio;
    }
    
    public synchronized float getVolumeFeeRatio() {
    	return this.VOLUME_FEE_RATIO;
    }
}

