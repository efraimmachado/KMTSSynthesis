package kmts.element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.booleanexpression.AtomicProposition;

public class State {
        
        //O estado e formado por:
        //rotulo
        //um conjunto de proposisões atômicas
        //um conjunto de transições que chegam no estado
        //um conjunto de transições que partem do estado
	private String label;
	private Set<AtomicProposition> prepositions;
	private Set<Transition> inTransitions;
	private Set<Transition> outTransitions;
	
	//Constroi um estado criando os conjuntos de proposisões atômicas e os de transições vazios
	public State() {
		super();
		inTransitions = new HashSet<Transition>();
		outTransitions = new HashSet<Transition>();
		prepositions = new HashSet<AtomicProposition>();
	}
	
        //Adiciona preposição atômica no estado.
        //Imprime frase erro se a preposição já existe.
	public void addPreposition(String literal, Boolean value)
	{
		AtomicProposition p = new AtomicProposition(literal, value);
		if (!prepositions.contains(p))
		{
			prepositions.add(p);
		} 
                //cobertura de casos temporária.
                else 
                {
                    System.out.println("Está preposição já existe no estado.");
                }
	}
	
        //Troca o valor da uma preposição.
        //Encontra preposição que vai trocar valos pelo literal e substitui pelo valor dado.
	public void updatePrepositionValue(String literal, Boolean value)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			p.setValue(value);
		}
		else
		{
			//está faltando 'else'? não tem retorno antes
			throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
		}
	}
	
        //Retorna o valor da uma preposição. 
        //Encontra preposição pelo literal.
	public Boolean getPrepositionValue(String literal)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			return p.getValue();
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
        //Retorna a preposição.
        //Encontra preposição pelo literal.
        //Retorn null se não achar uma prepoição com o literal dado.
	private AtomicProposition getPreposition(String literal) {
		Iterator<AtomicProposition> it = prepositions.iterator();
		while (it.hasNext())
		{
			AtomicProposition p = (AtomicProposition)it.next();
			if (p.getLiteral().equals(literal))
			{
				return p;
			}
		}
		return null;
	}

        //Retorna o cojunto de preposições.
	public Set<AtomicProposition> getPrepositions() {
		return prepositions;
	}
	
        //Retorna o conjunto de transições de chegada.
	public Set<Transition> getInTransitions() {
		return inTransitions;
	}
	
        //Retorna o conjunto de transições de saida.
	public Set<Transition> getOutTransitions() {
		return outTransitions;
	}
	

	//Retorna o rotulo do estado
	public String getLabel() {
		return label;
	}
        
        //Troca o rotulo do estado pelo rotulo dado.
	public void setLabel(String label) {
		this.label = label;
	}
        
        //Limpa os conjuntos de transições de chegada e saida
	public void clearAllTransitions() {
		inTransitions.clear();
		outTransitions.clear();
	}	
	
        //Sobrecarga 
        //Retorna uma String no formato:
        //<rotulo> (<Iterator<AtomicProposition>.Literal>)
        //EX: Q1 (X, W, Z, Y)
        
        //!!Talvez seja bom adicionar os valores das proposições no String.!!
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(getLabel()+ " (");
		Iterator<AtomicProposition> it = prepositions.iterator();
		boolean first = true;
		String toAppend;
		AtomicProposition ap;
		while(it.hasNext())
		{
			ap = it.next();
			if (first)
			{
				toAppend = ap.toString();
				first = false;
			}
			else
			{
				toAppend = ", "+ap.toString() ;
			}
			result.append(toAppend);
		}
		result.append(")\n");
		return result.toString();
	}

        //Adiciona uma transição da saida.
	public void addOutTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setFromState(this);
			outTransitions.add(t);
		}
                //cobertura de casos temporária.
                else 
                {
                    System.out.println("Transição invalida.");
                }
	}

        //Adiciona uma transição de chegada.
	public void addInTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setToState(this);
			inTransitions.add(t);
		}
                //cobertura de casos temporária.
                else 
                {
                    System.out.println("Transição invalida.");
                }
	}

	public boolean isDirectlyReachableFromMustTransition(State potentialFromState) 
	{
		return isDirectlyReachableFromTransition(potentialFromState, null, true);
	}
	
	public boolean isDirectlyReachableFromMayTransition(State potentialFromState) {
		return isDirectlyReachableFromTransition(potentialFromState, null, false);
	}

	private boolean isDirectlyReachableFromTransition(State potentialFromState, String action, boolean mustTransition) {
		
		if (potentialFromState != null && getInTransitions() != null)
		{
			Iterator<Transition> it = getInTransitions().iterator();
			Transition transition = null;
			while (it.hasNext())
			{
				transition = it.next();
				if (action == null || (action.equals(transition.getAction())))
				{
					if ((transition.isMustTransition() && mustTransition) || (!mustTransition))
					{
						if (transition.getFromState().equals(potentialFromState))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isDirectlyReachableFromMayTransitionAndAction(
			State potentialFromState, String toStateAction) {
		return isDirectlyReachableFromTransition(potentialFromState, toStateAction, false);
	}

	public Transition getOutTransition(String desiredAction) 
	{
		if (outTransitions != null)
		{
			return getTransition(outTransitions, desiredAction);
		}
		return null;
	}
	
	private Transition getTransition(Set<Transition> transitions, String action)
	{
		Iterator<Transition> it = transitions.iterator();
		Transition transition = null;
		while (it.hasNext())
		{
			transition = it.next();
			if (transition.getAction().equals(action))
			{
				return transition;
			}
		}
		return null;
		
	}
	
	public State clone()
	{
		State result = new State();
		result.setLabel(label);
		Iterator<AtomicProposition> it = prepositions.iterator();
		AtomicProposition ap;
		while(it.hasNext())
		{
			ap = it.next();
			result.addPreposition(ap.getLiteral(), ap.getValue());
		}
		return result;
	}

	public Set<String> getLiterals() {
		Set<String> result = new HashSet<String>();
		Iterator<AtomicProposition> it = prepositions.iterator();
		while(it.hasNext())
		{
			result.add(it.next().getLiteral());
		}
		return result;
	}

	public boolean hasLiteral(String literal) {
		return getLiterals().contains(literal);
	}

	public boolean isDirectlyReachableFromMustTransitionAndAction(
			State potentialFromState, String toStateAction) {
		return isDirectlyReachableFromTransition(potentialFromState, toStateAction, true);
	}

	public Transition getInTransition(String action) 
	{
		if (inTransitions != null)
		{
			return getTransition(inTransitions, action);
		}
		return null;
	}

	public void removeTransition(Transition transition) 
	{
		inTransitions.remove(transition);
		outTransitions.remove(transition);
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
}
