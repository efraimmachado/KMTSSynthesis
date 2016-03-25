package kmts.element;

public class Transition {

	private String action;
	private State fromState;
	private State toState;
	private boolean mustTransition;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public State getFromState() {
		return fromState;
	}
	public void setFromState(State fromState) {
		this.fromState = fromState;
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
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(toStringState(fromState));
		result.append(mustTransition?" ==> ":" --> ");
		result.append(toStringState(toState));
		result.append("\n");
		return result.toString();
	}
	
	private String toStringState(State state)
	{
		if (state != null)
		{
			return state.getLabel();
		}
		return "null";
	}
	
}
