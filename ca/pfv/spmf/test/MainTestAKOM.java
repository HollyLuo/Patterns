package pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import pfv.spmf.algorithms.sequenceprediction.ipredict.database.Item;
import pfv.spmf.algorithms.sequenceprediction.ipredict.database.Sequence;
import pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceDatabase;
import pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceStatsGenerator;
import pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.Markov.MarkovAllKPredictor;

/**
 * Example of how to use the AKOM (All-k-order-markov) sequence prediction model in the source code.
 * Copyright 2015.
 */
public class MainTestAKOM {

	public static void main(String [] arg) throws IOException{
		
		// Load the set of training sequences
		String inputPath = fileToPath("contextPrefixSpan2.txt");  
		SequenceDatabase trainingSet = new SequenceDatabase();
		trainingSet.loadFileSPMFFormat(inputPath, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		
		// Print the training sequences to the console
		System.out.println("--- Training sequences ---");
		for(Sequence sequence : trainingSet.getSequences()) {
			System.out.println(sequence.toString());
		}
		System.out.println();
		
		// Print statistics about the training sequences
		SequenceStatsGenerator.prinStats(trainingSet, " training sequences ");
		
		// The following line is to set optional parameters for the prediction model. 
		// Here we set the order of the markov model to 5.
		String optionalParameters = "order:10";

		// Train the prediction model
		MarkovAllKPredictor predictionModel = new MarkovAllKPredictor("AKOM", optionalParameters);
		predictionModel.Train(trainingSet.getSequences());
		
		// Now we will make a prediction.
		// We want to predict what would occur after the sequence <1, 3>.
		// We first create the sequence
		Sequence sequence = new Sequence(0);
		sequence.addItem(new Item(1));
		sequence.addItem(new Item(3));
//		sequence.addItem(new Item(10));
//		
		// Then we perform the prediction
		Sequence thePrediction = predictionModel.Predict(sequence);
		System.out.println("For the sequence <(1),(3)>, the prediction for the next symbol is: +" + thePrediction);
		
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestAKOM.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
