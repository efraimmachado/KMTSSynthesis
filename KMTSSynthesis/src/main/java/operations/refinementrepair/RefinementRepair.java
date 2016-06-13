package operations.refinementrepair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kmts.KMTS;
import kmts.element.State;
import logic.Pair;
import operations.refinementrepair.causeoffailure.CauseOfFailure;
import operations.refinementrepair.causeoffailure.SpoilerCannotStartGameCauseOfFailure;
import operations.refinementrepair.causeoffailure.ThereIsNoTransitionCauseOfFailure;
import operations.refinementrepair.causeoffailure.ThereIsTransitionCauseOfFailure;
import operations.refinementrepair.primitivechange.IPrimitiveChange;
import operations.refinementrepair.repairtemplate.AddTransitionRepairTemplate;
import operations.refinementrepair.repairtemplate.IRepairTemplate;
import operations.refinementrepair.repairtemplate.ModifyInitialStateRepairTemplate;
import operations.refinementrepair.repairtemplate.RemoveTransitionRepairTemplate;
import relations.refinement.refinementgame.DuplicatorVertice;
import relations.refinement.refinementgame.IVertice;
import relations.refinement.refinementgame.RefinementGame;
import relations.refinement.refinementgame.SpoilerVertice;

public class RefinementRepair {

	int maxNumberOfStatesInModel;
	
	public Set<KMTS> refinementRepair(KMTS specification, KMTS model)
	{
		maxNumberOfStatesInModel = calculateMaxNumberOfStatesInModel(specification);
		maxNumberOfStatesInModel = model.getStates().size();
		return refinementRepair(specification, model, new ArrayList<IPrimitiveChange>());
	}
	
	private int calculateMaxNumberOfStatesInModel(KMTS specification) 
	{
		//return specification.size();
		return 0;
	}

	private boolean isElementOfSubset(State element, Set<Set<State>> quocientSet) 
	{
		Iterator<Set<State>> it = quocientSet.iterator();
		while(it.hasNext())
		{
			if (it.next().contains(element))
			{
				return true;
			}
		}
		return false;
	}

	public Set<KMTS> refinementRepair(KMTS specification, KMTS model, List<IPrimitiveChange> appliedModifications)
	{
		specification.normalize();
		model.normalize();
		Set<KMTS> result = new HashSet<KMTS>();
		Set<CauseOfFailure> causesOfFailure = findCausesOfFailure(specification, model);
		if (!causesOfFailure.isEmpty())
		{
			Iterator<CauseOfFailure> it = causesOfFailure.iterator();
			CauseOfFailure causeOfFailure = null;
			Set<List<IPrimitiveChange>> possibleRepairs = null;
			
			Set<Pair<KMTS, List<IPrimitiveChange>>> modelsAndItsRepairs = null;
			while (it.hasNext())
			{
				causeOfFailure = it.next();
				//System.out.println("MUDANÇAS REALIZADAS: "+appliedModifications);
				//System.out.println("CAUSA DA FALHA ENCONTRADA: "+causeOfFailure);
				possibleRepairs = findApplicableRepairs(model, causeOfFailure, appliedModifications);
				if (!possibleRepairs.isEmpty())
				{
					//System.out.println("POSSIVEIS REPAROS: "+possibleRepairs);
					modelsAndItsRepairs = applyPossibleRepairs(model, possibleRepairs);
					if (!modelsAndItsRepairs.isEmpty())
					{
						Iterator<Pair<KMTS, List<IPrimitiveChange>>> itModels = modelsAndItsRepairs.iterator();
						Pair<KMTS, List<IPrimitiveChange>> pair = null;
						while(itModels.hasNext())
						{
							pair = itModels.next();
							pair.getSecond().addAll(appliedModifications);
							result.addAll(refinementRepair(specification, pair.getFirst(), pair.getSecond()));
						}
					}
				}
			}
		}
		else
		{
			//System.out.println("REFINAMENTO ENCONTRADO:\n"+model);
			System.out.println("REFINAMENTO:\nMODS: "+appliedModifications+"\nMODELO:"+model);
			result.add(model);
		}
		return result;
	}

	private Set<Pair<KMTS, List<IPrimitiveChange>>> applyPossibleRepairs(
			KMTS model, Set<List<IPrimitiveChange>> possibleRepairs) 
	{
		Set<Pair<KMTS, List<IPrimitiveChange>>> result = new HashSet<Pair<KMTS, List<IPrimitiveChange>>>();
		Iterator<List<IPrimitiveChange>> itPossibleRepairs = null;
		itPossibleRepairs = possibleRepairs.iterator();
		while (itPossibleRepairs.hasNext())
		{
			KMTS modelClone = model.clone();
			List<IPrimitiveChange> possibleRepair = itPossibleRepairs.next();
			for (int i =0; i < possibleRepair.size(); i++)
			{
				possibleRepair.get(i).apply(modelClone);
			}
			result.add(new Pair<KMTS, List<IPrimitiveChange>>(modelClone, possibleRepair));
		}
		return result;
	}


