/**
 * Assignment: Project 3 - Driver.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Jeremy Leon and Julie Swift
 */

import java.io.*;

/**
 * Driver creates a simulator object and runs the simulate method, which runs the simulation.
 * @author 	Jeremy Leon
 * @author 	Julie Swift
 */
public class Driver
{	
	public static void main(String[] args) throws IOException
	{
		Simulator sim = new Simulator();
		sim.simulate();
	}
}