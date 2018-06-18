package cm.uy1.modelUse.model;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDesign.State;
import org.apache.commons.lang3.StringUtils;

/**
 * This class contains methods that are used to calculate the most 
 * likely explanation of the observation. In our case, it will be to 
 * infer the labels of all the source code that is observed based on a 
 * train HMM
 * @author azanzi
 *
 */

public class MostLikelyExplanationConcept {
	
	/**
	 * knowExtracted method takes as input the alphaTable and retrun the most 
	 * likelly source code.
	 * 
	 * Its browse the elements (frames) from the end to the beginning and get the frames 
	 * that contains the most likely source code
	 * @param alphaTable
	 * @return
	 */
	
	public static String knowledgeExtraction(List<Column> alphaTable) {
		String mostLikelyExplanation="";
		
		int alphaTableSize = alphaTable.size()-1;
		Frame frame = new Frame();
		
		//Get the last colum of the alphaTable
		Column lasColumn = alphaTable.get(alphaTable.size()-1);
//		System.out.println("+++++++++++++++++++++++++++++The last column of the table"+lasColumn);
		//Get the last frame which has the greatest probability
		frame = mostGreatestFrameProba(lasColumn);
//		System.out.println("+++++++++++++++++++++++++++The most greater "
//				+ "frame probab : "+frame);
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
		//Extract the knowledge from the above frame which hidden state is TARGET
		String falsePositive[] = {";", "{", "}", 
				"public", "private", "protected", "static", "final"};
		String label="";
		int nbTarget=0, nbFalsePositive=0;
		for (int i = mostLikelyFrames.size()-1; i>=0; i--) {
			if(mostLikelyFrames.get(i).getStateLabel().equals("TARGET")&&
					StringUtils.indexOfAny(mostLikelyFrames.get(i).getObservedLabel(), 
							falsePositive)==-1) {
				nbTarget++;
				label = mostLikelyFrames.get(i).getObservedLabel();
				mostLikelyExplanation+=label+"\n";
//				System.out.println("Position : "+i+" "+label);
			}
			nbFalsePositive = mostLikelyFrames.size()-1-nbTarget;
			System.out.println("**********Number of false positives: "+nbFalsePositive+
					"******************");
		}
		return mostLikelyExplanation;		
	}
	
	/***
	 * This method is used to get the Frame which has the greatest probability
	 * amongst others
	 * @param colum
	 * @return
	 */

	private static Frame mostGreatestFrameProba(Column colum) {
		
		Frame frame = new Frame();
		
		double preProba = colum.getPreFrame().getProbabilityValue();
		double targetProba = colum.getTargetFrame().getProbabilityValue();
		double otherProba = colum.getOtherFrame().getProbabilityValue();

		if(Math.max(Math.max(preProba, targetProba), otherProba)==preProba)
			frame = colum.getPreFrame();

		if(Math.max(Math.max(preProba, targetProba), otherProba)==targetProba)
			frame = colum.getTargetFrame();

		if(Math.max(Math.max(preProba, targetProba),otherProba)==otherProba)
			frame = colum.getOtherFrame();
		return frame;
	}

