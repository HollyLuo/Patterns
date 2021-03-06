package pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.annotation.processing.SupportedAnnotationTypes;
import pfv.spmf.algorithms.splitpatterns.SplitBehaviorChain;

/**
 * Example of how to use APRIORI algorithm from the source code.
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestSplitPatterns_saveToFile {

	public static void main(String [] arg) throws Exception{

		String input = fileToPath("behavior3.txt");
//		SplitBehaviorChain chain = new SplitBehaviorChain();
		float support = 0.2f;
		SplitBehaviorChain.runAlgorithm(input,support);
		
//		String output = "./output.txt";  // the path for saving the frequent itemsets found
//		
//		double minsup = 0.4; // means a minsup of 2 transaction (we used a relative support)
		
		// Applying the Apriori algorithm
//		AlgoApriori algo = new AlgoApriori();
		
		
		// Uncomment the following line to set the maximum pattern length (number of items per itemset)
//		algo.setMaximumPatternLength(3);

//		algo.runAlgorithm(minsup, input, output);
//		algo.printStats();
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestSplitPatterns_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
