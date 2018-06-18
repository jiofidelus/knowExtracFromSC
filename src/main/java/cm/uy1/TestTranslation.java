package cm.uy1;

import java.io.File;

import cm.uy1.helper.Helper;
import cm.uy1.translation2OWL.Term2OWL;

public class TestTranslation {
	
	private static final String TERMSEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/conceptsExtracted";
	
	private static final String TERMSTRANSLATED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/sourceCode2ontov1.owl";
	

	private static final String CONCEPTSEXTRACTED =
			"/home/azanzi/Documents/workspace/AdditionalMaterials/conceptsExtracted";

	public static void main(String[] args) {
//		Translation in formal language
		Helper.writeOntoToFile(TERMSTRANSLATED, 
				Term2OWL.term2OWL(new File(CONCEPTSEXTRACTED)));
		
	}
	
}




