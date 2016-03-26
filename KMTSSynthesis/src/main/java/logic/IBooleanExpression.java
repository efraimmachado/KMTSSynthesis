package logic;

import java.util.Set;

import kmts.element.AtomicProposition;

public interface IBooleanExpression {

	public Boolean getValue();

	public Set<AtomicProposition> getAtomicPrepositions();
	
	public String toStringAtomicPropositionsValue();

	public void updateAtomicPropositionValues(
			Set<AtomicProposition> propositionsWithNewValues);
}
