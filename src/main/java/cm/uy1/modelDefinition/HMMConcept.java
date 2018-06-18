package cm.uy1.modelDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import cm.uy1.modelDesign.Observation;
import cm.uy1.modelDesign.State;
import cm.uy1.modelDesign.Transition;
import cm.uy1.trainning.HMMConceptInit;

public class HMMConcept {

	//The states
	private State pre=new State();
	private State target=new State();
	private State other=new State();
	
	
	//The transitions
	private Transition prePreTransition = new Transition(pre, pre, 0);
	private Transition preTargetTransition = new Transition(pre, target, 0);
	private Transition preOtherTransition = new Transition(pre, other, 0);

	private Transition targetPreTransition = new Transition(target, pre, 0);
	private Transition targetTargetTransition = new Transition(target, target, 0);
	private Transition targetOtherTransition = new Transition(target, other, 0);

	private Transition otherPreTransition = new Transition(other, pre, 0);
	private Transition otherTargetTransition = new Transition(other, target, 0);
	private Transition otherOtherTransition = new Transition(other, other, 0);

	//The observations
	
	//The string observed
	private static List<String> preObservation=new ArrayList<>() {{
		add("public");
		add("private");
		add("protected");
		add("static");
		add("final");
	}};
	//The observations
	Observation preObservation_public = new Observation("public", 0, pre);
	Observation preObservation_private = new Observation("private", 0, pre);
	Observation preObservation_protected = new Observation("protected", 0, pre);
	Observation preObservation_static = new Observation("static", 0, pre);
	Observation preObservation_final = new Observation("final", 0, pre);
	
	private List<String> targetObservation=new ArrayList<>() {{
		add("package");
		add("class");
		add("interface");
		add("extends");
		add("implements");
		add("abstract");
		add("enum");
	}};
	Observation targetObservation_package = new Observation("package", 0, target);
	Observation targetObservation_class = new Observation("class", 0, target);
	Observation targetObservation_interface = new Observation("interface", 0, target);
	Observation targetObservation_extends = new Observation("extends", 0, target);
	Observation targetObservation_implements = new Observation("implements", 0, target);
	Observation targetObservation_abstract = new Observation("abstract", 0, target);
	Observation targetObservation_enum = new Observation("enum", 0, target);
	Observation targetObservation_other = new Observation("otherTarget", 0, target);
	
	private static List<String> otherObservation=new ArrayList<>() {{
		add(";");
		add("{");
		add("}");
	}};
	Observation otherObservation_SemiCol = new Observation(";", 0, other);
	Observation otherObservation_openCurlBrass = new Observation("{", 0, other);
	Observation otherObservation_closeCurBrass = new Observation("}", 0, other);
	Observation otherObservation_other = new Observation("otherOther", 0, other);
	
	
	//Initial 
	private List<Transition> initialTransition=new ArrayList<>();
	
	/**
	 * This method is used to build the states and the 
	 * observation
	 */
	public void init() {
		//Initial vector
		initialTransition.add(new Transition(new State(), pre, 0));
		initialTransition.add(new Transition(new State(), target, 0));
		initialTransition.add(new Transition(new State(), other, 0));
		
		//Initialize PRE transition
		List<Transition> preTransition=new ArrayList<>() {{
			add(prePreTransition);
			add(preTargetTransition);
			add(preOtherTransition);
		}};
		//Initialize the PRE emission vector
		List<Observation> preObserved=new ArrayList<>(){{
			add(preObservation_final);
			add(preObservation_private);
			add(preObservation_protected);
			add(preObservation_public);
			add(preObservation_static);
		}};
		//Set the transition and the emission of the HMM
		pre.setLabel("PRE");
		pre.setObservations(preObserved);
		pre.setTransitions(preTransition);

		//Initialize TARGET transition vector with 0 probabilities
		List<Transition> targetTransition=new ArrayList<>() {{
			add(targetPreTransition);
			add(targetTargetTransition);
			add(targetOtherTransition);
		}};

		//Initialize the TARGET emission vector with 0 probabilities
		List<Observation> targetObserved=new ArrayList<>() {{
			add(targetObservation_package);
			add(targetObservation_class);
			add(targetObservation_extends);
			add(targetObservation_interface);
			add(targetObservation_other);
			add(targetObservation_implements);
			add(targetObservation_abstract);
			add(targetObservation_enum);
		}};
		//Set the transition and the emission of the HMM
		target.setLabel("TARGET");
		target.setObservations(targetObserved);
		target.setTransitions(targetTransition);

		//Initialize OTHER transition vector with 0 probabilities
		List<Transition> otherTransition=new ArrayList<>() {{
			add(otherPreTransition);
			add(otherTargetTransition);
			add(otherOtherTransition);
		}};
		//Initialize the OTHER emission vector with 0 probabilities
		List<Observation> otherObserved=new ArrayList<>() {{
			add(otherObservation_closeCurBrass);
			add(otherObservation_openCurlBrass);
			add(otherObservation_SemiCol);
			add(otherObservation_other);
		}};
		//Set the transition and the emission of the HMM
		other.setLabel("OTHER");
		other.setObservations(otherObserved);
		other.setTransitions(otherTransition);
	}
	
