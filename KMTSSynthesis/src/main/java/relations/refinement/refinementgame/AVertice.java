package relations.refinement.refinementgame;

import java.util.HashSet;
import java.util.Set;

public abstract class AVertice implements IVertice {

	protected Set<IEdge> inEdges;
	protected Set<IEdge> outEdges;
	protected int id;
	
	public AVertice() {
		super();
		inEdges = new HashSet<IEdge>();
		outEdges = new HashSet<IEdge>();
		id = 0;
	}
	
	@Override
	public Set<IEdge> getInEdges() {
		return inEdges;
	}
	
	@Override
	public Set<IEdge> getOutEdges() {
		return outEdges;
	}
	public void setInEdges(Set<IEdge> inEdges) {
		this.inEdges = inEdges;
	}
	public void setOutEdges(Set<IEdge> outEdges) {
		this.outEdges = outEdges;
	}
	
	public void addInEdge(IEdge edge)
	{
		if (edge != null)
		{
			inEdges.add(edge);
		}
	}
	
	public void addOutEdge(IEdge edge)
	{
		if (edge != null)
		{
			outEdges.add(edge);
		}
	}
	
	public boolean hasOutEdges()
	{
		if (outEdges != null)
		{
			return !outEdges.isEmpty();
		}
		return false; 
	}
	
	public boolean hasInEdges()
	{
		if (inEdges != null)
		{
			return !inEdges.isEmpty();
		}
		return false; 
	}
	
	
}
