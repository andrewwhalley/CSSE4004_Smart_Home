import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EMM extends Ice.Application {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private String fileName;
	private String fileLine;
	// New line character
	private final String NL = System.getProperty("line.separator");
	// Map<disc name, list of other info e.g filename:track1.mp3 as key,value>
	private Map<String, ArrayList<Map<String, String>>> mediaFiles;
	
	public EMM(String fileName) {
		mediaFiles = new HashMap<String, ArrayList<Map<String, String>>>();
		this.fileName = fileName;
		// Attempt to set up file reading
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		// attempt to read the file
		try {
			readFileIntoMap();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Check there's exactly 1 argument
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments. Must be "
					+ "exactly 1.");
			System.exit(1);
		}
		EMM emm = new EMM(args[0]);

	}
	
	private void readFileIntoMap() throws IOException {
		// Read one line at a time
		String tempTrackName;
		String tempTitle;
		String tempDisc = "";
		String newTempDisc = "";
		String tempTrack;
		ArrayList<Map<String, String>> tempList = new ArrayList<Map<String, String>>();
		HashMap<String, String> tempMap = new HashMap<String, String>();
		while ((fileLine = bufferedReader.readLine()) != null) {
			if (fileLine.isEmpty()) {
				continue;
			}
			newTempDisc = tempDisc.toString();
			tempMap.clear();
			tempTrackName = fileLine.split(":")[1].trim();
			fileLine = bufferedReader.readLine();
			tempTitle = fileLine.split(":")[1].trim();
			fileLine = bufferedReader.readLine();
			if (!tempDisc.equals("") && !tempDisc.equals(fileLine.split(":")[1].trim())) {
				newTempDisc = fileLine.split(":")[1].trim();
			}
			tempDisc = fileLine.split(":")[1].trim();
			fileLine = bufferedReader.readLine();
			tempTrack = fileLine.split(":")[1].trim();
			// Check if we're now up to a new disc
			if (!newTempDisc.equals(tempDisc) && !newTempDisc.equals("")) {
				mediaFiles.put(tempDisc, tempList);
				tempList.clear();
				tempDisc = newTempDisc.toString();
			}
			tempMap.put("filename", tempTrackName);
			tempMap.put("title", tempTitle);
			tempMap.put("track", tempTrack);
			tempList.add(tempMap);
		}
	}
	
	/**
	 * get title of track from the file name
	 * @param fileName
	 * @return title of disc or empty
	 */
	public String getTitle(String fileName) {
		// search through each disc for the correct file name
		for (String key : mediaFiles.keySet()) {
			for (Map<String, String> info : mediaFiles.get(key)) {
				if (info.get("filename").equals(fileName)) {
					// Return the title relating to that file name
					return info.get("title");
				}
			}
		}
		// If no corresponding title is found, return an empty string
		return "";
	}
	
	public String getDisc(String fileName) {
		// search through each disc for the correct file name
		for (String key : mediaFiles.keySet()) {
			for (Map<String, String> info : mediaFiles.get(key)) {
				if (info.get("filename").equals(fileName)) {
					// key is the name of the disc
					return key;
				}
			}
		}
		// If no corresponding title is found, return an empty string
		return "";
	}
	
	/**
	 * Prints a list of all the filenames corresponding to a disc name 
	 * @param discName
	 */
	public String getTracks(String discName) {
		String trackList = "";
		TreeMap<Integer, String> sortingMap = new TreeMap<Integer, String>();
		// place the track number and filename in a treemap
		for (Map<String, String> values : mediaFiles.get(discName)) {
			// convert track from String to Integer
			sortingMap.put(Integer.parseInt(values.get("track")), values.get("filename"));
		}
		// the filenames will be sorted ascending automatically by track (key)
		for (String trackName : sortingMap.values()) {
			// form the output
			trackList += trackName + NL;
		}
		// trackList will be empty if the disc isn't found
		return trackList;
	}
	
	/**
	 * return a list of all file names ordered alphabetically
	 * @return String with each file name on a line in alphabetical order
	 */
	public String getFiles() {
		ArrayList<String> sortingList = new ArrayList<String>();
		// extract each file name from the map
		for (String key : mediaFiles.keySet()) {
			for (Map<String, String> info : mediaFiles.get(key)) {
				sortingList.add(info.get("filename"));
			}
		}
		// sort the list to get in alphabetical order
		Collections.sort(sortingList);
		String output = "";
		// Go through the sorted list and add to string in correct format
		for (String fileName : sortingList) {
			output += fileName + NL;
		}
		return output;
	}

	@Override
	public int run(String[] args) {
		// TODO Auto-generated method stub
		return 0;
	}

}
