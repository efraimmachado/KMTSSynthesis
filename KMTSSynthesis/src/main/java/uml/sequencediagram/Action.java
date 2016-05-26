package uml.sequencediagram;

import java.util.HashSet;
import java.util.Set;
import logic.booleanexpression.IBooleanExpression;

import uml.ocl.OCLRule;

public class Action {
    
        //A a��o tem:
        //um "rotulo"
        //um conjundo de precondi��es
        //um conjunto de postcondi��es
	private String action;
	private Set<OCLRule> oclPreconditionRules;	
	private Set<OCLRule> oclPosconditionRules;

        //Cria uma a��o recebendo o "rotulo"
	public Action(String action) {
		super();
		this.action = action;
		oclPreconditionRules = new HashSet<OCLRule>();
		oclPosconditionRules = new HashSet<OCLRule>();
	}

        //Retorna o "rotulo" da a��o.
	public String getAction() {
		return action;
	}

        //Troca o "rotulo" da a��o pelo dado.
	public void setAction(String action) {
		this.action = action;
	}

        //Retorna o cojunto de precondi��es da a��o.
	public Set<OCLRule> getOclPreconditionRules() {
		return oclPreconditionRules;
	}

        //Adiciona uma express�o como pre condi��o da a��o.
        public void AddOclPreconditionRules(IBooleanExpression expression) {
            OCLRule p = new OCLRule();
            p.setAction(this);
            p.setExpression(expression);
            if(!oclPreconditionRules.contains(p))
            {
                this.oclPreconditionRules.add(p);
            }
            //cobertura tempor�ria de casos
            else 
            {
                System.out.println("Est� express�o j� existe como precondi��o da a��o.");
            }
        }
        
        //Retorna o cojunto de poscondi��es da a��o
	public Set<OCLRule> getOclPosconditionRules() {
		return oclPosconditionRules;
	}	
        
        //Adiciona uma express�o como poscondi��o da a��o.
        public void AddOclPosconditionRules(IBooleanExpression expression) {
            OCLRule p = new OCLRule();
            p.setAction(this);
            p.setExpression(expression);
            if(!oclPosconditionRules.contains(p))
            {
                this.oclPosconditionRules.add(p);
            }
            //cobertura tempor�ria de casos
            else 
            {
                System.out.println("Est� express�o j� existe como poscondi��o da a��o.");
            }
        }
}
