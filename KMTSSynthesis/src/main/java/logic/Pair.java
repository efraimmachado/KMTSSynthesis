package logic;

public class Pair<X, Y> {
	
	
	private X first;
	private Y second;
	
	public Pair(X first, Y second)
	{
		super();
		this.first = first;
		this.second = second;
	}
	
	public X getFirst() {
		return first;
	}
	public void setFirst(X first) {
		this.first = first;
	}
	public Y getSecond() {
		return second;
	}
	public void setSecond(Y second) {
		this.second = second;
	}
	
	@Override
	public String toString()
	{
		String firstString = "NULL";
		String secondString = "NULL";
		if (first != null)
		{
			firstString = first.toString();
		}
		if (second != null)
		{
			secondString = second.toString();
		}

		return "("+firstString+", "+secondString+")";
	}
	

}
