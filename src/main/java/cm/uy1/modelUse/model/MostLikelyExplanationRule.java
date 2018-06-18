package cm.uy1.modelUse.model;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDefinition.HMMRules;

/**
 * This class contains methods that are used to calculate the most likely
 * explanation of the observation. In our case, it will be to infer the labels
 * of all the source code that is observed based on a train HMM
 * 
 * @author azanzi
 *
 */

public class MostLikelyExplanationRule {
	
	
	public static String knowledgeExtraction(List<Column> alphaTable) {
		String mostLikelyExplanation="";
		
		int alphaTableSize = alphaTable.size()-1;
		Frame frame = new Frame();
				
		//Get the last colum of the alphaTable
		Column lasColumn = alphaTable.get(alphaTable.size()-1);
//		System.out.println("+++++++++++++++++++++++++++++The last column of the table"+lasColumn);
		//Get the last frame which has the greatest probability
		frame = Helper.mostGreatestFrameProba(lasColumn);
		System.out.println("+++++++++++++++++++++++++++The most greater "
				+ "frame probab : "+frame+"\nBuild by:"+frame.getFrameBuilder());
		//Get the frames that permit to have the above frame
		
		List<Frame> mostLikelyFrames = new ArrayList<>();
		mostLikelyFrames.add(frame);
		//Browse the frame list to get the most likely frame
		for (int i = alphaTableSize; i > 0; i--) {
 			
			frame = frame.getFrameBuilder();
//			System.out.println("The label cary by the frame added : "+frame.getStateLabel()+", "+
//			frame.getObservedLabel());
			mostLikelyFrames.add(frame);
		}
		String label="";
		
		for (int i = mostLikelyFrames.size()-1; i>=0; i--) {
			if(mostLikelyFrames.get(i).getStateLabel().equals("TARGET")) {
				label = mostLikelyFrames.get(i).getObservedLabel();
				if(Helper.belongs2Array(label, HMMRules.getTargetObservation()))
					label="\n"+label+" ";
				mostLikelyExplanation+=label+" ";
			}
		}
		return mostLikelyExplanation;
	}
	 /**
		 * This method calculate the alpha start table that is a table that contains
		 the argmax probability
		 * of all the observed source code.
		 *
		 * Takes one file and calculate the most likely explanation
		 *
		 * @param hmmConcept
		 * @param listSourceCode
		 * @return
		 */

