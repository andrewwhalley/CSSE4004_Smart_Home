import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Assignment._SmartHomeInterfaceDisp;
import Ice.Current;


public class SmartHomeUI extends Ice.Application {
	
	public class SmartHomeInterfaceI extends _SmartHomeInterfaceDisp
	{

		// Global Variables to deal with user input
		private String userInput;
		private BufferedReader stdinReader;
		
		public SmartHomeInterfaceI() {
			// Create the input Reader
			stdinReader = new BufferedReader(new InputStreamReader(System.in));
			// Start by prompting the user for their name
			askUser();
			// Move to the main menu once the user has entered their username
			mainMenu();
		}
		
		/**
		 * Method that welcomes user 
		 */
		@Override
		public void askUser(Current __current) {
			System.out.println("Welcome to the Smart Home Monitoring System");
			System.out.println("Please enter your user name:");
			
			try {
				userInput = stdinReader.readLine().trim();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
		
		/**
		 * Method for the Main Menu
		 */
		public void mainMenu(Current __current) {
			// Display the options to the user
			System.out.println(); // Whitespace
			System.out.println("Welcome to the Smart Home Monitoring System");
			System.out.println("Please select an option:");
			System.out.println("1. View log - temperature adjustment");
			System.out.println("2. View media files");
			System.out.println("3. View disc tracks");
			System.out.println("E. Exit");
			System.out.println();
			
			try {
				userInput = stdinReader.readLine().trim();
				switch (userInput) {
				case "1":
					// Display the temperature log to the user
					viewLog();
					break;
				case "2":
					// Display the media files to the user
					viewMediaFiles();
					break;
				case "3":
					// Display the disc tracks to the user
					viewDiscFiles();
					break;
				case "E":
					// Exit the system
					shutdown();
					break;
				default:
					System.out.println("Invalid Command");
					System.out.println();
					break;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			// Display the main menu once the previous command has been executed
			mainMenu();
		}

		private void viewLog() {
			// Issue request to Home Manager
			String log = ""; // TODO replace with actual call
			// Default message for no log recorded yet
			if (log.equals("")) {
				System.out.println("Log of temperature adjustment is empty");
			} else {
				System.out.println(log);
			}
			System.out.println();
			
			
			// prompt user to press enter key to return to main menu
		}

		/**
		 * Method to view a list of all media files
		 */
		private void viewMediaFiles() {
			// Issue request to Home Manager 
			
			// In case no files are found:
			System.out.println("No media files were found");
			
		}

		/**
		 * Method to view a list of the files on a disc 
		 */
		private void viewDiscFiles() {
			// Prompt user for disc title
			System.out.println("Please enter the disc title:");
			try {
				userInput = stdinReader.readLine().trim();
				// send disc title to Home Manager
				
				// display list of returned track titles
				
				// if none:
				System.out.println();
				System.out.println("The disc " + userInput + " was not found "
						+ "in the media collection");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
				
		/**
		 * Method that is triggered when the energy consumption exceeds
		 * the electricity usage threshold
		 * @param amount
		 */
		private void highEnergyConsumption(int amount) {
			System.out.println();
			System.out.println("Energy Usage Warning: Current electricity "
					+ "consumption is " + amount + ".");
			System.out.println();
			System.out.println("Please consider the environment before "
					+ "switching on any electrical appliances.");
			System.out.println();
		}

		/**
		 * Method to initiate shutdown process
		 */
		private void shutdown() {
			communicator().destroy();
		}
		
		/**
		 * Method to listen for user pressing enter
		 */
		private void listenForEnter() {
			
		}
		
	}

	public SmartHomeUI() {
		
	}
	
	public static void main(String[] args) {
		SmartHomeUI shui = new SmartHomeUI();
		int status = shui.main("SmartHomeUI", args);
		System.exit(status);

	}

	@Override
	public int run(String[] args) {
		Ice.ObjectAdapter adapter = communicator().createObjectAdapterWithEndpoints(
				"SmartHomeInterface", "tcp -h 127.0.0.1 -p 10000");
		
		adapter.add(new SmartHomeInterfaceI(), communicator().stringToIdentity("ui"));
		adapter.activate();
		
		communicator().waitForShutdown();
		return 0;
	}
}
