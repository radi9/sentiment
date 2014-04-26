package com.utils.opinionmining;
import java.io.*;
import java.util.StringTokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
public class info {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		MaxentTagger tagger =  new MaxentTagger("taggers/english-bidirectional-distsim.tagger");
		String fileName="reviews.txt";
		
				try {
		

					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String strLine = null;
					StringTokenizer st = null, x = null, y=null, p=null;
					int lineNumber = 0, tokenNumber = 0;
					String data = "";
					String header = br.readLine();
					while( (data = br.readLine()) != null){
						if (data.trim().length() == 0) {
							          break;
						}
		
						String[] values = data.split("\t");
		
					  
					  System.out.println("Name: " + values[1] + "\tReview: " +  values[3]);
					  
					  String tagged = tagger.tagString(values[3]);
						System.out.println(tagged);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
		//String a = "I like watching movies";
	}

	

}
