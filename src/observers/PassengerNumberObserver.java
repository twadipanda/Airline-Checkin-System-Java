package observers;

/**
 * The PassengerNumberObserver interface represents an observer interested in changes to the number of passengers.
 * Classes implementing this interface can be notified when the number of passengers changes.
 */
public interface PassengerNumberObserver {
	 /**
     * Updates the observer with the new number of passengers.
     * 
     * @param n The new number of passengers.
     */
	void update(int n);
}
