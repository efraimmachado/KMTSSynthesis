package kmts.element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.booleanexpression.AtomicProposition;

public class State {

	private String label;
	private Set<AtomicProposition> prepositions;
	private Set<Transition> inTransitions;
	private Set<Transition> outTransitions;

	
	public State() {
		super();
		inTransitions = new HashSet<Transition>();
		outTransitions = new HashSet<Transition>();
		prepositions = new HashSet<AtomicProposition>();
	}
	
	public void addPreposition(String literal, Boolean value)
	{
		AtomicProposition p = new AtomicProposition(literal, value);
		if (!prepositions.contains(p))
		{
			prepositions.add(p);
		}
	}
	
	public void updatePrepositionValue(String literal, Boolean value)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			p.setValue(value);
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
	public Boolean getPrepositionValue(String literal)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			return p.getValue();
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
	private AtomicProposition getPreposition(String literal) {
		Iterator<AtomicProposition> it = prepositions.iterator();
		while (it.hasNext())
		{
			AtomicProposition p = (AtomicProposition)it.next();
			if (p.getLiteral().equals(p))
			{
				return p;
			}
		}
		return null;
	}

	public Set<AtomicProposition> getPrepositions() {
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
		Iterator<AtomicProposition> it = prepositions.iterator();
		boolean first = true;
		String toAppend;
		while(it.hasNext())
		{
			if (first)
			{
				toAppend = ((AtomicProposition)it.next()).toString();
				first = false;
			}
			else
			{
				toAppend = ", "+((AtomicProposition)it.next()).toString() ;
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
