package cm.uy1.modelDesign;

public class Transition {
	
	private State startState;
	private State endState;
	private double transitionValue;
	
	public Transition(State startState, State endState, 
			double transitionValue) {
		this.startState = startState;
		this.endState = endState;
		this.transitionValue = transitionValue;
	}

	public State getStartState() {
		return startState;
	}

	public void setStartState(State startState) {
		this.startState = startState;
	}

	public State getEndState() {
		return endState;
	}

	public void setEndState(State endState) {
		this.endState = endState;
	}

	public double getTransitionValue() {
		return transitionValue;
	}

	public void setTransitionValue(double transitionValue) {
		this.transitionValue = transitionValue;
	}
	
	@Override
	public String toString() {
		String stringToBeReturn="";
		
		stringToBeReturn+=
				startState.getLabel()+" "+transitionValue+" "+endState.getLabel();
		
		return stringToBeReturn;
	}
	

}
