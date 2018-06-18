package cm.uy1.trainning;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMMConcept;
import cm.uy1.modelDesign.Observation;

public class HMMConceptObservation {
	
	
	/**
	 * 
	 * @param listDocs
	 * @return
	 *Calculate the emission vector that shows the probability for PRE state to emit some observation
	 */
	
	public static void preObservation(List<String> listCodeSource, HMMConcept hmmConcept) {
		double nbPublic=1, nbPrivate=1, nbProtected=1, nbStatic=1, nbFinal=1;
		double nbPre=5;
		String[] tmp;
		List<String> PRE = hmmConcept.getPreObservation();

		for (String docs : listCodeSource) {
			tmp=docs.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of times that we can see every elements in the file
				if(tmp[i].trim().equals("public")) nbPublic++;
				if(tmp[i].trim().equals("private")) nbPrivate++;
				if(tmp[i].trim().equals("protected")) nbProtected++;
				if(tmp[i].trim().equals("static")) nbStatic++;
				if(tmp[i].trim().equals("final")) nbFinal++;
				
			}
		}
		//Count the number of times that we have a PRE string in the source code
		nbPre = nbPublic+nbPrivate+nbProtected+nbStatic+nbFinal;
		
		Observation publicObservation = hmmConcept.getPreObservation_public();
		Observation privateObservation = hmmConcept.getPreObservation_private();
		Observation protectedObservation = hmmConcept.getPreObservation_protected();
		Observation staticObservation = hmmConcept.getPreObservation_static();
		Observation finalObservation = hmmConcept.getPreObservation_final();
		
		publicObservation.setEmissionValue(nbPublic/nbPre);
		privateObservation.setEmissionValue(nbPrivate/nbPre);
		protectedObservation.setEmissionValue(nbProtected/nbPre);
		staticObservation.setEmissionValue(nbStatic/nbPre);
		finalObservation.setEmissionValue(nbFinal/nbPre);
		
		hmmConcept.setPreObservation_public(publicObservation);
		hmmConcept.setPreObservation_private(privateObservation);
		hmmConcept.setPreObservation_protected(protectedObservation);
		hmmConcept.setPreObservation_static(staticObservation);
		hmmConcept.setPreObservation_final(finalObservation);
		
	}
	
	
	
	public static void targetObservation(List<String> listCodeSource, HMMConcept hmmConcept) {
		double nbClass=1, nbInterface=1, nbExtend=1, nbOther=1, 
				nbAbstract=1, nbImplements=1, nbEnum=1, nbPackage=1;
		double nbTarget=8;
		String[] tmp;
		List<String> PRE = hmmConcept.getPreObservation();
		List<String> TARGET = hmmConcept.getTargetObservation();
//		
		for (String docs : listCodeSource) {
			tmp=docs.split(" ");
			for (int i = 0; i < tmp.length; i++) {
//				//Count the number of times that we can see every elements in the file
				if(tmp[i].trim().equals("class")) {
					nbClass++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("package")) {
					nbPackage++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("interface")) {
					nbInterface++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("extends")) {
					nbExtend++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("abstract")) {
					nbAbstract++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("implements")) {
					nbImplements++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				if(tmp[i].trim().equals("enum")) {
					nbEnum++;
					//The next label is obligatory a target
					nbOther++;
					i++;
				}
				//For all the targets which do not fill the previous conditions
				//The case : private int age;
				if(i+2<tmp.length && 
						Helper.belongs2Array(tmp[i], PRE)&&
						!Helper.belongs2Array(tmp[i+1], PRE)) {
					nbOther+=2;
					i+=2;
				}
			}
		}
		nbTarget = nbClass+nbInterface+nbExtend+nbOther+nbAbstract+nbImplements+nbEnum+nbPackage;

		Observation classObservation = hmmConcept.getTargetObservation_class();
		Observation interfaceObservation = hmmConcept.getTargetObservation_interface();
		Observation extendsObservation = hmmConcept.getTargetObservation_extends();
		Observation implementsObservation = hmmConcept.getTargetObservation_implements();
		Observation abstractObservation = hmmConcept.getTargetObservation_abstract();
		Observation enumObservation = hmmConcept.getTargetObservation_enum();
		Observation packageObservation = hmmConcept.getTargetObservation_package();
		Observation otherObservation = hmmConcept.getTargetObservation_other();
		
		classObservation.setEmissionValue(nbClass/nbTarget);
		interfaceObservation.setEmissionValue(nbInterface/nbTarget);
		extendsObservation.setEmissionValue(nbExtend/nbTarget);
		implementsObservation.setEmissionValue(nbImplements/nbTarget);
		abstractObservation.setEmissionValue(nbAbstract/nbTarget);
		enumObservation.setEmissionValue(nbEnum/nbTarget);
		packageObservation.setEmissionValue(nbPackage/nbTarget);
		otherObservation.setEmissionValue(nbOther/nbTarget);

		hmmConcept.setTargetObservation_class(classObservation);
		hmmConcept.setTargetObservation_interface(interfaceObservation);
		hmmConcept.setTargetObservation_extends(extendsObservation);
		hmmConcept.setTargetObservation_implements(implementsObservation);
		hmmConcept.setTargetObservation_abstract(abstractObservation);
		hmmConcept.setTargetObservation_enum(enumObservation);
		hmmConcept.setTargetObservation_package(packageObservation);
		hmmConcept.setTargetObservation_other(otherObservation);
		
	}

	/////////////
	
	public static void otherObservation(List<String> listCodeSource, HMMConcept hmmConcept) {
		double nbSemiColon=1, nbOpenBracess=1, nbClosedBrassess=1, nbOtherOther=1;
		double nbOther=4;
		String[] tmp;
		List<String> TARGET = hmmConcept.getTargetObservation();
		List<String> OTHER = hmmConcept.getOtherObservation();
		List<String> PRE = hmmConcept.getPreObservation();
		
		for (String docs : listCodeSource) {
			tmp=docs.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//Count the number of times that we can see every elements in the file
				if(tmp[i].trim().equals(";")) {
					nbSemiColon++;
				}
				if(tmp[i].trim().equals("}")) {
					nbClosedBrassess++;
				}
				if(tmp[i].trim().equals("{")) {
					nbOpenBracess++;
				}
				if(i+1<tmp.length&& 
						Helper.belongs2Array(tmp[i], OTHER)&&
						!Helper.belongs2Array(tmp[i+1], PRE)&&
						!Helper.belongs2Array(tmp[i+1], TARGET)) {
					nbOtherOther++;
					i++;
				}
			}
		}

		nbOther=nbClosedBrassess+nbOpenBracess+nbOtherOther+nbSemiColon;
		
		Observation semiColonObservation = hmmConcept.getOtherObservation_SemiCol();
		Observation openBrassessObservation = hmmConcept.getOtherObservation_openCurlBrass();
		Observation closedBrassessObservation = hmmConcept.getOtherObservation_closeCurBrass();
		Observation otherObservation = hmmConcept.getOtherObservation_other();

		semiColonObservation.setEmissionValue(nbSemiColon/nbOther);
		openBrassessObservation.setEmissionValue(nbOpenBracess/nbOther);
		closedBrassessObservation.setEmissionValue(nbClosedBrassess/nbOther);
		otherObservation.setEmissionValue(nbOtherOther/nbOther);

		hmmConcept.setOtherObservation_SemiCol(semiColonObservation);
		hmmConcept.setOtherObservation_openCurlBrass(openBrassessObservation);
		hmmConcept.setOtherObservation_closeCurBrass(closedBrassessObservation);
		hmmConcept.setOtherObservation_other(otherObservation);
				
	}
	
}



