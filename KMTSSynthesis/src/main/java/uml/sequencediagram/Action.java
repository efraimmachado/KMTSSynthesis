package uml.sequencediagram;

import java.util.HashSet;
import java.util.Set;
import logic.booleanexpression.IBooleanExpression;

import uml.ocl.OCLRule;

public class Action {
    
        //A ação tem:
        //um "rotulo"
        //um conjundo de precondições
        //um conjunto de postcondições
	private String action;
	private Set<OCLRule> oclPreconditionRules;	
	private Set<OCLRule> oclPosconditionRules;

        //Cria uma ação recebendo o "rotulo"
	public Action(String action) {
		super();
		this.action = action;
		oclPreconditionRules = new HashSet<OCLRule>();
		oclPosconditionRules = new HashSet<OCLRule>();
	}

        //Retorna o "rotulo" da ação.
	public String getAction() {
		return action;
	}

        //Troca o "rotulo" da ação pelo dado.
	public void setAction(String action) {
		this.action = action;
	}

        //Retorna o cojunto de precondições da ação.
	public Set<OCLRule> getOclPreconditionRules() {
		return oclPreconditionRules;
	}

        //Adiciona uma expressão como pre condição da ação.
        public void AddOclPreconditionRules(IBooleanExpression expression) {
            OCLRule p = new OCLRule();
            p.setAction(this);
            p.setExpression(expression);
            if(!oclPreconditionRules.contains(p))
            {
                this.oclPreconditionRules.add(p);
            }
            //cobertura temporária de casos
            else 
            {
                System.out.println("Está expressão já existe como precondição da ação.");
            }
        }
        
        //Retorna o cojunto de poscondições da ação
	public Set<OCLRule> getOclPosconditionRules() {
		return oclPosconditionRules;
	}	
        
        //Adiciona uma expressão como poscondição da ação.
        public void AddOclPosconditionRules(IBooleanExpression expression) {
            OCLRule p = new OCLRule();
            p.setAction(this);
            p.setExpression(expression);
            if(!oclPosconditionRules.contains(p))
            {
                this.oclPosconditionRules.add(p);
            }
            //cobertura temporária de casos
            else 
            {
                System.out.println("Está expressão já existe como poscondição da ação.");
            }
        }
}
