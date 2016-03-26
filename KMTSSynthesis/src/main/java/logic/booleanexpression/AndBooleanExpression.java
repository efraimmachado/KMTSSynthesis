package logic.booleanexpression;

import logic.IBooleanExpression;
import logic.ThreeLogicResolver;

public class AndBooleanExpression implements IBooleanExpression {

	private IBooleanExpression e1;
	private IBooleanExpression e2;
	
	public AndBooleanExpression(IBooleanExpression e1, IBooleanExpression e2)
	{
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public Boolean getValue() {
		return ThreeLogicResolver.solveAnd(e1.getValue(), e2.getValue());
	}
	
	@Override
	public String toString()
	{
		return "("+e1.toString()+"^"+e2.toString()+")";
	}

}