	private Set<List<IPrimitiveChange>> findApplicableRepairs(KMTS model,
			CauseOfFailure causeOfFailure,
			List<IPrimitiveChange> appliedModifications) 
	{
		Set<List<IPrimitiveChange>> result =  new HashSet<List<IPrimitiveChange>>();
		Set<List<IPrimitiveChange>> possibleRepairs = findPossibleRepairs(model, causeOfFailure);
		result = removeNonConsistentPossibleRepairs(possibleRepairs, appliedModifications);
		return result;
	}

	private Set<List<IPrimitiveChange>> findPossibleRepairs(KMTS model,
			CauseOfFailure causeOfFailure) 
		{
		Set<List<IPrimitiveChange>> result = new HashSet<List<IPrimitiveChange>>();
		
		IRepairTemplate repairTemplate;
		
		if (causeOfFailure instanceof ThereIsNoTransitionCauseOfFailure)
		{
			repairTemplate = new AddTransitionRepairTemplate(model,(ThereIsNoTransitionCauseOfFailure)causeOfFailure);
		}
		else if (causeOfFailure instanceof ThereIsTransitionCauseOfFailure)
		{
			repairTemplate = new RemoveTransitionRepairTemplate((ThereIsTransitionCauseOfFailure)causeOfFailure);
		}
		else
		{
			repairTemplate = new ModifyInitialStateRepairTemplate((SpoilerCannotStartGameCauseOfFailure)causeOfFailure);
		}
		result = repairTemplate.generatePossibleRepairs(model);
		return result;
	}

	private Set<List<IPrimitiveChange>> removeNonConsistentPossibleRepairs(
			Set<List<IPrimitiveChange>> possibleRepairs,
			List<IPrimitiveChange> appliedModifications) {
		Set<List<IPrimitiveChange>> result =  new HashSet<List<IPrimitiveChange>>();
		Iterator<List<IPrimitiveChange>> it = possibleRepairs.iterator();
		List<IPrimitiveChange> possibleRepair = null;
		
		while(it.hasNext())
		{
			possibleRepair = it.next();
			if (areConsistent(possibleRepair, appliedModifications))
			{
				result.add(possibleRepair);
			}
		}

		return result;
	}

	private boolean areConsistent(List<IPrimitiveChange> possibleRepair,
			List<IPrimitiveChange> appliedModifications) {

		for(int i = 0; i < appliedModifications.size(); i++)
		{
			for (int j = 0; j < possibleRepair.size(); j++)
			{
				if (!areConsistent(possibleRepair.get(j), appliedModifications.get(i)))
				{
					return false;
				}
			}
		}
		return true;
	}

	private boolean areConsistent(IPrimitiveChange possibleChange,
			IPrimitiveChange appliedChange) {
		return appliedChange.isConsistent(possibleChange);
	}

	private Set<CauseOfFailure> findCausesOfFailure(KMTS specification,
			KMTS model) {
		RefinementGame refinementGame = new RefinementGame(specification, model);
		refinementGame.generateRefinementGame();
		Set<CauseOfFailure> result = new HashSet<CauseOfFailure>();
		result.addAll(extractCauseOfFailure(refinementGame.getFailureWitnesses()));
		return result;
	}

	private Set<CauseOfFailure> extractCauseOfFailure(
			Set<IVertice> failureWitnesses) {
		Set<CauseOfFailure> result = new HashSet<CauseOfFailure>();
		Iterator<IVertice> it = failureWitnesses.iterator();
		IVertice failureWitness = null;
		while(it.hasNext())
		{
			failureWitness = it.next();
			result.add(extractCauseOfFailure((DuplicatorVertice)failureWitness));
		}
		return result;
	}

	private CauseOfFailure extractCauseOfFailure(DuplicatorVertice failureWitness) {

		CauseOfFailure result = null;
		if (failureWitness.mustToMoveInSpecification())
		{
			result = new ThereIsTransitionCauseOfFailure(failureWitness);
		}
		else if(failureWitness.mustToMoveInModel())
		{
			result = new ThereIsNoTransitionCauseOfFailure(failureWitness);
		}
		else
		{
			result = new SpoilerCannotStartGameCauseOfFailure(failureWitness.getSpecificationState());
		}
		return result;
	}


}