	/**
	 * This method calculate the alpha start table that is a table that contains the argmax probability
	 * of all the observed source code.
	 * 
	 * Takes one file and calculate the most likely explanation
	 * 
	 * @param hmmConcept
	 * @param listSourceCode
	 * @return
	 */
	public  static List<Column> fillAlphaStartTable4Concepts(HMMConcept hmmConcept, 
			String sourceFile){
		List<Column> alphaTable = new ArrayList<>();
		
		//Get the transition probabilities that will be very helpfull later
		double prePreTransitionValue = hmmConcept.getPrePreTransition().getTransitionValue();
		double preTargetTransitionValue = hmmConcept.getPreTargetTransition().getTransitionValue();
		double preOtherTransitionValue = hmmConcept.getPreOtherTransition().getTransitionValue();
		
		double targetPreTransitionValue = hmmConcept.getTargetPreTransition().getTransitionValue();
		double targetTargetTransitionValue = hmmConcept.getTargetTargetTransition().getTransitionValue();
		double targetOtherTransitionValue = hmmConcept.getTargetOtherTransition().getTransitionValue();
		
		double otherPreTransitonValue = hmmConcept.getOtherPreTransition().getTransitionValue();
		double otherTargetTransitionValue = hmmConcept.getOtherTargetTransition().getTransitionValue();
		double otherOtherTransitionValue = hmmConcept.getOtherOtherTransition().getTransitionValue();
		
		sourceFile = sourceFile.trim().replaceAll(" +", " ");
		String[]tmp=sourceFile.split(" ");

		//Let us initialize the table
		//Creation of the first column
		Column column = new Column();
		//Create the frames of the first column 
		//This can can be simply calculate by hands because 
		//we know that the file always begin with package
		Frame framePRE0 = new Frame(tmp[0], "PRE", 0);
		Frame frameTARGET0 = new Frame(tmp[0], "TARGET", 1.0E300);
		Frame frameOTHER0 = new Frame(tmp[0], "OTHER", 0);

		//Add the first frames to the colums
		column.setPreFrame(framePRE0);
		column.setTargetFrame(frameTARGET0);
		column.setOtherFrame(frameOTHER0);
		//Add the first column to the table
		alphaTable.add(column);
		
		Frame framePRE;
		Frame frameTARGET;
		Frame frameOTHER;
		
		//will be used to carry the temporary data to be added to the table
		Column columnTMP;
		//Frame to be used to carry the temporary data frame before inserting in colum
		Frame frameTMP; 
		Frame frameUsed;

		double preEmission = 0, targetEmission=0, otherEmission=0;
		double calculPre=0, calculTarget=0, calculOTHER=0;
//		System.out.println("************* "+tmp.length);
		
		for (int i = 1; i <tmp.length; i++) {
			
			calculPre=0; calculTarget=0; calculOTHER=0;
			
			tmp[i]=tmp[i].trim();
			
			columnTMP=new Column();
			
			frameTMP=null;
			
			preEmission = 0; targetEmission=0; otherEmission=0;
			
//			System.out.println("The value of tmp : "+tmp[i]);
			
			//Get the last element of the list
			column = alphaTable.get(alphaTable.size()-1);

			//return the column of the last index with the state label PRE. 
			//e.g. alpha(PRE, 2), alpha(TARGET, 2)
			framePRE = column.getPreFrame();
			frameTARGET = column.getTargetFrame();
			frameOTHER = column.getOtherFrame();

			/**
			 * Now, let us calculte for the PRE state
			 */
			//Frame used to calculate the probability
			frameUsed = getMaxFramePre(framePRE, frameTARGET, frameOTHER, 
					prePreTransitionValue, preTargetTransitionValue, preOtherTransitionValue);

////			The value of the probability that is calculated
			calculPre = Math.max(Math.max(prePreTransitionValue*framePRE.getProbabilityValue(),
					preTargetTransitionValue*frameTARGET.getProbabilityValue()), 
					preOtherTransitionValue*frameOTHER.getProbabilityValue());
			
			//Get the emission probability
			if(Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation())) {
				//We call the method which, given this string, return the probability of the 
				//the observed string
				preEmission = preEmissionProbability(tmp[i], hmmConcept);
			}
//			System.out.println("................Pre state Frame used : "+frameUsed.toString());
//			System.out.println("+++++++++++++++++++++++ calcul pre :"+calculPre);
//			System.out.println("+++++++++++++++++++++++ pre emission :"+preEmission);
			
			//Create the new frame for PRE state and add to the colum (argMax times emission proba)
			frameTMP = new Frame(tmp[i], "PRE", calculPre*preEmission, frameUsed);

			columnTMP.setPreFrame(frameTMP);
			
			/**
			 * The same calcultation for the TARGET state
			 */
			frameUsed = getMaxFramePre(framePRE, frameTARGET, frameOTHER, 
					targetPreTransitionValue, targetTargetTransitionValue, 
					targetOtherTransitionValue);

			//The value of the probability which is calculated
			calculTarget = Math.max
					(Math.max(targetPreTransitionValue*framePRE.getProbabilityValue(),
					targetTargetTransitionValue*frameTARGET.getProbabilityValue()),
					targetOtherTransitionValue*frameOTHER.getProbabilityValue());

			//Get the emission probability
			if(Helper.belongs2Array(tmp[i], hmmConcept.getTargetObservation())||
					i-1>=0 && Helper.belongs2Array(tmp[i-1], hmmConcept.getTargetObservation())||
					i-1>=0 && Helper.belongs2Array(tmp[i-1], hmmConcept.getPreObservation())||
					i-2>=0 && Helper.belongs2Array(tmp[i-2], hmmConcept.getPreObservation())) {
				//Return the probability of the observed string
				targetEmission = targetEmissionProbability(tmp[i], hmmConcept);
			}
//			System.out.println("................Target state Frame used : "+frameUsed.toString());
//			System.out.println("+++++++++++++++++++++++ calcul target :"+calculTarget);
//			System.out.println("+++++++++++++++++++++++ target emission :"+targetEmission);
			//Create the new frame for target state and add to the column
			//alpha(target, i)=P(s=public|h=target=.argmax(P(h2=target|h1=pre)*aplha(pre,1)
			frameTMP = new Frame(tmp[i], "TARGET", calculTarget*targetEmission, frameUsed);
			columnTMP.setTargetFrame(frameTMP);

			/**
			 * The same calculation with OTHER
			 */
			frameUsed = getMaxFramePre(framePRE, frameTARGET, frameOTHER, 
					otherPreTransitonValue, otherTargetTransitionValue, 
					otherOtherTransitionValue);
			
			//The value of the probability which is calculated
			calculOTHER = Math.max
					(Math.max(otherPreTransitonValue*framePRE.getProbabilityValue(), 
							otherTargetTransitionValue*frameTARGET.getProbabilityValue()), 
							otherOtherTransitionValue*frameOTHER.getProbabilityValue());
			
			//Get the emission probability
			if(Helper.belongs2Array(tmp[i], hmmConcept.getOtherObservation())||
					i-1>0 && Helper.belongs2Array(tmp[i-1], hmmConcept.getOtherObservation())&&
						!Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation())||
						//if(a>b){...} -> i = a
					i-2>0 && !Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation())&&
					!Helper.belongs2Array(tmp[i], hmmConcept.getTargetObservation())&&
					!Helper.belongs2Array(tmp[i-1], hmmConcept.getPreObservation())&&
					!Helper.belongs2Array(tmp[i-2], hmmConcept.getPreObservation()))
			{
				//return the probability of the observed string given that it is in the OTHER set
				otherEmission=otherEmissionProbability(tmp[i], hmmConcept);
			}
