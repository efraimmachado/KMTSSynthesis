package uml.ocl;

import logic.booleanexpression.IBooleanExpression;
import uml.sequencediagram.Action;

public class OCLRule {
	
	private Action action;
	private IBooleanExpression expression;
	
	
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public IBooleanExpression getExpression() {
		return expression;
	}

	public void setExpression(IBooleanExpression expression) {
		this.expression = expression;
	}
	
}
