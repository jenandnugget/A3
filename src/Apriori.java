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
	
	String inputFile = ""; 
	String outputFile = ""; 
	
	double currentItemS = 0;
	ArrayList<String> data;
	ArrayList<String> dataType;
	
	//getter and setters
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
	
	
	//User input
	Scanner keyboard = new Scanner(System.in);

	public static void AprioriRun() {
		// TODO Auto-generated method stub
		
	}

	System.out.println("What is the file name?");
	inputFile = keyboard.nextLine();

	System.out.println("Select the minium support rate(Only values from 0.00-1.00):");
	minRate = keyboard.nextDouble();

	System.out.println("Please select the minimum confidence rate(Only values from 0.00-1.00):");
	minSupp = keyboard.nextDouble();

	keyboard.close();
	
	

	
}

