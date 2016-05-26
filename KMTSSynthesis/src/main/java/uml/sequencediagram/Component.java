package uml.sequencediagram;

public class Component {
	
        //Um rotulo.
        //Uma linha da vida.
	private String label;
	private Lifeline lifeline;
	
        //Retorna o rotulo.
	public String getLabel() {
		return label;
	}
	
        //Troca o rotulo pelo dado.
	public void setLabel(String label) {
		this.label = label;
	}
	
        //Retorna a linha da vida.
	public Lifeline getLifeline() {
		return lifeline;
	}
	
        //Troca a linha da vida pela dada.
	public void setLifeline(Lifeline lifeline) {
		this.lifeline = lifeline;
	}
	
}
