package kmts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.element.State;
import logic.booleanexpression.IBooleanExpression;
import synthesizer.ComponentData;

public class KMTSCreator {

        //Não tem construtor nem atributos
    
        //Recebe um componente de um diagrama de sequencia
        //Cria um KMTS.
        //Rotula o KMTS com o rotulo do componente.
        //Cria o estado inicial do KMTS baseado no componente.
        //Retorna o KMTS criado.
	public KMTS createKMTSFromComponentData(ComponentData componentData) 
	{
		KMTS result = new KMTS(componentData.getComponent().getLabel());
		result.addInitialState(createInitialState(componentData));
		
		return result;
	}

        //!!Não entendi direito!!
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
	
        //Definir o que faz e como faz
	private State createInitialState(ComponentData componentData) {
		// TODO Auto-generated method stub
		return null;
	}

}
