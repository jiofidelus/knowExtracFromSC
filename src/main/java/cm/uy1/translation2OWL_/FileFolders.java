package cm.uy1.translation2OWL_;
//package supports;
//
//import java.io.File;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import helpers.DataCollection;
//
//public class FileFolders {
//	
//	private static int nbFichier = 0;
//	private static int nbConcepts = 0;
//	
//	private static DataCollection collecte;
//	
//	private static String data="";
//	
//	
//	private static Pattern pattern;
//    private static Matcher matcher;
//    
//    private static String filePath="";
//    private static final File folder = new File("/home/these/these/workspace_these/hmm/ticket-agency-jpa");
//	
//	//Expressions régulières pour extraire les éléments non désirables
//	private static String exp = "import.|package.|//.|/*.*/|@.";
//	
////	private String exp = "^import.|^package.|^(//[a-z]*[A-Z]*[1-9]*)|^(/*[a-z]*[A-Z]*[1-9]*)";
//	
//	//Pour le helper pour le moment. Sera déplacé par la suite dans le bon fichier
//
//	private static String[] PRE = {"public", "private", "protected"};
//	//Notre concept et les éléments le constituant
//	private  static String[] TARGET = {"class", "}", "{", ";"};
//	//La collecte de données va permettre d'obtenir les chaînes séparées par les espaces
//	private static  String[] POST = {};
//	private static  String[] OTHER = {};
//	//Une classe commence par le type d'accès suivi par le mot clé class
//	//Ainsi, dans notre cas, 
//	
//	//Les probabilités de départ
//	private static  double startProba[] = {1/3, 0, 0, 0};
//	
//	//Les probabilités de transition
//	private static  double transitionMatrix[][];
//
//	//emission probability
//	private static  double emissionProbabilities[][] = new double[4][];
//	
//	//Entrainner mon modèle sur mon jeu de données
//	//Je dois trouver les différentes matrices de probabilités
//	
//	
//	
//
//
//	
//	//Tester si une chaine apparait dans un tableau
//	public static boolean belongs2Array(String chaineAtester, String[]tableau){
//		boolean belongs = false;
//		for (int i = 0; i < tableau.length; i++) {
//			if(chaineAtester.equals(tableau[i].trim())) belongs = true;
//		}
//		return belongs;
//	}
//	
//	//Compter le nombre de fois que PRE précède une chaîne
//	public static void getTransMatrix(String[] tableau){
//		double[] nbFois=null;
//		double nbPRE=0;
//		double prePre=0; //Un PRE ne peut pas précéder un autre PRE
//		double preTarget=1; //Un PRE est toujours suivi par un TARGET
//		double preOther=0; //Généralement, après le PRE, on ne peut avoir que TARGET
//		double nbPOST=0;
//		double nbTarget=0;
//		double targetPre=0; //De TARGET à PRE c'est 0
//		double targetTarget=0; //A déterminer
//		double targetOther=0; //A déterminer
//		double nbOTHER=0;
//		double otherPre=0; //A déterminer
//		double otherTarget = 0; // Other ne précède jamais un PRE
//		double ontherOther = 0; // A déterminer
//		String tmp="";
//		for (int i = 0; i < tableau.length; i++) {
//			tmp = tableau[i+1].trim();
//			if(belongs2Array(tableau[i], PRE) && tmp!=" "){
//				nbPRE++;
//				//Ajoute un nouveau TARGET dans la liste
//				if(!belongs2Array(tmp, TARGET)){
//					TARGET[TARGET.length+1] = tmp;
//					if(tableau[i+2]!=" " && !belongs2Array(tmp, TARGET)) TARGET[TARGET.length+1] = tableau[i+2];
//					if(tableau[i+3]!=" " && !belongs2Array(tmp, TARGET)) TARGET[TARGET.length+1] = tableau[i+3];
//				}
//			}
//			//Quand je sort de la première boucle, j'ai ma liste des targets
//			//Je construit alors la liste des others
//			if(!belongs2Array(tmp, TARGET) && !belongs2Array(tmp, PRE)){
//				OTHER[OTHER.length+1]=tmp;
//			}
//			//Je calcule le nombre de Targets nécessaires
//			if(belongs2Array(tableau[i], TARGET) && tmp!=" "){
//				nbTarget++;
//				//Je teste si l'élément suivant est un target. Alors, un target suit un autre 
//				if(belongs2Array(tmp, TARGET)){
//					targetTarget++;
//				}
//				//Je teste si l'élément suivant n'est pas un TARGET, alors, c'est OTHER
//				if(!belongs2Array(tmp, TARGET)){
//					targetOther++;
//				}
//			}
//			//Je fais de même pour OTHER
//			if(belongs2Array(tableau[i], OTHER)){
//				nbOTHER++;
//				//Je teste si l'élément suivant est un PRE 
//				if(belongs2Array(tmp, PRE)){
//					otherPre++;
//				}
//				//Je teste si l'élément suivant est OTHER
//				if(belongs2Array(tmp, OTHER)){
//					ontherOther++;
//				}
//			}
//			
//		}
//		// A ce niveau, j'ai tous les éléments me permettant de trouver ma matrice de transition
//		// On ajoute 1 à chaque fois au dénominateur pour éviter des divisions par 0
//		//Remplissage de la première ligne
//		transitionMatrix[0][0]= prePre/nbPRE+1; 
//		transitionMatrix[0][1]= preTarget/nbPRE+1; 
//		transitionMatrix[0][2]= preOther/nbPRE+1;
//		//Remplissage de la seconde ligne
//		transitionMatrix[1][0]= targetPre/nbTarget+1; 
//		transitionMatrix[1][1]= targetTarget/nbTarget+1; 
//		transitionMatrix[1][2]= targetOther/nbTarget+1;
//		//Remplissage de la seconde ligne
//		transitionMatrix[2][0]= otherPre/nbOTHER+1; 
//		transitionMatrix[2][1]= otherTarget/nbOTHER+1; 
//		transitionMatrix[2][2]= ontherOther/nbOTHER+1;
//		
//	}
//	
//	//ici l'on va calculer les probabilités d'emettre les observations à partir des 
//	//états caché.
//	//Par exemple, la probabilité 
//	public static void getEmissionMatrix(String[] tableau){
//		
//		double nbPre=0; //nb fois que avec PRE j'émet quelque chose
//		
//		for (int i = 0; i < tableau.length; i++) {
//			String tabi = tableau[i];
//			
//		}
//		
//	}
//	
//
//	public static void main(String[]args) throws Exception{
//		
////		collecte = new CollecteDonnees();
////		System.out.println(collecte.getData(folder));
//		
//		String test = "public class SeatPositionConverter implements "
//				+ "AttributeConverter<SeatPosition, String> {    public ";
//		test.replaceAll("    ", "");
////		System.out.println(test);
//		String[] test1 = test.trim().replace("\t", "").split(" ");
//		for (int i = 0; i < test1.length; i++) {
//			if(test1[i].equals(" "))
//				System.out.println(test1[i]);
//		}
////		System.out.println(belongs2Array("public", test1));
////		System.out.println(new String(" hello     there   ").trim().replaceAll("\\s{2,}", " "));
//
//		
////		System.out.println(data);
////		listFilesForFolder(folder);
////		System.out.println(data);
//	}
//}
