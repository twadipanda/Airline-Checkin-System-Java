package app;
/**
 * The QueueInterface interface represents a queue of passengers.
 * Implementing classes are responsible for adding passengers to the queue.
 */
public interface QueueInterface {
	/**
	   * Adds passengers to the queue.
	   * 
	   * @throws InterruptedException if the thread is interrupted while waiting
	   *                               for the queue operation to complete
	   */
  public void addPassengers() throws InterruptedException;
}
