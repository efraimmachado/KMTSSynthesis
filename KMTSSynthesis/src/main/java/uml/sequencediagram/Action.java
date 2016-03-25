package uml.sequencediagram;

import java.util.HashSet;
import java.util.Set;

import uml.ocl.OCLRule;

public class Action {

	private String action;
	private Set<OCLRule> oclPreconditionRules;
	
	private Set<OCLRule> oclPosconditionRules;

	public Action(String action) {
		super();
		this.action = action;
		oclPreconditionRules = new HashSet<OCLRule>();
		oclPosconditionRules = new HashSet<OCLRule>();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Set<OCLRule> getOclPreconditionRules() {
		return oclPreconditionRules;
	}

	public Set<OCLRule> getOclPosconditionRules() {
		return oclPosconditionRules;
	}	
}
