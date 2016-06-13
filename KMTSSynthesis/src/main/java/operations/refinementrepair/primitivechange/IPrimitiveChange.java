package operations.refinementrepair.primitivechange;

import kmts.KMTS;

public interface IPrimitiveChange {

	boolean isConsistent(IPrimitiveChange otherChange);

	void apply(KMTS modelClone);

}
