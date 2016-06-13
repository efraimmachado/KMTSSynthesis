package operations.refinementrepair.repairtemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kmts.KMTS;
import kmts.element.State;
import operations.refinementrepair.causeoffailure.SpoilerCannotStartGameCauseOfFailure;
import operations.refinementrepair.primitivechange.IPrimitiveChange;

public class ModifyInitialStateRepairTemplate extends ARepairTemplate{

	private State specificationInitialState;
	
	public ModifyInitialStateRepairTemplate(
			SpoilerCannotStartGameCauseOfFailure causeOfFailure) {
		super();
		specificationInitialState = causeOfFailure.getSpecificationInitialState();
		
	}

	@Override
	public Set<List<IPrimitiveChange>> generatePossibleRepairs(KMTS model) {
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		List<IPrimitiveChange> mods = getModificationsForLabelRefinement(specificationInitialState, model.getInitialState());
		result.add(mods);
		return result;
	}

}
