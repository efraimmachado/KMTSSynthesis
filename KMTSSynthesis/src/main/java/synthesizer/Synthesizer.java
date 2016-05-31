package synthesizer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kmts.KMTS;
import kmts.KMTSCreator;
import uml.UMLReader;
import uml.sequencediagram.SequenceDiagram;

public class Synthesizer {

	public Set<KMTS> createKMTSFromSequenceDiagramsWithOCLConstraints(
			List<String> fileInputs) 
	{
		Set<KMTS> result = null;
		if (fileInputs != null)
		{
			Set<SequenceDiagram> extractedSDwithOCL = extractSequenceDiagramWithOCL(fileInputs);
			Set<ComponentData> extractedComponentData = extractComponentDataFromSDWithOCL(extractedSDwithOCL);
			result = createKMTSsFromComponentData(extractedComponentData);		
		}
		return result;
	}

	private Set<KMTS> createKMTSsFromComponentData(
			Set<ComponentData> extractedComponentData) 
	{
		Set<KMTS> result = new HashSet<KMTS>();
		Iterator<ComponentData> it = extractedComponentData.iterator();
		KMTS kmts = null;
		while(it.hasNext())
		{
			kmts = createKMTSFromComponentData(((ComponentData)it.next()));
			result.add(kmts);
		}
		return result;
	}

	private KMTS createKMTSFromComponentData(ComponentData componentData) {
		KMTSCreator creator = new KMTSCreator();
		return creator.createKMTSFromComponentData(componentData);
	}

	private Set<ComponentData> extractComponentDataFromSDWithOCL(
			Set<SequenceDiagram> extractedSDwithOCL) 
	{
		UMLReader umlReader = new UMLReader();
		return umlReader.extractComponentsDataFromSDWithOCL(extractedSDwithOCL);	
	}

	private Set<SequenceDiagram> extractSequenceDiagramWithOCL(
			List<String> fileInputs) {
		UMLReader umlReader = new UMLReader();
		return umlReader.extractSequenceDiagramsWithOCL(fileInputs);
	}
}
