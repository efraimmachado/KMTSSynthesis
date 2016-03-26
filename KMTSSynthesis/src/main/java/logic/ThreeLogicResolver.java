package logic;

public class ThreeLogicResolver {

	public static Boolean solveOr(Boolean v1, Boolean v2) 
	{
		if (v1 == null && v2 == null)
		{
			return null;
		}
		else if (v1 == null)
		{
			return v2;
		}
		else if (v2 == null)
		{
			return v1;
		}
		else
		{
			return v1 || v2;
		}
	}

	public static Boolean solveAnd(Boolean v1, Boolean v2) 
	{
		if (v1 == null || v2 == null)
		{
			return null;
		}
		else
		{
			return v1 && v2;
		}
	}

	public static Boolean solveAtomPreposition(Boolean v)
	{
		return v;
	}
	
	public static Boolean solveNeg(Boolean v) 
	{
		if (v != null)
		{
			return !v;
		}
		return null;
	}


	
	
	
}
