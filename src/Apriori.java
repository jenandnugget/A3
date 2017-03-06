//base project, Negin Sauermann section
//Referenced Apriori Algorithm by Nathan Magnus, 2009 (http://www2.cs.uregina.ca/~dbd/cs831/notes/itemsets/itemset_prog1.html)
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Apriori {
	//necessary declarations
	double minSupp; //minimum support for frequent item sets
	double minRate; //minimum confidence rate for declaration 
	
	String inputFile = ""; 
	String outputFile = ""; 
	
	double currentItemS;
	
	ArrayList<dataNode> rulesList;
	ArrayList<String> data;
	ArrayList<String> dataType;

	//initializing 
	public Apriori(){
		minSupp = 0.0;
		minRate = 0.0;
		currentItemS = 0;
		String inputFile = "file.txt";
		String outputFile = "output.txt"; 

		rulesList = new ArrayList<dataNode>();

		startData = new ArrayList<String>();
		dataType = new ArrayList<String>();
		data = new ArrayList<String>();
	}

	
}

