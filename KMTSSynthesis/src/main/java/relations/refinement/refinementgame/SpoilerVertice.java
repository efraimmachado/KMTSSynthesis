package relations.refinement.refinementgame;

import kmts.element.State;

public class SpoilerVertice extends AVertice{

	private State specificationState;
	private State modelState;
	
	public SpoilerVertice(State specificationState, State modelState, int id) {
		super();
		this.specificationState = specificationState;
		this.modelState = modelState;
		this.id = id;
	}

	public State state() {
		return specificationState;
	}

	public void setSpecificationState(State specificationState) {
		this.specificationState = specificationState;
	}

	public State getModelState() {
		return modelState;
	}

	public void setModelState(State modelState) {
		this.modelState = modelState;
	}

	public State getSpecificationState() {
		return specificationState;
	}
	
	@Override
	public String toString()
	{
		return "("+getSpecificationState().getLabel()+", "+getModelState().getLabel()+")S"+id;
	}

	@Override
	public boolean isSpoilerVertice() {
		return true;
	}

	@Override
	public boolean isDuplicatorVertice() {
		return false;
	}
	
}
