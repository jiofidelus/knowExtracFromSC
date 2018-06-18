package cm.uy1.trainning;

import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDefinition.HMMRules;
import cm.uy1.modelDesign.Transition;

/**
 * Used to calculate the PRE, TARGET, POST and OTHER values
 * @author azanzi
 *
 */

public class HMMRuleTransition {

	public static void preTransition(List<String>lisSourceCode, HMMRules hmmRules) {
		//Every times, the PRE state is directly follows by the TARGET state
		//Then, we have the transition preTarget which is always 1
		double nbPre=4.0, nbPrePre=1, nbPreTarget=1.0, nbPrePost=1.0, nbPreOther=1.0;

		String[]tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		List<String> POST = hmmRules.getPostObservation();

		for (String doc : lisSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of PRE
				//If the element at the position i is a TARGET, then, all the elements between
				//the element "if" and the caracter "}" are targets
				if(i+1<tmp.length && Helper.belongs2Array(tmp[i], PRE)&&
						Helper.belongs2Array(tmp[i+1], TARGET))
					nbPreTarget++;
			}
		}
		nbPre+=nbPreTarget;
		
		Transition prePre = hmmRules.getPrePreTransition();
		Transition preTarget = hmmRules.getPreTargetTransition();
		Transition prePost = hmmRules.getPrePostTransition();
		Transition preOther = hmmRules.getPreOtherTransition();
		
		prePre.setTransitionValue(nbPrePre/nbPre);
		preTarget.setTransitionValue(nbPreTarget/nbPre);
		prePost.setTransitionValue(nbPrePost/nbPre);
		preOther.setTransitionValue(nbPreOther/nbPre);
		
