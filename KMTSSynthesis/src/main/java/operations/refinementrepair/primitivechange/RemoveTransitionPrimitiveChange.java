package operations.refinementrepair.primitivechange;

import kmts.KMTS;
import kmts.element.State;

public class RemoveTransitionPrimitiveChange implements IPrimitiveChange {



	private State fromState;
	private String action;
	private State toState;
	
	
	public RemoveTransitionPrimitiveChange(State fromState, String action,
			State toState) {
		this.fromState = fromState;
		this.action = action;
		this.toState = toState;
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
		if(otherChange instanceof AddTransitionPrimitiveChange)
		{
			AddTransitionPrimitiveChange addChange = (AddTransitionPrimitiveChange)otherChange;
			
			if (addChange.getFromState().equals(fromState) &&
				addChange.getAction().equals(action) &&
				addChange.getToState().equals(toState))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void apply(KMTS model) {
		State from = model.getStateByLabel(fromState.getLabel());
		model.removeTransition(from.getOutTransition(action));
		
	}

	@Override
	public String toString()
	{
		String transition = fromState.getOutTransition(action).isMustTransition()?"=":"-";
		return "REMOVE("+fromState.getLabel()+transition+"["+action+"]"+transition+">"+toState.getLabel()+")";
	}
	
}
