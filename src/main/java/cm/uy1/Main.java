package cm.uy1;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.trainning.HMMConceptInit;

public class Main {

	public static final String TRAININDATA = "/home/azanzi/Documents/workspace/"
			+ "knowExtracFromSC/experiments/DataSources/1-TrainDS/1-raw_data";
	
	public static final String EPICAMDATA = 
			"/home/azanzi/Documents/workspace/knowExtracFromSC/experiments/DataSources/2-TestDS";
	
	private static final String GEOSERVERDATA=
			"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";

	private static final String GEOSERVER = "/home/azanzi/Documents/workspace/AdditionalMaterials"
			+ "/DataSources/3-Geoserver/geoserver-master";
	
	
	public static void main(String[] args) {
		/**
		 * Counting the number of files and instructions of a project.
		 * To count for a specific project, uncomment the specify code
		 */
		/**
		 * Training data
		 */
//		long nbTrainingFiles = Helper.getNBFiles(TRAININDATA);
//
//		int nbTrainingInstructions = Helper.getNBInstructions(TRAININDATA);
//		
//		System.out.println("The number of trainig files are: "+nbTrainingFiles+"\n"+
//				"The number of training instructions are : "+nbTrainingInstructions);

		long nbFiles = Helper.getNBFiles(GEOSERVER);

		int nbInstructions = Helper.getNBInstructions(GEOSERVER);
		
		System.out.println("The number of files are: "+nbFiles+"\n"+
				"The number of instructions are : "+nbInstructions);
		
	}
	
	
	

}
