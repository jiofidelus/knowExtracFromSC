package cm.uy1.trainning;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDesign.Transition;

public class HMMConceptInit {
	
	/**
	 * Calculate the initial probability
	 */
	public static void initTransition(HMMConcept hmmConcept) {
		//After the step where the comments and import libraries are removed, the file will 
		//every time begin by a target which is package. Then, return the following transition
		List<Transition> initTransition=hmmConcept.getInitialTransition();
		for (Transition transition : initTransition) {
			if (transition.getEndState().getLabel().equals("TARGET")) {
				transition.setTransitionValue(1);
			}
		}
	}
}
