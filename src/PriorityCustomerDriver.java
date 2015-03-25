/**
 * Class PriorityCustomerDriver
 * 
 * This is the driver for the Priority Customer program. The program simulates 60 minutes in a grocery store,
 * where every minute, there is a 25% chance a new customer enters the service line. Each customer
 * is randomly given a 1-5 minute wait-time and a 1-5 priority. Customers waiting in line behind the customer
 * being serviced are sort in line based on priority (higher priority is moved to the front of the line). 
 * When a customer is serviced,they are removed from the line. At the end of the program, the total number of
 * customers serviced and the maximum length of the line are provided.
 * 
 * @author Jordan Harris
 * @date November 17, 2014
 */

import java.util.Random;

public class PriorityCustomerDriver {

	public static void main(String[] args) {
		
		final int time = 60;  // Represents 60 minutes
		
		// Creates the line though a priority queue implimentation
		PriorityQueue customerLine = new PriorityQueue(60);
		
		// Iterates through 60 minutes in the store, 1 minute at a time
		for(int i = 0; i < time; i++) {
			
			// Creates a 25% chance for adding a new customer to the queue
			if(new Random().nextInt(4) == 0) {
				customerLine.addCustomer(new PriorityCustomer());
			}
			
			// Checks to see if the queue is not empty
			if(customerLine.getLineSize() > 0) {
				
				// Decreases the first customer's service time
				customerLine.getFirst().decServiceTime();
				
				// If the first customer's service time reaches 0, they are serviced and removed
				// from the queue
				if(customerLine.getFirst().getServiceTime() == 0) {
					customerLine.removeCustomer();
				}
			}
			
			// Simulates the passage of time (1 minute)
			System.out.println("---------------------------------------------------");
			
		}
		
		System.out.println("Total customers serviced: " + customerLine.getTotalCustomersServiced());
		System.out.println("Maximum line length: " + customerLine.getMaxLineLength());
	}

}