		public static List<Column> fillAlphaTable4Rules(HMMRules hmmRules, String sourceFile) {
			List<Column> alphaTable = new ArrayList<>();

			// Get the transition probabilities that will be very helpfull later
			double prePreTransitionValue = hmmRules.getPrePreTransition().getTransitionValue();
			double preTargetTransitionValue = hmmRules.getPreTargetTransition().getTransitionValue();
			double prePostTransitionValue = hmmRules.getPrePostTransition().getTransitionValue();
			double preOtherTransitionValue = hmmRules.getPreOtherTransition().getTransitionValue();

			double targetPreTransitionValue = hmmRules.getTargetPreTransition().getTransitionValue();
			double targetTargetTransitionValue = hmmRules.getTargetTargetTransition().getTransitionValue();
			double targetPostTransitionValue = hmmRules.getTargetPostTransition().getTransitionValue();
			double targetOtherTransitionValue = hmmRules.getTargetOtherTransition().getTransitionValue();
			
			double postPreTransitionValue = hmmRules.getPostPreTransition().getTransitionValue();
			double postTargetTransitionValue = hmmRules.getPostTargetTransition().getTransitionValue();
			double postPostTransitionValue = hmmRules.getPostPostTransition().getTransitionValue();
			double postOtherTransitionValue = hmmRules.getPostOtherTransition().getTransitionValue();
			
			double otherPreTransitionValue = hmmRules.getOtherPreTransition().getTransitionValue();
			double otherTargetTransitionValue = hmmRules.getOtherTargerTransition().getTransitionValue();
			double otherPostTransitionValue = hmmRules.getOtherPostTransition().getTransitionValue();
			double otherOtherTransitionValue = hmmRules.getOtherOtherTransition().getTransitionValue();

			sourceFile = sourceFile.trim().replaceAll(" +", " ");
			String[]tmp=sourceFile.split(" ");

			// Let us initialize the table
			// Create the first column
			Column column = new Column();
			// Create the frames of this column
			// We know that the file always begin by an other word
			Frame framePRE0 = new Frame(tmp[0], "PRE", 0);
			Frame frameTARGET0 = new Frame(tmp[0], "TARGET", 0);
			Frame framePOST0 = new Frame(tmp[0], "POST", 0);
			Frame frameOTHER0 = new Frame(tmp[0], "OTHER", 1E300);

//			// Add the first frame to the columns
//			System.out.println("The first frames added : "
//					+ ""+framePRE0+"\n"+frameTARGET0+"\n"+framePOST0+"\n"+frameOTHER0);
			column.setPreFrame(framePRE0);
			column.setTargetFrame(frameTARGET0);
			column.setPostFrame(framePOST0);
			column.setOtherFrame(frameOTHER0);

			//Add the first column to the table
			alphaTable.add(column);
			//Some frames that will be use later
			Frame framePRE;
			Frame frameTARGET;
			Frame framePOST;
			Frame frameOTHER;
			Frame frameUsed;
			//Tp carry some temporary variables
			Column columnTMP = new Column();
			//Carry temporary variable before the insertion into the column
			Frame frameTMP = new Frame();
			double preEmission=0, targetEmission=0, postEmission=0, otherEmission=0;
			double calculPRE, calculTARGET, calculPOST, calculOTHER;
			int k=0;
			
			/////loop
			for (int i = 0; i < tmp.length; i++) {

				tmp[i]=tmp[i].trim();
				
				preEmission=0; targetEmission=0; postEmission=0; otherEmission=0;
				
				calculPRE=0; calculTARGET=0; calculPOST=0; calculOTHER=0;
				
				columnTMP = new Column();
				
				frameTMP=null;
				
				
				System.out.println("******************Element of the file : "+tmp[i]);
				//Get the last element of the list
				column = alphaTable.get(alphaTable.size()-1);
				
				//Return the column of the last index with the state label PRE.
				// e.g. alpha(PRE 2), alpha(TARGET, 2)
				
				framePRE = column.getPreFrame();
				frameTARGET = column.getTargetFrame();
				framePOST = column.getPostFrame();
				frameOTHER = column.getOtherFrame();
				
				/**
				 * Now, let us calculate the frame for the PRE state
				 */
				
				//Frame used to calculate the probability
				frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
						prePreTransitionValue, preTargetTransitionValue, prePostTransitionValue,
						preOtherTransitionValue);
				System.out.println("................The frame used to build PRE:\n"+frameUsed);
				//The value of the probability that is calculated
				calculPRE = Math.max
						(Math.max(prePreTransitionValue*framePRE.getProbabilityValue(), 
								preTargetTransitionValue*frameTARGET.getProbabilityValue()), 
						Math.max(prePostTransitionValue*framePOST.getProbabilityValue(),
								preOtherTransitionValue*frameOTHER.getProbabilityValue()));
				
				//Find the emission probability
				if(i+1<tmp.length && Helper.belongs2Array(tmp[i], hmmRules.getPreObservation())&&
						Helper.belongs2Array(tmp[i+1], hmmRules.getTargetObservation()))
					preEmission = preEmissionProbability(tmp[i], hmmRules);
				//Create the new frame for PRE state and add to the column
				System.out.println("********* Calcul PRE: "+calculPRE+"\nCalcul PRE emission"+preEmission);
				frameTMP = new Frame(tmp[i], "PRE", calculPRE*preEmission, frameUsed);
				System.out.println("+++++++++++PRE inserted:\n"+frameTMP);
				columnTMP.setPreFrame(frameTMP);
				
				/**
				 * The same calculation for TARGET state
				 */
				frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
						targetPreTransitionValue, targetTargetTransitionValue, 
						targetPostTransitionValue, targetOtherTransitionValue);
				System.out.println("................The frame used to build TARGET:\n"+frameUsed);
				//the value of the probability that is calculates
				calculTARGET = Math.max
						(Math.max(targetPreTransitionValue*framePRE.getProbabilityValue(), 
								targetTargetTransitionValue*frameTARGET.getProbabilityValue()), 
						Math.max(targetPostTransitionValue*framePOST.getProbabilityValue(), 
								targetOtherTransitionValue*frameOTHER.getProbabilityValue()));
				//Get the emission probability
				if(Helper.belongs2Array(tmp[i], hmmRules.getTargetObservation()))
					targetEmission=targetEmissionProbability(tmp[i], hmmRules);
				
