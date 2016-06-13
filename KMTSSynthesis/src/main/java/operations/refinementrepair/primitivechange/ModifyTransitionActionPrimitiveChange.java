package operations.refinementrepair.primitivechange;

import kmts.KMTS;
import kmts.element.State;
import kmts.element.Transition;

public class ModifyTransitionActionPrimitiveChange implements IPrimitiveChange {

	private State fromState;
	private String action;
	private State toState;
	private String newAction;
	
	public ModifyTransitionActionPrimitiveChange(Transition transition,
			String action) {
		fromState = transition.getFromState();
		this.action = transition.getAction();
		toState = transition.getToState();
		newAction = action;
	}

	public String getNewAction() {
		return newAction;
	}


	public void setNewAction(String newAction) {
		this.newAction = newAction;
	}

	public State getFromState() {
		return fromState;
	}

	public String getAction() {
		return action;
	}

	public State getToState() {
		return toState;
	}

	public void setFromState(State fromState) {
		this.fromState = fromState;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setToState(State toState) {
		this.toState = toState;
	}

	@Override
	public boolean isConsistent(IPrimitiveChange otherChange) {
		if (otherChange instanceof ModifyTransitionActionPrimitiveChange)
		{
			ModifyTransitionActionPrimitiveChange change = (ModifyTransitionActionPrimitiveChange)otherChange;
			if (fromState.equals(change.getFromState()) &&
				toState.equals(change.getToState()) &&
				!newAction.equals(change.getAction()))
				{
					return false;
				}
		}
		else if (otherChange instanceof RemoveTransitionPrimitiveChange)
		{
			RemoveTransitionPrimitiveChange change = (RemoveTransitionPrimitiveChange)otherChange;
			if (fromState.equals(change.getFromState()) &&
					toState.equals(change.getToState()) &&
					newAction.equals(change.getAction()))
					{
						return false;
					}
		}
		return true;
	}
	
	@Override
	public void apply(KMTS model) 
	{
		State from = model.getStateByLabel(fromState.getLabel());
		from.getOutTransition(action).setAction(newAction);
	}

	@Override
	public String toString()
	{
		String transition = fromState.getOutTransition(action).isMustTransition()?"=":"-";
		return "MOD("+fromState.getLabel()+transition+"["+action+"]"+transition+">"+toState.getLabel()+") TO ("+fromState.getLabel()+transition+"["+newAction+"]"+transition+">"+toState.getLabel()+")";
	}
}
