package cm.uy1.translation2OWL;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import cm.uy1.helper.OWLHelper;

/**
 * This class contains the methods to transform a set of extracted terms to an
 * OWL ontology
 * 
 * @author azanzi
 *
 */
public class Term2OWL {
	// Define the owl:DataProperty that may be identified in knowledge extracted
	private static String rangeDataProperty[] = 
		{ "byte", "short", "int", "long", "float", "double", "char", "String",
			"boolean" };

	/**
	 * OWL begin with a list of standart string
	 */
	private static final String BEGINFILE = "<?xml version=\"1.0\"?>\n" + 
			"<Ontology xmlns=\"http://www.w3.org/2002/07/owl#\"\n" + 
			"     xml:base=\"http://www.semanticweb.org/azanzi/ontologies/2018/5/untitled-ontology-2\"\n" + 
			"     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" + 
			"     xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"\n" + 
			"     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n" + 
			"     xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n" + 
			"     ontologyIRI=\"http://www.semanticweb.org/azanzi/ontologies/2018/5/untitled-ontology-2\">\n" + 
			"    <Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>\n" + 
			"    <Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n" + 
			"    <Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>\n" + 
			"    <Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>\n" + 
			"    <Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>";

	/**
	 * OWL file end with a standart keyword
	 */
	private static final String ENDFILE = "</Ontology>";

	/**
	 * This method takes as input a file containing some terms extracted from source
	 * code and generate an OWL ontology using the methods of OWLHelper
	 * 
	 * @param file
	 */
	public static String term2OWL(File file) {
		String ontology = BEGINFILE;

		String line;
		String previous;
		String next;
		String owlPackage = ""; //will be used to define subclass
		String owlDataProp="";
		String owlClass="";
		String owlObjectProp="";
		
		try (final Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				previous = line;
				if(line.trim().equals("package")) {
					// Read the next line which is the name of the package (identified by
					// the meta-data package)
					owlPackage = scanner.nextLine();
					//Create a new owl:class
					ontology += OWLHelper.genClassDeclarations(owlPackage);
					//All the class contains in this package are define as subclass of 
					//this package
					if (scanner.hasNextLine()) {
						previous = line;
						line = scanner.nextLine();
					}
					else break;
					if (line.trim().equals("class")) {
						//Define a new class that is the subclass of the package
						owlClass=scanner.nextLine();
						ontology += OWLHelper.genSubClasses(owlPackage, owlClass);
						// Pass to the next line - all the elements after the class and before the
						// next class are properties and methods of the current class
						if (scanner.hasNextLine())
							line = scanner.nextLine();
						else break;
						while (!line.contains("class")) {
							//If we got a DataProperty
							if(Arrays.asList(rangeDataProperty).contains(line.trim())) {
								//The next term is a DataProperty
//								previous = line;
								owlDataProp=scanner.nextLine();
								ontology += OWLHelper.genDataPropertiesDeclaration(owlDataProp);
								ontology += OWLHelper.genDataPropertiesDomain(owlDataProp, owlClass);
								ontology += OWLHelper.genDataPropertiesRange(owlDataProp, line);
							}
							if(!Arrays.asList(rangeDataProperty).contains(line.trim())&&
									!Arrays.asList(rangeDataProperty).contains(previous.trim())) {
//								previous = line;
								//The next term is an ObjectProperty
								owlObjectProp=scanner.nextLine();
								ontology += OWLHelper.genObjectPropertiesDeclaration(owlObjectProp);
								//Get the cardinality
								if(line.contains("<List>")) {
									ontology += OWLHelper.genObjectPropertiesDomain(owlDataProp, owlClass);
									ontology += OWLHelper.
									genObjectPropertiesRangeAllValFrom(owlObjectProp, owlClass);
								}else {
									ontology += OWLHelper.genObjectPropertiesDomain(owlDataProp, owlClass);
									ontology += OWLHelper.genObjectPropertiesRange(owlDataProp, owlClass);
								}
							}
							// Next time, generate the objectOpropertyRange and data property
							if (scanner.hasNextLine()) {
								previous=line;
								line = scanner.nextLine();
							}
							else
								break;
				}
				
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Add the end of the file to the terms translated
		ontology += ENDFILE;
		//Return the terms translated
		//Next time, this will be wrote to a file
		return ontology;
	}
}

