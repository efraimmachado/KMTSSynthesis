package logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
		List<AtomicProposition> orderedAtomicPropositions = new ArrayList<AtomicProposition>(atomicPropositions);
		Boolean[][] thruthTable = generateThruthTable(orderedAtomicPropositions.size());
		int rows, collumns;
		rows = thruthTable.length;
		collumns = thruthTable[0].length;
		
		for (int i  = 0; i < rows; i++)
		{
			for (int j = 0; j < collumns; j++)
			{
				orderedAtomicPropositions.get(j).setValue(thruthTable[i][j]);
			}
			if (copyExpression.isTrue())
			{
				result.add(copyAtomicPropositions(atomicPropositions));
			}
		}
		return result;
	}

	private static Boolean[][] generateThruthTable(int numberOfAtomicPropositions)
	{
 	    Boolean[] possibleValues = {true, false, null};
        int numberOfPossibleValues = possibleValues.length;         
        int i[] = new int[numberOfAtomicPropositions];

        int numberOfCombinations = (int) Math.pow(numberOfPossibleValues,numberOfAtomicPropositions);
        Boolean[][] thruthTable = new Boolean[numberOfCombinations][numberOfAtomicPropositions];
        int possibleValueIndex = 0;
        for(int j=0; j<Math.pow(numberOfPossibleValues,numberOfAtomicPropositions); j++)
        {
        	possibleValueIndex=0;
        	while(possibleValueIndex<numberOfAtomicPropositions)
        	{
        		thruthTable[j][possibleValueIndex] = possibleValues[i[possibleValueIndex]]; 
        		possibleValueIndex++;
        	}
        	possibleValueIndex = 0;
        	while(possibleValueIndex<numberOfAtomicPropositions)
        	{
        		if(i[possibleValueIndex]<numberOfPossibleValues-1)
        		{
        			i[possibleValueIndex]++;
        			break;
        		}
        		else
        		{
        			i[possibleValueIndex]=0;
        		}
        		possibleValueIndex++;
            }
        }
		return thruthTable;
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
