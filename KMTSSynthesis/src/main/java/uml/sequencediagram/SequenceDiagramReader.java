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
                        //Pega cada linha do tempo
                        Iterator<Lifeline> it = sequenceDiagram.getLifelines().iterator();
                        //Se tem mais uma linha do tempo
                        while(it.hasNext())
                        {
                                //Cria um ComponentData
                                //Passa o componente da linha do tempo para o construtor do componentData
                                ComponentData componentData = new ComponentData(it.next().getComponent());
                                //Identifica e classifica os eventos e variaveis do component
                                componentData.updateComponenteData();
                                if(!result.contains(componentData))
                                {
                                        result.add(componentData);       
                                }
                        }
                }
		return result;
	}

        //Retorna o conjunto de mensagens do SD
        public Set<Message> extractAllMessages(
                        SequenceDiagram sequenceDiagram) 
        {
                Set<Message> result = new HashSet<Message>();
                if(sequenceDiagram != null)
                {
                        result.addAll(sequenceDiagram.getMessages());
                }
                return result;
        }
}
