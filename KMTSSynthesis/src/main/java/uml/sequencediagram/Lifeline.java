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
}
