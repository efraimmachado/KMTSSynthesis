package relations.refinement;

import java.util.Iterator;

import relations.refinement.refinementgame.RefinementGame;

import kmts.KMTS;
import kmts.element.State;
import logic.booleanexpression.AtomicProposition;

public class RefinementRelation {

	
	public static boolean isRefinement(KMTS specification, KMTS model)
	{
		RefinementGame refinementGame = new RefinementGame(specification, model);
		refinementGame.generateRefinementGame();
		return refinementGame.getIsRefinement();

	}
	
	public static boolean areLiteralRefinament(State specificationState, State modelState) 
	{
		Iterator<AtomicProposition> itSpecification = specificationState.getPrepositions().iterator();
		AtomicProposition specificationLiteral;
		Boolean specificationLiteralValue, modelLiteralValue;
	
		while(itSpecification.hasNext())
		{
			specificationLiteral = itSpecification.next();
			specificationLiteralValue = specificationLiteral.getValue();
			modelLiteralValue = modelState.getPrepositionValue(specificationLiteral.getLiteral());
			if (!areLogicalRefinement(specificationLiteralValue, modelLiteralValue))
			{
				return false;
			}
		}
		return true;
		
	}

	public static boolean areLogicalRefinement(Boolean p, Boolean q)
	{
		if (p == null)
		{
			return true;
		}
		else if(p == q)
		{
			return true;
		}
		return false;
	}
	
	public static boolean arePropositionRefinement(
			AtomicProposition specificationLiteral,
			AtomicProposition modelLiteral) 
	{
		if (specificationLiteral.isUndefined())
		{
			return true;
		}
		else if(specificationLiteral.isFalse() && modelLiteral.isFalse())
		{
			return true;
		}
		else if(specificationLiteral.isTrue() && modelLiteral.isTrue())
		{
			return true;
		}
			
		return false;
	}

	
}
