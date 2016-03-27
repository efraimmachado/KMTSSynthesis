package kmts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.element.State;
import logic.booleanexpression.IBooleanExpression;
import synthesizer.ComponentData;

public class KMTSCreator {

	public KMTS createKMTSFromComponentData(ComponentData componentData) 
	{
		KMTS result = new KMTS(componentData.getComponent().getLabel());
		result.addInitialState(createInitialState(componentData));
		
		return result;
	}

	private Set<State> getStatesWhenExpressionIsValid(KMTS kmts, IBooleanExpression expression)
	{
		Set<State> result = new HashSet<State>();
		Set<State> statesFromKMTS = kmts.getStates();
		Iterator<State> it = statesFromKMTS.iterator();
		IBooleanExpression copyExpression = null;
		State currentState = null;
		while(it.hasNext())
		{
			copyExpression = expression.createCopiedExpression();
			currentState = it.next();
			copyExpression.updateAtomicPropositionValues(currentState.getPrepositions());
			if (copyExpression.isTrue())
			{
				result.add(currentState);
			}
		}
		return result;
	}
	
	private State createInitialState(ComponentData componentData) {
		// TODO Auto-generated method stub
		return null;
	}

}
