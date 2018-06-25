package cm.uy1;

import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDefinition.HMMRules;
import cm.uy1.modelUse.model.Column;
import cm.uy1.modelUse.model.MostLikelyExplanationConcept;
import cm.uy1.trainning.HMMConceptInit;
import cm.uy1.trainning.HMMConceptObservation;
import cm.uy1.trainning.HMMConceptTransition;
import cm.uy1.trainning.HMMRuleInit;
import cm.uy1.trainning.HMMRuleObservation;
import cm.uy1.trainning.HMMRuleTransition;

public class TestsRulesExtraction {
	


	private static final String DATASET1 =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";
	
	private static final String CONCEPTSEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/conceptsExtracted";
//	/home/azanzi/Documents/workspace/hmm/AdditionalMaterials
	private static final String TESTEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/tests";

	private static final String RULESEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/rulesExtracted";
	

	private static final String DATASET2="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/2-treated_data/";

	private static final String TEST="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/tests/";

	
	///Geoserver
	//Data source
	private static final String GEOSERVER = "/home/azanzi/Documents"
			+ "/workspace/AdditionalMaterials/DataSources/3-Geoserver/1-raw_data";
	
	//Terms extracted
	private static final String RULESEXTRACTED2GEOSERVER = "/home/azanzi/Documents"
			+ "/workspace/AdditionalMaterials/geoserverRules";
	
	public static void main(String[] args) {
		//***********************Train the HMM***************************************
		//Get the data
		List<String> listSouceCode = Helper.getAllDataFromFolder(DATASET1);

		//Create the HMM for rules
		HMMRules hmmRules = new HMMRules();
		hmmRules.init();
		//Initializing the first vector label
		HMMRuleInit.initTransition(hmmRules);
		//Calculating the transition probability
		HMMRuleTransition.preTransition(listSouceCode, hmmRules);
		HMMRuleTransition.targetTransition(listSouceCode, hmmRules);
		HMMRuleTransition.postTransition(listSouceCode, hmmRules);
		HMMRuleTransition.otherTransition(listSouceCode, hmmRules);
		//Calculation of the observation probability
		HMMRuleObservation.preObservation(listSouceCode, hmmRules);
		HMMRuleObservation.targetObservation(listSouceCode, hmmRules);
		HMMRuleObservation.postObservation(listSouceCode, hmmRules);
		HMMRuleObservation.otherObservation(listSouceCode, hmmRules);
		
		System.out.println(hmmRules);
	}

}
