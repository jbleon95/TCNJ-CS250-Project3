/**
 * Assignment: Project 3 - Simulator.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Jeremy Leon and Julie Swift
 */

import java.util.*;
import java.text.DecimalFormat;
import java.io.*;
import jsjf.*;
import jsjf.exceptions.*;

/**
 * The Simulator class provides a simulate method which simulates the flow of traffic in a three way intersection, with six lanes total.
 * The flow of traffic is than written into an output file.
 * Queues made using linked lists are used to simulate the traffic lanes, and other methods are present to help simulate the traffic.
 * @author 	Jeremy Leon
 * @author 	Julie Swift
 */
public class Simulator 
{
	/**
	 * These six linked queues represent the six different lanes of traffic in the intersection.
	 * Each lane has a car going in a different direction, which is represented in the name.
	 */
	private LinkedQueue<Vehicle> churchRight = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> churchLeft = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> mainEastStraight = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> mainEastTurn = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> mainWestStraight = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> mainWestTurn = new LinkedQueue<Vehicle>();

	/**
	 * The number of vehicles created, also used to assign vehicle number.
	 */
	private int vehicleCount = 0;

	/**
	 * The timer of the program, which tells how much time has passed. Also used as the clock.
	 */	
	private int timer = 0;

	/**
	 * Allows the writing to an output file.
	 */
	private PrintWriter writer;

	/**
	 * Decimal formatter to format output to at least two digits.
	 */
	private DecimalFormat fmt = new DecimalFormat("00");

	/**
	 * Allows the generation of random numbers.
	 */
	private Random rand = new Random();

	/**
	 * Creates a Simulator object and initializes the printwriter object
	 * @throws 	IOException 	An exception that may be thrown if there is a problem finding or opening the "output.txt" file.
	 */
	public Simulator() throws IOException
	{
		writer = new PrintWriter(new File("output.txt"));
	}

	/**
	 * Simulates the traffic at the intersection and writes it to an output file. Method begins with initializing all needed variables.
	 * Simulation than initially populates the interesection. Then until the simulation is empty (after 120 cars have), the simulator dequeues
	 * the different queues based on which light it is processing. Every time a car is dequeued, a message is printed using the printmessage method.
	 * When the simulator is over, the writer closes and the output file is ready to be read.
	 */
	public void simulate()
	{
		writer.println("---Start of simulation, time set to 0.");
		/**
	 	 * Sets the number of cycles the light will go for. Church has 2 cycles, main has three.
	     */
		int cycle = 2;

		/**
	 	 * Creates a random number between 7 and 12 inclusive and populates the intersections with that many vehicles.
	     */
		int randomCar = rand.nextInt(6) + 7;
		populate(randomCar);

		/**
	 	 * Simulator will run until all vehicles have been processed and the queues are all empty.
	     */		
		while (allEmpty() == false)
		{
			/**
	 	 	 * Processes the southbound traffic. Adds 3 seconds to the clock and tries to dequeue the queues.
	 	 	 * If not empty, than it is dequeued, departure time is set, and a message is printed to the output.
	 	 	 * Runs for two cycles (6 seconds).
	    	 */			
			writer.println("---Light changed. Now processing southbound traffic---");
			cycle = 2;
			for (int i = 0; i < cycle; i++)
			{
				timer += 3;
				if (churchRight.isEmpty() == false)
				{
					Vehicle car = churchRight.dequeue();
					car.setDepartureTime(timer);
					printMessage(car, 1);
				}
				if (churchLeft.isEmpty() == false)
				{
					Vehicle car = churchLeft.dequeue();
					car.setDepartureTime(timer);
					printMessage(car, 2);
				}
			}
			/**
	 	 	 * Creates a random number between 8 and 15 inclusive and populates the intersections with that many vehicles.
	    	 */			
			randomCar = rand.nextInt(8) + 8;
			populate(randomCar);

			/**
	 	 	 * Processes the east/westbound traffic. First makes sure all the queues are still empty, as the southbound queues
	 	 	 * may have been the only ones not empty. Adds 3 seconds to the clock and tries to dequeue the queues.
	 	 	 * If not empty, than it is dequeued, departure time is set, and a message is printed to the output.
	 	 	 * Runs for three cycles (9 seconds).
	    	 */				
			if (allEmpty()==false)
			{
				writer.print("\n");
				writer.println("---Light changed. Now processing east/west-bound traffic---");
				cycle = 3;
				for (int i = 0; i < cycle; i++)
				{
					timer += 3;
					if (mainEastStraight.isEmpty() == false)
					{
						Vehicle car = mainEastStraight.dequeue();
						car.setDepartureTime(timer);
						printMessage(car, 3);
					}
					if (mainEastTurn.isEmpty() == false)
					{
						Vehicle car = mainEastTurn.dequeue();
						car.setDepartureTime(timer);
						printMessage(car, 4);
					}
					if (mainWestStraight.isEmpty() == false)
					{
						Vehicle car = mainWestStraight.dequeue();
						car.setDepartureTime(timer);
						printMessage(car, 5);
					}
					if (mainWestTurn.isEmpty() == false)
					{
						Vehicle car = mainWestTurn.dequeue();
						car.setDepartureTime(timer);
						printMessage(car, 6);
					}
				}
				/**
	 	 		 * Creates a random number between 3 and 15 inclusive and populates the intersections with that many vehicles.
	    		 */						
				randomCar = rand.nextInt(13) + 3;
				populate(randomCar);	
				writer.print("\n");
			}
		}
		writer.close();
	}

