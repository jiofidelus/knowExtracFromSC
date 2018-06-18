package cm.uy1.modelDesign;

import java.util.List;

public class State {
	
	private String label;
	private List<Transition> transitions;
	private List<Observation> observations;
	
	public State() {
	}

	public State(String label) {
		this.label = label;
	}

	public State(String label, List<Transition> transitions, List<Observation> observations) {
		this.label = label;
		this.transitions = transitions;
		this.observations = observations;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	/**
	 * Method toString to show the HMM
	 */
	@Override
	public String toString() {
		String toBeReturn="State name: "+this.label;
		toBeReturn+="\n------------The list of hidden to transit to"
				+ "-------------------------------\n";
		
		//The list of transition to others states
		if(transitions!=null) {
			for (Transition transition : transitions)
				toBeReturn+=transition.getEndState().getLabel()+
						"->"+transition.getTransitionValue()+"\n";
		}
		
		toBeReturn+="\n------------The list of observation states emitted"
				+ "-------------------------------\n";

		//Add the list of observations
		if(observations!=null) {
			for (Observation observation : observations)
				toBeReturn+=observation.getLabel()+
				"->"+observation.getEmissionValue()+"\n";
		}
		return toBeReturn;
	}
	
	

}
