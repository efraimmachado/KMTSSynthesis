package kmts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.element.State;
import kmts.element.Transition;

public class KMTS {

	private State initialState;
	private Set<State> states;
	private Set<Transition> transitions;
	private boolean deterministic;
	private boolean allowDuplicatedStateLabels;

	
	public KMTS() {
		super();
		states = new HashSet<State>();
		transitions = new HashSet<Transition>();
		deterministic = true;
		allowDuplicatedStateLabels = false;
	}
	public State getInitialState() {
		return initialState;
	}
	public Set<State> getStates() {
		return states;
	}
	public Set<Transition> getTransitions() {
		return transitions;
	}
	
	public Transition addMustTransitionBetween(State fromState, State toState, String action)
	{
		Transition t = addMayTransitionBetween(fromState, toState, action);
		t.setMustTransition(true);
		return t;
	}
	
	public Transition addMayTransitionBetween(State fromState, State toState, String action)
	{
		checkCanAddMayTransitionBetween(fromState, toState, action);
		Transition t = new Transition();
		t.setAction(action);
		t.setFromState(fromState);
		t.setToState(toState);
		t.setMustTransition(false);
		fromState.addOutTrasition(t);
		toState.addInTrasition(t);
		transitions.add(t);
		return t;
	}
	
	
	private void checkCanAddMayTransitionBetween(State fromState,
			State toState, String action) {
		// TODO Auto-generated method stub
		
	}
	public void addState(State state)
	{
		checkCanAddState(state);
		state.clearAllTransitions();
		states.add(state);
	}
	
	public void addInitialState(State state)
	{
		addState(state);
		initialState = state;
	}
	
	private void checkCanAddState(State state) {
		// TODO Auto-generated method stub
	}
	public State getStateByLabel(String label)
	{
		if (states != null)
		{
			Iterator<State> it = states.iterator();
			while (it.hasNext())
			{
				State state = ((State)it.next());
				if (state.getLabel().equals(label))
				{
					return state;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("---[KMTS]---\n");
		if (initialState != null)
		{
			result.append("INITIAL STATE: "+initialState.getLabel()+"\n");
		}
		if (states != null)
		{
			Iterator<State> it = states.iterator();
			result.append("STATES: ");
			boolean first = true;
			while (it.hasNext())
			{
				if (first)
				{
					result.append(((State)it.next()).getLabel());
					first = false;
				}
				else
				{
					result.append(", "+((State)it.next()).getLabel());
				}
			}
			result.append("\n");
		}
		if (transitions != null)
		{
			Iterator<Transition> it = transitions.iterator();
			result.append("TRANSITIONS:\n");
			while (it.hasNext())
			{
				result.append(((Transition)it.next()).toString());
			}
			result.append("\n");
		}
		result.append("------------\n");
		return result.toString();
	}
}
