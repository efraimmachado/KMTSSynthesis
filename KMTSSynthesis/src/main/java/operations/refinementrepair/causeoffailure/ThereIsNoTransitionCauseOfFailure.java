package operations.refinementrepair.causeoffailure;

import kmts.element.State;
import relations.refinement.refinementgame.DuplicatorVertice;

public class ThereIsNoTransitionCauseOfFailure extends CauseOfFailure {

	private State fromState;
	
	private String desiredAction;
	
	private State stateThatToStateNeedToBeSimilar;
	
	public ThereIsNoTransitionCauseOfFailure(DuplicatorVertice failureWitness) 
	{
		fromState = failureWitness.getModelState();
		desiredAction = failureWitness.getToStateAction();
		stateThatToStateNeedToBeSimilar = failureWitness.getToState();
	}

	public State getFromState() {
		return fromState;
	}

	public void setFromState(State fromState) {
		this.fromState = fromState;
	}

	public String getDesiredAction() {
		return desiredAction;
	}

	public void setDesiredAction(String desiredAction) {
		this.desiredAction = desiredAction;
	}

	public State getStateThatToStateNeedToBeSimilar() {
		return stateThatToStateNeedToBeSimilar;
	}

	public void setStateThatToStateNeedToBeSimilar(
			State stateThatToStateNeedToBeSimilar) {
		this.stateThatToStateNeedToBeSimilar = stateThatToStateNeedToBeSimilar;
	}

	@Override
	public String toString()
	{
		return "("+fromState.getLabel()+", "+desiredAction+", "+stateThatToStateNeedToBeSimilar.getLabel()+") NOT IN R+ OR L("+fromState.getLabel()+") !<= L("+stateThatToStateNeedToBeSimilar.getLabel()+")";
	}
	
}
