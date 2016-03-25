package uml.sequencediagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Lifeline {

	private int currentPosition;
	private Component component;
	private Set<LifelinePoint> points;
	private Set<Message> messages;
	
	public Lifeline(String componentName) {
		super();
		currentPosition = 0;
		points = new HashSet<LifelinePoint>();
		messages = new HashSet<Message>();
		Component component = new Component();
		component.setLabel(componentName);
		setComponent(component);
		component.setLifeline(this);
	}

	public Component getComponent() {
		return component;
	}
	
	public void setComponent(Component component) {
		this.component = component;
	}
	
	public Set<LifelinePoint> getPoints() {
		return points;
	}
	
	public void setPoints(Set<LifelinePoint> points) {
		this.points = points;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public List<LifelinePoint> getOrderedLifelinePoints()
	{
		List<LifelinePoint> result = null;
		if (points != null)
		{
			result = new ArrayList<LifelinePoint>();
			result.addAll(points);
			Collections.sort(result, new Comparator<LifelinePoint>() {
				@Override
				public int compare(LifelinePoint p0, LifelinePoint p1) 
				{
					return p0.getPosition().compareTo(p1.getPosition());
				}
			});
		}
		return result;
	}
	
	public Set<Message> getIncomingMessages()
	{
		Set<Message> result = new HashSet<Message>();
		if (getPoints() != null)
		{
			Iterator<LifelinePoint> it = getPoints().iterator();
			LifelinePoint p;
			while (it.hasNext())
			{
				p = ((LifelinePoint)it.next());
				if (p.isOutPoint())
				{
					result.add(p.getMessage());
				}
			}
		}
		return result;
	}

	public Set<Message> getOutcomingMessages()
	{
		Set<Message> result = new HashSet<Message>();
		if (getPoints() != null)
		{
			Iterator<LifelinePoint> it = getPoints().iterator();
			LifelinePoint p;
			while (it.hasNext())
			{
				p = ((LifelinePoint)it.next());
				if (p.isInPoint())
				{
					result.add(p.getMessage());
				}
			}
		}
		return result;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void clearTimeInfo() {
		setCurrentPosition(0);
		messages.clear();
		points.clear();
		
	}

	public LifelinePoint addFromMessage(Message message) {
		LifelinePoint p = null;
		if (message != null)
		{
			p = new LifelinePoint();
			p.setPosition(currentPosition);
			p.setMessage(message);
			message.setOutPoint(p);
			addPoint(p);
		}
		return p;
	}

	public LifelinePoint addToMessage(Message message) {
		LifelinePoint p = null;
		if (message != null)
		{
			p = new LifelinePoint();
			p.setPosition(currentPosition);
			p.setMessage(message);
			message.setInPoint(p);
			addPoint(p);
			currentPosition++;
		}
		return p;
	}

	
	private void addPoint(LifelinePoint p) 
	{
		points.add(p);
		p.setLifeline(this);
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("["+getComponent().getLabel()+"]\n");
		List<LifelinePoint> orderedPoints = getOrderedLifelinePoints();
		LifelinePoint orderedPoint = null;
		int drewLifelinePos = 0;
		int posToDrawBetweenPoints = 0;
		for (int i = 0; i < orderedPoints.size(); i++)
		{
			orderedPoint = orderedPoints.get(i);
			posToDrawBetweenPoints = orderedPoint.getPosition() - drewLifelinePos;
			while (posToDrawBetweenPoints > 0)
			{
				result.append("    |\n");
				posToDrawBetweenPoints--;
				drewLifelinePos++;
			}
			String action = orderedPoint.getMessage().getAction().getAction();
			if (orderedPoint.isOutPoint())
			{
				result.append("    |---["+action+"]--->("+orderedPoint.getMessage().getInPoint().getLifeline().getComponent().getLabel()+")\n");
			}
			else
			{
				result.append("    |<---["+action+"]---("+orderedPoint.getMessage().getOutPoint().getLifeline().getComponent().getLabel()+")\n");
			}
		}
		return result.toString();
	}
}
