package relations.refinement.refinementgame;

import kmts.element.State;

public class DuplicatorVertice extends AVertice{

	private State specificationState;
	private State toState;
	private String toStateAction;
	private State modelState;
	
	public DuplicatorVertice(State specificationState, String toStateAction, State toState, State modelState, int id) {
		super();
		this.specificationState = specificationState;
		this.modelState = modelState;
		this.toState = toState;
		this.toStateAction = toStateAction;
		this.id = id;
	}

	public State getSpecificationState() {
		return specificationState;
	}

	public void setSpecificationState(State specificationState) {
		this.specificationState = specificationState;
	}

	public State getToState() {
		return toState;
	}

	public void setToState(State toState) {
		this.toState = toState;
	}

	public State getModelState() {
		return modelState;
	}

	public void setModelState(State modelState) {
		this.modelState = modelState;
	}

	public String getToStateAction() {
		return toStateAction;
	}

	public void setToStateAction(String toStateAction) {
		this.toStateAction = toStateAction;
	}
	
	@Override
	public String toString()
	{
		return "("+getSpecificationState().getLabel()+", "+getToState().getLabel()+", "+getModelState().getLabel()+")D"+id;
	}

	@Override
	public boolean isSpoilerVertice() {
		return false;
	}

	@Override
	public boolean isDuplicatorVertice() 
	{
		return true;
	}

	public boolean mustToMoveInSpecification() 
	{
		if (toState != null)
		{
			if (specificationState != null && toState != null)
			{
				return !toState.isDirectlyReachableFromMayTransition(specificationState);
			}
		}
		return false;
	}
	
	public boolean mustToMoveInModel() 
	{
		if (toState != null)
		{
			if (modelState != null && toState != null)
			{
				return !toState.isDirectlyReachableFromMustTransition(modelState);
			}
		}
		return false;
	}
}
