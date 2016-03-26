package logic.booleanexpression;

import java.util.Iterator;
import java.util.Set;

import kmts.element.AtomicProposition;
import logic.IBooleanExpression;

public abstract class ABooleanExpression implements IBooleanExpression {

	@Override
	public String toStringAtomicPropositionsValue()
	{
		StringBuilder result = new StringBuilder();
		Set<AtomicProposition> ap = getAtomicPrepositions();
		Iterator<AtomicProposition> it = ap.iterator();
		while (it.hasNext())
		{
			result.append(((AtomicProposition)it.next()).toStringWithValue()+"\n");
		}
		return result.toString();
	}
	
	@Override
	public void updateAtomicPropositionValues(Set<AtomicProposition> propositionsWithNewValues)
	{
		if (propositionsWithNewValues != null)
		{
			Iterator<AtomicProposition> itNewValues = propositionsWithNewValues.iterator();
			Set<AtomicProposition> propositions = getAtomicPrepositions();
			Iterator<AtomicProposition> itValues = propositions.iterator();
			AtomicProposition propositionToUpdate, p;
			//TODO is possible to obtain best performance changing hashset to hashmap indexing by literal
			while (itNewValues.hasNext())
			{
				propositionToUpdate = itNewValues.next();
				while(itValues.hasNext())
				{
					p = itValues.next();
					if (propositionToUpdate.getLiteral().equals(p.getLiteral()))
					{
						p.setValue(propositionToUpdate.getValue());
					}
				}
			}
		}
	}
}
