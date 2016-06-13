package relations.refinement.refinementgame;

import java.util.Set;

public interface IVertice {

	public void addInEdge(IEdge edge);
	public void addOutEdge(IEdge edge);
	public boolean hasOutEdges();
	public boolean hasInEdges();
	public boolean isSpoilerVertice();
	public boolean isDuplicatorVertice();
	public Set<IEdge> getInEdges();
	public Set<IEdge> getOutEdges();
}
