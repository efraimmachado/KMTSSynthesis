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
		result.append(mustTransition?" =["+action+"]=> ":" -["+action+"]-> ");
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
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((action == null) ? 0 : action.hashCode());
//		result = prime * result
//				+ ((fromState == null) ? 0 : fromState.hashCode());
//		result = prime * result + (mustTransition ? 1231 : 1237);
//		result = prime * result + ((toState == null) ? 0 : toState.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Transition other = (Transition) obj;
//		if (action == null) {
//			if (other.action != null)
//				return false;
//		} else if (!action.equals(other.action))
//			return false;
//		if (fromState == null) {
//			if (other.fromState != null)
//				return false;
//		} else if (!fromState.equals(other.fromState))
//			return false;
//		if (mustTransition != other.mustTransition)
//			return false;
//		if (toState == null) {
//			if (other.toState != null)
//				return false;
//		} else if (!toState.equals(other.toState))
//			return false;
//		return true;
//	}
//	
}
