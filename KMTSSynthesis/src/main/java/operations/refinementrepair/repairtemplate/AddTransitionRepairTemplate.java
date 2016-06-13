package operations.refinementrepair.repairtemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import operations.refinementrepair.causeoffailure.ThereIsNoTransitionCauseOfFailure;
import operations.refinementrepair.primitivechange.AddTransitionPrimitiveChange;
import operations.refinementrepair.primitivechange.IPrimitiveChange;
import operations.refinementrepair.primitivechange.ModifyStateLabelValuePrimitiveChange;
import operations.refinementrepair.primitivechange.ModifyTransitionActionPrimitiveChange;
import operations.refinementrepair.primitivechange.RemoveTransitionPrimitiveChange;

import kmts.KMTS;
import kmts.element.State;
import kmts.element.Transition;
import logic.booleanexpression.AtomicProposition;
import relations.refinement.RefinementRelation;

public class AddTransitionRepairTemplate extends ARepairTemplate {

	private State fromState;
	private String action;
	private State toStateMusBeStateRefinement;
	private KMTS model;
	
	public AddTransitionRepairTemplate(KMTS model,
			ThereIsNoTransitionCauseOfFailure causeOfFailure) 
	{
		this.model = model;
		fromState = causeOfFailure.getFromState();
		action = causeOfFailure.getDesiredAction();
		toStateMusBeStateRefinement = causeOfFailure.getStateThatToStateNeedToBeSimilar();
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


	public State getToStateMusBeStateRefinement() {
		return toStateMusBeStateRefinement;
	}


	public void setToStateMusBeStateRefinement(State toStateMusBeStateRefinement) {
		this.toStateMusBeStateRefinement = toStateMusBeStateRefinement;
	}


	@Override
	public Set<List<IPrimitiveChange>> generatePossibleRepairs(KMTS model) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		Set<State> allStates = model.getStates();
		Iterator<State> it = allStates.iterator();
		State state = null;
		Map<State, List<IPrimitiveChange>> modificationsPerState = new HashMap<State, List<IPrimitiveChange>>();
		List<IPrimitiveChange> modificationsInState = new ArrayList<IPrimitiveChange>();
		Set<List<IPrimitiveChange>> modificationsInTransition = new HashSet<List<IPrimitiveChange>>();
		Iterator<List<IPrimitiveChange>> itModTransition;
		while(it.hasNext())
		{
			state = it.next();
			modificationsInState = getModificationsForLabelRefinement(toStateMusBeStateRefinement, state);
			modificationsInTransition = getModificationsForCreateTransitionTo(state);
			itModTransition = modificationsInTransition.iterator();
			List<IPrimitiveChange> possibility;
			while(itModTransition.hasNext())
			{
				possibility = itModTransition.next();
				possibility.addAll(modificationsInState);
				modificationsPerState.put(state, possibility);
			}
			
		}
		result.addAll(modificationsPerState.values());
		return result;
	}

	private Set<List<IPrimitiveChange>> getModificationsForCreateTransitionTo(
			State state) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		Transition transition = fromState.getOutTransition(action); 
		List<IPrimitiveChange> modificationsToAddTransition;
		if (transition != null)
		{
			//if exists a transition with the correct action to wrong state
			if (!transition.getToState().equals(state))
			{
				//possibility to alter existent transition altering only the action
				Set<List<IPrimitiveChange>> alterTransitionPossibility = getModificationsToAlterTransition(transition);
				//possibility to remove existent transition without any type of altering
				List<IPrimitiveChange> removeTransitionPossibility = getModificationsToRemoveTransition(transition);
				
				//adding correct transition
				modificationsToAddTransition = getModificationsToAddTransition(fromState, action, state);
				
				
				Set<List<IPrimitiveChange>> unionOfPossibilities = new HashSet<List<IPrimitiveChange>>();
				unionOfPossibilities.addAll(alterTransitionPossibility);
				unionOfPossibilities.add(removeTransitionPossibility);
				Iterator<List<IPrimitiveChange>> itPossibilities = unionOfPossibilities.iterator();
				while(itPossibilities.hasNext())
				{
					itPossibilities.next().addAll(modificationsToAddTransition);
				}
				result.addAll(unionOfPossibilities);
			}
			else
			{
				ArrayList<IPrimitiveChange> convertMayToMustChange = new ArrayList<IPrimitiveChange>();
				convertMayToMustChange.add(new AddTransitionPrimitiveChange(transition.getFromState(), action, transition.getToState(), true));
				result.add(convertMayToMustChange);
			}
		}
		else
		{
			modificationsToAddTransition = getModificationsToAddTransition(fromState, action, state);
			result.add(modificationsToAddTransition);
		}
		return result;
	}

	

	private Set<List<IPrimitiveChange>> getModificationsForCreateTransitionToOLD(
			State state) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		Transition transition = state.getOutTransition(action); 
		List<IPrimitiveChange> modificationsToAddTransition;
		if (transition != null)
		{
			if (!transition.getToState().equals(state))
			{
				//possibility to alter existent transition
				Set<List<IPrimitiveChange>> alterTransitionPossibility = getModificationsToAlterTransition(transition);
				//possibility to remove existent transition without any type of altering
				List<IPrimitiveChange> removeTransitionPossibility = getModificationsToRemoveTransition(transition);
				
				//adding correct transition
				modificationsToAddTransition = getModificationsToAddTransition(fromState, action, state);
				
				Set<List<IPrimitiveChange>> unionOfPossibilities = new HashSet<List<IPrimitiveChange>>();
				unionOfPossibilities.addAll(alterTransitionPossibility);
				unionOfPossibilities.add(removeTransitionPossibility);
				Iterator<List<IPrimitiveChange>> itPossibilities = unionOfPossibilities.iterator();
				while(itPossibilities.hasNext())
				{
					itPossibilities.next().addAll(modificationsToAddTransition);
				}
				result.addAll(unionOfPossibilities);
			}
		}
		else
		{
			modificationsToAddTransition = getModificationsToAddTransition(fromState, action, state);
			result.add(modificationsToAddTransition);
		}
		return result;
	}




	private List<IPrimitiveChange> getModificationsToAddTransition(
			State fromState2, String action2, State state) {
		List<IPrimitiveChange> result = new ArrayList<IPrimitiveChange>();
		result.add(new AddTransitionPrimitiveChange(fromState2, action2, state, true));
		return result;
	}


	private Set<List<IPrimitiveChange>> getModificationsToAlterTransition(
			Transition transition) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		Iterator<String> itActions = model.getActions().iterator();
		List<IPrimitiveChange> possibility = null;
		String action;
		while(itActions.hasNext())
		{
			action = itActions.next();
			if (!action.equals(transition.getAction()))
			{
				possibility = new ArrayList<IPrimitiveChange>();
				if (transition.getFromState().getOutTransition(action) != null)
				{
					possibility.add(new RemoveTransitionPrimitiveChange(transition.getFromState(), action, transition.getToState()));
				}
				possibility.add(new ModifyTransitionActionPrimitiveChange(transition, action));
				result.add(possibility);
			}
		}
		return result;
	}





}
