package relations.refinement.refinementgame;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kmts.KMTS;
import kmts.element.State;
import kmts.element.Transition;
import logic.Pair;
import relations.refinement.RefinementRelation;

public class RefinementGame {

	private KMTS specification;
	private KMTS model;
	
	Set<IVertice> vertices;
	Set<IEdge> edges;
	
	Set<IVertice> failureWitnesses;
	Set<SpoilerVertice> spoilerVertices;
	
	Boolean isRefinement;
	
	public RefinementGame(KMTS specification, KMTS model) {
		super();
		this.specification = specification;
		this.model = model;
		isRefinement = false;
	}
	
	public void generateRefinementGame()
	{
		initGraphComponents();
		if (specification != null && model != null)
		{
			Set<SpoilerVertice> spoilerVertices = generateSpoilerVertices();
			Set<DuplicatorVertice> duplicatorVertices = generateDuplicatorVertices();
			Set<IEdge> allEdges = generateEdges(spoilerVertices, duplicatorVertices);
			if (allEdges != null && !allEdges.isEmpty())
			{
				edges.addAll(allEdges);
				vertices.addAll(spoilerVertices);
				vertices.addAll(duplicatorVertices);
				removeUntargeableVertices(vertices);
				failureWitnesses.addAll(findFailureWitnesses());
				//printGraph();
				this.spoilerVertices = getSpoilerVertices();
				if (getInitialVertice(vertices) == null)
				{
					failureWitnesses.add(new DuplicatorVertice(specification.getInitialState(), null, null, null, -1));
				}
				isRefinement = isDuplicatorWinsRefinamentGame();
			}
		}
	}

	public Set<SpoilerVertice> getSpoilerVertices() {
		Set<SpoilerVertice> result = new HashSet<SpoilerVertice>();
		Iterator<IVertice> it = vertices.iterator();
		IVertice vertice;
		while(it.hasNext())
		{
			vertice = it.next();
			if (vertice instanceof SpoilerVertice)
			{
				result.add((SpoilerVertice) vertice);
			}
		}
		return result;
	}

	private void printGraph() {
		System.out.println("REFINEMENT GAME");
		Iterator<IVertice> it = vertices.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		Iterator<IEdge> itEdge = edges.iterator();
		while(itEdge.hasNext())
		{
			System.out.println(itEdge.next());
		}
	}

	private void removeUntargeableVertices(Set<IVertice> allVertices) 
	{
		Set<IVertice> visitedVertices = new HashSet<IVertice>();
		IVertice vertice = getInitialVertice(allVertices);
		if (vertice != null)
		{
			visitVertice(vertice, visitedVertices);
		}
		allVertices.retainAll(visitedVertices);
		vertices.addAll(allVertices);
		Iterator<IEdge> it = edges.iterator();
		IEdge edge;
		Set<IEdge> edgesToRemove = new HashSet<IEdge>();
		while(it.hasNext())
		{
			edge = it.next();
			if (!vertices.contains(edge.getFromVertice()) && !vertices.contains(edge.getToVertice()))
			{
				edgesToRemove.add(edge);
			}
		}
		edges.removeAll(edgesToRemove);
	}

	private void visitVertice(IVertice vistedVertice, Set<IVertice> visitedVertices) {
		Iterator<IEdge> it = null; 
		visitedVertices.add(vistedVertice);
		if (vistedVertice.getOutEdges() != null)
		{
			it = vistedVertice.getOutEdges().iterator();
			while (it.hasNext())
			{
				IVertice nextVertice = it.next().getToVertice();
				if (!visitedVertices.contains(nextVertice))
				{
					visitVertice(nextVertice, visitedVertices);
				}
			}
		}
	}

	private IVertice getInitialVertice(Set<IVertice> allVertices) {
		
		Iterator<IVertice> it = allVertices.iterator();
		IVertice vertice = null;
		while(it.hasNext())
		{
			vertice = it.next();
			if (vertice.isSpoilerVertice())
			{
				if (((SpoilerVertice)vertice).getSpecificationState().equals(specification.getInitialState()))
				{
					if (((SpoilerVertice)vertice).getModelState().equals(model.getInitialState()))
					{
						return vertice;
					}
				}
			}
		}
		return null;		
	}

