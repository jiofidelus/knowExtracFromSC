package cm.uy1.modelDefinition;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.modelDesign.Observation;
import cm.uy1.modelDesign.State;
import cm.uy1.modelDesign.Transition;
import cm.uy1.trainning.HMMRuleInit;

public class HMMRules {
	
	//The states
	private State pre = new State();
	private State target = new State();
	private State post = new State();
	private State other = new State();

	//The transitions
	private Transition prePreTransition = new Transition(pre, pre, 0);
	private Transition preTargetTransition = new Transition(pre, target, 0);
	private Transition prePostTransition = new Transition(pre, post, 0);
	private Transition preOtherTransition = new Transition(pre, other, 0);

	private Transition targetPreTransition = new Transition(target, pre, 0);
	private Transition targetTargetTransition = new Transition(target, target, 0);
	private Transition targetPostTransition = new Transition(target, post, 0);
	private Transition targetOtherTransition = new Transition(target, other, 0);

	private Transition postPreTransition = new Transition(post, pre, 0);
	private Transition postTargetTransition = new Transition(post, target, 0);
	private Transition postPostTransition = new Transition(post, post, 0);
	private Transition postOtherTransition = new Transition(post, other, 0);

	private Transition otherPreTransition = new Transition(other, pre, 0);
	private Transition otherTargerTransition = new Transition(other, target, 0);
	private Transition otherPostTransition = new Transition(other, post, 0);
	private Transition otherOtherTransition = new Transition(other, other, 0);
	
	
	//The observations
	//The PRE observation
	private List<String> preObservation = new ArrayList<>() {{
		add(";");
		add("{");
		add("}");
	}};
	private Observation preObservation_SemiCol = new Observation(";", 0, other);
	private Observation preObservation_openCurlBrass = new Observation("{", 0, other);
	private Observation preObservation_closeCurBrass = new Observation("}", 0, other);
	//The TARGET observation
	private static List<String> targetObservation = new ArrayList<>() {{
		add("if");
		add("else");
		add("switch");
	}};
	private Observation targetObservation_if = new Observation("if", 0, target);
	private Observation targetObservation_else = new Observation("else", 0, target);
	private Observation targetObservation_switch = new Observation("switch", 0, target);
	private Observation targetObservation_other = new Observation("other", 0, target);
	//The POST observation
	private List<String> postObservation = new ArrayList<>() {{
		add("}");
		add(";");
	}};
	private Observation postObservation_brassess = new Observation("}", 0, post);
	private Observation postObservation_semiColon = new Observation(";", 0, post);
	
	//All other observations
	private Observation otherObservation_other = new Observation("other", 0, other);
	
	//Definition of the initial probability
	List<Transition> initialTransition = new ArrayList<>();
	