		hmmRules.setPrePreTransition(prePre);
		hmmRules.setPreTargetTransition(preTarget);
		hmmRules.setPrePostTransition(prePost);
		hmmRules.setPreOtherTransition(preOther);
	}
	
	/**
	 * Calculate the transition vector from TARGET transition to others transitions
	 * */
	
	public static void targetTransition(List<String> listSourceCode, HMMRules hmmRules) {
		//Initialisation
		double nbTarget=4;
		double nbTargetPre=1, nbTargetTarget=1, nbTargetPost=1, nbTargetOther=1;
		String[]tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		List<String> POST = hmmRules.getPostObservation();
		
		for (String doc : listSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of TARGET
				//If the element at the position i is a TARGET, then, all the elements between
				//the element "if" and the caracter "}" are targets
				if(Helper.belongs2Array(tmp[i], TARGET)) {
					nbTarget++;
					while (i<tmp.length&&
							!Helper.belongs2Array(tmp[i], POST)) {
						i++;
						nbTarget++;
						nbTargetTarget++;
					}
					if(Helper.belongs2Array(tmp[i], POST))
						nbTargetPost++;
				}
			}
		}
		Transition targetPre = hmmRules.getTargetPreTransition();
		Transition targetTarget = hmmRules.getTargetTargetTransition();
		Transition targetPost = hmmRules.getTargetPostTransition();
		Transition targetOther = hmmRules.getTargetOtherTransition();
		
		targetPre.setTransitionValue(nbTargetPre/nbTarget);
		targetTarget.setTransitionValue(nbTargetTarget/nbTarget);
		targetPost.setTransitionValue(nbTargetPost/nbTarget);
		targetOther.setTransitionValue(nbTargetOther/nbTarget);
		
		hmmRules.setTargetPreTransition(targetPre);
		hmmRules.setTargetTargetTransition(targetTarget);
		hmmRules.setTargetPostTransition(targetPost);
		hmmRules.setTargetOtherTransition(targetOther);
	}
	
	public static void postTransition(List<String> listSourceCode, HMMRules hmmRules) {

		//Every times, the POST state is directly follows by the OTHER state
		//Then, we have the transition postOther which is always 

		double nbPost=4.0, nbPostPre=1, nbPostTarget=1.0, nbPostPost=1.0, nbPostOther=1.0;

		String[]tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		List<String> POST = hmmRules.getPostObservation();
		
		for (String doc : listSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				
				if(Helper.belongs2Array(tmp[i], POST)){
					//A post is precede by a TARGET
					int k=1;
					while (i-k>0 && !Helper.belongs2Array(tmp[i-k], TARGET)&&
							!tmp[i-k].equals("{"))
						k++;
					if(i-k>0 && Helper.belongs2Array(tmp[i-k], TARGET)) {
						nbPost++;
//						nbPostTarget++;
						if(i+1<tmp.length && 
							!Helper.belongs2Array(tmp[i+1], hmmRules.getTargetObservation())) {
							nbPostOther++;
						}
					}
					if(i-k>0 && tmp[i-k].equals("{")) {
						while((i-k>0 && !Helper.belongs2Array(tmp[i-k], TARGET)) || 
								(i-k>0 && !Helper.belongs2Array(tmp[i-k], PRE)))
							k++;
						if(i-k>0 && Helper.belongs2Array(tmp[i-k], TARGET)) {
							nbPost++;
							if(i+1<tmp.length && 
								!Helper.belongs2Array(tmp[i+1], hmmRules.getTargetObservation())) {
								nbPostOther++;
							}
						}
					}
				}
			}
		}
		nbPost+=nbPostOther;
		
		Transition postPre = hmmRules.getPostPreTransition();
		Transition postTarget = hmmRules.getPostTargetTransition();
		Transition postPost = hmmRules.getPostPostTransition();
		Transition postOther = hmmRules.getPostOtherTransition();
		
		postPre.setTransitionValue(nbPostPre/nbPost);
		postTarget.setTransitionValue(nbPostTarget/nbPost);
		postPost.setTransitionValue(nbPostPost/nbPost);
		postOther.setTransitionValue(nbPostOther/nbPost);
		
		hmmRules.setPostPreTransition(postPre);
		hmmRules.setPostTargetTransition(postTarget);
		hmmRules.setPostPostTransition(postPost);
		hmmRules.setPostOtherTransition(postOther);
	}
	
	/**
	 * Calculate the transition vector from the TARGET state to OTHER state
	 * */
	public static void otherTransition(List<String> listSourceCode, HMMRules hmmRules) {
		double nbOther=4;
		double nbOtherPre=1, nbOtherTarget=1, nbOtherPost=1, nbOtherOther=1;
		String[] tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		List<String> POST = hmmRules.getPostObservation();
		
		for (String doc : listSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//If the element at position i is not a TARGET, a PRE and a POST, 
				//then, it is a OTHER
				if(!Helper.belongs2Array(tmp[i], PRE)&& //It is not a PRE
						!Helper.belongs2Array(tmp[i], TARGET)&& //It is not a TARGET
						!Helper.belongs2Array(tmp[i], POST)) { //It is  not a POST -> it is a OTHER
					nbOther++;
					//Let us see if this OTHER is followed by a PRE or by another OTHER
					if(i+2<tmp.length&&
							Helper.belongs2Array(tmp[i+1], PRE)&&
							Helper.belongs2Array(tmp[i+2], TARGET)) {//The element which followed the OTHER is a PRE
						nbOtherPre++;
						//Advance the value of i to the end of the TARGET. The end of the condition
						while (i<tmp.length && 
								Helper.belongs2Array(tmp[i], POST)) { //We are still in a condition
							i++;
						}
					}
					else { //The element which followed the OTHER is another OTHER element
						while (i<tmp.length)
							if(i+2<tmp.length &&
								Helper.belongs2Array(tmp[i+1], PRE)&&
								Helper.belongs2Array(tmp[i+2], TARGET)) {
								nbOtherPre++;
								i++;
							}else {
								i++;
								nbOther++;
								nbOtherOther++;
						}
					}
				}
			}
		}
		
		Transition otherPre = hmmRules.getOtherPreTransition();
		Transition otherTarget = hmmRules.getOtherTargerTransition();
		Transition otherPost = hmmRules.getOtherPostTransition();
		Transition otherOther = hmmRules.getOtherOtherTransition();
		
		otherPre.setTransitionValue(nbOtherPre/nbOther);
		otherTarget.setTransitionValue(nbOtherTarget/nbOther);
		otherPost.setTransitionValue(nbOtherPost/nbOther);
		otherOther.setTransitionValue(nbOtherOther/nbOther);
		
		hmmRules.setOtherPreTransition(otherPre);
		hmmRules.setOtherTargerTransition(otherTarget);
		hmmRules.setOtherPostTransition(otherPost);
		hmmRules.setOtherOtherTransition(otherOther);
	}
	
}
