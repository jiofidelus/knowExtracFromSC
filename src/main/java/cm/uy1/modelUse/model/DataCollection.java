package cm.uy1.modelUse.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cm.uy1.helper.Helper;

public class DataCollection {
	
	public static final String RAWDATA = 
			"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/1-TrainDS";
	private static final String DATASET=
			"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";
	  private static final File folder = new File(RAWDATA);
	  
	private String folderPATH = "";
	
	private static Pattern pattern, patternBlankSpace;
	private static Matcher matcher;
	
//	private static String regex = "^import.|^/\\*\\*.|\\*/$.|^\\*.|^//.";
	//This pattern will be used to remove all blank spaces
	private static String regex = "^import|^/\\*\\*|.\\*/|^\\s\\*|^\\s//";//|^\\";
	
	
	private static String regexImport = "^import";
	private static String regexBlocComment = "^/\\*\\*";
	private static String regexEnBlocComment = ".\\*/$";
	private static String regexInBlocComment = "^\\s\\*";
	private static String regexComment = "^\\s//";
	
	/**
	 * This method takes as input a file and get
	 * the date from this file
	 * @param filePath
	 * @return
	 */
		public static String collectDataFromFiles(String filePath) {
			String data = "";
			pattern = Pattern.compile(regex);
			FileReader fr;
			BufferedReader br;
			try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			for (String line; (line=br.readLine())!=null;) {
//				System.out.println("++++The line readed : "+line);
				//get the data
				matcher = pattern.matcher(line);
				if(matcher.find());
				else {
					if(!line.contains("/*")) {
						if(!line.contains("*/")) {
						//Removes the blank space
							line = line.replaceAll("\\s+"," ");
							line = line.replaceAll(";","\\ ;\\ ");
							line = line.replaceAll("\\s+}","\\ }\\ ");
//							line = line.replaceAll("{","\\ {\\ ");
						data+=line;
						}
					}
				}
			}
			}catch (IOException e) {
				// TODO: handle exception
			}
			
			return data;
		}
		
		/**
		 * Get all relevant data from all the files
		 * @param folderPath
		 * @return
		 */
		public static List<String> getAllRelevantData(String folderPath){
			List<String> data = new ArrayList<>();
			List<String> listFiles = Helper.getAllJAVAFiles(folderPath);
			
			for (String filePath : listFiles) {
				data.add(collectDataFromFiles(filePath));
			}
			return data;
		}
		
		
		//Testing data collection
		/**
		 * This method is used to build the data set
		 * It will create for any JAVA file, the file with some data removed
		 */
		public static void buildDataSet(){
			
			System.out.println("*********************************************************************************************************************************");
			List<String> listFiles = Helper.getAllJAVAFiles(RAWDATA);

			List<String>  data = DataCollection.getAllRelevantData(RAWDATA); 

			//Write data to a file
			String[] tmp;
			FileWriter fw=null;
			File treatedData;
			PrintWriter pw=null;
			for (int i = 0; i < listFiles.size()-1; i++) {
				tmp=listFiles.get(i).split("/");
				treatedData = new File(DATASET+tmp[tmp.length-1]);
				try {
					fw = new FileWriter(treatedData);
				} catch (IOException e) {
					e.printStackTrace();
				}
				pw = new PrintWriter(fw);
				pw.write(data.get(i).toString());
				pw.close();
				System.out.println(data.get(i).toString());
			}
			
			System.out.println("*********************************************************************************************************************************");
		}

}
