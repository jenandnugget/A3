//base project, Negin Sauermann section
//Referenced Apriori Algorithm by Nathan Magnus, 2009 (http://www2.cs.uregina.ca/~dbd/cs831/notes/itemsets/itemset_prog1.html)
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Apriori {
	              
	//declaring variables and initializing 
	double minSupp = 0.0; //minimum support for frequent item sets
	double minRate = 0.0; //minimum confidence rate for declaration 
	
	String inputFile = ""; //text file from user
	String outputFile = ""; //output file with rules
	Scanner keyboard = new Scanner(System.in); //userInput
	
	double currentItemS = 0;
	ArrayList<String> dataBuild;
	ArrayList<String> dataType;
	
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
	
	
	//generate user input, get file name and amount of min support and confidence rate to generate rules
	public void getInput(){
		System.out.println("What is the file name?");
		inputFile = keyboard.nextLine(); //set input to inputFile
	
		System.out.println("Select the minium support rate, values range from 0.00-1.00:");
<<<<<<< HEAD
		minRate = keyboard.nextDouble(); //set input to minRate
	
		System.out.println("Please select the minimum confidence rate, values range from 0.00-1.00):");
		minSupp = keyboard.nextDouble(); ////set input to minSupp
	
		keyboard.close();
	
	}
	
		
=======
		minRate = keyboard.nextDouble();
			
		System.out.println("Please select the minimum confidence rate, values range from 0.00-1.00):");
		minSupp = keyboard.nextDouble();

		keyboard.close();
	
<<<<<<< HEAD
	}//end getInput
>>>>>>> origin/master
=======
	}
>>>>>>> origin/master

public static runApriori() {
	// TODO Auto-generated method stub
	
	
}
	
}