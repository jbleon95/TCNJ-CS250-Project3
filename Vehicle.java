/**
 * Assignment: Project 3 - Vehicle.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Jeremy Leon and Julie Swift
 */

import java.util.Random;

/**
* The Vehicle class contains all the accessor and mutator methods that define
* what a vehicle object consists of. It contains a switch case that determines
* which random street a vehicle will be placed into. 
* @author 	Jeremy Leon
* @author 	Julie Swift
*/
public class Vehicle
{
	/**
	* The private integer value for the vehicle number, arrivale time, and departure time 
	* which is later defined in the class 
	*/
	private int vehicleNumber, arrivalTime, departureTime;
	
	/**
	* The enumerated type object street consists of Church and Main street
	*/
	enum street {Church, Main}
	
	/**
	* The enumerated type object direction consists of S (south), E (east), and W (west)
	*/
	enum direction {S, E, W}
	
	/**
	* The private street stores the enumerated street 
	*/
	private street street;
	
	/**
	* The private direction stores the enumerated direction
	*/	
	private direction direction;
	/**
	* Creating a random object rand from method in the util class 
	*/
	Random rand = new Random();

	/**
	* The constructor sets up the vehicle object using the specified data
	*
	* @param vehicleNumber The integer value of the number of the vehicle
	* @param arrivalTime The integer value of the arrivae time of the vehicle 
	*/

	public Vehicle(int vehicleNumber, int arrivalTime)
	{
		this.vehicleNumber = vehicleNumber;
		this.arrivalTime = arrivalTime;

		/**
		* Creating a random number between 0 and 2 in which is the random number
		* that is now stored in integer value randomStreet.
		*/
		int randomStreet = rand.nextInt(3);

		/**
		* The switch case now uses that random number between 0 and 2 to pick
		* a street and a direcion which is different for each of the 3 cases
		*/
		switch(randomStreet)
		{
			case 0: street = street.Church;
					direction = direction.S;
					break;
			case 1: street = street.Main;
					direction = direction.E;
					break;
			case 2: street = street.Main;
					direction = direction.W;
					break;
		}
	}

	/**
	* Sets the the elapsed time in seconds since the start of the simulation
	* that the vehicle exited the intersection using specified data
	*
	* @param departure time The integer of the departure time of the vehicle
	*/
	public void setDepartureTime(int departureTime)
	{
		this.departureTime = departureTime;
	}

	/**
	* Returns the vehicle value of the number of the vehicle object
	*
	* @return The integer of the vehicle number of the vehicle 
	*/
	public int getVehicleNumber()
	{
		return vehicleNumber;
	}

	/**
	* Returns the only two possible values here and they represent the street 
	* the vehicle is initially placed upon
	*
	* @return The string of the street of the vehicle
	*/
	public String getStreet()
	{
		return street.toString();
	}

	/**
	* Returns the only three possible values which represent the direction 
	* of movement upon the street where the vehicle is initially placed
	*
	* @return The string of the direction of the vehicle
	*/
	public String getDirection()
	{
		return direction.toString();
	}

	/**
	* Returns the elapsed time in seconds since the start of the simulation 
	* that the vehicle arrived at the intersection
	*
	* @return The integer of the arrival time of the vehicle
	*/
	public int getArrivalTime()
	{
		return arrivalTime;
	}

	/**
	* Returns the the elapsed time in seconds since the start of the simulation
	* that the vehicle exited the intersection.
	*
	* @return the departure time in an integer object 
	*/
	public int getDepartureTime()
	{
		return departureTime;
	}

	/**
	* Compiles the data of the Vehicle object into a string object and returns it
	*
	* @return vehicle number, street, direction and arrival time The string object
	* containing Vehicle data
	*/
	public String toString()
	{
		return ("Vehicle Number: " + vehicleNumber + " Travelling: " + street + " " + direction + " Arrived at " + arrivalTime);
	}
}