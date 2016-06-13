package operations.refinementrepair.causeoffailure;

import kmts.element.Transition;
import relations.refinement.refinementgame.DuplicatorVertice;

public class ThereIsTransitionCauseOfFailure extends CauseOfFailure {

	private Transition transition;
	
	public ThereIsTransitionCauseOfFailure(DuplicatorVertice failureWitness) 
	{
		//transition = failureWitness.getSpecificationState().getOutTransition(failureWitness.getToStateAction());
		transition = failureWitness.getModelState().getOutTransition(failureWitness.getToStateAction());
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}
	
	@Override
	public String toString()
	{
		return "("+transition.getFromState().getLabel()+", "+transition.getAction()+", "+transition.getToState().getLabel()+" IN R-";
	}

}
