package cm.uy1;

import java.io.File;
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
import cm.uy1.translation2OWL.Term2OWL;

public class TestTrainingConcepts {

	private static final String DATASET1 =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";

	private static final String TERMSTRANSLATED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/sourceCode2onto.owl";
	
	private static final String CONCEPTSEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/conceptsExtracted";
	
	private static final String DATASET2="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/2-treated_data/";

	private static final String TEST="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/tests/";


	
	///Geoserver
	//Data source
	private static final String GEOSERVER = "/home/azanzi/Documents"
			+ "/workspace/AdditionalMaterials/DataSources/3-Geoserver/2-treated_data/";
	
	//Terms extracted
	private static final String TERMSEXTRACTED2GEOSERVER = "/home/azanzi/Documents"
			+ "/workspace/AdditionalMaterials/geoserverTerms";
	
	public static void main(String[] args) {
		//***********************Train the HMM***************************************
		//Get the data
		List<String> listSouceCode = Helper.getAllDataFromFolder(DATASET1);

		//Create the HMM for concepts
		HMMConcept hmmConcept = new HMMConcept();
		hmmConcept.init();
		HMMConceptInit.initTransition(hmmConcept);

		//Now, let us test the training phase
		//Transition probabilities
		HMMConceptTransition.
			preTransition(listSouceCode, hmmConcept);
		HMMConceptTransition.
			targetTransition(listSouceCode, hmmConcept);
		HMMConceptTransition.
			otherTransition(listSouceCode, hmmConcept);
//		//Observations probabilities
		HMMConceptObservation.
			preObservation(listSouceCode, hmmConcept);
		HMMConceptObservation.
			targetObservation(listSouceCode, hmmConcept);
		HMMConceptObservation.
			otherObservation(listSouceCode, hmmConcept);
		
//		System.out.println(hmmConcept);

//		//*************************END of the HMM training***************************************

		///To extract knowledge, just uncomment the corresponding source code
		
		/**
		 * EPICAM Knowledge extraction
//		 */
//		//Knowledge extraction
//		List<String> testedSourceCode = Helper.getAllDataFromFolder(DATASET2);
//		List<Column> alphaTable;
//		
//		for (String sourceFile : testedSourceCode) {
//			alphaTable = MostLikelyExplanationConcept.
//					fillAlphaStartTable4Concepts(hmmConcept, sourceFile);
//
//			String extracted = MostLikelyExplanationConcept.knowledgeExtraction(alphaTable);
////			Writing to an OWL file
//			Helper.writeDataToFile(CONCEPTSEXTRACTED, extracted);
//			//The number of false positives and the number of target
//			System.out.println(MostLikelyExplanationConcept.nbFalsePositive+"\n and nb target: "+
//			MostLikelyExplanationConcept.nbTarget);
//			
////			System.out.println(Term2OWL.term2OWL(new File(CONCEPTSEXTRACTED)));
//
//		}

		/**
		 * Geoserver knowledge extraction
		 */

		List<String> testedSourceCode = Helper.getAllDataFromFolder(GEOSERVER);
		List<Column> alphaTable;
		
		for (String sourceFile : testedSourceCode) {
			alphaTable = MostLikelyExplanationConcept.
					fillAlphaStartTable4Concepts(hmmConcept, sourceFile);

			String extracted = MostLikelyExplanationConcept.knowledgeExtraction(alphaTable);
//			Writing to an OWL file
			Helper.writeDataToFile(TERMSEXTRACTED2GEOSERVER, extracted);
			//The number of false positives and the number of target
			
//			System.out.println(Term2OWL.term2OWL(new File(CONCEPTSEXTRACTED)));

	}
		System.out.println(MostLikelyExplanationConcept.nbFalsePositive+"\n and nb target: "+
		MostLikelyExplanationConcept.nbTarget);
	}

}