				if(!Helper.belongs2Array(tmp[i], hmmRules.getTargetObservation())) {
					k=0;
					
					while (i-k>0 && !Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation())&&
							!tmp[i-k].equals("}"))
						k++;
					
					if(i-k>=0&&
							Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation()))
						targetEmission=targetEmissionProbability(tmp[i], hmmRules);
				}

//				System.out.println("********* Calcul TARGET: "+
//						calculTARGET+"\nCalcul TARGET emission"+targetEmission);
				//Create the new frame for TARGET state and add to the column
				frameTMP = new Frame(tmp[i], "TARGET", calculTARGET*targetEmission, frameUsed);
				System.out.println("+++++++++++TARGET inserted:\n"+frameTMP);
				columnTMP.setTargetFrame(frameTMP);
				
				/**
				 * The same calculation for the POST
				 */
				frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
						postPreTransitionValue, postTargetTransitionValue, postPostTransitionValue, 
						postOtherTransitionValue);
				System.out.println("................The frame used to build POST:\n"+frameUsed);
				//The value of the probability that is calculates
				calculPOST = Math.max
						(Math.max(postPreTransitionValue*framePRE.getProbabilityValue(),
								postTargetTransitionValue*frameTARGET.getProbabilityValue()), 
						Math.max(postPostTransitionValue*framePOST.getProbabilityValue()
								, postOtherTransitionValue*frameOTHER.getProbabilityValue()));
				//get the emission probability
				if(Helper.belongs2Array(tmp[i], hmmRules.getPostObservation())) {
					//Verify if this is a POST
					k=0;
					while (i-k>0 && !tmp[i-k].equals("}") || 
							i-k>0 && !Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation())){
						k++;
					}
					//If at the end of the loop, the element tmp[i-k] is a target, then, the element 
					//tmp[i] is a POST
					if(i-k>=0 && Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation())) {
						postEmission = postEmissionProbability(tmp[i], hmmRules);
					}
				}
//				System.out.println("********* Calcul POST: "+calculPOST
//						+"\nCalcul POST emission"+postEmission);
				//Create the new frame for POST state and add to the column
				frameTMP = new Frame(tmp[i], "POST", calculPOST*postEmission, frameUsed);
				System.out.println("+++++++++++POST inserted:\n"+frameTMP);
				columnTMP.setPostFrame(frameTMP);
					
				/**
				 * The same calculation with OTHER 
				 * */
				frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
						otherPreTransitionValue, otherTargetTransitionValue, otherPostTransitionValue, 
						otherOtherTransitionValue);

				System.out.println("................The frame used to build OTHER:\n"+frameUsed);
				//The value of the probability which is calculated
				calculOTHER = Math.max
						(Math.max(otherPreTransitionValue*framePRE.getProbabilityValue(), 
								otherTargetTransitionValue*frameTARGET.getProbabilityValue()), 
						Math.max(otherPostTransitionValue*framePOST.getProbabilityValue(), 
								otherOtherTransitionValue*frameOTHER.getProbabilityValue()));
				
				//Get the emission probability
				if(!Helper.belongs2Array(tmp[i], hmmRules.getTargetObservation())) {
					k=0;
					
					while (i-k>0 && !Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation())&&
							i-k>0 && !tmp[i-k].equals("}"))
						k++;
					
					if(i-k>=0&&
							!Helper.belongs2Array(tmp[i-k], hmmRules.getTargetObservation()))
						otherEmission=otherEmissionProbability(tmp[i], hmmRules);
				}
//				System.out.println("********* Calcul OTHER: "+calculOTHER+
//						"\nCalcul OTHER emission"+otherEmission);
				
				frameTMP = new Frame(tmp[i], "OTHER", calculOTHER*otherEmission, frameUsed);
				System.out.println("+++++++++++OTHER inserted:\n"+frameTMP);
				columnTMP.setOtherFrame(frameTMP);
				System.out.println("The column inserted: \n"+columnTMP);
				alphaTable.add(Helper.avoidZeroProba(columnTMP));
				
