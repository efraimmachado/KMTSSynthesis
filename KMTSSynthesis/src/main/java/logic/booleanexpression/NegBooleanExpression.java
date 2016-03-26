package logic.booleanexpression;

import logic.IBooleanExpression;
import logic.ThreeLogicResolver;

public class NegBooleanExpression implements IBooleanExpression {

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


}
