package cm.uy1.trainning;

import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDesign.Transition;

/**
 * 
 * @author azanzi In this class, the transition model is calculated by
 *         calculating the PRE, POST, TARGET, OTHER vectors
 */
public class HMMConceptTransition {
	
	/**
	 * @param listDocs
	 * @return
	 * Calculate the transition from the PRE state.
	 * Takes as input the HMM concept and a list of documents
	 */
	
	public static void preTransition(List<String> listSouceCode, HMMConcept hmmConcept) {
//		System.out.println("**************************Enter in PRE transition method*************");
		//Initialization
		double nbPre = 3;
		double nbPrePre = 1;
		double nbPreTarget = 1;
		double nbPreOther = 1;
		List<String> PRE = hmmConcept.getPreObservation();
		String[] tmp;
		
		for (String doc : listSouceCode) {
//			System.out.println(doc);
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Number of PRE in the document
				if(Helper.belongs2Array(tmp[i], PRE)) {
					nbPre++;
				}
				//Number of times that a PRE is follows by another PRE
				if(i<tmp.length && 
						Helper.belongs2Array(tmp[i], PRE) && 
						Helper.belongs2Array(tmp[i+1], PRE)) {
					nbPrePre++;
				}
				//Number of times that a PRE is follows by a TARGET
				if(i<tmp.length && 
						Helper.belongs2Array(tmp[i], PRE) && 
						!Helper.belongs2Array(tmp[i+1], PRE)) {
					nbPreTarget++;
				}
		}
	}
		Transition prePre = hmmConcept.getPrePreTransition();
		Transition preTarget = hmmConcept.getPreTargetTransition();
		Transition preOther = hmmConcept.getPreOtherTransition();

		prePre.setTransitionValue(nbPrePre/nbPre);
		preTarget.setTransitionValue(nbPreTarget/nbPre);
		preOther.setTransitionValue(nbPreOther/nbPre);
		
		hmmConcept.setPrePreTransition(prePre);
		hmmConcept.setPreTargetTransition(preTarget);
		hmmConcept.setPreOtherTransition(preOther);
	}

/**
 * 
 * @param listDocs
 * @return
 * Calculate the transition vector from TARGET state to the other states
 */
	
	public static void targetTransition(List<String>listSourceCode, HMMConcept hmmConcept) {
//		System.out.println("**************************Enter in TARGET transition method*************");
		double nbTarget=3;
		double nbTargetPre=1;
		double nbTargetTarget=1;
		double nbTargetOther=1;
		String[]tmp;
		List<String> PRE = hmmConcept.getPreObservation();
		List<String> TARGET = hmmConcept.getTargetObservation();
		List<String> OTHER = hmmConcept.getOtherObservation();
		
		for (String doc : listSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of TARGETS
				if(i+1<tmp.length &&
						Helper.belongs2Array(tmp[i], PRE)&&
						!Helper.belongs2Array(tmp[i+1], PRE)) {
					//The two next words are targets (e.g. private int age;)
					nbTarget+=2;
					nbTargetTarget+=2; //One target follows another one (e.g. int age;)
//					i+=2;
				}
				if(i+1<tmp.length&&
						Helper.belongs2Array(tmp[i], TARGET)) {
					nbTarget++;
					if(Helper.belongs2Array(tmp[i+1], TARGET)) {
						nbTarget++;
						nbTargetTarget++;
//						i++;
					}
				}
				if(i-3>=0 && Helper.belongs2Array(tmp[i], OTHER)&&
						Helper.belongs2Array(tmp[i-3], hmmConcept.getPreObservation())) {
					nbTargetOther++;
				}	
		
	}
			
	}
			
//			System.out.println("++++++++++nb target "+nbTarget);
//			System.out.println("++++++++++nb target target "+nbTargetTarget);
//			System.out.println("+++++++++++nb target other "+nbTargetOther);

			Transition targetPre = hmmConcept.getTargetPreTransition();
			Transition targetTarget = hmmConcept.getTargetTargetTransition();
			Transition targetOther = hmmConcept.getTargetOtherTransition();
			
			targetPre.setTransitionValue(nbTargetPre/nbTarget);
			targetTarget.setTransitionValue(nbTargetTarget/nbTarget);
			targetOther.setTransitionValue(nbTargetOther/nbTarget);
			
			hmmConcept.setTargetPreTransition(targetPre);
			hmmConcept.setTargetTargetTransition(targetTarget);
			hmmConcept.setTargetOtherTransition(targetOther);
		}
	
	/**
	 * 
	 * @param listDocs
	 * @return
	 * 
	 * Calculate the transition vector from the TARGET state to OTHER state 
	 */
	
	public static void otherTransition(List<String>listSourceCode, HMMConcept hmmConcept) {
//		System.out.println("**************************Enter in OTHER transition method*************");
		double nbOther=3;
		double nbOtherPre=1;
		double nbOtherTarget=1;
		double nbOtherOther=1;
		String[] tmp;

		List<String> PRE = hmmConcept.getPreObservation();
		List<String> OTHER = hmmConcept.getOtherObservation();
		
		for (String doc : listSourceCode) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				if(Helper.belongs2Array(tmp[i], OTHER)) {
					nbOther++;
					if(i+1<tmp.length && 
							Helper.belongs2Array(tmp[i+1], PRE)) {
						nbOtherPre++;
					}
					if(i+1<tmp.length && 
							!Helper.belongs2Array(tmp[i], PRE)) {
						nbOtherOther++;
					}
				}
			}
	}

		Transition otherPre = hmmConcept.getOtherPreTransition();
		Transition otherTarget = hmmConcept.getOtherTargetTransition();
		Transition otherOther = hmmConcept.getOtherOtherTransition();
		
		otherPre.setTransitionValue(nbOtherPre/nbOther);
		otherTarget.setTransitionValue(nbOtherTarget/nbOther);
		otherOther.setTransitionValue(nbOtherOther/nbOther);
		
		hmmConcept.setOtherPreTransition(otherPre);
		hmmConcept.setOtherTargetTransition(otherTarget);
		hmmConcept.setOtherOtherTransition(otherOther);
	}		
}