
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * This class contains the main method.
 * It also opens and reads the data files, interacts with the user, performs data validation and handles
 * errors that occur. 
 * 
 * @author Samira Mantri
 * @version Feb 9, 2017
 *
 */

public class NYCStreetTrees {

	public static void main(String[] args) {
			
		// if the user enters no file name the program should print a message
		// explaining that a file name is necessary before terminating 
		if (args.length == 0) {
			  System.err.println("Usage Error: the program expects a file name as an argument");
			  System.exit(-1);
		}
		
		// create a file with the entered file name
		File file= new File(args[0]);
		
		// if the file does not exist inform the user and terminate the program
		if(file.exists()!=true){
				System.err.println("Usage Error: the file "+args[0]+" cannot be opened.");
				System.exit(-1);
		}
		// if the file cannot be read inform the user and terminate the program
		else if(file.canRead()!=true){
					System.err.println("Usage Error: the file "+args[0]+" cannot be opened.");
					System.exit(-1);
		}
		// if the file is not a file inform the user and terminate the program
		else if(file.isFile()!=true){
					System.err.println("Usage Error: the file "+args[0]+" cannot be opened.");
					System.exit(-1);
		}
			
		// create a list of type TreeList that will contain all the tree objects 
		TreeCollection list= new TreeCollection();
			
		try{
			// send the file to the scanner
			Scanner inputFile = new Scanner(file);

			
			// while there is another line in the file continue the loop
			while (inputFile.hasNextLine())	{
				
				// create a string that represents a single line from the file
				String line= inputFile.nextLine();
				
				// send the line to have its information separated and entered into an
				// arrayList of strings
				ArrayList<String> splitLine= splitCSVLine(line);
				
					// ensure the splitLine contains 41 elements, if not, skip it
					if (splitLine.size()==41){
						try{
							// parse the information according to the needed information
							// about a tree that is sent to the tree class
							int id= Integer.parseInt(splitLine.get(0));
							int diam= Integer.parseInt(splitLine.get(3));
							String status= splitLine.get(6);
							String health= splitLine.get(7);
							String spc= splitLine.get(9);
							int zip= Integer.parseInt(splitLine.get(25));
							String boro= splitLine.get(29);
							double x=  Double.parseDouble(splitLine.get(39));
							double y=  Double.parseDouble(splitLine.get(40));
							// once information is parsed, use its information to create a 
							// new tree
							Tree tree= new Tree(id,diam,status,health,spc,zip,boro,x,y);
							
							// if the tree object is valid, add it to treeList list
							list.add(tree);
							
						}
						// catch any invalid values sent to the constructor of the tree class
						catch (IllegalArgumentException e){
						
						}
						// throw a general exception in the case there is a parsing error
						// therefore the line will be ignored
						catch (Exception e){
						}
					}
				//}

			
			}
			// close the scanner
			inputFile.close();
		 }
		
		// catch if the file cannot be found and send a message describing what happened
		// to the user. Exit the program.
		catch (FileNotFoundException e){
			System.err.println("Usage Error: the file "+args[0]+" does not exist.");
			System.exit(-1);
		}
		
		// create an if statement to terminate the program if the file is empty or contains
		// no usable 
		if (list.getTotalNumberOfTrees()==0){
			System.err.println("Usage Error: the file "+args[0]+" contains no usable information.");
			System.exit(-1);
		}
		
		// this portion of the program will handle user input and print the correct
		// tree information according to the tree species the user enters 
		
	 	// create an exit word that will terminate the loop
		String exitWord="quit";
		
		// create a string variable that represents the user's answer
		String answer;
		 
		// open the scanner 
		Scanner input= new Scanner(System.in);
		// print a statement informing the user what to do
		System.out.println("Enter the tree species to learn more about it ('quit' to stop):");
		// set the answer equal to the user's response
        answer= input.nextLine();
        
        // while the answer is not quit, keep prompting the user for a tree species
		while (!(answer.equalsIgnoreCase(exitWord))){
			// if the tree species exists within the list of trees, print its matching species
			// and how many of that species exists within NYC and its boroughs
			if ((list.getCountByTreeSpecies(answer))>0){
				System.out.println(findMatchingSpecies(list,answer)+"\n");
				System.out.println(getSpeciesInfo(list,answer));
			}
			
			// if the tree species is not within the list of trees, print a statement to 
			// the user explaining it does not exist in NYC
			else{
				System.out.println("");
				System.out.println("There are no records of "+answer+" on NYC streets");
				System.out.println("");
			}
		
		// re-prompt the user to enter a tree species
		 System.out.println("Enter the tree species to learn more about it ('quit' to stop):");
		 answer= input.nextLine();
		}
		
		//close the scanner
		 input.close();
		 
		 
	}
	
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround  multi-word entries that may contain commas). 
	 * 
	 * @param textLine 
	 * 	the line of text to be parsed
	 * 
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	
	/**
	 * This method creates a string that contains the matching species
	 * of the species the user enters 
	 * 
	 * @param list
	 * 	list is a reference to the arraylist that contains all the tree objects
	 * 
	 * @param species
	 * 	the species is the entered species that the user wants to learn more about
	 *  
	 * @return
	 * 	the method returns a string that contains all of the matching species
	 * 	with a header to inform the user of what the information is
	 */
	public static String findMatchingSpecies(TreeCollection list, String species){
		String matchingSpecies="All matching species:\n";
		ArrayList<String> matchingSpeciesList= (ArrayList<String>) list.getMatchingSpecies(species);
		for (int x=0; x<matchingSpeciesList.size();x++){
			matchingSpecies+=String.format("\t%s\n",matchingSpeciesList.get(x));
		}
		return matchingSpecies;
	}
	
	/**
	 * This method creates a string that contains how many trees of a particular species, 
	 * total trees, and the percent of trees of a particular species that exist within NYC 
	 * and its boroughs 
	 * 
	 * @param list
	 * 	the list is a reference to the arrayList that contains all the tree objects
	 * 
	 * @param species
	 *  the species is the entered species that the user wants to learn more about
	 * 
	 * @return
	 * 	the method returns a string with all the tree's information with a header
	 * 	to inform the user of what the information is
	 */
	public static String getSpeciesInfo(TreeCollection list, String species){
		// create and new string that will act as header that lets the user know that 
		// the program will display the popularity of the tree species in the city
		String info= "Popularity in the city:\n";
		String equals= ":";
		
		// create an array to hold all the boroughs in NYC
		String[] boroArray= {"NYC","Manhattan","Bronx","Brooklyn","Queens","Staten Island"};
		
		// iterate through the array to obtain which borough the appropriate information
		// needs to be gathered for
		for (int x=0; x<boroArray.length;x++){
			String boro= boroArray[x];
			// if boro equals NYC create a formatted string that includes the getTotalNumberOfTrees() method
			// to the info string. If not, create a similar formatted string for the boroughs instead
			if (boro.equals("NYC")){
				// create an integer variable that represents the number of the 
				// particular tree species that grows in NYC
				int treesInCity = 0;
				
				// create a loop to search through each borough and add the number of the particular
				// tree species that appears in every borough to treesInCity
				for (int i=1; i<boroArray.length;i++){
					treesInCity+=list.getCountByTreeSpeciesBorough(species,boroArray[i]);
				}
				
				// create an integer variable that represents the total number of trees in NYC
				int totalTreesInCity = list.getTotalNumberOfTrees();
				
				// create an integer variable that represents the percent amount of that species in NYC.
				// send treesInCity and totalTreesInCity to the calculatePercent method to find the percent amount
				// of a particular tree species in NYC
				float percentOfSpecies = calculatePercent(treesInCity,totalTreesInCity);
				
				// add NYC to the info string
				info+=String.format("\t%s%12s",boro,equals);
				
				// create a formatted string to add to info that contains all the information
				String treeInfo= String.format("%,d(%,d)", treesInCity,totalTreesInCity);
				info+=String.format("%18s\t%6.2f",treeInfo,percentOfSpecies);
				info+="%\n";
			}
			else{
				// create an integer variable that represents the number of the particular tree species that grows in the borough
				int treesInBoro = list.getCountByTreeSpeciesBorough(species,boro);
				
				// create an integer variable that represents the total number of trees in the borough
				int totalTreesInBoro = list.getCountByBorough(boro);
				
				// create an integer variable that represents the percent amount of that species in the borough
				float percentOfSpecies;
				
				// if the trees in the borough is 0 as well as the total trees, set percentOfSpecies
				// equal to 0
				if (treesInBoro==0&&totalTreesInBoro==0){
					percentOfSpecies=0;
				}
				else{
					// create an integer variable that represents the percent amount of that species in the borough.
					// send treesInBoro and totalTreesInBoro to the calculatePercent method to find the percent amount
					// of a particular tree species in the borough
					percentOfSpecies = calculatePercent(treesInBoro,totalTreesInBoro);
				}
				
				// format the info string
				if (boro.equalsIgnoreCase("manhattan")){
					info+=String.format("\t%s%6s",boro,equals);
				}
				else if (boro.equalsIgnoreCase("bronx")){
					info+=String.format("\t%s%10s",boro,equals);
				}
				else if (boro.equalsIgnoreCase("brooklyn")){
					info+=String.format("\t%s%7s",boro,equals);
				}
				else if (boro.equalsIgnoreCase("queens")){
					info+=String.format("\t%s%9s",boro,equals);
				}
				else {
					info+=String.format("\t%s%2s",boro,equals);
				}
				
				// create a formatted string to add to info that contains all the information
				String treeInfo= String.format("%,d(%,d)", treesInBoro,totalTreesInBoro);
				info+=String.format("%18s\t%6.2f",treeInfo, percentOfSpecies);
				info+="%\n";
			}

		}
		// return the formatted info string
		return info;
	}
	
	/**
	 * This method calculates the percent amount of trees of
	 * a particular species that exists within a borough
	 * 
	 * @param numOfSpecificTrees
	 * 	the number of trees of a specific species that exists within
	 * 	a particular borough
	 * 
	 * @param totalTrees
	 * 	 the total number of trees that exist within a particular borough
	 * 
	 * @return returns the percent amount of trees within a particular 
	 * 	borough that belong to a particular species
	 */
	public static float calculatePercent(float numOfSpecificTrees, float totalTrees){
		float percent= numOfSpecificTrees/totalTrees*100;
		return percent;
		
	}
	
	


}
