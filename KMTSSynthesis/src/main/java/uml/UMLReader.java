package uml;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import synthesizer.ComponentData;
import uml.sequencediagram.SequenceDiagram;

public class UMLReader {

	
	
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
		
		return null;
	}
	
	
}
