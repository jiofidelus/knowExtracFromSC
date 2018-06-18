package cm.uy1.translation2OWL_;
//package helpers;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.LineNumberReader;
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
//public class DataCollection {
//
//	private static Pattern pattern;
//	private static Matcher matcher;
//
////	private static final String PROJECTPATH = "/home/these/these/workspace_these/hmm/ticket-agency-jpa";
//
////	private static final File folder = new File(PROJECTPATH);
//	// Expressions régulières pour extraire les éléments non désirables
//	private static String exp4Concepts = "^import.|^package.|^//.|\\*.|@.";
//	
//	private static String exp4Rules = "^import.|^package.|^//.|\\*.|@."
//			+ "|public.|private.|protected.|this.|\\n.";
//
//	public List<String> getAllFiles(String PROJECTPATH) {
//		int nbFiles=0;
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
//		return listFiles;
//	}
//
//	// Take a file and return all relevant data for concepts extraction
//	public String getDataFromFile4Concepts(String fileName) {
//		String data = "";
//		pattern = Pattern.compile(exp4Concepts);
//		FileReader fr;
//		BufferedReader br;
//		try {
//			fr = new FileReader(fileName);
//			br = new BufferedReader(fr);
//			for (String line; (line = br.readLine()) != null;) {
//				matcher = pattern.matcher(line);
//				if (!matcher.find() && !line.equals("")) {
//					data += line;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return data;
//	}
//
//	// Get all relevant data from all files for concepts extraction
//	public List<String> getAllRelevantData4Concepts(String PROJECTPATH) {
//		List<String> data = new ArrayList<String>();
//		List<String> listFiles = getAllFiles(PROJECTPATH);
//		for (String filePath : listFiles) {
//			data.add(getDataFromFile4Concepts(filePath));
//		}
//		return data;
//	}
//	
//	
//	//Take a file and return all relevant data for rules extraction
//	public String getDataFromFile4Rules(String fileName) {
//		String data = "";
//		pattern = Pattern.compile(exp4Rules);
//		FileReader fr;
//		BufferedReader br;
//		try {
//			fr = new FileReader(fileName);
//			br = new BufferedReader(fr);
//			for (String line; (line = br.readLine()) != null;) {
//				matcher = pattern.matcher(line);
//				if (!matcher.find() && !line.equals("")) {
//					data += line;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return data;
//	}
//	
//	public List<String> getAllRelevantData4Rules(String PROJECTPATH){
//		List<String> data = new ArrayList<String>();
//		List<String> listFiles = getAllFiles(PROJECTPATH);
//		for (String filePath : listFiles) {
//			data.add(getDataFromFile4Rules(filePath));
//		}
//		return data;
//		
//	}
//
//	public static void main(String[] args) {
//
//	}
//}
