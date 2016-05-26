package kmts.element;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.booleanexpression.AtomicProposition;

public class State {
        
        //O estado e formado por:
        //rotulo
        //um conjunto de proposis�es at�micas
        //um conjunto de transi��es que chegam no estado
        //um conjunto de transi��es que partem do estado
	private String label;
	private Set<AtomicProposition> prepositions;
	private Set<Transition> inTransitions;
	private Set<Transition> outTransitions;

	//Constroi um estado criando os conjuntos de proposis�es at�micas e os de transi��es vazios
	public State() {
		super();
		inTransitions = new HashSet<Transition>();
		outTransitions = new HashSet<Transition>();
		prepositions = new HashSet<AtomicProposition>();
	}
	
        //Adiciona preposi��o at�mica no estado.
        //Imprime frase erro se a preposi��o j� existe.
	public void addPreposition(String literal, Boolean value)
	{
		AtomicProposition p = new AtomicProposition(literal, value);
		if (!prepositions.contains(p))
		{
			prepositions.add(p);
		} 
                //cobertura de casos tempor�ria.
                else 
                {
                    System.out.println("Est� preposi��o j� existe no estado.");
                }
	}
	
        //Troca o valor da uma preposi��o.
        //Encontra preposi��o que vai trocar valos pelo literal e substitui pelo valor dado.
	public void updatePrepositionValue(String literal, Boolean value)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			p.setValue(value);
		}
                //est� faltando 'else'? n�o tem retorno antes
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
        //Retorna o valor da uma preposi��o. 
        //Encontra preposi��o pelo literal.
	public Boolean getPrepositionValue(String literal)
	{
		AtomicProposition p = getPreposition(literal);
		if (p != null)
		{
			return p.getValue();
		}
		throw new RuntimeException("STATE DOESN'T HAVE LITERAL "+literal);
	}
	
        //Retorna a preposi��o.
        //Encontra preposi��o pelo literal.
        //Retorn null se n�o achar uma prepoi��o com o literal dado.
	private AtomicProposition getPreposition(String literal) {
		Iterator<AtomicProposition> it = prepositions.iterator();
		while (it.hasNext())
		{
			AtomicProposition p = (AtomicProposition)it.next();
			if (p.getLiteral().equals(p))
			{
				return p;
			}
		}
		return null;
	}

        //Retorna o cojunto de preposi��es.
	public Set<AtomicProposition> getPrepositions() {
		return prepositions;
	}
	
        //Retorna o conjunto de transi��es de chegada.
	public Set<Transition> getInTransitions() {
		return inTransitions;
	}
	
        //Retorna o conjunto de transi��es de saida.
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
        
        //Limpa os conjuntos de transi��es de chegada e saida
	public void clearAllTransitions() {
		inTransitions.clear();
		outTransitions.clear();
	}	
	
        //Sobrecarga 
        //Retorna uma String no formato:
        //<rotulo> (<Iterator<AtomicProposition>.Literal>)
        //EX: Q1 (X, W, Z, Y)
        
        //!!Talvez seja bom adicionar os valores das proposi��es no String.!!
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append(getLabel()+ " (");
		Iterator<AtomicProposition> it = prepositions.iterator();
		boolean first = true;
		String toAppend;
		while(it.hasNext())
		{
			if (first)
			{
				toAppend = ((AtomicProposition)it.next()).toString();
				first = false;
			}
			else
			{
				toAppend = ", "+((AtomicProposition)it.next()).toString() ;
			}
			result.append(toAppend);
		}
		result.append(")\n");
		return result.toString();
	}

        //Adiciona uma transi��o da saida.
        //Adiciona � transi��o este estado como ponto de partida.
	public void addOutTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setFromState(this);
			outTransitions.add(t);
		}
                //cobertura de casos tempor�ria.
                else 
                {
                    System.out.println("Transi��o invalida.");
                }
	}

        //Adiciona uma transi��o de chegada.
        //Adiciona � transi��o este estado como ponto de chegada.
	public void addInTrasition(Transition t) 
	{
		if (t != null)
		{
			t.setToState(this);
			inTransitions.add(t);
		}
                //cobertura de casos tempor�ria.
                else 
                {
                    System.out.println("Transi��o invalida.");
                }
	}
}
