/**
 * Class PriorityQueue
 * 
 * Class file for a Priority Queue of Customer objects. This class provides functionality for
 * adding customers to the queue, sorting customers in line based on priority, removing customers, 
 * returning the customer at the front of the queue, and it keeps track of the current length of 
 * the queue, total customers removed from the queue, and the maximum size of the queue.
 * 
 * @author Jordan Harris
 * @date November 17th, 2014
 */

public class PriorityQueue {

	private PriorityCustomer[] customerPriorityLine;  // An array of Priority Customers (the line)
	private static int lineSize = 0;				  // Current size of the line
	private static int totalCustomersServiced = 0;    // Total customers serviced
	private static int maxLineLength = 0;			  // Maximum length of the line
	
	// Priority Queue Constructor
	public PriorityQueue(int size) {
		customerPriorityLine = new PriorityCustomer[size];
	}
	
	// Returns the current size of the line (number of customers in line)
	public int getLineSize() {
		return lineSize;
	}
	
	// Returns the customer currently being serviced (in the very front of the line)
	public PriorityCustomer getFirst() {
		return customerPriorityLine[1];
	}
	
	// Adds a customer to the line
	public void addCustomer(PriorityCustomer customer) {
		
		// Increases the size of the line and assigns this value to the position where
		// the customer will be inserted
		lineSize++;
		int customerPosition = lineSize;
		
		// Assigns the customer to the last position in line
		customerPriorityLine[customerPosition] = customer;
		
		// Sorts all of the customers waiting in line (not in front)
		while (customerPosition > 3) {
			
			// Assigns the current customer position and that customer's parent position
			int currentPosition = customerPosition;
			int parentPosition = customerPosition / 2;
			
			// Assigns the current customer's priority and their parent's priority
			int currentPriority = customerPriorityLine[currentPosition].getPriority();
			int parentPriority = customerPriorityLine[parentPosition].getPriority();
			
			// Checks to see if the parent's priority is less than the current customer's priority
			if(parentPriority < currentPriority) {
				
				// Creates a temporary customer to hold the parent customer
				PriorityCustomer tempParent = customerPriorityLine[parentPosition];
				
				// Assigns the current customer to the parent's position in line
				customerPriorityLine[parentPosition] = customerPriorityLine[currentPosition];
				
				// Assigns the parent to the customer's old position in line
				customerPriorityLine[currentPosition] = tempParent;
				
				// Assigns the customer position pointer to the parent's old position in line
				customerPosition = parentPosition;
			} else {
				break;
			}
		}
		
		// Checks to see if the current line has exceeded the maximum length of the line,
		// and if it has, assigns the line length to the maximum line length
		if (lineSize > maxLineLength) {
			maxLineLength = lineSize;
		}
		
		System.out.println("New customer added! Queue length is now " + lineSize);
	}
	
	// Removes the first customer line line (the one being serviced)
	public void removeCustomer() {
		
		// Assigns the end of the line to the current customer position
		int customerPosition = lineSize;
		
		// Assigns the person at the end of the line to the front of the line and
		// then sets their old position to null (first person is thereby removed)
		customerPriorityLine[1] = customerPriorityLine[customerPosition];
		customerPriorityLine[customerPosition] = null;
		
		// Decreases the current size of the line
		lineSize--;
		
		// Assigns the current customer position to the new customer at the front of the line
		customerPosition = 1;
		
		// Sets that customer's left and right children in line
		int leftChildPosition = customerPosition * 2;
		int rightChildPosition = (customerPosition * 2) + 1;
		
		// Loops through the line so long as the current customer has a left child
		while(leftChildPosition <= lineSize) {

			// Assigns the current customer's priority and their left child's priority
			int currentCustomerPriority = customerPriorityLine[customerPosition].getPriority();
			int leftChildPriority = customerPriorityLine[leftChildPosition].getPriority();
			
			// Temporarily assigns the customer's right child priority to -1 in case they
			// don't have a right child
			int rightChildPriority = -1;
			
			// If they have a right child, their priority is assigned
			if (rightChildPosition <= lineSize) {
				rightChildPriority = customerPriorityLine[rightChildPosition].getPriority();
			}
			
			// Checks to see if either of the children has a greater priority than the current customer
			if(currentCustomerPriority < Math.max(leftChildPriority, rightChildPriority)) {
				
				// Checks if the left child has a greater priority than the right child
				if(leftChildPriority >= rightChildPriority) {
					
					// Creates a temporary customer to hold the left child customer
					PriorityCustomer tempLeftChild = customerPriorityLine[leftChildPosition];
					
					// Assigns the current customer to the left child's position in line
					customerPriorityLine[leftChildPosition] = customerPriorityLine[customerPosition];
					
					// Assigns the left child to the customer's old position in line
					customerPriorityLine[customerPosition] = tempLeftChild;
					
					// Assigns the customer position pointer to the left child's old position in line
					customerPosition = leftChildPosition;
				} else {
					
					// Creates a temporary customer to hold the right child customer
					PriorityCustomer tempRightChild = customerPriorityLine[rightChildPosition];
					
					// Assigns the current customer to the right child's position in line
					customerPriorityLine[rightChildPosition] = customerPriorityLine[customerPosition];
					
					// Assigns the right child to the customer's old position in line
					customerPriorityLine[customerPosition] = tempRightChild;
					
					// Assigns the customer position pointer to the right child's old position in line
					customerPosition = rightChildPosition;
				}
			} else {
				break;
			}
			
			// Updates the left and right children positions based on the new customer position pointer
			leftChildPosition = customerPosition * 2;
			rightChildPosition = (customerPosition * 2) + 1;
					
		}
		
		// Increases the total number of customers serviced
		totalCustomersServiced++;
		
		System.out.println("Customer serviced and removed from the queue. Queue length is now " + lineSize);
	}
	
	// Returns the total number of customers serviced
	public int getTotalCustomersServiced() {
		return totalCustomersServiced;
	}
		
	// Returns the maximum length of the line
	public int getMaxLineLength() {
		return maxLineLength;
	}

}