	/**
	 * Uses all the LinkedQueue isEmpty methods to determine if every queue is empty. If they all are, it returns true.
	 */
	public boolean allEmpty()
	{
		boolean result = false;
		if (churchRight.isEmpty() == true && churchLeft.isEmpty() == true 
			&& mainEastTurn.isEmpty() == true && mainEastStraight.isEmpty() == true 
			&& mainWestTurn.isEmpty() == true && mainWestStraight.isEmpty() == true)
		{
			result = true;
		}
		return result;
	}

	/**
	* Populates the intersection with a specific number of vehicles. Creates a vehicle object, which will be randomly
	* in one of three streets, and than randomly places it in one of the two lanes it can be in. Can only create up to
	* 120 vehicles, after which this method will do nothing.
	* @param numVehicles the number of vehicles to be created
	*/
	private void populate(int numVehicles)
	{
		/**
		 * For loop used to create as many vehicles as entered.
		 */
		for(int i = 0; i < numVehicles; i++)
		{
			/**
			 * Makes sure that 120 vehicles have not been created
			 */
			if (vehicleCount != 120)
			{
				/**
			 	 * Increases the vehicleCount and than creates a vehicle entering vehicleCount as the vehicleNumber
			 	 * and the current time as the arrival time.
				 */
				vehicleCount++;
				Vehicle car = new Vehicle(vehicleCount, timer);
				
				/**
			 	 * Gets string representations of the enumerated types that make up Street and direction
				 */
				String street = car.getStreet();
				String direction = car.getDirection();
				
				/**
			 	 * Creates a random number of 0 or 1 to deterime which lane the vehicle will go in.
				 */
				int randomLane = rand.nextInt(2);

				/**
			 	 * Determines which Street and direction the vehicle was created in, and than randomly places it in one
			 	 * of the two lanes it can be in.
				 */
				if (street.equals("Church"))
				{
					if (randomLane == 0)
						churchLeft.enqueue(car);
					if (randomLane == 1)
						churchRight.enqueue(car);
				}
				if (street.equals("Main") && direction.equals("E"))
				{
					if (randomLane == 0)
						mainEastTurn.enqueue(car);
					if (randomLane == 1)
						mainEastStraight.enqueue(car);
				}
				if (street.equals("Main") && direction.equals("W"))
				{
					if (randomLane == 0)
						mainWestTurn.enqueue(car);
					if (randomLane == 1)
						 mainWestStraight.enqueue(car);
				}
			}
		}	
	}

	/**
	* Prints a message to the output file when a car moves past a light. Uses a vehicle object to get the vehicle number
	* and the wait time (departureTime - arrivalTime), and a lane number to determine which message will be outputted.
	* @param auto the specific vehicle object that is being written about
	* @param lane which lane the vehicle is moving from, determines which message to print
	*/	
	private void printMessage(Vehicle auto, int lane)
	{
		String message = "";
		/**
		* Lane refers to what queue the vehicle is in
		* 1 = Church, turning right
		* 2 = Church, turning left
		* 3 = Main E, going straight
		* 4 = Main E, turning left
		* 5 = Main W, going straight
		* 6 = Main W, turning right
		* Switch statement chooses appropriate message based on queue
		*/
		switch(lane)
		{
			case 1: message = " (southbound) turned right and headed westbound. ";
					break;
			case 2: message = " (southbound) turned left and headed eastbound. ";
					break;	
			case 3: message = " (eastbound) continued straight. ";
					break;
			case 4: message = " (eastbound) turned left and headed northbound. ";
					break;
			case 5: message = " (westbound) continued straight. ";
					break;
			case 6: message = " (westbound) turned right and headed northbound. ";
					break;
		}
		int wait = auto.getDepartureTime() - auto.getArrivalTime();
		writer.println("[Time " + fmt.format(timer) + "] Vehicle #" + auto.getVehicleNumber() + message
						+ "Total wait time " + fmt.format(wait) + " seconds.");
	}
}


