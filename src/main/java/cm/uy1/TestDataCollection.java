package cm.uy1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cm.uy1.helper.Helper;
import cm.uy1.modelUse.model.DataCollection;

public class TestDataCollection {
	

	public static final String RAWDATA1 = 
			"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/1-TrainDS";
	public static final String RAWDATA2 = "/home/azanzi/Documents/workspace/hmm/"
			+ "AdditionalMaterials/DataSources/2-TestDS/1-raw_data/";
	private static final String DATASET1=
			"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/"
			+ "1-TrainDS/2-treated_data/";
	private static final String DATASET2="/home/azanzi/Documents/workspace/hmm/AdditionalMaterials"
			+ "/DataSources/2-TestDS/2-treated_data/";
	  private static final File folder = new File(RAWDATA2);
	  
	  
	private static Pattern pattern, patternBlankSpace;
	private static Matcher matcher;
	
	private static String exp4Concepts = "^import.|^package.|^//.|\\*.|@.";

	private static String regex =  "^import|^/\\*|^/\\*\\*|\\*/$|^\\*|^//";
	
	private static String regexImport = "^import.";
	private static String regexBlocComment = "^/\\*\\*";
	private static String regexInBlocComment = "\\*/$";
	private static String regexBeginBlocComment = "^\\*";
	private static String regexComment = "^//";
//	private static String regexBlankCaracter = "\\s";
	private static String regexBlankSpace = "(\\w)(\\s+)([\\.,])";
	
	
	
public static void main(String[] args){
		//This is to build the training data set
		Helper.buildDataSet(RAWDATA1, DATASET1);
		//this is to build the test data set (epicam dataset)
		Helper.buildDataSet(RAWDATA2, DATASET2);
		
	}
}






