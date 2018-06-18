package cm.uy1.translation2OWL_;
//package helpers;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.LineNumberReader;
//import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
///**will content all methods needed by another classes
// * 
// * @author these
// *
// */
//public class Helper {
//
//	static LineNumberReader  lnr;
//    private static final String PROJECTPATH = "/home/these/these/workspace_these/hmm/source_code/pourTest/1";
//
//	//Test if a String belong to an array
//	public boolean belongs2Array(String chaineAtester, String[]tableau){
//		boolean belongs = false;
//		for (int i = 0; i < tableau.length; i++) {
//			if(chaineAtester.contains(tableau[i].trim())) belongs = true;
//		}
//		return belongs;
//	}
//	
//	public static String[] removeSpaces(String[] tmp){
//		String[] tmp1 = new String[tmp.length];
//		int j=0;
//		for (int i = 0; i < tmp.length; i++) {
//			if(!tmp[i].equals("")){
//				tmp1[j]=tmp[i];
//				j++;
//			}
//		}
//		//Calcul de la taille de la partie contenant les données
//		int taille = 0;
//		for (int i = 0; i < tmp1.length; i++) {
//			if(tmp1[i]==null) break;
//			else taille++;
//		}
//		//On peut maintenant créer un nouveau tableau avec la bonne taille et les bonnes données
//		String[] tmp2 = new String[taille];
//		
//		for(int i= 0; i<taille; i++){
//			tmp2[i]=tmp1[i];
//		}
//		return tmp2;
//	}
//
//	//Get T, the number of documents
//	public int getNBDocuments(List<String>listDocuments){
//		return listDocuments.size();
//	}
//	
//
//	//Calculer le nombre de fichiers et d'instructions d'un projet
//	public static List<String> getProjetNBFAndIntr(String PROJECTPATH) {
//		List<String> listFiles = new ArrayList<String>();
//		try (Stream<Path> filePathStream = Files.walk(Paths.get(PROJECTPATH))) {
//			filePathStream.forEach(filePath -> {
//				if (Files.isRegularFile(filePath)) {
//					//compter le nombre d'instructions
//					listFiles.add(filePath.toString());
//				}
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listFiles;
//	}
//
//	
//	//nombre d'instructions d'un projet JAVA 
//	//Parcoure les fichiers en comptant le nombre d'instructions
//	
//	public static void getNBInstructions(String PROJECTPATH){
//
////		String PATH="/home/these/these/workspace_these/hmm/source_code/epicamweb"; //pour le numbre de lignes de code de EPICAM
////		String PATH="/home/these/these/workspace_these/hmm/c-sources/chemtool-1.6.14"; //pour le numbre de lignes de code de Chemtool
////		String PATH="/home/these/these/workspace_these/hmm/c-sources/linux-master/0-dataSource"; //pour le numbre de lignes de code de Linux 
////		String PATH="/home/these/these/workspace_these/hmm/c-sources/linux-master/0-dataSource"; //pour le numbre de lignes de code de Linux 
//		//Pour compter le nombre d'instructions de GeoServer
////		String PATH="/home/these/these/workspace_these/hmm/java-sources/geoserver-master"; //Number de ligne de l'ensemble d'entrainnement
//		//PATH for MapServer source code
//		String PATH="/home/these/these/workspace_these/hmm/c-sources/mapserver-branch-7-0";
//		List<String> listDocs = getProjetNBFAndIntr(PATH);
//		System.out.println(listDocs.size());
//		getProjetNBFAndIntr(PATH);
//		int nbInstr=0;
//		for (String doc : listDocs) {
//			try {
//				lnr = new LineNumberReader(new FileReader(new File(doc.trim())));
//				lnr.skip(Long.MAX_VALUE);
//				nbInstr = nbInstr + lnr.getLineNumber() + 1;
//				lnr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		for (String string : listDocs) {
//			System.out.println(string);
//		}
//
//		System.out.println("Le nombre d'instructions : "+nbInstr);
//		System.out.println("Le nombre de fichiers : "+listDocs.size());
//
//	}
//	
//	//HelperRules
//	public static String getRules(){
//		//j'ouvre le fichier
//		//je récupère tous les termes commençant par if/ele/switch
//		String rules="";
//		FileReader fr;
//		BufferedReader br;
//		
//
//		File file = new File("RULESEXTRACTED");
//		FileWriter fw=null;
//		try {
//			fw = new FileWriter(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		PrintWriter pw = new PrintWriter(fw);
//		
//		List<String> listFiles = new ArrayList<String>();
//		try (Stream<Path> filePathStream = Files.walk(Paths.get(PROJECTPATH))) {
//			filePathStream.forEach(filePath -> {
//				if (Files.isRegularFile(filePath)
//						&& filePath.toString().substring(filePath.toString().length() - 5).equals(".java")) {
//					listFiles.add(filePath.toString());
//				}
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (String fileName : listFiles) {		
//			try {
//				fr = new FileReader(fileName);
//				br = new BufferedReader(fr);
//				for (String line; (line = br.readLine()) != null;) {
//					if (line.contains("if")) {
//						while((line = br.readLine())!= null && !line.contains("}")){
//							rules+=" "+line;
////							System.out.println(line);
//							pw.println(line);
//						}
//					}
//					if (line.contains("else")) {
//						while((line = br.readLine())!= null && !line.contains("}")){
//							rules+=" "+line;
////							System.out.println(line);
//							pw.println(line);
//						}
//					}
//					if (line.contains("switch")) {
//						while((line = br.readLine())!= null && !line.contains("}")){
//							rules+=" "+line;
////							System.out.println(line);
//							pw.println(line);
//						}
//					}
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return rules;
//	}
//	
//	//Avec les expressions régulières
//	
//	public static void main(String[] args) {
////		System.out.println(getRules());
//		getNBInstructions("getNBInstructions");
////		File file;
////		List<String> listFile = getProjetNBFAndIntr
////				("/home/these/these/workspace_these/hmm/c-sources/mapserver-branch-7-0/0-dataSource");
////		try{
////			for (String f : listFile) {
////				file = new File(f);
////				System.out.println("Concept "+f);
////			BufferedReader br = new BufferedReader(new FileReader(file));
////		    String line;
////		    while ((line = br.readLine()) != null) {
////			       if(line.contains("struct"))
////			    	   System.out.println(line);
//////			       if(line.contains("int"))
//////			    	   System.out.println(line);
//////			       if(line.contains("String"))
//////			    	   System.out.println(line);
//////			       if(line.contains("Char*"))
//////			    	   System.out.println(line);
////		}
////
////			}
////	}catch (Exception e) {
////		e.printStackTrace();
////	}
//	}
//		
//}
//
//
//
//
//
//
//
//
//
