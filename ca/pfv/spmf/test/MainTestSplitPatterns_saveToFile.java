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
//		String output = "/Users/ling/Documents/Eclipseworkspace/Weka/pattern/ca/pfv/spmf/test/result/behavior3.txt";
		SplitBehaviorChain.runAlgorithm(input,support);
		
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestSplitPatterns_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