	private Set<IVertice> findFailureWitnesses()
	{
		Set<IVertice> result = new HashSet<IVertice>();
		Set<IVertice> visitedVertices = new HashSet<IVertice>();
		IVertice vertice = getInitialVertice(vertices);
		if (vertice != null)
		{
			visitVertice(vertice, visitedVertices);
		}
		Iterator<IVertice> it = visitedVertices.iterator();
		while(it.hasNext())
		{
			vertice = it.next();
			if (vertice.isDuplicatorVertice() && !vertice.hasOutEdges())
			{
				result.add(vertice);
			}
		}
		return result;
	}
	
	private boolean isDuplicatorWinsRefinamentGame() 
	{
		return failureWitnesses.isEmpty();
	}

	private Set<IEdge> generateEdges(Set<SpoilerVertice> spoilerVertices,
			Set<DuplicatorVertice> duplicatorVertices) {
		Set<IEdge> result = new HashSet<IEdge>();
		Iterator<SpoilerVertice> itSpoilerVertice = spoilerVertices.iterator();
		Iterator<DuplicatorVertice> itDuplicatorVertice;
		Edge edge = null;
		SpoilerVertice spoilerVertice = null;
		DuplicatorVertice duplicatorVertice = null;
		while (itSpoilerVertice.hasNext())
		{
			spoilerVertice = itSpoilerVertice.next();
			itDuplicatorVertice = duplicatorVertices.iterator();
			while (itDuplicatorVertice.hasNext())
			{
				duplicatorVertice = itDuplicatorVertice.next();
				edge = null;
				if (hasConditionToCreateES1(spoilerVertice, duplicatorVertice))
				{
					edge = new Edge(spoilerVertice, duplicatorVertice, true);
				}
				else if(hasConditionToCreateES2(spoilerVertice, duplicatorVertice))
				{
					edge = new Edge(spoilerVertice, duplicatorVertice, false);
				}
				else if(hasConditionToCreateED1(spoilerVertice, duplicatorVertice))
				{
					edge = new Edge(duplicatorVertice, spoilerVertice, false);
				}
				else if(hasConditionToCreateED2(spoilerVertice, duplicatorVertice))
				{
					edge = new Edge(duplicatorVertice, spoilerVertice, true);
				}
				if (edge != null)
				{
					result.add(edge);
				}
				
			}
			
		}
		return result;
	}
	private boolean hasConditionToCreateED2(SpoilerVertice spoilerVertice,
			DuplicatorVertice duplicatorVertice) 
	{
		if (duplicatorVertice.getToState().equals(spoilerVertice.getModelState()))
		{
			if (spoilerVertice.getSpecificationState().isDirectlyReachableFromMayTransitionAndAction(duplicatorVertice.getSpecificationState(), duplicatorVertice.getToStateAction()))
			{
				if (spoilerVertice.getModelState().isDirectlyReachableFromMayTransitionAndAction(duplicatorVertice.getModelState(),
						duplicatorVertice.getToStateAction()))
				{
					return true;
				}	
			}
		}
		return false;
	}

	
	private boolean hasConditionToCreateED1(SpoilerVertice spoilerVertice,
			DuplicatorVertice duplicatorVertice) {
		if (duplicatorVertice.getToState().equals(spoilerVertice.getSpecificationState()))
		{
			if (spoilerVertice.getSpecificationState().isDirectlyReachableFromMustTransition(duplicatorVertice.getSpecificationState()))
			{
				if (spoilerVertice.getModelState().isDirectlyReachableFromMustTransitionAndAction(duplicatorVertice.getModelState(),
						duplicatorVertice.getToStateAction()))
				{
					return true;
				}	
			}
		}
		return false;
	}

	private boolean hasConditionToCreateES2(SpoilerVertice spoilerVertice,
			DuplicatorVertice duplicatorVertice) {
		if (spoilerVertice.getSpecificationState().equals(duplicatorVertice.getSpecificationState()))
		{
			if (spoilerVertice.getModelState().equals(duplicatorVertice.getModelState()))
			{
				return duplicatorVertice.getToState().isDirectlyReachableFromMayTransition(spoilerVertice.getModelState());
			}
		}
		return false;
	}

	
	private boolean hasConditionToCreateES1(SpoilerVertice spoilerVertice,
			DuplicatorVertice duplicatorVertice) {
		if (spoilerVertice.getSpecificationState().equals(duplicatorVertice.getSpecificationState()))
		{
			if (spoilerVertice.getModelState().equals(duplicatorVertice.getModelState()))
			{
				return duplicatorVertice.getToState().isDirectlyReachableFromMustTransition(spoilerVertice.getSpecificationState());
			}
		}
		return false;
	}

