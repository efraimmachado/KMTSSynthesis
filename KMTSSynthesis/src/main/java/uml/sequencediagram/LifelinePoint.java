package uml.sequencediagram;

public class LifelinePoint {

	private Integer position;
	private Lifeline lifeline;
	private Message message;
	
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Lifeline getLifeline() {
		return lifeline;
	}
	public void setLifeline(Lifeline lifeline) {
		this.lifeline = lifeline;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public boolean isInPoint()
	{
		if (message != null)
		{
			return this.equals(message.getInPoint());
		}
		return false;
	}

	public boolean isOutPoint()
	{
		if (message != null)
		{
			return this.equals(message.getOutPoint());
		}
		return false;
	}

	
}
