package cm.uy1.modelUse.model;

/**
 * This class contains informations on one colone which is composed 
 * of the probability that the state will generate the observation
 * 
 * A column may have one or many frame
 * 
 * In this version, we will consider that a column has just four frames
 * corresponding to the four hidden states that we have described in 
 * this source code
 * 
 * In the next version, a column will just have a list of the states that it is composed
 * @author azanzi
 *
 */
public class Column {
	
	private Frame preFrame;
	private Frame targetFrame;
	private Frame postFrame;
	private Frame otherFrame;

	public Column() {
	}

	public Frame getPreFrame() {
		return preFrame;
	}

	public void setPreFrame(Frame preFrame) {
		this.preFrame = preFrame;
	}

	public Frame getTargetFrame() {
		return targetFrame;
	}

	public void setTargetFrame(Frame targetFrame) {
		this.targetFrame = targetFrame;
	}

	public Frame getPostFrame() {
		return postFrame;
	}

	public void setPostFrame(Frame postFrame) {
		this.postFrame = postFrame;
	}

	public Frame getOtherFrame() {
		return otherFrame;
	}

	public void setOtherFrame(Frame otherFrame) {
		this.otherFrame = otherFrame;
	}
	
	@Override
	public String toString() {
		String stringToBeReturn = " ";

		if(preFrame!=null) stringToBeReturn+=" " + preFrame;
		if(targetFrame!=null) stringToBeReturn+=" " + targetFrame;
		if(postFrame!=null) stringToBeReturn+=" " + postFrame;
		if(otherFrame!=null) stringToBeReturn+=" " + otherFrame;
		
		return stringToBeReturn+"\n";
	}
	

}
