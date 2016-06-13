package operations.refinementrepair.repairtemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import operations.refinementrepair.causeoffailure.ThereIsTransitionCauseOfFailure;
import operations.refinementrepair.primitivechange.IPrimitiveChange;

import kmts.KMTS;
import kmts.element.Transition;

public class RemoveTransitionRepairTemplate extends ARepairTemplate implements IRepairTemplate {

	private Transition transitionToRemove;
	
	public RemoveTransitionRepairTemplate(
			ThereIsTransitionCauseOfFailure causeOfFailure) 
	{
		transitionToRemove = causeOfFailure.getTransition();
	}

	public Transition getTransitionToRemove() {
		return transitionToRemove;
	}

	public void setTransitionToRemove(Transition transitionToRemove) {
		this.transitionToRemove = transitionToRemove;
	}

	@Override
	public Set<List<IPrimitiveChange>> generatePossibleRepairs(KMTS model) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		List<IPrimitiveChange> modifications = getModificationsToRemoveTransition(transitionToRemove);
		//TODO - Alterar transicao de forma que o duplicator possa jogar
		result.add(modifications);
		return result;
	}

}
