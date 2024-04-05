package observers;

import constants_and_singleton_classes.Enums.BookingClass;

/**
 * The CheckInObserver interface represents an observer that listens for changes in the check-in process.
 * Implementations of this interface can observe the addition or removal of passengers in different booking classes.
 */
public interface CheckInObserver {
	
	/**
     * Notifies the observer that a passenger of the specified booking class has been added to the check-in process.
     * 
     * @param bc The booking class of the passenger added to check-in.
     */
	void add(BookingClass bc);
	
	/**
     * Notifies the observer that a passenger of the specified booking class has been removed from the check-in process.
     * 
     * @param bc The booking class of the passenger removed from check-in.
     */
	void remove(BookingClass bc);
}
