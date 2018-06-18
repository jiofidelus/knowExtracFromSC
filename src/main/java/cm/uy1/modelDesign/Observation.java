package cm.uy1.modelDesign;

public class Observation {

	private String label;
	private double emissionValue;
	private State hiddenState;

	public Observation(String label, double emissionValue, 
			State hiddenState) {
		this.label = label;
		this.emissionValue = emissionValue;
		this.hiddenState = hiddenState;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getEmissionValue() {
		return emissionValue;
	}

	public void setEmissionValue(double emissionValue) {
		this.emissionValue = emissionValue;
	}

	public State getHiddenState() {
		return hiddenState;
	}

	public void setHiddenState(State hiddenState) {
		this.hiddenState = hiddenState;
	}
	
	@Override
	public String toString() {
		String stringToBeReturn="";
		
		stringToBeReturn+=
				" "+hiddenState+" "+label+""+emissionValue;
		
		return stringToBeReturn;
	}
	
	
}
