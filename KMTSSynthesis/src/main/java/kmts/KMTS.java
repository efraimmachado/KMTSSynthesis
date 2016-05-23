package kmts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.element.State;
import kmts.element.Transition;

public class KMTS {

        //O KMTS tem um rotulo
        //é constituido de:
        //estado inicial e conjunto de estados
        //conjunto de transições entre os estados
        //um boolean pra dizer se o KMTS é deterministico
        //um boolean pra diser se e é permitido duplicar rotulos de estados
	private String label;
	private State initialState;
	private Set<State> states;
	private Set<Transition> transitions;
	private boolean deterministic;
	private boolean allowDuplicatedStateLabels;
	
        //Constroi um KMTS recebendo o rotulo
	public KMTS(String label) {
		super();
                this.label = label;
		states = new HashSet<State>();
		transitions = new HashSet<Transition>();
		deterministic = true;
		allowDuplicatedStateLabels = false;
	}
        
        //Retorna o estado inicial do KMTS
	public State getInitialState() {
		return initialState;
	}
        
        //Retorna todos os estados do KMTS
	public Set<State> getStates() {
		return states;
	}
        
        //Retorna todas as trasições do KMTS
	public Set<Transition> getTransitions() {
		return transitions;
	}
	
        //Recebe o estado de partida e o de chegada da nova transição e o rotulo dela.
        //Cria uma trasição 'may' e tranforma em must.
        //Retorna a transição criada. ??para teste??
	public Transition addMustTransitionBetween(State fromState, State toState, String action)
	{
		Transition t = addMayTransitionBetween(fromState, toState, action);
		t.setMustTransition(true);
		return t;
	}
	
        //Recebe o estado de partida e o de chegada da nova transição e o rotulo dela.
        //Checa se uma trasição 'may' pode ser addicionada entre os estados dados.
        //Cria uma transição.
        //adiciona a trasição na saida e chegada dods estados recebidos.
        //Retorna a transição criada. ??para teste??
	public Transition addMayTransitionBetween(State fromState, State toState, String action)
	{
		checkCanAddMayTransitionBetween(fromState, toState, action);
		Transition t = new Transition();
		t.setAction(action);
		t.setFromState(fromState);
		t.setToState(toState);
		t.setMustTransition(false);
		fromState.addOutTrasition(t);
		toState.addInTrasition(t);
		transitions.add(t);
		return t;
	}
	
	//Decidir o que faz e como faz.
	private void checkCanAddMayTransitionBetween(State fromState,
			State toState, String action) {
		// TODO Auto-generated method stub
		
	}
        
        //Adiciona ao KMTS o estado que recebe como parametro.
	public void addState(State state)
	{
		checkCanAddState(state);
		state.clearAllTransitions();
		states.add(state);
	}
	
        //Adiciona um estado inicial
	public void addInitialState(State state)
	{
		addState(state);
		initialState = state;
	}
	
        //Decidir o que faz e como faz.
	private void checkCanAddState(State state) {
		// TODO Auto-generated method stub
	}
        
        //Busca estado pelo rotulo.
        //Como 'iterator' funciona?
	public State getStateByLabel(String label)
	{
		if (states != null)
		{
			Iterator<State> it = states.iterator();
			while (it.hasNext())
			{
				State state = ((State)it.next());
				if (state.getLabel().equals(label))
				{
					return state;
				}
			}
		}
		return null;
	}
	
        //Imprime as informações do KMTS no padrao:
        //LABEL: <rotulo do KMTS>
        //INITIAL STATE: <rotulo do estado inicial>
        //STATES: <rotulo de todos estados>
        //TRANSITIONS: <todas transições>
        //<estadoDe.rotulo transição.tipo estatoPara.rotulo>
        //EX:
            //LABEL: null
            //INITIAL STATE: S0
            //STATES: S1, S0
            //TRANSITIONS:
            //S1 --> S0
            //S0 ==> S1
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("---[KMTS]---\n");
		result.append("LABEL: "+getLabel()+"\n");
		if (initialState != null)
		{
			result.append("INITIAL STATE: "+initialState.getLabel()+"\n");
		}
		if (states != null)
		{
			Iterator<State> it = states.iterator();
			result.append("STATES: ");
			boolean first = true;
			while (it.hasNext())
			{
				if (first)
				{
					result.append(((State)it.next()).getLabel());
					first = false;
				}
				else
				{
					result.append(", "+((State)it.next()).getLabel());
				}
			}
			result.append("\n");
		}
		if (transitions != null)
		{
			Iterator<Transition> it = transitions.iterator();
			result.append("TRANSITIONS:\n");
			while (it.hasNext())
			{
				result.append(((Transition)it.next()).toString());
			}
			result.append("\n");
		}
		result.append("------------\n");
		return result.toString();
	}
        
        //Retorna o rotulo do KMTS
	public String getLabel() {
		return label;
	}
        
        //Troca o rotudo do KMTS pelo recebido.
	public void setLabel(String label) {
		this.label = label;
	}
}
