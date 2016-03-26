package synthesizer;

import java.util.Set;

import logic.booleanexpression.AtomicProposition;
import uml.sequencediagram.Action;
import uml.sequencediagram.Component;

public class ComponentData {

	private Component component;
	private Set<Action> inActions;
	private Set<Action> outActions;
	private Set<AtomicProposition> internalVariables;
	private Set<AtomicProposition> externalVariables;
	
	public ComponentData(Component component)
	{
		super();
		this.component = component;
	}
	
	public Component getComponent() {
		return component;
	}

	public Set<Action> getInActions() {
		return inActions;
	}

	public void setInActions(Set<Action> inActions) {
		this.inActions = inActions;
	}

	public Set<Action> getOutActions() {
		return outActions;
	}

	public void setOutActions(Set<Action> outActions) {
		this.outActions = outActions;
	}

	public Set<AtomicProposition> getInternalVariables() {
		return internalVariables;
	}

	public void setInternalVariables(Set<AtomicProposition> internalVariables) {
		this.internalVariables = internalVariables;
	}

	public Set<AtomicProposition> getExternalVariables() {
		return externalVariables;
	}

	public void setExternalVariables(Set<AtomicProposition> externalVariables) {
		this.externalVariables = externalVariables;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

}