	/**
	 * This method initialize the HMM
	 */
	public void init() {
		//Initialize the initial transition
		initialTransition.add(new Transition(new State(), pre, 0));
		initialTransition.add(new Transition(new State(), target, 0));
		initialTransition.add(new Transition(new State(), post, 0));
		initialTransition.add(new Transition(new State(), other, 0));
		
		//Initialize the PRE transition
		List<Transition> preTransition = new ArrayList<>() {{
			add(prePreTransition);
			add(preTargetTransition);
			add(prePostTransition);
			add(preOtherTransition);
		}};
		//Initialize the PRE observation
		List<Observation> preObserved = new ArrayList<>() {{
			add(preObservation_closeCurBrass);
			add(preObservation_openCurlBrass);
			add(preObservation_SemiCol);
		}};
		//Set the transition vector and the observation vector of the 
		//PRE state
		pre.setLabel("PRE");
		pre.setTransitions(preTransition);
		pre.setObservations(preObserved);
	
		//Initialize the target transition
		List<Transition> targetTransition = new ArrayList<>() {{
			add(targetPreTransition);
			add(targetTargetTransition);
			add(targetPostTransition);
			add(targetOtherTransition);
		}};
		//Initialize the TARGET observation
		List<Observation> targetObserved = new ArrayList<>() {{
			add(targetObservation_if);
			add(targetObservation_else);
			add(targetObservation_switch);
			add(targetObservation_other);
		}};
		//Set the TARGET state
		target.setLabel("TARGET");
		target.setTransitions(targetTransition);
		target.setObservations(targetObserved);
		
		//Initialize the POST transition
		List<Transition> postTransition = new ArrayList<>() {{
			add(postPreTransition);
			add(postTargetTransition);
			add(postPostTransition);
			add(postOtherTransition);
		}};
		//Initialize the POST observations
		List<Observation> postObserved = new ArrayList<>() {{
			add(postObservation_brassess);
		}};
		//Set the POST state
		post.setLabel("POST");
		post.setTransitions(postTransition);
		post.setObservations(postObserved);
		
		//Initialize the OTHER transition
		List<Transition> otherTransition = new ArrayList<>() {{
			add(otherPreTransition);
			add(otherTargerTransition);
			add(otherPostTransition);
			add(otherOtherTransition);
		}};
		//Initialize the OTHER observation state
		List<Observation> otherObserved = new ArrayList<>() {{
			add(otherObservation_other);
		}};
		//Set the OTHER state
		other.setLabel("OTHER");
		other.setTransitions(otherTransition);
		other.setObservations(otherObserved);
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

	public State getPost() {
		return post;
	}

	public void setPost(State post) {
		this.post = post;
	}

	public State getOther() {
		return other;
	}

	public void setOther(State other) {
		this.other = other;
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

	public Transition getPrePostTransition() {
		return prePostTransition;
	}

	public void setPrePostTransition(Transition prePostTransition) {
		this.prePostTransition = prePostTransition;
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

	public Transition getTargetPostTransition() {
		return targetPostTransition;
	}

	public void setTargetPostTransition(Transition targetPostTransition) {
		this.targetPostTransition = targetPostTransition;
	}

	public Transition getTargetOtherTransition() {
		return targetOtherTransition;
	}

	public void setTargetOtherTransition(Transition targetOtherTransition) {
		this.targetOtherTransition = targetOtherTransition;
	}

	public Transition getPostPreTransition() {
		return postPreTransition;
	}

	public void setPostPreTransition(Transition postPreTransition) {
		this.postPreTransition = postPreTransition;
	}

	public Transition getPostTargetTransition() {
		return postTargetTransition;
	}

	public void setPostTargetTransition(Transition postTargetTransition) {
		this.postTargetTransition = postTargetTransition;
	}

	public Transition getPostPostTransition() {
		return postPostTransition;
	}

	public void setPostPostTransition(Transition postPostTransition) {
		this.postPostTransition = postPostTransition;
	}

	public Transition getPostOtherTransition() {
		return postOtherTransition;
	}

	public void setPostOtherTransition(Transition postOtherTransition) {
		this.postOtherTransition = postOtherTransition;
	}

	public Transition getOtherPreTransition() {
		return otherPreTransition;
	}

	public void setOtherPreTransition(Transition otherPreTransition) {
		this.otherPreTransition = otherPreTransition;
	}

	public Transition getOtherTargerTransition() {
		return otherTargerTransition;
	}

	public void setOtherTargerTransition(Transition otherTargerTransition) {
		this.otherTargerTransition = otherTargerTransition;
	}

	public Transition getOtherPostTransition() {
		return otherPostTransition;
	}

	public void setOtherPostTransition(Transition otherPostTransition) {
		this.otherPostTransition = otherPostTransition;
	}

	public Transition getOtherOtherTransition() {
		return otherOtherTransition;
	}

	public void setOtherOtherTransition(Transition otherOtherTransition) {
		this.otherOtherTransition = otherOtherTransition;
	}

	public List<String> getPreObservation() {
		return preObservation;
	}

	public void setPreObservation(List<String> preObservation) {
		this.preObservation = preObservation;
	}

	public Observation getPreObservation_SemiCol() {
		return preObservation_SemiCol;
	}

	public void setPreObservation_SemiCol(Observation preObservation_SemiCol) {
		this.preObservation_SemiCol = preObservation_SemiCol;
	}

	public Observation getPreObservation_openCurlBrass() {
		return preObservation_openCurlBrass;
	}

	public void setPreObservation_openCurlBrass(Observation preObservation_openCurlBrass) {
		this.preObservation_openCurlBrass = preObservation_openCurlBrass;
	}

	public Observation getPreObservation_closeCurBrass() {
		return preObservation_closeCurBrass;
	}

	public void setPreObservation_closeCurBrass(Observation preObservation_closeCurBrass) {
		this.preObservation_closeCurBrass = preObservation_closeCurBrass;
	}

	public static List<String> getTargetObservation() {
		return targetObservation;
	}

	public void setTargetObservation(List<String> targetObservation) {
		this.targetObservation = targetObservation;
	}

	public Observation getTargetObservation_if() {
		return targetObservation_if;
	}

	public void setTargetObservation_if(Observation targetObservation_if) {
		this.targetObservation_if = targetObservation_if;
	}

	public Observation getTargetObservation_else() {
		return targetObservation_else;
	}

	public void setTargetObservation_else(Observation targetObservation_else) {
		this.targetObservation_else = targetObservation_else;
	}

	public Observation getTargetObservation_switch() {
		return targetObservation_switch;
	}

	public void setTargetObservation_switch(Observation targetObservation_switch) {
		this.targetObservation_switch = targetObservation_switch;
	}

	public Observation getTargetObservation_other() {
		return targetObservation_other;
	}

	public void setTargetObservation_other(Observation targetObservation_other) {
		this.targetObservation_other = targetObservation_other;
	}

	public List<String> getPostObservation() {
		return postObservation;
	}

	public void setPostObservation(List<String> postObservation) {
		this.postObservation = postObservation;
	}

	public Observation getPostObservation_brassess() {
		return postObservation_brassess;
	}

	public void setPostObservation_brassess(Observation postObservation_brassess) {
		this.postObservation_brassess = postObservation_brassess;
	}

	public Observation getPostObservation_semiColon() {
		return postObservation_semiColon;
	}

	public void setPostObservation_semiColon(Observation postObservation_semiColon) {
		this.postObservation_semiColon = postObservation_semiColon;
	}

	public Observation getOtherObservation_other() {
		return otherObservation_other;
	}

	public void setOtherObservation_other(Observation otherObservation_other) {
		this.otherObservation_other = otherObservation_other;
	}

	public List<Transition> getInitialTransition() {
		return initialTransition;
	}

	public void setInitialTransition(List<Transition> initialTransition) {
		this.initialTransition = initialTransition;
	}
	
	@Override
	public String toString() {
		String toBeReturn="\n-------------Initial transition-----------------------\n";
		for (Transition transition : initialTransition) {
			toBeReturn+=transition.getEndState().getLabel()+
					"->"+transition.getTransitionValue()+"\n";
		}
		toBeReturn+=pre.toString();
		toBeReturn+=target.toString();
		toBeReturn+=post.toString();
		toBeReturn+=other.toString();
		
		return toBeReturn;
	}
	
//	public static void main(String[] args) {
//		HMMRules hmmRules = new HMMRules();
//		hmmRules.init();
//		System.out.println(hmmRules);
//		////////////////////////////////////////
//		HMMRuleInit.initTransition(hmmRules);
//		System.out.println(hmmRules);
//		
//	}

}