	private Set<DuplicatorVertice> generateDuplicatorVertices() {
		Set<DuplicatorVertice> duplicatorVertices = new HashSet<DuplicatorVertice>();
		int id = 0;
		if (!specification.getStates().isEmpty()&& !model.getStates().isEmpty())
		{
			Iterator<State> itSpecification = specification.getStates().iterator();
			Iterator<State> itModel = null;
			State specificationState, modelState;
			DuplicatorVertice duplicatorVertice = null;
			Iterator<Transition> itTransition = null;
			Transition transition = null;
			while(itSpecification.hasNext())
			{
				specificationState = itSpecification.next();
				itModel = model.getStates().iterator();
				while(itModel.hasNext())
				{
					modelState = itModel.next();
					if (RefinementRelation.areLiteralRefinament(specificationState, modelState))
					{
						itTransition = specificationState.getOutTransitions().iterator();
						while(itTransition.hasNext())
						{
							transition = itTransition.next();
							
							if (transition.isMustTransition())
							{
								duplicatorVertice = new DuplicatorVertice(specificationState, transition.getAction(), transition.getToState(), modelState, id++);
								duplicatorVertices.add(duplicatorVertice);								
							}							
						}
						
						itTransition = modelState.getOutTransitions().iterator();
						while(itTransition.hasNext())
						{
							transition = itTransition.next();
							duplicatorVertice = new DuplicatorVertice(specificationState, transition.getAction(), transition.getToState(), modelState, id++);
							duplicatorVertices.add(duplicatorVertice);								
						}

					}
				}
			}		
		}
		return duplicatorVertices;
	}

	private Set<SpoilerVertice> generateSpoilerVertices() {
		Set<SpoilerVertice> SpoilerVertices = new HashSet<SpoilerVertice>();
		int id = 0;
		if (!specification.getStates().isEmpty()&& !model.getStates().isEmpty())
		{
			Iterator<State> itSpecification = specification.getStates().iterator();
			Iterator<State> itModel = null;
			State specificationState, modelState;
			SpoilerVertice spoilerVertice = null;
			while(itSpecification.hasNext())
			{
				specificationState = itSpecification.next();
				itModel = model.getStates().iterator();
				while(itModel.hasNext())
				{
					modelState = itModel.next();
					if (RefinementRelation.areLiteralRefinament(specificationState, modelState))
					{
						spoilerVertice = new SpoilerVertice(specificationState, modelState, id++);
						SpoilerVertices.add(spoilerVertice);
					}
				}
			}		
		}
		return SpoilerVertices;
	}


	private void initGraphComponents() 
	{
		//vertices.clear();
		//edges.clear();
		vertices = new HashSet<IVertice>();
		edges = new HashSet<IEdge>();
		failureWitnesses = new HashSet<IVertice>();
		
	}

	public Boolean getIsRefinement() {
		return isRefinement;
	}

	public void setIsRefinement(Boolean isRefinement) {
		this.isRefinement = isRefinement;
	}

	public Set<IVertice> getFailureWitnesses() {
		return failureWitnesses;
	}

	public void setFailureWitnesses(Set<IVertice> failureWitnesses) {
		this.failureWitnesses = failureWitnesses;
	}

	public Set<Pair<State, State>> getRefinementPairs() {
		//retirar os estados que alcancam testemunhas de falha
		Set<Pair<State, State>> result = new HashSet<Pair<State, State>>();
		Iterator<SpoilerVertice> it = spoilerVertices.iterator();
		SpoilerVertice vertice;
		Pair<State, State> pair;
		while(it.hasNext())
		{
			vertice = it.next();
			pair = new Pair<State, State>(vertice.getSpecificationState(), vertice.getModelState());
			result.add(pair);
		}
		return result;
	}
	
	
}
