package cm.uy1.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelUse.model.Column;
import cm.uy1.modelUse.model.DataCollection;
import cm.uy1.modelUse.model.Frame;

public class Helper {

	/**
	 * //Test if a String belong to an array
	 * 
	 * @param chaineAtester
	 * @param tableau
	 * @return
	 */
	public static boolean belongs2Array(String chaineAtester, List<String> tableau) {
		boolean belongs = false;
		for (String chaine : tableau) {
			if (chaineAtester.equals(chaine)) {
				belongs = true;
			}
		}
		return belongs;
	}

	/**
	 * Find all the JAVA files in the data set Saved all files paths in a List of
	 * String
	 * 
	 * @param getAllJAVAFiles
	 * @return
	 */
	public static List<String> getAllJAVAFiles(String folderPath) {
		List<String> listFiles = new ArrayList<>();
		try (Stream<Path> filePathStream = Files.walk(Paths.get(folderPath))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath)
						&& filePath.toString().substring(filePath.toString().length() - 5).equals(".java")) {
					listFiles.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFiles;
	}

	/**
	 * Build a data set based on raw data
	 * 
	 * @param RAWDATA
	 * @param DATASET
	 */
	public static void buildDataSet(String RAWDATA, String DATASET) {
		List<String> listFiles = Helper.getAllJAVAFiles(RAWDATA);

		List<String> data = DataCollection.getAllRelevantData(RAWDATA);

		// Write data to a file
		String[] tmp;
		FileWriter fw = null;
		File treatedData;
		PrintWriter pw = null;
		for (int i = 0; i < listFiles.size() - 1; i++) {
			tmp = listFiles.get(i).split("/");
			treatedData = new File(DATASET + tmp[tmp.length - 1]);
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
	}

	/**
	 * get the data from a file Very usefull when training after the data collection
	 * step without collect information again
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getDataFromFiles(String filePath) {
		String data = "";
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			for (String line; (line = br.readLine()) != null;) {
				data += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * get the data from a file
	 * 
	 * @param folderPath
	 * @return
	 */
	public static List<String> getAllDataFromFolder(String folderPath) {
		List<String> filesList = Helper.getAllJAVAFiles(folderPath);
		List<String> data = new ArrayList<>();

		for (String filePath : filesList) {
			data.add(getDataFromFiles(filePath));
		}

		return data;
	}

	/**
	 * //Get all the files of a projects in form of a list of string
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static List<String> getFiles(String PROJECTPATH) {
		List<String> listFiles = new ArrayList<String>();
		try (Stream<Path> filePathStream = Files.walk(Paths.get(PROJECTPATH))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					// Count the number of files
					listFiles.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listFiles;
	}

	/**
	 * //Calculate the number of files
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static int getNBFiles(String PROJECTPATH) {
		return getFiles(PROJECTPATH).size();
	}

	/**
	 * //Calculate the number of instructions of a project
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static int getNBInstructions(String PROJECTPATH) {
		List<String> listDocs = getFiles(PROJECTPATH);
		LineNumberReader lnr;
		int nbInstr = 0;
		for (String doc : listDocs) {
			try {
				lnr = new LineNumberReader(new FileReader(new File(doc.trim())));
				lnr.skip(Long.MAX_VALUE);
				nbInstr = nbInstr + lnr.getLineNumber() + 1;
				lnr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		for (String string : listDocs) {
//			System.out.println(string);
//		}
		return nbInstr;
	}

	/**
	 * Takes has input a string sequence, an indice and return the label (PRE, POST,
	 * OTHER) of the String at i position Calculate the label of the element tmp[i]
	 * 
	 * @param i
	 * @param tmp
	 * @return
	 */
	public String getLabel(int i, String tmp[], HMMConcept hmmConcept) {
		String label = "";

		if (Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation()))
			label = "PRE";

		if (Helper.belongs2Array(tmp[i - 1], hmmConcept.getPreObservation())
				&& !Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation())) {
			label = "TARGET";
		}
		if (Helper.belongs2Array(tmp[i], hmmConcept.getTargetObservation()))
			label = "TARGET";

