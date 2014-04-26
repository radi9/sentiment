package com.utils.opinionmining;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class SentimentScore {

	/**
	 * @param args
	 */
	private Map<String, Double> dictionary;
	
	public static void main(String[] args) throws IOException{
		String pathToSWN = "C:\\Users\\BPPIMT\\Downloads\\Desktop\\SentiWordNet_3.0.0\\home\\swn\\www\\admin\\dump\\SentiWordNet_3.0.0_20130122.txt";
	    SentiWordNetDemoCode sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
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
		

		//String reviewSentence = "This a great pair of headsets ,, soft on the ear and greatest sound quality !!";
		String POS_TaggedSentence =createPOStag("reviews.txt");
				
		String adjectives = "";
		String adverbs = "";			
		
		if(POS_TaggedSentence.contains("JJS")){
			String temp[] = POS_TaggedSentence.split("_JJS ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adjectives = adjectives+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		if(POS_TaggedSentence.contains("JJR")){
			String temp[] = POS_TaggedSentence.split("_JJR ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adjectives = adjectives+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		if(POS_TaggedSentence.contains("JJ")){
			String temp[] = POS_TaggedSentence.split("_JJ ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adjectives = adjectives+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		adjectives = adjectives.trim();
		System.out.println("Adjectives are available in review sentence are:-"+adjectives);	
		
		if(POS_TaggedSentence.contains("RB")){
			String temp[] = POS_TaggedSentence.split("_RB ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adverbs = adverbs+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		if(POS_TaggedSentence.contains("RBR")){
			String temp[] = POS_TaggedSentence.split("_RBR ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adverbs = adverbs+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		if(POS_TaggedSentence.contains("RBS")){
			String temp[] = POS_TaggedSentence.split("_RBS ");
			if(temp.length>1){
				for(int m = 0; m<temp.length - 1; m++){
					adverbs = adverbs+temp[m].substring(temp[m].lastIndexOf(" ")).trim()+",";
				}
			}
		}
		adverbs = adverbs.trim();
		System.out.println("Adverbs are available in review sentence are:-"+adverbs);
			
		
		
		double finalScore = 0;
		String sentimentVal = "neutral";
		if(!adjectives.equalsIgnoreCase("")){
			String adjWords[] = adjectives.split(",");
			for(int k = 0; k<adjWords.length; k++){
				
				double scoreTemp = sentiwordnet.extract(adjWords[k].trim(), "a");
			    System.out.println(adjWords[k]+"#a "+scoreTemp);
			 
			   
			   finalScore = finalScore+scoreTemp;
			}
		}
		if(!adverbs.equalsIgnoreCase("")){
			String advWords[] = adverbs.split(",");
			for(int k = 0; k<advWords.length; k++){
				
				double scoreTemp = sentiwordnet.extract(advWords[k].trim(), "a"); 
			    System.out.println(advWords[k]+"#a "+scoreTemp);
			 
			   
			    finalScore = finalScore+scoreTemp;
			}
		}
		
		if(finalScore > 0){
			sentimentVal = "positive";
		}else{
			sentimentVal = "negetive";
		}
		System.out.println("Final score of sentence is: "+finalScore+" and Sentiment Score is "+sentimentVal);
	}

	private static String createPOStag(String string) {
		// TODO Auto-generated method stub
		return null;
	}
		  
}


