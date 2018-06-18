//package cm.uy1.translation2OWL_;
//package traductionowl;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Scanner;
//
//public class ToFormalLangage {
//    private static final String CONCEPTSEXTRACTED = 
//    		"/home/these/these/workspace_these/hmm/termesExtrait";
//
//    private static final String RULESEXTRACTED = 
//    		"/home/these/these/00these/these_fidel/article/article/0-data_extracted/RULESEXTRACTED";
//    
//    private static final String beginFile = 
//    		"<?xml version=\"1.0\"?> <Ontology xmlns=\"http://www.w3.org/2002/07/owl#\""+
//     "xml:base=\"http://www.ummisco.fr/2016/6/epicam\""+
//     "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\""+
//     "xmlns:xml=\"http://www.w3.org/XML/1998/namespace\""+
//     "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\""+
//     "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\""+
//     "ontologyIRI=\"http://www.ummisco.fr/2016/6/epicam\""+
//     "versionIRI=\"http://www.ummisco.fr/2016/6/epicam/01\">"+
//    "<Prefix name=\"\" IRI=\"http://www.ummisco.fr/2016/6/epicam\"/>"+
//    "<Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>"+
//    "<Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>"+
//    "<Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>"+
//    "<Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>"+
//    "<Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>"
//    + "</Ontology>";
//    
//
//    private static final String ENDFILE = "</Ontology>";
//
//    
//    private static GenHelper helper;
//
//    
//
//	public static void main(String[] args) {
//		helper = new GenHelper();
//		String ontology=beginFile;
//		
//        final File file = new File(CONCEPTSEXTRACTED);
//        String line;
//        String entity="";
//        try ( final Scanner scanner = new Scanner( file ); ) {
//            while ( scanner.hasNextLine() ) {
//                line = scanner.nextLine();
//                if(line.trim().equals("class")){
//                	line = scanner.nextLine();
//                	//On ajoute la classe et on passe à la ligne suivante
//                	entity=line;
//                	ontology+=helper.genClassDeclarations(line);
//                	if(scanner.hasNextLine())
//                		line = scanner.nextLine();
//                	else break;
//                	//Les éléments qui suivent la classe sont les attributs, les relations et les axiomes
//                	//On met tous dans les propriétés et c'est plus tard que l'on va écrire les méthodes 
//                	//permettant de les scinder
//                	while (!line.contains("class")) {
//                		ontology+=helper.genObjectPropertiesDeclaration(line);
//                		ontology+=helper.genObjectPropertiesDomain(line, entity);
//	                	if(scanner.hasNextLine()){
//	                		line = scanner.nextLine();
//	                	}
//	                	else break;
//					}
//                }
//            }
//        } catch ( FileNotFoundException e ) {
//            e.printStackTrace();
//        }
//        ontology+=ENDFILE;
//        //On écrit l'ontologie dans un fichier
//
//      File fileonto = new File("source2onto");
//      FileWriter fw=null;
//      try {
//      	fw = new FileWriter(file);
//      } catch (IOException e) {
//      	e.printStackTrace();
//      }
//      PrintWriter pw = new PrintWriter(fw);
//      pw.write(ontology);
//      System.out.println("************************Terminé***********************************");
//    }
//}
//
////File file = new File("DATAEXTRACTED");
////FileWriter fw=null;
////try {
////	fw = new FileWriter(file);
////} catch (IOException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}
////PrintWriter pw = new PrintWriter(fw);
//////pw.println("Hello World");
//
//
////final File file = new File( "data.txt" );
////
////try ( final Scanner scanner = new Scanner( file ); ) {
////    while ( scanner.hasNextLine() ) {
////        String line = scanner.nextLine();
////        System.out.println( line );
////    }
////} catch ( FileNotFoundException e ) {
////    e.printStackTrace();
////}
////}