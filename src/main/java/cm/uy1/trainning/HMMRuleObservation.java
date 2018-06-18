package cm.uy1.trainning;

import java.util.List;

import javax.print.DocFlavor.STRING;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMRules;
import cm.uy1.modelDesign.Observation;

/**
 * This class is used to calculte the observation vectors of 
 * each hidden states
 * @author azanzi
 *
 */

public class HMMRuleObservation {

	//Calculate the PRE observation vector
	
	public static void preObservation(List<String> listSourceCoce, HMMRules hmmRules) {
		double nbSemiCol=1, nbClosedCurBrasses=1, nbOpenCurBrassess=1;
		double nbPre=3;
		String[] tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		
		for (String doc : listSourceCoce) {
			tmp=doc.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of times that we see every element in the source code and that elements 
				//are the PRE elements if the current element is in the PRE state and the next one a TARGET,
				//the element is a PRE
				if(i+1<tmp.length&&
						Helper.belongs2Array(tmp[i], PRE)&&
						Helper.belongs2Array(tmp[i+1], TARGET)) {
					nbPre++;
					switch (tmp[i]) {
					case ";":
						nbSemiCol++;
						break;
					case "}":
						nbClosedCurBrasses++;
						break;
					case "{":
						nbOpenCurBrassess++;
						break;
					default: break;
					}
				}
			}
		}
		Observation semiColonObservation = hmmRules.getPreObservation_SemiCol();
		Observation openBrassessObservation = hmmRules.getPreObservation_openCurlBrass();
		Observation closedBrassessObservation = hmmRules.getPreObservation_closeCurBrass();
		
		semiColonObservation.setEmissionValue(nbSemiCol/nbPre);
		openBrassessObservation.setEmissionValue(nbOpenCurBrassess/nbPre);
		closedBrassessObservation.setEmissionValue(nbClosedCurBrasses/nbPre);
		
		hmmRules.setPreObservation_SemiCol(semiColonObservation);
		hmmRules.setPreObservation_openCurlBrass(openBrassessObservation);
		hmmRules.setPreObservation_closeCurBrass(closedBrassessObservation);
	}
	
	//Calculate the TARGET observation vector

	public static void targetObservation(List<String> listSourceCoce, HMMRules hmmRules) {
		double nbTarget=4;
		double nbIf=1, nbElse=1, nbSwitch=1, nbOther=1;
		String[]tmp;
		List<String> PRE = hmmRules.getPreObservation();
		List<String> TARGET = hmmRules.getTargetObservation();
		List<String> POST = hmmRules.getPostObservation();
		
		for (String doc : listSourceCoce) {
			tmp=doc.split(" ");
			
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of times that we can see every elements in the file
				//If the element at the "i" position is a TARGET, then loop to the end 
				//of the TARGET that is the POST element
				if(Helper.belongs2Array(tmp[i], TARGET)) {
					nbTarget++;
					while (!Helper.belongs2Array(tmp[i], POST)) { //We are in a condition and we continue the loop
						i++;
						nbTarget++;
						switch (tmp[i]) {
						case "if":
							nbIf++;
							break;
						case "else":
							nbElse++;
							break;
						case "switch":
							nbSwitch++;
							break;
						default: 
							nbOther++;
							break;
						}
					}
				}
			}
		}
		Observation ifObservation = hmmRules.getTargetObservation_if();
		Observation elseObservation = hmmRules.getTargetObservation_else();
		Observation switchObservation = hmmRules.getTargetObservation_switch();
		Observation otherObservation = hmmRules.getTargetObservation_other();
		
		ifObservation.setEmissionValue(nbIf/nbTarget);
		elseObservation.setEmissionValue(nbElse/nbTarget);
		switchObservation.setEmissionValue(nbSwitch/nbTarget);
		otherObservation.setEmissionValue(nbOther/nbTarget);
		
		hmmRules.setTargetObservation_if(ifObservation);
		hmmRules.setTargetObservation_else(elseObservation);
		hmmRules.setTargetObservation_switch(switchObservation);
		hmmRules.setTargetObservation_other(otherObservation);
	}
	
	//Calculate the POST observation vector
	public static void postObservation(List<String> listSourceCoce, HMMRules hmmRules) {
		//Every time, POST emit juste one element '}', which is the end of the 
		//condition instruction block
		Observation brassessObservation = hmmRules.getPostObservation_brassess();
		brassessObservation.setEmissionValue(1);
		hmmRules.setPostObservation_brassess(brassessObservation);
	}
	
	//Calculate the OTHER observation vector
		public static void otherObservation(List<String> listSourceCoce, HMMRules hmmRules) {
			//The OTHER hidden state always emit an observation which we called other
			//because they cannot be list
			Observation otherObservation = hmmRules.getOtherObservation_other();
			otherObservation.setEmissionValue(1);
			hmmRules.setOtherObservation_other(otherObservation);
		}


}




