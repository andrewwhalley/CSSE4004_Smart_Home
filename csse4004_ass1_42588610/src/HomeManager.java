import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Server.HelloI;

public class HomeManager extends Ice.Application {

	// New line character
	private final String NL = System.getProperty("line.separator");
	private int currentTemp, currentEnergy, previousTemp, previousEnergy;
	private String tempLog;
	// Threshold values
	private final int ENERGY_THRESHOLD = 4000, AWAY_MIN = 15, AWAY_MAX = 28, 
			HOME_TEMP = 22;
	private ArrayList<String> usersAtHome;
		
	
	public HomeManager() {
		tempLog = "";
		usersAtHome = new ArrayList<String>();
		
	}
	
	private void manageTemperature() {
		
	}
	
	/**
	 * Set the current temperature to what is provided by the sensors
	 * @param amount
	 */
	public void setTemp(String amount) {
		currentTemp = Integer.parseInt(amount);
	}
	
	/**
	 * Set the current Energy to what is provided by the sensors
	 * @param amount
	 */
	public void setEnergy(String amount) {
		currentEnergy = Integer.parseInt(amount);
		previousEnergy = currentEnergy;
	}
	
	/**
	 * Method to modify who is at home or away
	 * @param users - array of the systems users (max 2)
	 * @param value - Values representing if they're home or away
	 */
	public void setUsersAtHome(String[] users, String[] value) {
		// If no users detected, empty the array and return
		if (users.length == 0) {
			usersAtHome.clear();
			return;
		}
		// for each of the inputs check if it's home/away
		for (int i = 0; i < users.length; i++) {
			if (value[i].equals("home") && !usersAtHome.contains(users[i])) {
				usersAtHome.add(users[i]);
			}
			if (value[i].equals("away") && usersAtHome.contains(users[i])) {
				usersAtHome.remove(users[i]);
			}
		}
	}
	
	private void monitorEnergy() {
		// Check to see if energy usage is above threshold without having 
		// changed
		if (currentEnergy > ENERGY_THRESHOLD && previousEnergy != 
				currentEnergy) {
			// send warning to ui
			
			// Update previous energy
			previousEnergy = currentEnergy;
		}
	}
	
	public String getTrackTitlesFromEMM(String discName) {
		return "";
	}
	
	public String getFilesFromEMM() {
		return "";
	}
	
	private void adjustTemperature(int triggerTemp) {
		tempLog += NL + "Air-conditioning adjusted." + NL +
				"Temperature: at " + triggerTemp + " degrees" + 
				"At Home:";
		// Message above is fine for no users at home. Below accounts for
		// if they are at home
		if (usersAtHome.size() == 1) {
			tempLog += usersAtHome.get(0);
		} else if (usersAtHome.size() == 2) {
			tempLog += usersAtHome.get(0) + " and " + usersAtHome.get(1);
		}
	}
	
	public String getTempLog() {
		return tempLog;
	}
	
	public void shutdown() {
		// Stop everything
		communicator().destroy();
	}
	
	public static void main(String[] args) {
		if (args.length != 0) {
			System.out.println("Must be no arguments");
			System.exit(1);
		}
		HomeManager hm = new HomeManager();
		int status = hm.main("HomeManager", args);
		System.exit(status);

	}

	@Override
	public int run(String[] args) {
		// Create server to communicate with UI and EMM
		Ice.ObjectAdapter adapter = communicator().createObjectAdapterWithEndpoints(
				"Hello", "tcp -h 127.0.0.1 -p 10000");
		
		//adapter.add(new HelloI(), communicator().stringToIdentity("hello"));
		adapter.activate();
		
		// Need to work as a subscriber to the sensors
		
		communicator().waitForShutdown();
		return 0;
	}
}
