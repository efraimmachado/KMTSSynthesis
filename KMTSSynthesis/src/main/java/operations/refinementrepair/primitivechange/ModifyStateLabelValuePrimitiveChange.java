package operations.refinementrepair.primitivechange;

import kmts.KMTS;
import kmts.element.State;
import logic.booleanexpression.AtomicProposition;

public class ModifyStateLabelValuePrimitiveChange implements IPrimitiveChange {

	private State targetState;
	private String literal;
	private Boolean value;
	
	public ModifyStateLabelValuePrimitiveChange(State state, AtomicProposition p) 
	{
		targetState = state;
		literal = p.getLiteral();
		value = p.getValue();
	}

	public State getTargetState() {
		return targetState;
	}

	public void setTargetState(State targetState) {
		this.targetState = targetState;
	}

	public String getLiteral() {
		return literal;
	}

	public Boolean getValue() {
		return value;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public boolean isConsistent(IPrimitiveChange otherChange) {
		if (otherChange instanceof ModifyStateLabelValuePrimitiveChange)
		{
			ModifyStateLabelValuePrimitiveChange modifyLabelChange = (ModifyStateLabelValuePrimitiveChange)otherChange;
			if (modifyLabelChange.getTargetState().equals(targetState) &&
				modifyLabelChange.getLiteral().equals(literal) &&
				!isConsistentValueChange(modifyLabelChange.getValue()))
			{
				return false;
			}
		}
		return true;
	}

	private boolean isConsistentValueChange(Boolean newValue) {
		if (value == null)
		{
			return true;
		}
		else if (newValue == null)
		{
			return false;
		}
		return value == newValue;
	}
	
	@Override
	public void apply(KMTS model) 
	{
		State state;
		state = model.getStateByLabel(targetState.getLabel());
		state.updatePrepositionValue(literal, value);
	}

	@Override
	public String toString()
	{
		return "ALTER_LABEL("+targetState.getLabel()+ "."+literal+" = "+value+")";
	}
	
}
