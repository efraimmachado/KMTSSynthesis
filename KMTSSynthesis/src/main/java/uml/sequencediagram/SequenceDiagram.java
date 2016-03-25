package uml.sequencediagram;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SequenceDiagram {

	private String label;
	private Set<Lifeline> lifelines;
	private Set<Message> messages;
	private int currentTimePosition; 
	
	public SequenceDiagram(String label) {
		super();
		setCurrentTimePosition(0);
		this.label = label;
		lifelines = new HashSet<Lifeline>();
		messages = new HashSet<Message>();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<Lifeline> getLifelines() {
		return lifelines;
	}
	public void setLifelines(Set<Lifeline> lifelines) {
		this.lifelines = lifelines;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public void addLifeline(Lifeline lifeline)
	{
		if (lifeline != null)
		{
			lifeline.clearTimeInfo();
			lifelines.add(lifeline);
		}
	}
	
	public void addMessageBetween(Lifeline fromLifeline, Lifeline toLifeline, Action action)
	{
		checkCanAddMessageBetween(fromLifeline, toLifeline, action);
		Message message = new Message();
		message.setAction(action);
		LifelinePoint inPoint, outPoint;
		outPoint = fromLifeline.addFromMessage(message);
		inPoint = toLifeline.addToMessage(message);
		message.setInPoint(inPoint);
		message.setOutPoint(outPoint);
		updateLifelinesPosition();
	}
	private void updateLifelinesPosition() 
	{
		Iterator<Lifeline> it = lifelines.iterator();
		while (it.hasNext())
		{
			((Lifeline)it.next()).setCurrentPosition(currentTimePosition);
		}
		currentTimePosition++;
		
	}
	
	private void checkCanAddMessageBetween(Lifeline fromLifeline,
			Lifeline toLifeline, Action action) {
		// TODO Auto-generated method stub
		
	}
	public int getCurrentTimePosition() {
		return currentTimePosition;
	}
	public void setCurrentTimePosition(int currentTimePosition) {
		this.currentTimePosition = currentTimePosition;
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("---[SEQUENCE DIAGRAM]----\n");
		result.append("LABEL:"+label+"\n");
		result.append("LIFELINES (COMPONENTS): \n");
		Iterator<Lifeline> it = lifelines.iterator();
		while (it.hasNext())
		{
			result.append(((Lifeline)it.next()).toString()+"\n");
		}
		
		
		return result.toString();
	}
}
