package cm.uy1.modelUse.model;

/**
 * This class contains the information of any frame of the 
 * probability table that will be calculated based on the HMM
 * @author azanzi
 *
 */

public class Frame {

	private String observedLabel;
	private String stateLabel;
	private double probabilityValue;
	
	//The pointer to the label that is used to build thid label
	private Frame frameBuilder;

	public Frame() {
		super();
	}

	public Frame(String observedLabel, String stateLabel, double probabilityValue, Frame frameBuilder) {
		super();
		this.observedLabel = observedLabel;
		this.stateLabel = stateLabel;
		this.probabilityValue = probabilityValue;
		this.frameBuilder = frameBuilder;
	}

	public Frame(String observedLabel, String stateLabel) {
		super();
		this.observedLabel = observedLabel;
		this.stateLabel = stateLabel;
	}

	public Frame(String observedLabel, String stateLabel, double probabilityValue) {
		super();
		this.observedLabel = observedLabel;
		this.stateLabel = stateLabel;
		this.probabilityValue = probabilityValue;
	}

	public String getObservedLabel() {
		return observedLabel;
	}

	public void setObservedLabel(String observedLabel) {
		this.observedLabel = observedLabel;
	}

	public String getStateLabel() {
		return stateLabel;
	}

	public void setStateLabel(String stateLabel) {
		this.stateLabel = stateLabel;
	}

	public double getProbabilityValue() {
		return probabilityValue;
	}

	public void setProbabilityValue(double probabilityValue) {
		this.probabilityValue = probabilityValue;
	}

	public Frame getFrameBuilder() {
		return frameBuilder;
	}

	public void setFrameBuilder(Frame frameBuilder) {
		this.frameBuilder = frameBuilder;
	}
	
	@Override
	public String toString() {
		String stringToBeReturn="";
		
		stringToBeReturn+="State label : "+this.stateLabel;
		stringToBeReturn+=" - Observed value : "+this.observedLabel;
		stringToBeReturn+=" - Probability of observation : "+this.probabilityValue;
		
		
		
		return stringToBeReturn+"\t";
	}
}
