package cm.uy1;

import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelUse.model.Column;
import cm.uy1.modelUse.model.MostLikelyExplanationConcept;
import cm.uy1.trainning.HMMConceptInit;
import cm.uy1.trainning.HMMConceptObservation;
import cm.uy1.trainning.HMMConceptTransition;

public class TestTermsExtraction {

	private static final String DATASET1 =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";
	
	private static final String CONCEPTSEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/conceptsExtracted";

	private static final String TESTEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/tests";

	private static final String DATASET2="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/2-treated_data/";

	private static final String TEST="/home/azanzi/Documents/workspace/AdditionalMaterials/"
			+ "/DataSources/2-TestDS/tests/";

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

		//Knowledge extraction
		List<String> testedSourceCode = 
				Helper.getAllDataFromFolder(TEST);
		List<Column> alphaTable;

		for (String sourceFile : testedSourceCode) {
			alphaTable = MostLikelyExplanationConcept.
					fillAlphaStartTable4Concepts(hmmConcept, sourceFile);
			for (Column column : alphaTable) {
//				Helper.writeDataToFile(CONCEPTSEXTRACTED, column.toString());
			
			
			String extracted = MostLikelyExplanationConcept.knowledgeExtraction(alphaTable);
			
			System.out.println(extracted);
//			Translation in formal language
//			Writing in the OWL file
			Helper.writeDataToFile(CONCEPTSEXTRACTED, extracted);

		}
		}
	}
}