		if (Helper.belongs2Array(tmp[i], hmmConcept.getOtherObservation()))
			label = "OTHER";

		if (Helper.belongs2Array(tmp[i - 1], hmmConcept.getOtherObservation())) {
			if (!Helper.belongs2Array(tmp[i], hmmConcept.getPreObservation())) {
				label = "OTHER";
			}
		}

		return label;
	}

	/**
	 * //These methods are used to return the probability values to transit from one
	 * state to other
	 * 
	 * @param state1
	 * @param state2
	 * @param hmmConcept
	 * @return
	 */
	public double transitFromStateToOther(String state1, String state2, HMMConcept hmmConcept) {
		double transitionValue = 0;
		// For the PRE state
		if (state1.equals("PRE") && state2.equals("PRE")) {
			transitionValue = hmmConcept.getPrePreTransition().getTransitionValue();
		}
		if (state1.equals("PRE") && state2.equals("TARGET")) {
			transitionValue = hmmConcept.getPreTargetTransition().getTransitionValue();
		}
		if (state1.equals("PRE") && state2.equals("OTHER")) {
			transitionValue = hmmConcept.getPreOtherTransition().getTransitionValue();
		}
		// For the TARGET state
		if (state1.equals("TARGET") && state2.equals("PRE")) {
			transitionValue = hmmConcept.getTargetPreTransition().getTransitionValue();
		}
		if (state1.equals("TARGET") && state2.equals("TARGET")) {
			transitionValue = hmmConcept.getTargetTargetTransition().getTransitionValue();
		}
		if (state1.equals("TARGET") && state2.equals("OTHER")) {
			transitionValue = hmmConcept.getTargetOtherTransition().getTransitionValue();
		}
		// For the OTHER state
		if (state1.equals("OTHER") && state2.equals("PRE")) {
			transitionValue = hmmConcept.getOtherPreTransition().getTransitionValue();
		}
		if (state1.equals("OTHER") && state2.equals("TARGET")) {
			transitionValue = hmmConcept.getOtherTargetTransition().getTransitionValue();
		}
		if (state1.equals("OTHER") && state2.equals("OTHER")) {
			transitionValue = hmmConcept.getOtherOtherTransition().getTransitionValue();
		}
		return transitionValue;
	}

	/***
	 * This method is used to get the Frame which has the greatest probability
	 * amongst others
	 * 
	 * @param colum
	 * @return
	 */

	public static Frame mostGreatestFrameProba(Column colum) {

		Frame frame = new Frame();

		double preProba = colum.getPreFrame().getProbabilityValue();
		double targetProba = colum.getTargetFrame().getProbabilityValue();
		double postProba = colum.getPostFrame().getProbabilityValue();
		double otherProba = colum.getOtherFrame().getProbabilityValue();

		if (Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba)) == preProba)
			frame = colum.getPreFrame();

		if (Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba)) == targetProba)
			frame = colum.getTargetFrame();

		if (Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba)) == postProba)
			frame = colum.getPostFrame();

		if (Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba)) == otherProba)
			frame = colum.getOtherFrame();

		return frame;
	}

	/**
	 * Return the argMax value usable to calculate the probability
	 * alpha(i,t+1)=P(Wt+1=wt+1|Ht+1=i) x argMax[(P(Ht+1=i|Ht=j) x alpha(j,t)] The
	 * method return argMax[(P(Ht+1=i|Ht=j) x alpha(j,t)] For example alpha(PRE, 2)
	 * = P(W2=public|H2=PRE) x argMax[P(H2=PRE|H1=j) x alpha(j,1)] In the previous
	 * case, j takes the values: j=PRE, POST, TARGET ou OTHER and t = 1
	 * 
	 * @param framePRE
	 * @param frameTARGET
	 * @param frameOTHER
	 * @param prePre
	 * @param preTARGET
	 * @param preOTHER
	 * @return
	 */

	public static Frame getMaxFrame(Frame framePRE, Frame frameTARGET, Frame framePOST, 
			Frame frameOTHER, double prePre, double preTARGET, double prePost, double preOTHER) {
		Frame frameToBeReturn = null;

		double preValue = prePre * framePRE.getProbabilityValue();
		double targetValue = preTARGET * frameTARGET.getProbabilityValue();
		double postValue = prePost * framePOST.getProbabilityValue();
		double otherValue = preOTHER * frameOTHER.getProbabilityValue();

		if (preValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = framePRE;

		if (targetValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameTARGET;

		if (postValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = framePOST;

		if (otherValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameOTHER;

		return frameToBeReturn;
	}

	/**
	 * knowExtracted method takes as input the alphaTable and retrun the most
	 * likelly source code.
	 *
	 * Its browse the elements (frames) from the end to the beginning and get the
	 * frames that contains the most likely source code
	 * 
	 * @param alphaTable
	 * @return
	 */

	public String knowledgeExtraction(List<Column> alphaTable) {
		String knowledgeExtracted = "";
		List<Frame> mostLikelyFrames = new ArrayList<>();

		int alphaTableSize = alphaTable.size() - 1;
		Frame frame = new Frame();

		// Get the last column of the table
		Column lasColumn = alphaTable.get(alphaTableSize);
		// Get the last frame of the last column which has the greates probability value
		frame = mostGreatestFrameProba(lasColumn);
		// Get all the frames that permit to construct the above frame
		mostLikelyFrames.add(frame);
		// Browse the frame list to get the most likely frames
		for (int i = alphaTableSize; i > 0; i--) {
			frame = frame.getFrameBuilder();
			mostLikelyFrames.add(frame);
		}
		// Now, extract the knowledge
		for (int i = mostLikelyFrames.size() - 1; i > 0; i--) {
			if (mostLikelyFrames.get(i).getStateLabel().equals("TARGET"))
				knowledgeExtracted += mostLikelyFrames.get(i).getObservedLabel();
		}
		return knowledgeExtracted;
	}

	/**
	 * To avoid zero probability, this method test the values of the probabilities.
	 * If these values are too close to zero, it multiply by a big number
	 * 
	 * @param column
	 * @param alphaTable
	 */

	public static Column avoidZeroProba(Column column) {
		double preProba = column.getPreFrame().getProbabilityValue();
		double targetProba = column.getTargetFrame().getProbabilityValue();
		double postProba = column.getPostFrame().getProbabilityValue();
		double otherProba = column.getOtherFrame().getProbabilityValue();

		if (Math.max(
				Math.max(column.getPreFrame().getProbabilityValue(),
						column.getTargetFrame().getProbabilityValue()),
				Math.max(column.getOtherFrame().getProbabilityValue(),
						column.getPostFrame().getProbabilityValue())) < 10E-200) {
			preProba = preProba * 10E200;
			targetProba = targetProba * 10E200;
			postProba = postProba * 10E200;
			otherProba = otherProba * 10E200;
		}
		column.getPreFrame().setProbabilityValue(preProba);
		column.getTargetFrame().setProbabilityValue(targetProba);
		column.getPostFrame().setProbabilityValue(postProba);
		column.getOtherFrame().setProbabilityValue(otherProba);

		return column;
	}

	public static void writeDataToFile(String filePATH, String data) {
		FileWriter fw = null;
		PrintWriter pw = null;
		String[] tmp = data.split(" ");
		String dataTMP = "";
		try {
			fw = new FileWriter(filePATH, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		for (int i = 0; i < tmp.length; i++) {
			pw.print(tmp[i]);
		}
		pw.append(dataTMP);
		pw.close();
	}

	public static void writeOntoToFile(String filePATH, String data) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(filePATH, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.write(data);
		pw.close();
	}

}
