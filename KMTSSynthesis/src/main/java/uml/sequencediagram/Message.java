package uml.sequencediagram;

public class Message {

	private Action action;
	private LifelinePoint inPoint;
	private LifelinePoint outPoint;
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public LifelinePoint getInPoint() {
		return inPoint;
	}
	public void setInPoint(LifelinePoint inPoint) {
		this.inPoint = inPoint;
	}
	public LifelinePoint getOutPoint() {
		return outPoint;
	}
	public void setOutPoint(LifelinePoint outPoint) {
		this.outPoint = outPoint;
	}
	
}