	public static List<String> getPreObservation() {
		return preObservation;
	}

	public void setPreObservation(List<String> preObservation) {
		this.preObservation = preObservation;
	}

	public List<String> getTargetObservation() {
		return targetObservation;
	}

	public void setTargetObservation(List<String> targetObservation) {
		this.targetObservation = targetObservation;
	}

	public static List<String> getOtherObservation() {
		return otherObservation;
	}

	public void setOtherObservation(List<String> otherObservation) {
		this.otherObservation = otherObservation;
	}

	public State getPre() {
		return pre;
	}

	public void setPre(State pre) {
		this.pre = pre;
	}

	public State getTarget() {
		return target;
	}

	public void setTarget(State target) {
		this.target = target;
	}

	public State getOther() {
		return other;
	}

	public void setOther(State other) {
		this.other = other;
	}

	public List<Transition> getInitialTransition() {
		return initialTransition;
	}

	public void setInitialTransition(List<Transition> initialTransition) {
		this.initialTransition = initialTransition;
	}

	public Transition getPrePreTransition() {
		return prePreTransition;
	}

	public void setPrePreTransition(Transition prePreTransition) {
		this.prePreTransition = prePreTransition;
	}

	public Transition getPreTargetTransition() {
		return preTargetTransition;
	}

	public void setPreTargetTransition(Transition preTargetTransition) {
		this.preTargetTransition = preTargetTransition;
	}

	public Transition getPreOtherTransition() {
		return preOtherTransition;
	}

	public void setPreOtherTransition(Transition preOtherTransition) {
		this.preOtherTransition = preOtherTransition;
	}

	public Transition getTargetPreTransition() {
		return targetPreTransition;
	}

	public void setTargetPreTransition(Transition targetPreTransition) {
		this.targetPreTransition = targetPreTransition;
	}

	public Transition getTargetTargetTransition() {
		return targetTargetTransition;
	}

	public void setTargetTargetTransition(Transition targetTargetTransition) {
		this.targetTargetTransition = targetTargetTransition;
	}

	public Transition getTargetOtherTransition() {
		return targetOtherTransition;
	}

	public void setTargetOtherTransition(Transition targetOtherTransition) {
		this.targetOtherTransition = targetOtherTransition;
	}

	public Transition getOtherPreTransition() {
		return otherPreTransition;
	}

	public void setOtherPreTransition(Transition otherPreTransition) {
		this.otherPreTransition = otherPreTransition;
	}

	public Transition getOtherTargetTransition() {
		return otherTargetTransition;
	}

	public void setOtherTargetTransition(Transition otherTargetTransition) {
		this.otherTargetTransition = otherTargetTransition;
	}

	public Transition getOtherOtherTransition() {
		return otherOtherTransition;
	}

	public void setOtherOtherTransition(Transition otherOtherTransition) {
		this.otherOtherTransition = otherOtherTransition;
	}

	public Observation getPreObservation_public() {
		return preObservation_public;
	}

	public void setPreObservation_public(Observation preObservation_public) {
		this.preObservation_public = preObservation_public;
	}

	public Observation getPreObservation_private() {
		return preObservation_private;
	}