//			System.out.println("................Other state Frame used : "+frameUsed.toString());
//			System.out.println("+++++++++++++++++++++++ calcul other :"+calculOTHER);
//			System.out.println("+++++++++++++++++++++++ other emission :"+otherEmission);
			//Create the new frame for the OTHER state and add to the column
			frameTMP = new Frame(tmp[i], "OTHER", calculOTHER*otherEmission, frameUsed);
//			System.out.println("$$$$$$$$$$$$$$$$$ "+(double)calculOTHER*(double)otherEmission);
//			System.out.println("------------------Other state Frame : "+frameTMP.toString());
			columnTMP.setOtherFrame(frameTMP);
//			System.out.println("****************The column that is inserted : "+columnTMP);
			//The method that add the column to the table

			alphaTable.add(addColumnToAlphaTable(columnTMP));
		}
//		System.out.println("The last value : "+tmp[116]);
//		System.out.println("-------------------Calcul PRE and company-------------------------");
//		System.out.println("+++++++++++++++++++++++ calcul pre :"+calculPre);
//		System.out.println("+++++++++++++++++++++++ pre emission :"+preEmission);
//
//		System.out.println("+++++++++++++++++++++++ calcul target :"+calculTarget);
//		System.out.println("+++++++++++++++++++++++ target emission :"+targetEmission);
//		
//		System.out.println("+++++++++++++++++++++++ calcul other :"+calculOTHER);
//		System.out.println("+++++++++++++++++++++++ other emission :"+otherEmission);
//
//		System.out.println("-------------------Calcul PRE and company-------------------------");
//		System.out.println("Print the last column: ");
//		System.out.println(alphaTable.get(0));
//		System.out.println(alphaTable.get(1));
//		System.out.println(alphaTable.get(2));
//		System.out.println(alphaTable.get(3));
//		System.out.println("--------------------------------------------");
//		System.out.println(alphaTable.get(alphaTable.size()-4));
//		System.out.println(alphaTable.get(alphaTable.size()-3));
//		System.out.println(alphaTable.get(alphaTable.size()-2));
//		System.out.println(alphaTable.get(alphaTable.size()-1));

		return alphaTable;
	}
	
	/**
	 * To avoid zero probability, this method test the values of the probabilities. If these values are 
	 * too close to zero, it multiply by a big number
	 * @param columnTMP
	 * @param alphaTable
	 */
	
	private static Column addColumnToAlphaTable(Column columnTMP) {
		double preProba = columnTMP.getPreFrame().getProbabilityValue();
		double targetProba = columnTMP.getTargetFrame().getProbabilityValue();
		double otherProba = columnTMP.getOtherFrame().getProbabilityValue();
		
		if(Math.max(Math.max(columnTMP.getPreFrame().getProbabilityValue(), 
				columnTMP.getTargetFrame().getProbabilityValue()), 
				columnTMP.getOtherFrame().getProbabilityValue())<10E-200) {
			preProba = preProba*10E200;
			targetProba = targetProba*10E200;
			otherProba=otherProba*10E200;
		}
		columnTMP.getPreFrame().setProbabilityValue(preProba);
		columnTMP.getTargetFrame().setProbabilityValue(targetProba);
		columnTMP.getOtherFrame().setProbabilityValue(otherProba);
		
		return columnTMP;
	}

	public  static Frame getMaxFramePre(Frame framePRE, Frame frameTARGET, Frame frameOther,
			double prePre, double preTARGET, double preOTHER) {
		Frame frameToBeReturn=null;
		
		double preValue = prePre*framePRE.getProbabilityValue();
		double targetValue = preTARGET*frameTARGET.getProbabilityValue();
		double otherValue = preOTHER*frameOther.getProbabilityValue();

		if(preValue==Math.max(Math.max(preValue, targetValue), otherValue))
			frameToBeReturn = framePRE;

		if(targetValue==Math.max(Math.max(preValue, targetValue), otherValue))
			frameToBeReturn = frameTARGET;

		if(otherValue==Math.max(Math.max(preValue, targetValue), otherValue))
			frameToBeReturn = frameOther;
				
		return frameToBeReturn;
	}
	
	
	

