package kmts;

import synthesizer.ComponentData;

public class KMTSCreator {

	public KMTS createKMTSFromComponentData(ComponentData componentData) 
	{
		KMTS result = new KMTS(componentData.getComponent().getLabel());
		return result;
	}

}
