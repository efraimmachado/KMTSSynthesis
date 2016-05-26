package synthesizer;

import java.util.Set;
import java.util.HashSet;

import logic.booleanexpression.AtomicProposition;
import uml.sequencediagram.Action;
import uml.sequencediagram.Component;
import uml.sequencediagram.Message;

public class ComponentData {

        //um componente
        //um conjunto de a��es que chegam ??no componente??
        //um conjunto de a��es que saem ??do componente??
        //um conjunto de *preposi��es atomicas* das variaveis do escopo
        //um conjunto de *preposi��es atomicas* das variaveis significantes
        // *n�o acho que vai dar pra fazer com preposi��es atomicas 
        //  por que no conjunto de variaveis nao � atribudo nenum valor a elas
	private Component component;
	private Set<Action> inActions;
	private Set<Action> outActions;
	private Set<AtomicProposition> scopedVariables;
	private Set<AtomicProposition> significantVariables;
	
        //Cria um componemtedata ligado ao componente dado
	public ComponentData(Component component)
	{
		super();
		this.component = component;
	}
	
        //Retorna o componente ao qual o componente data esta ligado
	public Component getComponent() {
		return component;
	}

        //Retorna o conjunto de a��es que chegam no componente
	public Set<Action> getInActions() {
		return inActions;
	}

        //Troca o conjunto de a��es que chegam no componente pelo conjunto dado
	public void setInActions(Set<Action> inActions) {
		this.inActions = inActions;
	}

        //Retorna o conjunto de a��es que saem do componente
	public Set<Action> getOutActions() {
		return outActions;
	}

        //Troca o conjunto de a��es que saem do componente pralo conjunto dado
	public void setOutActions(Set<Action> outActions) {
		this.outActions = outActions;
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

        //Atualiza o conjunto de variaveis do escopo baseado nas mensagens do componente
        public void updateScopedVariables() {
            //TODO
        }
        
        //Atualiza o conjunto de variaveis significantes baseado nas mensagens do componente
        public void updateSignificantVariables() {
            //TODO
        }
}
