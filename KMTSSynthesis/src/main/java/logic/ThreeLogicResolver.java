package logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.booleanexpression.AtomicProposition;
import logic.booleanexpression.IBooleanExpression;

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

	public static Set<Set<AtomicProposition>> getValuationsSatisfiesExpression(IBooleanExpression expression)
	{
		Set<Set<AtomicProposition>> result = new HashSet<Set<AtomicProposition>>();
		IBooleanExpression copyExpression = expression.createCopiedExpression();
		Set<AtomicProposition> atomicPropositions = copyExpression.getAtomicPrepositions();
		int numberOfCombinations = (int) Math.pow(atomicPropositions.size(),3);
		
		if (copyExpression.isTrue())
		{
			result.add(copyAtomicPropositions(atomicPropositions));
		}
		
		
		return result;
	}

	private static Set<AtomicProposition> copyAtomicPropositions(
			Set<AtomicProposition> atomicPropositions) {
		Set<AtomicProposition> result = new HashSet<AtomicProposition>();
		Iterator<AtomicProposition> it = atomicPropositions.iterator();
		while(it.hasNext())
		{
			result.add((AtomicProposition) it.next().createCopiedExpression());
		}
		return result;
	}
	
	
	
}
