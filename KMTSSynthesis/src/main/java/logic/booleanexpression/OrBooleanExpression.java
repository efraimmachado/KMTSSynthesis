package logic.booleanexpression;

import java.util.HashSet;
import java.util.Set;

import logic.ThreeLogicResolver;

public class OrBooleanExpression extends ABooleanExpression implements IBooleanExpression {

	private IBooleanExpression e1;
	private IBooleanExpression e2;
	
	public OrBooleanExpression(IBooleanExpression e1, IBooleanExpression e2)
	{
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public Boolean getValue() {
		return ThreeLogicResolver.solveOr(e1.getValue(), e2.getValue());
	}
	
	@Override
	public String toString()
	{
		return "("+e1.toString()+" || "+e2.toString()+")";
	}

	public Set<AtomicProposition> getAtomicPrepositions()
	{
		Set<AtomicProposition> result = new HashSet<AtomicProposition>();
		result.addAll(e1.getAtomicPrepositions());
		result.addAll(e2.getAtomicPrepositions());
		return result;
	}

	@Override
	public IBooleanExpression createCopiedExpression() 
	{
		return new OrBooleanExpression(e1.createCopiedExpression(), e2.createCopiedExpression());
	}

}
