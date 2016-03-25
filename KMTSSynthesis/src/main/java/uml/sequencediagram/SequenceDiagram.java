package uml.sequencediagram;

import java.util.Set;

public class SequenceDiagram {

	private String label;
	private Set<Lifeline> lifelines;
	private Set<Message> messages;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<Lifeline> getLifelines() {
		return lifelines;
	}
	public void setLifelines(Set<Lifeline> lifelines) {
		this.lifelines = lifelines;
	}
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
}