//				System.out.println("+++++++++++++++++++++++The element "+tmp[i]);
////				System.out.println("-------------------Calcul PRE and company-------------------------");
//				System.out.println("+++++++++++++++++++++++ calcul pre :"+calculPRE);
//				System.out.println("+++++++++++++++++++++++ pre emission :"+preEmission);
//
//				System.out.println("+++++++++++++++++++++++ calcul target :"+calculTARGET);
//				System.out.println("+++++++++++++++++++++++ target emission :"+targetEmission);
//
//				System.out.println("+++++++++++++++++++++++ calcul post :"+calculPOST);
//				System.out.println("+++++++++++++++++++++++ post emission :"+postEmission);
//				
//				System.out.println("+++++++++++++++++++++++ calcul other :"+calculOTHER);
//				System.out.println("+++++++++++++++++++++++ other emission :"+otherEmission);

				

			}
//			System.out.println("Print the last column: ");
//			System.out.println(alphaTable.get(0));
//			System.out.println(alphaTable.get(1));
//			System.out.println(alphaTable.get(2));
//			System.out.println(alphaTable.get(3));
//			System.out.println("--------------------------------------------");
//			System.out.println(alphaTable.get(alphaTable.size()-4));
//			System.out.println(alphaTable.get(alphaTable.size()-3));
//			System.out.println(alphaTable.get(alphaTable.size()-2));
//			System.out.println(alphaTable.get(alphaTable.size()-1));
//			System.out.println("***************************************************************");
//			System.out.println("***************************************************************");
//			System.out.println("**************+++++++++++The alphatable********+++++++++++++");
//			for (Column column2 : alphaTable) {
//				System.out.println(column2);
//			}
			
			return alphaTable;
		}

	// These methods are used to return the probability values of the elements that
	// is observed
	/**
	 * This method determine the probability that the state PRE emit the word passed
	 * as parameter
	 * 
	 * @param chaine
	 * @param hmmRules
	 * @return
	 */
	public static double preEmissionProbability(String chaine, HMMRules hmmRules) {
		double preValue = 0;

		switch (chaine) {
		case ";":
			preValue = hmmRules.getPreObservation_SemiCol().getEmissionValue();
			break;
		case "{":
			preValue = hmmRules.getPreObservation_openCurlBrass().getEmissionValue();
			break;
		case "}":
			preValue = hmmRules.getPreObservation_closeCurBrass().getEmissionValue();
			break;
		}

		return preValue;
	}

	/**
	 * This method return the value of the emission probability for a given word in
	 * the source code identified as TARGET
	 * 
	 * @param chaine
	 * @param hmmRules
	 * @return
	 */
	public static double targetEmissionProbability(String chaine, HMMRules hmmRules) {
		double targetValue = 0;

		switch (chaine) {
		case "if":
			targetValue = hmmRules.getTargetObservation_if().getEmissionValue();
			break;
		case "else":
			targetValue = hmmRules.getTargetObservation_else().getEmissionValue();
			break;
		case "switch":
			targetValue = hmmRules.getTargetObservation_switch().getEmissionValue();
			break;

		default:
			targetValue = hmmRules.getTargetObservation_other().getEmissionValue();
			break;
		}
		return targetValue;
	}

	/**
	 * This method return the value of the emission probability for a given word in
	 * the source code identified as POST
	 * 
	 * @param chaine
	 * @param hmmRules
	 * @return
	 */
	public static double postEmissionProbability(String chaine, HMMRules hmmRules) {
		double postValue = 0;

		switch (chaine) {
		case ";":
			postValue = hmmRules.getPostObservation_semiColon().getEmissionValue();
			break;
		case "}":
			postValue = hmmRules.getPostObservation_brassess().getEmissionValue();
			break;
		}
		return postValue;
	}

	/**
	 * This method return the emission probability of all the words identified as
	 * other words that are not PRE and TARGET words
	 * 
	 * @param chaine
	 * @param hmmConcept
	 * @return
	 */
	public static double otherEmissionProbability(String chaine, HMMRules hmmRules) {
		return hmmRules.getOtherObservation_other().getEmissionValue();
	}

}
