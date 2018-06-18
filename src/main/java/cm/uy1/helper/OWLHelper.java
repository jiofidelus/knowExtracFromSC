package cm.uy1.helper;

/**
 * This class contains 
 * @author azanzi
 *
 */

public class OWLHelper {
	private String dataPropertiesDeclaration = "";
	//List of methods for owl:class declaration, ow:SubClass, owl:DisjointClass
	/**
	 * This method takes a String as parameter and 
	 * generate an OWL class declaration
	 * @param entity
	 * @return
	 */
	public static String genClassDeclarations(final String entity) {
	  String class2Add = "";
	  class2Add = (class2Add + "\n<Declaration>");
	  class2Add = (((((class2Add + "\n\t <Class IRI=") + "\"#") + entity) + "\"") + "/>");
	  class2Add = (class2Add + "\n</Declaration>");
	  return class2Add;
	}
	/**
	 * This method generates subClass based (owl:SubClassOf) on a main classe
	 * @param mainClass
	 * @param subClasse
	 * @return
	 */
	public static  String genSubClasses(final String mainClass, final String subClasse) {
	  String genSubClass = "";
	  genSubClass = (genSubClass + "<SubClassOf>");
	  genSubClass = (((((genSubClass + "\n\t <Class IRI=") + "\"#") + subClasse) + "\"") + "/>");
	  genSubClass = (((((genSubClass + "\n\t <Class IRI=") + "\"#") + mainClass) + "\"") + "/>");
	  genSubClass = (genSubClass + "</SubClassOf>");
	  return genSubClass;
	}
	/**
	 * The method that permit to two disjoint classes (owl:DisjointClasses)
	 * @param mainClass
	 * @param disjoinedClass
	 * @return
	 */
	public static  String genDisjoinedClasses(final String mainClass, final String disjoinedClass) {
	  String genSubClass = "";
	  genSubClass = (genSubClass + "<DisjointClasses>");
	  genSubClass = (((((genSubClass + "\n\t <Class IRI=") + "\"#") + mainClass) + "\"") + "/>");
	  genSubClass = (((((genSubClass + "\n\t <Class IRI=") + "\"#") + disjoinedClass) + "\"") + "/>");
	  genSubClass = (genSubClass + "</DisjointClasses>");
	  return genSubClass;
	}
	
//This list of methods to generate ObjectProperty, ObjectPropertyDomain and ObjectPropertyRange
	
	/**
	 * This method takes a string (that is an owl:ObjectProperty) and generate the the 
	 * declaration of this property
	 * @param property
	 * @return
	 */
	public static String genObjectPropertiesDeclaration(String property){
			String objectProperty="";
			objectProperty = objectProperty + "\n<Declaration>";
			objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+property+"\"/>";
			objectProperty = objectProperty + "\n</Declaration>";
			return objectProperty;
	}
	
	/**
	 * This method takes an owl:class and owl:ObjectProperty as parameters and 
	 * generate the owl:ObjectPropertyDomain
	 * @param relationField
	 * @param entity
	 * @return
	 */
	public static  String genObjectPropertiesDomain(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyDomain>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		objectProperty = objectProperty + "\n</ObjectPropertyDomain>";
		return objectProperty;
	}
	/**
	 * Define the owl:ObjectPropertySomeValuesFrom
	 * @param relationField
	 * @param entity
	 * @return
	 */
	public static  String genObjectPropertiesDomainSomeValFrom(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyDomain>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty + "\n<ObjectSomeValuesFrom>";

		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";

		objectProperty = objectProperty + "\n</ObjectSomeValuesFrom>";
		objectProperty = objectProperty + "\n</ObjectPropertyDomain>";
		return objectProperty;
	}
	
	/**
	 * Define the owl:ObjectPropertyAllValuesFrom
	 * @param relationField
	 * @param entity
	 * @return
	 */
	public static  String genObjectPropertiesDomainAllValFrom(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyDomain>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty + "\n<ObjectAllValuesFrom>";

		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";

		objectProperty = objectProperty + "\n</ObjectAllValuesFrom>";
		objectProperty = objectProperty + "\n</ObjectPropertyDomain>";
		return objectProperty;
	}
/**
 * 
 * @param relationField
 * @param entity
 * @param cardinality
 * @return
 */
	public static  String genObjectPropertiesDomainMinCard(String relationField, String entity, int 
			cardinality)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyDomain>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty + "\n<ObjectMinCardinality cardinality=\""+cardinality+">";

		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";

