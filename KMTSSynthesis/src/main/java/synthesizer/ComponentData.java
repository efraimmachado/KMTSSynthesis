package synthesizer;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import logic.booleanexpression.AtomicProposition;
import uml.ocl.OCLRule;
import uml.sequencediagram.Action;
import uml.sequencediagram.Component;
import uml.sequencediagram.Message;

//!!Bem provavel que eu tenha sobrecarregado a classe. talvez precise de outra classe para faazer os updates!!
public class ComponentData {

        //um componente
        //um conjunto de ações que chegam no componente
        //um conjunto de ações que saem do componente
        //um conjunto de *preposições atomicas* das variaveis do escopo
        //um conjunto de *preposições atomicas* das variaveis significantes
        // *não acho que vai dar pra fazer com preposições atomicas 
        //  por que no conjunto de variaveis nao é atribudo nenum valor a elas
	private Component component;
	private Set<Action> providedActions;
	private Set<Action> expectedActions;
	private Set<AtomicProposition> scopedVariables;
	private Set<AtomicProposition> significantVariables;
	
        //Cria um componemtedata ligado ao componente dado
	public ComponentData(Component component)
	{
		super();
		this.component = component;
	}
	
        //Faz a união deste componentData com o componentData dado
        public void mergewithComponentData(ComponentData componentData)
        {
                this.getExpectedActions().addAll(componentData.getExpectedActions());
                this.getProvidedActions().addAll(componentData.getProvidedActions());
                this.getScopedVariables().addAll(componentData.getScopedVariables());
                this.getSiginificantVariables().addAll(componentData.getSiginificantVariables());

        }
        
        //Atualiza os conjuntos de ações.
        //Atualiza os conjuntos de variaveis.
        public void updateComponenteData()
        {
                updateProvidedEvents();
                updateExpectedEvents();
                updateScopedVariables();
                updateSignificantVariables();
        }
        
        //Retorna o componente ao qual o componente data esta ligado
	public Component getComponent() {
		return component;
	}

        //Retorna o conjunto de ações que chegam no componente
	public Set<Action> getProvidedActions() {
		return providedActions;
	}

        //Troca o conjunto de ações que chegam no componente pelo conjunto dado
	private void setProvidedActions(Set<Action> inActions) {
		this.providedActions = inActions;
	}

        //Retorna o conjunto de ações que saem do componente
	public Set<Action> getExpectedActions() {
		return expectedActions;
	}

        //Troca o conjunto de ações que saem do componente pralo conjunto dado
	private void setExpectedActions(Set<Action> outActions) {
		this.expectedActions = outActions;
	}

        //Retorna o conjunto de variaveis do escopo 
	public Set<AtomicProposition> getScopedVariables() {
		return scopedVariables;
	}

        //Troca o conjunto de variaveis do escopo pelo conjunto dado
	public void setScopedVariables(Set<AtomicProposition> ScopedVariables) {
		this.scopedVariables = ScopedVariables;
	}

        //Retorna o conjunto de variaveis significantes do componente
	public Set<AtomicProposition> getSiginificantVariables() {
		return significantVariables;
	}

        //Troca o conjunto de variaveis significantes do componente pelo conjunto dado
        public void setSignificantVariables(Set<AtomicProposition> significantVariables) {
		this.significantVariables = significantVariables;
	}

        //Troca o componente pelo componente dado
	public void setComponent(Component component) {
		this.component = component;
	}

        //Atualiza o conjunto de eventos provided baseado nas mensagens do componente
        private void updateProvidedEvents(){
                Set<Action> result = new HashSet<Action>();
                if(this.component.getLifeline().getMessages() != null){
                        Iterator<Message> it = this.component.getLifeline().getIncomingMessages().iterator();
                        while(it.hasNext())
                        {
                                result.add(it.next().getAction());
                        }
                }
                setProvidedActions(result);
        }
        
        //Atualiza o conjunto de eventos expected baseado nas mensagens do componente
        private void updateExpectedEvents(){
                Set<Action> result = new HashSet<Action>();
                if(this.component.getLifeline().getMessages() != null){
                        Iterator<Message> it = this.component.getLifeline().getOutcomingMessages().iterator();
                        while(it.hasNext())
                        {
                                result.add(it.next().getAction());
                        }
                }
                setExpectedActions(result);
        }
        
        //Atualiza o conjunto de variaveis do escopo baseado nas mensagens do componente
        private void updateScopedVariables() {
            //TODO
        }
        
        //Atualiza o conjunto de variaveis significantes baseado nas mensagens do componente
        private void updateSignificantVariables() {
                Set<AtomicProposition> result = new HashSet<AtomicProposition>();
                if(getExpectedActions() != null)
                {
                        Iterator<Action> it = getExpectedActions().iterator();
                        while(it.hasNext())
                        {
                                result.addAll(it.next().getPreconditionRulesAtomicProposition());
                        }
                }
                setSignificantVariables(result);
        }

        //Uso provavel em SequenceDiagramReader atravez de 'contains'
        
        /*
        public boolean equals(ComponentData obj) 
        {
                String objlabel = obj.getComponent().getLabel();
                return this.component.getLabel().equals(objlabel);
        }*/
}
