package uml.sequencediagram;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import synthesizer.ComponentData;

public class SequenceDiagramReader {
        
        //Recebi um SD e extrai as informações dos components para os componentDatas
	public Set<ComponentData> extractAllComponentsData(
			SequenceDiagram sequenceDiagram) 
        {
		Set<ComponentData> result = new HashSet<ComponentData>();
                if(sequenceDiagram != null && sequenceDiagram.getLifelines() != null)
                {
                        Iterator<Lifeline> it = sequenceDiagram.getLifelines().iterator();
                        while(it.hasNext())
                        {
                                ComponentData componentData = new ComponentData(it.next().getComponent());
                                componentData.updateComponenteData();
                                if(!result.contains(componentData))
                                {
                                       result.add(componentData);       
                                }
                        }
                }
		return result;
	}

}
