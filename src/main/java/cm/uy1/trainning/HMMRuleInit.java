package cm.uy1.trainning;

import java.util.List;

import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDefinition.HMMRules;
import cm.uy1.modelDesign.Transition;

/**
 * This class calculate the initial probability
 * @author azanzi
 *
 */
public class HMMRuleInit {

	public static void initTransition (HMMRules hmmRules) {
		//After removing the import and comments, the file will every time begin with 
		//a other. Then, the initial transition will be 
		List<Transition> initialTransition = hmmRules.getInitialTransition();
		for (Transition transition : initialTransition) {
			if(transition.getEndState().getLabel().equals("OTHER"))
				transition.setTransitionValue(1);
		}
	}
}

