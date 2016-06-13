package relations.refinement.refinementgame;

public class Edge implements IEdge {

	private IVertice fromVertice;
	private IVertice toVertice;
	
	private boolean movementAtSpecification;
	
	
	public Edge(IVertice fromVertice, IVertice toVertice, boolean movementAtSpecification) {
		super();
		this.fromVertice = fromVertice;
		fromVertice.addOutEdge(this);
		this.toVertice = toVertice;
		toVertice.addInEdge(this);
		this.movementAtSpecification = movementAtSpecification;
	}
	
	@Override
	public IVertice getFromVertice() {
		return fromVertice;
	}
	@Override
	public void setFromVertice(IVertice fromVertice) {
		this.fromVertice = fromVertice;
	}
	@Override
	public IVertice getToVertice() {
		return toVertice;
	}
	@Override
	public void setToVertice(IVertice toVertice) {
		this.toVertice = toVertice;
	}
	
	public boolean isMovementAtSpecification() {
		return movementAtSpecification;
	}

	public void setMovementAtSpecification(boolean movementAtSpecification) {
		this.movementAtSpecification = movementAtSpecification;
	}

	@Override
	public String toString()
	{
		return fromVertice+" => "+toVertice;
	}
}