	public void setPreObservation_private(Observation preObservation_private) {
		this.preObservation_private = preObservation_private;
	}

	public Observation getPreObservation_protected() {
		return preObservation_protected;
	}

	public void setPreObservation_protected(Observation preObservation_protected) {
		this.preObservation_protected = preObservation_protected;
	}

	public Observation getPreObservation_static() {
		return preObservation_static;
	}

	public void setPreObservation_static(Observation preObservation_static) {
		this.preObservation_static = preObservation_static;
	}

	public Observation getPreObservation_final() {
		return preObservation_final;
	}

	public void setPreObservation_final(Observation preObservation_final) {
		this.preObservation_final = preObservation_final;
	}

	public Observation getTargetObservation_class() {
		return targetObservation_class;
	}

	public void setTargetObservation_class(Observation targetObservation_class) {
		this.targetObservation_class = targetObservation_class;
	}

	public Observation getTargetObservation_interface() {
		return targetObservation_interface;
	}

	public void setTargetObservation_interface(Observation targetObservation_interface) {
		this.targetObservation_interface = targetObservation_interface;
	}

	public Observation getTargetObservation_extends() {
		return targetObservation_extends;
	}

	public void setTargetObservation_extends(Observation targetObservation_extends) {
		this.targetObservation_extends = targetObservation_extends;
	}

	public Observation getTargetObservation_other() {
		return targetObservation_other;
	}

	public Observation getTargetObservation_implements() {
		return targetObservation_implements;
	}

	public void setTargetObservation_implements(Observation targetObservation_implements) {
		this.targetObservation_implements = targetObservation_implements;
	}

	public void setTargetObservation_other(Observation targetObservation_other) {
		this.targetObservation_other = targetObservation_other;
	}

	public Observation getOtherObservation_SemiCol() {
		return otherObservation_SemiCol;
	}

	public void setOtherObservation_SemiCol(Observation otherObservation_SemiCol) {
		this.otherObservation_SemiCol = otherObservation_SemiCol;
	}

	public Observation getOtherObservation_openCurlBrass() {
		return otherObservation_openCurlBrass;
	}

	public void setOtherObservation_openCurlBrass(Observation otherObservation_openCurlBrass) {
		this.otherObservation_openCurlBrass = otherObservation_openCurlBrass;
	}

	public Observation getOtherObservation_closeCurBrass() {
		return otherObservation_closeCurBrass;
	}

	public void setOtherObservation_closeCurBrass(Observation otherObservation_closeCurBrass) {
		this.otherObservation_closeCurBrass = otherObservation_closeCurBrass;
	}

	public Observation getOtherObservation_other() {
		return otherObservation_other;
	}

	public void setOtherObservation_other(Observation otherObservation_other) {
		this.otherObservation_other = otherObservation_other;
	}
	
	
	public Observation getTargetObservation_abstract() {
		return targetObservation_abstract;
	}

	public void setTargetObservation_abstract(Observation targetObservation_abstract) {
		this.targetObservation_abstract = targetObservation_abstract;
	}

	public Observation getTargetObservation_enum() {
		return targetObservation_enum;
	}

	public void setTargetObservation_enum(Observation targetObservation_enum) {
		this.targetObservation_enum = targetObservation_enum;
	}


	public Observation getTargetObservation_package() {
		return targetObservation_package;
	}

	public void setTargetObservation_package(Observation targetObservation_package) {
		this.targetObservation_package = targetObservation_package;
	}

	@Override
	public String toString() {
		String stringToBeReturn="\n-------------Initial transition-----------------------\n";
		for (Transition transition : initialTransition) {
			stringToBeReturn+=transition.getEndState().getLabel()+
					"->"+transition.getTransitionValue()+"\n";
		}
		stringToBeReturn+=pre.toString();
		stringToBeReturn+=target.toString();
		stringToBeReturn+=other.toString();
		return stringToBeReturn;
	}
	//Testing the model
//	public static void main(String[] args) {
//		HMMConcept hmmConcept = new HMMConcept();
//		hmmConcept.init();
//		System.out.println(hmmConcept);
//		//////////////////////////
//		HMMConceptInit.initTransition(hmmConcept);
//		System.out.println(hmmConcept);
//	}
	
	
}