		objectProperty = objectProperty + "\n</ObjectMinCardinality>";
		objectProperty = objectProperty + "\n</ObjectPropertyDomain>";
		return objectProperty;
	}	
	/**
	 * 
	 * @param relationField
	 * @param entity
	 * @param cardinality
	 * @return
	 */
	public static  String genObjectPropertiesDomainMaxCard(String relationField, String entity,
			int cardinality)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyDomain>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty + "\n<ObjectMaxCardinality cardinality=\""+cardinality+"\">";

		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";

		objectProperty = objectProperty + "\n</ObjectMaxCardinality>";
		objectProperty = objectProperty + "\n</ObjectPropertyDomain>";
		return objectProperty;
	}


	/**
	 * This method takes an owl:class and owl:ObjectProperty as parameters and 
	 * generate the owl:ObjectPropertyRange
	 * @param relationField
	 * @param entity
	 * @return
	 */

	public static  String genObjectPropertiesRange(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyRange>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		objectProperty = objectProperty + "\n</ObjectPropertyRange>";
		return objectProperty;
	}
	/**
	 * 
	 * @param relationField
	 * @param entity
	 * @return
	 */
	public static  String genObjectPropertiesRangeSomeValFrom(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyRange>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";

		objectProperty = objectProperty + "\n<ObjectSomeValuesFrom>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		
		objectProperty = objectProperty + "\n</ObjectSomeValuesFrom>";
		objectProperty = objectProperty + "\n</ObjectPropertyRange>";
		
		return objectProperty;
	}
	public static  String genObjectPropertiesRangeAllValFrom(String relationField, String entity)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyRange>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";

		objectProperty = objectProperty + "\n<ObjectAllValuesFrom>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		
		objectProperty = objectProperty + "\n</ObjectAllValuesFrom>";
		objectProperty = objectProperty + "\n</ObjectPropertyRange>";
		
		return objectProperty;
	}
	/**
	 * 
	 * @param relationField
	 * @param entity
	 * @param cardinality
	 * @return
	 */
	public static  String genObjectPropertiesRangeMaxCard(String relationField, String entity,
			int cardinality)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyRange>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";

		objectProperty = objectProperty + "\n<ObjectMaxCardinality"+"\""+cardinality+"\">";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		
		objectProperty = objectProperty + "\n</ObjectMaxCardinality>";
		objectProperty = objectProperty + "\n</ObjectPropertyRange>";
		
		return objectProperty;
	}
	/**
	 * 
	 * @param relationField
	 * @param entity
	 * @param cardinality
	 * @return
	 */
	public static  String genObjectPropertiesRangeMinCard(String relationField, String entity,
			int cardinality)
	{
		String objectProperty="";
		objectProperty = objectProperty + "\n<ObjectPropertyRange>";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";

		objectProperty = objectProperty + "\n<ObjectMaxCardinality"+"\""+cardinality+"\">";
		objectProperty = objectProperty+"\n\t <ObjectProperty IRI="+"\"#"+relationField+"\"/>";
		objectProperty = objectProperty+"\n\t <Class IRI="+"\"#"+entity+"\"/>";
		
		objectProperty = objectProperty + "\n</ObjectMaxCardinality>";
		objectProperty = objectProperty + "\n</ObjectPropertyRange>";
		
		return objectProperty;
	}
	
	//The list of methods to generate DataProperty, DataPropertyDomain and DataPropertyRange
	/**
	 * The method that generate the DataPropertyDeclaration
	 * @param property
	 * @return
	 */
	public static  String genDataPropertiesDeclaration(String property){
		String objectProperty="";
		objectProperty = objectProperty + "\n<Declaration>";
		objectProperty = objectProperty+"\n\t <DataProperty IRI="+"\"#"+property+"\"/>";
		objectProperty = objectProperty + "\n</Declaration>";
		return objectProperty;
	}
	
	/**
	 * The method to generate owl:DataPropertyDomain for an owl:class
	 * @param field
	 * @param entity
	 * @return
	 */
	public static  String  genDataPropertiesDomain(String field, String entity)
	{
		String dataProperty="";
		dataProperty = dataProperty + "\n<DataPropertyDomain>";
		dataProperty=dataProperty+"\n\t <DataProperty IRI="+"\"#"+field+"\""+"/>";
		dataProperty = dataProperty+"\n\t <Class IRI="+"\"#"+entity+"\""+"/>";
		dataProperty = dataProperty + "\n</DataPropertyDomain>";
		return dataProperty;
	}

	/**
	 * The method to generate owl:DataPropertyRange for an owl:class
	 * @param field
	 * @param entity
	 * @return
	 */
	public static  String   genDataPropertiesRange(String field, String value)
	{
		String dataProperty="";
		dataProperty = dataProperty + "\n<DataPropertyRange>";
		dataProperty=dataProperty+"\n\t <DataProperty IRI="+"\"#"+field+"\""+"/>";

		switch (value) {
		case "short":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:short\" />";
			break;
		case "byte":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:byte\" />";
			break;
		case "int":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:int\" />";
			break;
		case "long":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:long\" />";
			break;
		case "float":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:float\" />";
			break;
		case "double":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:double\" />";
			break;
		case "char":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:char\" />";
			break;
		case "String":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:string\" />";
			break;
		case "boolean":
			dataProperty = dataProperty+"\n\t <Datatype abbreviatedIRI=\"xsd:boolean\" />";
			break;
		}
		dataProperty = dataProperty + "\n</DataPropertyRange>";
		return dataProperty;
	}
	
	//Some methods for labels and comment 

	/**
	 * Add a comment on a resource (takes as input the iri of the resource and the comment)
	 * @param iriElement
	 * @param comment
	 * @return
	 */
	public static  String genComment(final String iriElement, final String comment) {
	  String genComment = "";
	  genComment = (genComment + "<AnnotationAssertion>");
	  genComment = (genComment + "\n\t <AnnotationProperty abbreviatedIRI=\"rdfs:comment\"/>");
	  genComment = ((((genComment + "\n\t <IRI>") + "#") + iriElement) + "</IRI>");
	  genComment = (((genComment + "\n\t <Literal datatypeIRI=\n\t\t\"\"http://www.w3.org/2001/XMLSchema#string\"\">") + comment) + "</Literal>");
	  genComment = (genComment + "</AnnotationAssertion>");
	  return genComment;
	}
	
	/**
	 * The method that generate the label of a resource
	 * @param iriElement
	 * @param label
	 * @return
	 */
	public String genLabels(final String iriElement, final String label) {
	  String genLabel = "";
	  genLabel = (genLabel + "<AnnotationAssertion>");
	  genLabel = (genLabel + "\n\t <AnnotationProperty abbreviatedIRI=\"rdfs:label\"/>");
	  genLabel = ((((genLabel + "\n\t <IRI>") + "#") + iriElement) + "</IRI>");
	  genLabel = (((genLabel + "\n\t <Literal datatypeIRI=\n\t\t\"\"http://www.w3.org/2001/XMLSchema#string\"\">") + label) + "</Literal>");
	  genLabel = (genLabel + "</AnnotationAssertion>");
	  return genLabel;
	}
	
	/**
	 * The method that generate the see also of a resource
	 * @param iriElement
	 * @param label
	 * @return
	 */
	public static  String genSeeAlso(final String iriElement, final String url) {
	  String genSeeAlso = "";
	  genSeeAlso = (genSeeAlso + "<AnnotationAssertion>");
	  genSeeAlso = (genSeeAlso + "\n\t <AnnotationProperty abbreviatedIRI=\"rdfs:seeAlso\"/>");
	  genSeeAlso = ((((genSeeAlso + "\n\t <IRI>") + "#") + iriElement) + "</IRI>");
	  genSeeAlso = (((genSeeAlso + "\n\t <Literal datatypeIRI=\n\t\t\"\"http://www.w3.org/2001/XMLSchema#string\"\">") + url) + "</Literal>");
	  genSeeAlso = (genSeeAlso + "</AnnotationAssertion>");
	  return genSeeAlso;
	}
}


