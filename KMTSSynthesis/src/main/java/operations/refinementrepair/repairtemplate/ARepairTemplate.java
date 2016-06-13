package operations.refinementrepair.repairtemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kmts.element.State;
import kmts.element.Transition;
import logic.booleanexpression.AtomicProposition;
import operations.refinementrepair.primitivechange.IPrimitiveChange;
import operations.refinementrepair.primitivechange.ModifyStateLabelValuePrimitiveChange;
import operations.refinementrepair.primitivechange.RemoveTransitionPrimitiveChange;
import relations.refinement.RefinementRelation;

public abstract class ARepairTemplate implements IRepairTemplate {

	protected List<IPrimitiveChange> getModificationsToRemoveTransition(
			Transition transition) {
		List<IPrimitiveChange> result = new ArrayList<IPrimitiveChange>();
		result.add(new RemoveTransitionPrimitiveChange(transition.getFromState(), transition.getAction(), transition.getToState()));
		return result;
	}
	
	protected List<IPrimitiveChange> getModificationsForLabelRefinement(State toStateMusBeStateRefinement, State state) {
		List<IPrimitiveChange> result = new ArrayList<IPrimitiveChange>();
		
		if (!RefinementRelation.areLiteralRefinament(toStateMusBeStateRefinement, state))
		{
			Iterator<AtomicProposition> it = toStateMusBeStateRefinement.getPrepositions().iterator();
			AtomicProposition p = null;
			List<IPrimitiveChange> modifications = new ArrayList<IPrimitiveChange>();
			while (it.hasNext())
			{
				p = it.next();
				modifications = getModificationsToCopyPropositonValue(state, p);
				result.addAll(modifications);
			}
		}
		return result;
	}


	private List<IPrimitiveChange> getModificationsToCopyPropositonValue(
			State state, AtomicProposition p) 
	{
		List<IPrimitiveChange> result = new ArrayList<IPrimitiveChange>();
		if (!p.isUndefined())
		{
			result.add(new ModifyStateLabelValuePrimitiveChange(state, p));			
		}
		return result;
	}

}
