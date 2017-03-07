/******************************************************************************
 * Project by Negin Sauermann - Apriori Main Class, loadDB, buildFirstItemSet. Jen Cooper - genFreqItemSet, createRules, outputRules
 * Purpose: Association Rule Mining
 * Referenced: Apriori Algorithm by Nathan Magnus created in 2009, free to implement with proper reference.
 * URL: (http://www2.cs.uregina.ca/~dbd/cs831/notes/itemsets/itemset_prog1.html)
 * Utilizing: ArrayLists, Scanner, BufferedReader, FileInputStream, FileReader, StringTokenizer, IOException
 *****************************************************************************/

import java.util.ArrayList; //for tuples/item sets
import java.util.Scanner; //for user input

//for file input and tokenizing:
import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.util.StringTokenizer;

//input/output errors
import java.io.IOException;

/******************************************************************************
 * Class Name: Apriori
 * Created By: Negin
 * Purpose: Declaring and initializing all variables as well as ArrayList for Candidates 
 *****************************************************************************/
public class Apriori {
	              
	//declaring variables and initializing 
	double minSupp = 0.0; //minimum support for frequent item sets
	double minRate = 0.0; //minimum confidence rate for declaration 
	
	String inputFile = ""; //text file from user
	String outputFile = ""; //output file with rules
	Scanner keyboard = new Scanner(System.in); //userInput
	
	double currentItemS = 0;
	
	ArrayList<String> data;
	ArrayList<String> dataType;
	ArrayList<String> firstData;
	
	//getters
	public double getminSupp() {
		return minSupp;
	}

	public double getminRate() {
		return minRate;
	}

	public String getinputFile() {
		return inputFile;
	}

	public double getCurrentItemS() {
		return currentItemS;
	}
	
	/******************************************************************************
	 * Class Name: getInput
	 * Created By: Negin
	 * Purpose: User input for tokenizing later, error handling accounts for input within range specified only
	 *****************************************************************************/
	public void getInput(){
		System.out.println("Please enter the file name, include the extension (.txt)?");
		inputFile = keyboard.nextLine(); //set user input to inputFile for tokenizing
	
		System.out.println("Select the minium support rate, values range from 0.00-1.00:");
		minRate = keyboard.nextDouble();
		//ensure that the confidence rate is within the boundaries of 0 and 1
		if (minRate> 1 | minRate < 0){
			System.out.println("The confidence rate you entered is invalid, please enter a value in the specified range: ");
			minRate = keyboard.nextDouble();
		}		
		System.out.println("Please select the minimum confidence rate, values range from 0.00-1.00):");
		minSupp = keyboard.nextDouble();
		
		//ensure that the support rate is within the boundaries of 0 and 1
		if (minSupp > 1 | minSupp < 0){
			System.out.println("The support value you entered is invalid, please enter a value in the specified range: ");
			minSupp = keyboard.nextDouble();
		}

		keyboard.close();
	
	}//end getInput
	

	/******************************************************************************
	 * Class Name: loadDB
	 * Created By: Negin
	 * Purpose: Reading file specified by user using bufferedReader
	 * @throws IOException 
	 *****************************************************************************/
	public void loadData() throws IOException{

		BufferedReader read = null;
			String currentLine;

			read = new BufferedReader(new FileReader(inputFile)); //from user input

			while ((currentLine = read.readLine()) != null) { 
				if(dataType.isEmpty()){
					String[] words = currentLine.split("\\s+");
					
					for(int i = 0; i < words.length;i++){
						dataType.add(words[i]);
					}
				}
				String[] words = currentLine.split("\\s+");
				for(int i = 0; i < words.length;i++){
					firstData.add(dataType.get(i) + "=" + words[i]);
					data.add(dataType.get(i) + "=" + words[i]);
				}
			}

		} 
	
	/******************************************************************************
	 * Class Name: buildFirstItemSet
	 * Created By: Negin
	 * Purpose: Creating the candidate 1-itemset and then the frequent 1-itemset through String Tokenizing the ArrayList 
	 *****************************************************************************/
	public void buildFirstItemSet(){
		ArrayList<String> temp = new ArrayList<String>();
		String can1, can2; //candidate for 1 item set and frequent itemset
		StringTokenizer token1, token2;

		for(int i=0; i<data.size(); i++)
		{
			token1 = new StringTokenizer(data.get(i));
			can1 = token1.nextToken();
			for(int j=i+1; j<data.size(); j++)
			{
				token2 = new StringTokenizer(data.get(j));
				can2 = token2.nextToken();
				temp.add(can1 + " " + can2);
			}
		}
	}

	/******************************************************************************
	 * Class Name: runApriori
	 * Created By: Negin
	 * Purpose: I NEED TO FIX THIS SO IGNORE IT I GUESS LOL
	 *****************************************************************************/
	public static void runApriori() {
	// TODO Auto-generated method stub
		getInput();
		loadData();
		buildFirstItemSet();
		currentItemSet++;
		do{
			currentItemSet++;
			generateCandidates();
		}while(true);
		
	}

	
}