package logic.booleanexpression;

import java.util.HashSet;
import java.util.Set;

import logic.ThreeLogicResolver;

public class NegBooleanExpression extends ABooleanExpression implements IBooleanExpression {

	private IBooleanExpression e;
	
	public NegBooleanExpression(IBooleanExpression e)
	{
		super();
		this.e = e;
	}
	
	@Override
	public Boolean getValue() {
		return ThreeLogicResolver.solveNeg(e.getValue());
	}
	
	@Override
	public String toString()
	{
		return "¬("+e.toString()+")";
	}

	public Set<AtomicProposition> getAtomicPrepositions()
	{
		Set<AtomicProposition> result = new HashSet<AtomicProposition>();
		result.addAll(e.getAtomicPrepositions());
		return result;
	}
	
	@Override
	public IBooleanExpression createCopiedExpression() 
	{
		return new NegBooleanExpression(e.createCopiedExpression());
	}

}
