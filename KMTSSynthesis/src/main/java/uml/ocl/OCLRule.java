package uml.ocl;

import java.util.HashSet;
import java.util.Set;
import logic.booleanexpression.AtomicProposition;
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
        
        //Retorna as variaveis usadas na regra
        public Set<AtomicProposition> getAtomicProposition() 
        {
                Set<AtomicProposition> result = new HashSet<AtomicProposition>();
                result = expression.getAtomicPrepositions();
                return result;
        }	
}