//These methods are used to return the probability values of the elements that is observed
/**
 * This method determine the probability that the state PRE emit the word passed as parameter
 * @param chaine
 * @param hmmConcept
 * @return
 */
	public  static double preEmissionProbability(String chaine, HMMConcept hmmConcept) {
		double preValue=0;

		//The string is that is observed can be labelled has PRE
			switch (chaine) {
			case "public": preValue=hmmConcept.
					getPreObservation_public().getEmissionValue();
				break;
			case "private": preValue=hmmConcept.
					getPreObservation_private().getEmissionValue();
				break;
			case "protected": preValue=hmmConcept.
					getPreObservation_protected().getEmissionValue();
				break;

			case "static": preValue=hmmConcept.
					getPreObservation_static().getEmissionValue();
				break;

			case "final": preValue=hmmConcept.
					getPreObservation_final().getEmissionValue();
				break;

			default:
				break;
			}
		
		return preValue;
	}
	
	/**
	 * This method return the value of the emission probability for a given word in the source code
	 * identified as TARGET
	 * @param chaine
	 * @param hmmConcept
	 * @return
	 */
	
	public  static double targetEmissionProbability(String chaine, HMMConcept hmmConcept) {
		double targetValue=0;
		
		switch (chaine) {
		case "package": targetValue=hmmConcept.
				getTargetObservation_package().getEmissionValue();
//				System.out.println("the calcultarget the target is package");
			break;

		case "class": targetValue=hmmConcept.
				getTargetObservation_class().getEmissionValue();
//			System.out.println("the calcultarget the target is class");
			break;

		case "interface": targetValue=hmmConcept.
				getTargetObservation_interface().getEmissionValue();
//				System.out.println("the calcultarget the target is interface");
			break;

		case "extends": targetValue=hmmConcept.
				getTargetObservation_extends().getEmissionValue();
//				System.out.println("the calcultarget the target is extends");
			break;

		case "implements": targetValue=hmmConcept.
				getTargetObservation_implements().getEmissionValue();
//		System.out.println("the calcultarget the target is implements");
			break;

		case "abstract": targetValue=hmmConcept.
				getTargetObservation_abstract().getEmissionValue();
//		System.out.println("the calcultarget the target is abstract");
			break;

		case "enum": targetValue=hmmConcept.
				getTargetObservation_enum().getEmissionValue();
//		System.out.println("the calcultarget the target is enum");
			break;
//In this case, the other TARGET that are not in the list are observed
		default: targetValue=hmmConcept.getTargetObservation_other().getEmissionValue();
//		System.out.println("the calcultarget the target is other");
			break;
		}
		
		return targetValue;
	}
	/**
	 * This method return the emission probability of all the words identified as other words
	 * that are not PRE and TARGET words
	 * @param chaine
	 * @param hmmConcept
	 * @return
	 */
	public  static double otherEmissionProbability(String chaine, HMMConcept hmmConcept) {
		double otherValue=0;
		
		switch (chaine) {
		case ";": otherValue= hmmConcept.getOtherObservation_SemiCol().getEmissionValue();
		break;
		case "}": otherValue= hmmConcept.getOtherObservation_closeCurBrass().getEmissionValue();
		break;
		case "{": otherValue= hmmConcept.getOtherObservation_openCurlBrass().getEmissionValue();
		break;
//In the default case, we have identified a word that is not in the above list
		//These words are then treated here
		default: otherValue = hmmConcept.getOtherObservation_other().getEmissionValue();
			break;
		}
		
		return otherValue;
	}
	
}











