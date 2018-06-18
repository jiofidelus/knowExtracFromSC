package cm.uy1.translation2OWL_;
//package helpers;
//
//import java.awt.image.BufferedImageFilter;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Stream;
//
////Extrait un ensemble d'éléments obsolets du code source et renvoie le code dont on a besoin
//// pour l'étape suivante
//public class CollecteDonnees2 {
//	
//	private static Pattern pattern;
//    private static Matcher matcher;
//
//
//    private static final String PROJECTPATH = "/home/these/these/workspace_these/hmm/ticket-agency-jpa";
//    
//    private static final File folder = new File(PROJECTPATH);
//	//Expressions régulières pour extraire les éléments non désirables
//	private static String exp = "^import.|^package.|^//.|\\*.|@.";
//	
////	if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n' || ch == '\x0b') {
//
//	public static List<String> getAllFiles(String folderPath){
//		List<String> listFiles = new ArrayList<String>();
//		try (Stream<Path> filePathStream=Files.walk(Paths.get(PROJECTPATH))) {
//		    filePathStream.forEach(filePath -> {
//		        if (Files.isRegularFile(filePath) && filePath.toString().substring(filePath.toString().length()-5).equals(".java")) {
//		            listFiles.add(filePath.toString());
//		        }
//		    });
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listFiles;
//	}
//	//Take a file and return all relevant data
//	public static String getDataFromFile(String fileName){
//		String data = "";
//		pattern = Pattern.compile(exp);
//		FileReader fr;
//		BufferedReader br;
//		try {
//			fr = new FileReader(fileName);
//		br = new BufferedReader(fr);
//		for(String line; (line=br.readLine())!=null;){
//			matcher = pattern.matcher(line);
//			if(!matcher.find()){
//				data+=line;
//			}
//		}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return data;
//	}
//	//Get all relevant data from all files
//	public static List<String> getAllRelevantData(String PROJECTPATH){
//		List<String> data = new ArrayList<String>();
//		List<String> listFiles = getAllFiles(PROJECTPATH);
//		
//		for (String filePath : listFiles) {
//			data.add(getDataFromFile(filePath));
//		}
//		return data;
//	}
//	
//	
//	
//	public static void main(String[] args){
//		
//		System.out.println("*********************************************************************************************************************************");
//		List<String> listFiles = getAllFiles(PROJECTPATH);
//
//		List<String>  data = getAllRelevantData(PROJECTPATH); 
//		
////		System.out.println(data);
//		for (String filePath : data) {
//			System.out.println(filePath+"\n");
//		}
//		
//		System.out.println("*********************************************************************************************************************************");
//	}
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
//
