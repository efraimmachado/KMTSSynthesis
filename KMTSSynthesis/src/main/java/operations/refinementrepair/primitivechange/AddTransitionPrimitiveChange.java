package operations.refinementrepair.primitivechange;

import kmts.KMTS;
import kmts.element.State;
import kmts.element.Transition;

public class AddTransitionPrimitiveChange implements IPrimitiveChange {

	private State fromState;
	private String action;
	private State toState;
	private boolean mustTransition;
	
	public AddTransitionPrimitiveChange(State fromState, String action,
			State state, boolean mustTransition) 
	{
		this.fromState = fromState;
		this.action = action;
		this.toState = state;
		this.mustTransition = mustTransition;
	}

	public State getFromState() {
		return fromState;
	}

	public void setFromState(State fromState) {
		this.fromState = fromState;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public State getToState() {
		return toState;
	}

	public void setToState(State toState) {
		this.toState = toState;
	}

	public boolean isMustTransition() {
		return mustTransition;
	}

	public void setMustTransition(boolean mustTransition) {
		this.mustTransition = mustTransition;
	}
	
	@Override
	public boolean isConsistent(IPrimitiveChange otherChange) {
		if(otherChange instanceof RemoveTransitionPrimitiveChange)
		{
			RemoveTransitionPrimitiveChange removeChange = (RemoveTransitionPrimitiveChange)otherChange;
			
			if (removeChange.getFromState().equals(fromState) &&
				removeChange.getAction().equals(action) &&
				removeChange.getToState().equals(toState))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void apply(KMTS model) 
	{
		State from, to;
		from = model.getStateByLabel(fromState.getLabel());
		to = model.getStateByLabel(toState.getLabel());
		if (to.isDirectlyReachableFromMayTransitionAndAction(from, action))
		{
			if (mustTransition)
			{
				from.getOutTransition(action).setMustTransition(true);
			}
			else
			{
				from.getOutTransition(action).setMustTransition(false);
			}
		}
		else
		{
			if (mustTransition)
			{		
				model.addMustTransitionBetween(from, to, action);
			}
			else
			{
				model.addMayTransitionBetween(from, to, action);
			}
		}
	}

	@Override
	public String toString()
	{
		String transition = mustTransition?("=["+action+"]=>"):("-["+action+"]->");
		return "ADD("+fromState.getLabel()+transition+toState.getLabel()+")";
	}
			
	
}
