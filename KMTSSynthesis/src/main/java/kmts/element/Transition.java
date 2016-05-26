package kmts.element;

public class Transition {

        //A trasi��es � constituida por:
        //por uma String que funciona como rotulo
        //um estado de onde a transi��o parti.
        //um estado para onde a transi��o vai.
        //um boolean que determina se a transi��o � 'must' ou 'may'.
	private String action;
	private State fromState;
	private State toState;
	private boolean mustTransition;
	
        //Construtor subentendido.
        //Criado como may.
        
        //Retorna a String(rotulo) da transi��o
	public String getAction() {
		return action;
	}
        
        //Troca a String (rotulo) da transi��o pela dada.
	public void setAction(String action) {
		this.action = action;
	}
        
        //Retorna o estado de onde a transi��o parti.
	public State getFromState() {
		return fromState;
	}
        
        //Troca o estado de onde a transi��o parti pela o dado.
	public void setFromState(State fromState) {
		this.fromState = fromState;
	}
        
        //Retorna o estado para onde a transi��o vai.
	public State getToState() {
		return toState;
	}
        
        //Troca o estado para onde a transi��o vai pela o dado.
	public void setToState(State toState) {
		this.toState = toState;
	}
        
        //Retorna o booblean identificador de 'must' ou 'may'.
	public boolean isMustTransition() {
		return mustTransition;
	}
        
        //troca o valos do boolean identificador de 'must' e 'may' pelo valor dado.
	public void setMustTransition(boolean mustTransition) {
		this.mustTransition = mustTransition;
	}
	
        //Sobreposi��o
        //<StateFrom> -->(May) or ==>(Must) <StateTo> 
        //EX:
        //Q2 ==> Q1
        //Q1 --> Q2
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(toStringState(fromState));
		result.append(mustTransition?" ==> ":" --> ");
		result.append(toStringState(toState));
		result.append("\n");
		return result.toString();
	}
	
        //Returna o rotulo do estado dado.
	private String toStringState(State state)
	{
		if (state != null)
		{
			return state.getLabel();
		}
		return "null";
	}
	
}
