import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Assignment._SensorDisp;
import Ice.Current;

/**
 * Sensor class is a publisher that sends data to the Home Manager
 * @author Andrew
 *
 */
public class Sensor extends Ice.Application {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private String dataFile;
	private String typeOfSensor;
	
	public Sensor(String sensor, String file) {
		typeOfSensor = sensor;
		dataFile = file;
	}
	
	public static void main(String[] args) {
		// Check if correct number of arguments are given
		if (args.length != 2) {
			System.err.println("Incorrect number of arguments provided."
					+ " Must be 2 arguments.");
			System.exit(1);
		}
		// Create the sensor from the input parameters
		Sensor sensor = new Sensor(args[0], args[1]);
	}

	public void readFile(String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int run(String[] args) {
		// TODO Auto-generated method stub
		return 0;
	}
}
