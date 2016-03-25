package kmts.element;

public class AtomPreposition {

	private String literal;
	private Boolean value;
	
	public AtomPreposition(String literal, Boolean value) {
		super();
		this.literal = literal;
		this.value = value;
	}
	
	public String getLiteral() {
		return literal;
	}
	public void setLiteral(String literal) {
		this.literal = literal;
	}
	public Boolean getValue() {
		return value;
	}
	public void setValue(Boolean value) {
		this.value = value;
	}
	
	
	@Override
	public String toString()
	{
		return literal+" = "+(value == null?"?":(value?"T":"F")); 
	}
	
}
