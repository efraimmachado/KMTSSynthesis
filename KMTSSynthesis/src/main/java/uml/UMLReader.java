package uml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import synthesizer.ComponentData;
import uml.sequencediagram.Component;
import uml.sequencediagram.SequenceDiagram;
import uml.sequencediagram.SequenceDiagramReader;

public class UMLReader {
    
        private SequenceDiagramReader sdReader;
	
        public UMLReader() {
		super();
		sdReader = new SequenceDiagramReader();
	}

	public SequenceDiagram extractSequenceDiagramWithOCL(String fileInput)
	{
		return null;
	}

	public Set<SequenceDiagram> extractSequenceDiagramsWithOCL(
			List<String> fileInputs) {
		Set<SequenceDiagram> result = new HashSet<SequenceDiagram>();
		for (int i = 0; i < fileInputs.size(); i++)
		{
			result.add(extractSequenceDiagramWithOCL(fileInputs.get(i)));
		}
		return result;
	}

	public Set<ComponentData> extractComponentsDataFromSDWithOCL(
			Set<SequenceDiagram> extractedSDwithOCL) 
	{
		//armazena as informacoes extraidas
		Map<Component, ComponentData> result = new HashMap<Component, ComponentData>();
		//checa para evitar null pointer
		if (extractedSDwithOCL != null)
		{
			//itera sobre o conjunto dos diagramas de sequencia
			Iterator<SequenceDiagram> it = extractedSDwithOCL.iterator();
			while(it.hasNext())
                        {
				//junta as informacoes extraidas de cada diagrama com as informacoes que ja foram extraidas
				mergeComponentsData(result, extractComponentsData(it.next()));
			}
		}
		return (Set<ComponentData>) result.values();
	}
	

	private void mergeComponentsData(Map<Component, ComponentData> result,
			Set<ComponentData> extractedComponentsData) {
		Iterator<ComponentData> it = extractedComponentsData.iterator();
		ComponentData extractedComponentData = null;
		while(it.hasNext())
		{
			extractedComponentData = it.next();
			if (result.containsKey(extractedComponentData.getComponent()))
			{
				ComponentData componentData = result.get(extractedComponentData.getComponent());
				componentData = mergeComponentData(componentData, extractedComponentData);
			}
			else
			{
				result.put(extractedComponentData.getComponent(), extractedComponentData);
			}
		}
		
	}

	private ComponentData mergeComponentData(ComponentData componentData,
			ComponentData extractedComponentData) 
        {
		ComponentData result = componentData;
                //!!Não muito certo sobre comferir se result == null!!
                if(extractedComponentData != null && result != null)
                {
                        result.getExpectedActions().addAll(extractedComponentData.getExpectedActions());
                        result.getProvidedActions().addAll(extractedComponentData.getProvidedActions());
                        result.getScopedVariables().addAll(extractedComponentData.getScopedVariables());
                        result.getSiginificantVariables().addAll(extractedComponentData.getSiginificantVariables());
                } 
                else 
                {
                        result = extractedComponentData;
                }
		return result;
	}

	private Set<ComponentData> extractComponentsData(
			SequenceDiagram sequenceDiagram) 
	{
		Set<ComponentData> result = new HashSet<ComponentData>();
		result.addAll(sdReader.extractAllComponentsData(sequenceDiagram));
		return result;
	}
	
	
}
