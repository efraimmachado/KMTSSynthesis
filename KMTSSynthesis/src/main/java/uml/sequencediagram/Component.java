package uml.sequencediagram;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Component {
	
	private String label;
	private Lifeline lifeline;
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Lifeline getLifeline() {
		return lifeline;
	}
	
	public void setLifeline(Lifeline lifeline) {
		this.lifeline = lifeline;
	}
	
}
