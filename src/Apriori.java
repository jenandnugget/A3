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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Vector;
//input/output errors
import java.io.IOException;
import java.io.InputStreamReader;

/******************************************************************************
 * Class Name: Apriori
 * Created By: Negin
 * Purpose: Declaring and initializing all variables as well as ArrayList for Candidates 
 *****************************************************************************/
public class Apriori {
	              
	Vector<String> candidates=new Vector<String>(); //the current candidates
    //String configFile="configuration.txt"; //configuration file
    String transactionFile="transaction.txt"; //transaction file
    int numItems; //number of items per transaction
    int numTransactions; //number of transactions
    String oneValue[]; //array of value per column that will be treated as a '1'
    String itemSep = " "; //the separator value for items in the database
    
    
    //initalize ruleList information
    private ArrayList<Apriori> ruleList =  new ArrayList<Apriori>();
    String impliedData;
    String resultingData;
    double supportCalculate;
    double confidenceRate; 
    //no argument constructor
    
    public Apriori()
    {
    	
    }
    //edit no argument constructor to house impliedData, resultingData, supportCalculate, and confidenceRate. 
    public Apriori(String impliedData, String resultingData, double supportCalculate, double confidenceRate) 
    {
		this.impliedData = impliedData;
		this.resultingData = resultingData;
		this.supportCalculate = supportCalculate;
		this.confidenceRate = confidenceRate;
	}
	
	
	//declaring variables and initializing 
	double minSupp = 0.0; //minimum support for frequent item sets
	double minRate = 0.0; //minimum confidence rate for declaration 
	
	String inputFile = ""; //text file from user
	String outputFile = ""; //output file with rules
	Scanner keyboard = new Scanner(System.in); //userInput
	
	static double currentItemS = 0;
	
	static ArrayList<String> data;
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
	 * Class Name: generateTheCandidates
	 * Created By: Jen Cooper
	 * Purpose: To create the 1 itemset candidates and then the more frequent ones through Tokenizer.
	 *****************************************************************************/
	private static void generateTheCandidates()
	{
		//create an arraylist with the currentCandidates
		ArrayList<String> currentCandidates = new ArrayList<String>();
		
		//initalize variables for first and second candidates and two tokens. 
		String firstCandidate;
		String secondCandidate;
		StringTokenizer tokenOne;
		StringTokenizer tokenTwo;
		
		
		//use nest for loops to parse through all of the data and to set the tokens equal to the variable in the for loop. 
		for (int j = 0; j<data.size(); j++)
		{
			for (int k = j+1; k <data.size(); k++)
			{
				firstCandidate = new String();
				secondCandidate = new String();
				tokenOne = new StringTokenizer(data.get(j));
				tokenTwo = new StringTokenizer(data.get(k));
				
				//if there are still items to go, reinitalize the candidates
				for (int x = 0; x < currentItemS - 2; x++)
				{
					firstCandidate = firstCandidate + " " + tokenOne.nextToken();
					secondCandidate = secondCandidate + " " + tokenTwo.nextToken();
				}//end x for
				
			}//end k for
		}//end j for
	}//end generateTheCandidates

	
	/******************************************************************************
	 * Class Name: generateFrequentItemS
	 * Created By: Jen Cooper
	 * Purpose: To create the single candidate item sets, then to find frequent (occuring more than twice) through String Tokenizer 
	 *****************************************************************************/
	private void generateFrequentItemS()
			{
				Vector <String> frequentCandidates = new Vector <String>(); //list candidates for current itemset
				FileInputStream inputStream;
				BufferedReader bufferedReader;
				FileWriter fileWriter;
				BufferedWriter bufferedWriter;
				int numberOfItems = 4; //determines how many items per transaction
				
				StringTokenizer tokenizer, tokenizerFile;
				boolean match; //t/f as to if the transaction has the candidates we are looking for
				boolean transaction[] = new boolean[numberOfItems]; //houses a whole transaction, all items
				int matching[] = new int[candidates.size()]; //number of successful matches
				
				
				try
				{
					fileWriter = new FileWriter(outputFile, true);
					bufferedWriter = new BufferedWriter(fileWriter);
					
					inputStream = new FileInputStream(transactionFile);
					bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					
					for (int n = 0; n < numTransactions; n++)
					{
						tokenizerFile = new StringTokenizer (bufferedReader.readLine(), itemSep);
						
						for (int x = 0; x < numItems; x++)
						{
							//if it is not a 0 then assign the value to be true
							transaction[x] = (tokenizerFile.nextToken().compareToIgnoreCase(oneValue[x]) == 0);
						}
							for (int y = 0; y < candidates.size(); y++)
							{
								match = false;
								tokenizer = new StringTokenizer(candidates.get(y));
					
								while (tokenizer.hasMoreTokens())
									{
									match = transaction[Integer.valueOf(tokenizer.nextToken())-1];
						
									if (match)
										matching[y]++;
									else 
										break;
					
									}//end while

						}//end y for
				}// end n for
					
					for (int z = 0; z < candidates.size(); z++)
					{
						if ((matching[z] / (double) numTransactions) >= minSupp)
						{
							frequentCandidates.add(candidates.get(z)); //add this candidate to the frequent ones
							bufferedWriter.write(candidates.get(z) + ", " +matching[z]/(double)numTransactions); //put this frequent candidate in output
						}//end if
						bufferedWriter.write("-\n");
						bufferedWriter.close();
					}
					
					
				}//end try
				
				catch (IOException e)
				{
					System.out.println("this has run into an exception");
					
					//clear out any and all data
					candidates.clear();
					candidates = new Vector <String> (frequentCandidates);
					frequentCandidates.clear();
				}
				
				
				
				
				
				
			}


	
	/******************************************************************************
	 * Class Name: countTheItemS
	 * Created By: Jen Cooper
	 * Purpose: To create the rules using variables impliedData, supportCalculate, firstData and the dataType.
	 ******************************************************************************/
	private void countTheItemS()
	{
		boolean match;
		int counter1[] = new int[data.size()];
		int counter2[] = new int[data.size()];
		
		for (int x = 0; x < (firstData.size()/dataType.size()); x++);
		{
			for (int y = 0; y < data.size(); y++)
			{
				match = true;
				String [] word = data.get(y).split("\\s+");
	
				if(match)
				{
					counter1[y]++;
				}
			}//end y for
		}//end x for
		
		
		for (int z = 0; z < data.size(); z++)
		{
			if (counter1[z] / (double) ((firstData.size()/dataType.size())) >= minSupp)
				{
				double supportCalculate = (counter1[z] / (double) (firstData.size()/dataType.size()));
				
				String [] word = data.get(z).split("\\+s");
				String impliedData = "";
				
				for (int w = 0; w <word.length -1; w++)
				{
					impliedData = impliedData + " " + word[w];
				}
				
				ruleList.add(new Apriori(impliedData, word[word.length -1], supportCalculate, 0));
						
				}//end if
					
		}//end z for
		
		
	}//end CountTheItemS
	

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
		currentItemS++;
		//Vector<String> candidates = new Vector<String>(); //the current candidates
		do{
			currentItemS++;
			generateTheCandidates();
		}while(true);
		
	}

	
}