package operations.refinementrepair.repairtemplate;

import java.util.List;
import java.util.Set;

import operations.refinementrepair.primitivechange.IPrimitiveChange;

import kmts.KMTS;

public interface IRepairTemplate {

	Set<List<IPrimitiveChange>> generatePossibleRepairs(KMTS model);

}
