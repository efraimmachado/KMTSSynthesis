package logic.booleanexpression;

import java.util.Set;


public interface IBooleanExpression {

	public Boolean getValue();

	public Set<AtomicProposition> getAtomicPrepositions();
	
	public String toStringAtomicPropositionsValue();

	public void updateAtomicPropositionValues(
			Set<AtomicProposition> propositionsWithNewValues);
}
