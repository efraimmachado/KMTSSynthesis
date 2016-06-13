package operations.refinementrepair.causeoffailure;

import kmts.element.State;

public class SpoilerCannotStartGameCauseOfFailure extends CauseOfFailure {

	private State specificationInitialState;
	
	public SpoilerCannotStartGameCauseOfFailure(State specificationState) {
		this.specificationInitialState = specificationState;
	}

	public State getSpecificationInitialState() {
		return specificationInitialState;
	}

}
