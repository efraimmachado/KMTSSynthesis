package kmts.element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class State {

	private String label;
	private Set<AtomPreposition> prepositions;
	private Set<Transition> inTransitions;
	private Set<Transition> outTransitions;

	
	public State() {
		super();
		inTransitions = new HashSet<Transition>();
		outTransitions = new HashSet<Transition>();
		prepositions = new HashSet<AtomPreposition>();
	}
	
	public void addPreposition(String literal, Boolean value)
	{
		AtomPreposition p = new AtomPreposition(literal, value);
		if (!prepositions.contains(p))
		{
			prepositions.add(p);
		}
	}
	
	public void updatePrepositionValue(String literal, Boolean value)
	{
		AtomPreposition p = getPreposition(literal);
		if (p != null)
		{
			p.setValue(value);
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
	public Boolean getPrepositionValue(String literal)
	{
		AtomPreposition p = getPreposition(literal);
		if (p != null)
		{
			return p.getValue();
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
	private AtomPreposition getPreposition(String literal) {
		Iterator<AtomPreposition> it = prepositions.iterator();
		while (it.hasNext())
		{
			AtomPreposition p = (AtomPreposition)it.next();
			if (p.getLiteral().equals(p))
			{
				return p;
			}
		}
		return null;
	}

	public Set<AtomPreposition> getPrepositions() {
		return prepositions;
	}
	
	public Set<Transition> getInTransitions() {
		return inTransitions;
	}
	
	public Set<Transition> getOutTransitions() {
		return outTransitions;
	}

	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void clearAllTransitions() {
		inTransitions.clear();
		outTransitions.clear();
	}	
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(getLabel()+ " (");
		Iterator<AtomPreposition> it = prepositions.iterator();
		boolean first = true;
		String toAppend;
		while(it.hasNext())
		{
			if (first)
			{
				toAppend = ((AtomPreposition)it.next()).toString();
			}
			else
			{
				toAppend = ", "+((AtomPreposition)it.next()).toString() ;
			}
			result.append(toAppend);
		}
		result.append(")\n");
		return result.toString();
	}

	public void addOutTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setFromState(this);
			outTransitions.add(t);
		}
	}

	public void addInTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setToState(this);
			inTransitions.add(t);
		}
	}
}
