package kmts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.element.State;
import kmts.element.Transition;
import logic.booleanexpression.AtomicProposition;

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
		fromState.addOutTrasition(t);
		toState.addInTrasition(t);
		t.setMustTransition(false);
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

	public Set<String> getActions() {
		Set<String> result = new HashSet<String>();
		if (transitions != null)
		{
			Iterator<Transition> it = transitions.iterator();
			while(it.hasNext())
			{
				result.add(it.next().getAction());
			}
		}
		return result;
	}
	
	public KMTS clone()
	{
		KMTS result = new KMTS(label+"'");
		result.addInitialState(getInitialState().clone());
		Iterator<State> itStates = getStates().iterator();
		State state;
		while(itStates.hasNext())
		{
			state = itStates.next();
			if (!state.equals(initialState))
			{
				result.addState(state.clone());
			}
		}
		Iterator<Transition> itTransitions = getTransitions().iterator();
		Transition transition;
		State from, to;
		while(itTransitions.hasNext())
		{
			transition = itTransitions.next();
			from = result.getStateByLabel(transition.getFromState().getLabel());
			to = result.getStateByLabel(transition.getToState().getLabel());
			if (transition.isMustTransition())
			{
				result.addMustTransitionBetween(from, to, transition.getAction());
			}
			else
			{
				result.addMayTransitionBetween(from, to, transition.getAction());
			}
		}
		
		return result;
	}

	public void removeTransition(Transition transition) 
	{
		transitions.remove(transition);
		transition.getFromState().removeTransition(transition);
		transition.getToState().removeTransition(transition);
	}

	public void normalize() {
		Set<String> allLiterals = getLiterals();
		Iterator<State> it = states.iterator();
		State state;
		while(it.hasNext())
		{
			state = it.next();
			Iterator<String> itProp = allLiterals.iterator();
			String literal;
			while(itProp.hasNext())
			{
				literal = itProp.next();
				if (!state.hasLiteral(literal))
				{
					state.addPreposition(literal, null);
				}
			}
			
		}
		
	}
	
	public Set<String> getLiterals()
	{
		Set<String> result = new HashSet<String>();
		Iterator<State> it = states.iterator();
		while(it.hasNext())
		{
			result.addAll(it.next().getLiterals());
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allowDuplicatedStateLabels ? 1231 : 1237);
		result = prime * result + (deterministic ? 1231 : 1237);
		result = prime * result
				+ ((initialState == null) ? 0 : initialState.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
		result = prime * result
				+ ((transitions == null) ? 0 : transitions.hashCode());
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		KMTS other = (KMTS) obj;
//		if (allowDuplicatedStateLabels != other.allowDuplicatedStateLabels)
//			return false;
//		if (deterministic != other.deterministic)
//			return false;
//		if (initialState == null) {
//			if (other.initialState != null)
//				return false;
//		} else if (!initialState.equals(other.initialState))
//			return false;
//		if (label == null) {
//			if (other.label != null)
//				return false;
//		} else if (!label.equals(other.label))
//			return false;
//		if (states == null) {
//			if (other.states != null)
//				return false;
//		} else if (!states.equals(other.states))
//			return false;
//		if (transitions == null) {
//			if (other.transitions != null)
//				return false;
//		} else if (!transitions.equals(other.transitions))
//			return false;
//		return true;
//	}
}
