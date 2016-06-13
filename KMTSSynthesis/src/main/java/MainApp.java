import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import operations.refinementrepair.RefinementRepair;

import kmts.KMTS;
import kmts.element.State;
import kmts.element.Transition;
import logic.ThreeLogicResolver;
import logic.booleanexpression.AndBooleanExpression;
import logic.booleanexpression.AtomicProposition;
import logic.booleanexpression.IBooleanExpression;
import logic.booleanexpression.NegBooleanExpression;
import relations.refinement.refinementgame.RefinementGame;
import synthesizer.Synthesizer;
import uml.sequencediagram.Action;
import uml.sequencediagram.Lifeline;
import uml.sequencediagram.SequenceDiagram;


public class MainApp {

	public static void main(String args[])
	{
		//testKMTS();
		//testSequenceDiagram();
		//testBooleanExpression();
		//testValuationToExpression();
		//testPermutation();
        //testStateToString();
		//testRefinementGame();
		//testSimpleRefinementRepair();
		testRefinementRepair();
		
	}
	
	private static void testSimpleRefinementRepair() {
		RefinementRepair refinementRepair = new RefinementRepair();
		System.out.println("REFINAMENTOS:\n"+refinementRepair.refinementRepair(getSimpleSpecification(), getSimpleModel()));
		
	}

	private static KMTS getSimpleSpecification()
	{
		KMTS kmts = new KMTS("SIMPLE SPECIFICATION");
		
		State readyState = new State();
		readyState.setLabel("s0");
		readyState.addPreposition("s", true);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("s1");
		autoFocusState.addPreposition("s", true);
		kmts.addState(autoFocusState);
		kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMustTransitionBetween(autoFocusState, readyState, "a");
		return kmts;
	}

	private static KMTS getSimpleModel()
	{
		KMTS kmts = new KMTS("SIMPLE MODEL");
		
		State readyState = new State();
		readyState.setLabel("t0");
		readyState.addPreposition("s", true);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", true);
		kmts.addState(autoFocusState);
		kmts.addMayTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMustTransitionBetween(autoFocusState, readyState, "a");
		return kmts;
	}

	
	private static void testRefinementRepair() {
		RefinementRepair refinementRepair = new RefinementRepair();
		//refinementRepair.refinementRepair(getCameraSpecification(), getCameraModelCThreeLines());
		System.out.println("REFINAMENTOS:\n"+refinementRepair.refinementRepair(getCameraSpecification(), getCameraModelC()));
		
	}

	private static void testRefinementGame()
	{
		RefinementGame refinementGame = new RefinementGame(getCameraSpecification(), getCameraModelB());
		refinementGame.generateRefinementGame();
		System.out.println("OS MODELOS"+(refinementGame.getIsRefinement()?"":" NÃO")+" SÃO REFINAMENTO");
		RefinementGame refinementGame2 = new RefinementGame(getCameraSpecification(), getCameraModelD());
		refinementGame2.generateRefinementGame();
		System.out.println("OS MODELOS"+(refinementGame2.getIsRefinement()?"":" NÃO")+" SÃO REFINAMENTO");
		System.out.println("CAUSAS DE FALHA: "+refinementGame2.getFailureWitnesses());
		

	}
	
	private static KMTS getCameraSpecification()
	{
		KMTS kmts = new KMTS("CAMERA SPECIFICATION");
		
		State readyState = new State();
		readyState.setLabel("s0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", false);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("s1");
		autoFocusState.addPreposition("s", false);
		autoFocusState.addPreposition("fo", true);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		State faceFocusState = new State();
		faceFocusState.setLabel("s2");
		faceFocusState.addPreposition("s", false);
		faceFocusState.addPreposition("fo", true);
		faceFocusState.addPreposition("fl", null);
		kmts.addState(faceFocusState);
		kmts.addMayTransitionBetween(faceFocusState, autoFocusState, "b");
		kmts.addMayTransitionBetween(autoFocusState, faceFocusState, "b");
		
		State shootingState = new State();
		shootingState.setLabel("s3");
		shootingState.addPreposition("s", true);
		shootingState.addPreposition("fo", false);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		kmts.addMustTransitionBetween(autoFocusState, shootingState, "a");
		kmts.addMustTransitionBetween(shootingState, readyState, "t");
		
		System.out.println(kmts.toString());
		return kmts;
	}
	
	private static KMTS getCameraModelB()
	{
		KMTS kmts = new KMTS("CAMERA MODEL B");
		
		State readyState = new State();
		readyState.setLabel("t0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", false);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", false);
		autoFocusState.addPreposition("fo", true);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		State shootingState = new State();
		shootingState.setLabel("t2");
		shootingState.addPreposition("s", true);
		shootingState.addPreposition("fo", false);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		kmts.addMustTransitionBetween(autoFocusState, shootingState, "a");
		kmts.addMustTransitionBetween(shootingState, readyState, "t");
		
		System.out.println(kmts.toString());
		return kmts;
	}
	
	private static KMTS getCameraModelC()
	{
		KMTS kmts = new KMTS("CAMERA MODEL C");
		
		State readyState = new State();
		readyState.setLabel("t0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", false);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", false);
		autoFocusState.addPreposition("fo", true);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		State shootingState = new State();
		shootingState.setLabel("t2");
		shootingState.addPreposition("s", true);
		shootingState.addPreposition("fo", false);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		kmts.addMustTransitionBetween(autoFocusState, shootingState, "a");
		//kmts.addMustTransitionBetween(shootingState, readyState, "t");
		
		System.out.println(kmts.toString());
		return kmts;
	}

	private static KMTS getCameraModelCThreeLines()
	{
		KMTS kmts = new KMTS("CAMERA MODEL C'''");
		
		State readyState = new State();
		readyState.setLabel("t0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", true);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		kmts.addMustTransitionBetween(readyState, readyState, "a");
		
		State autoFocusState = new State();
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", false);
		autoFocusState.addPreposition("fo", false);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		//kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		State shootingState = new State();
		shootingState.setLabel("t2");
		shootingState.addPreposition("s", true);
		shootingState.addPreposition("fo", false);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		kmts.addMustTransitionBetween(autoFocusState, shootingState, "a");
		//kmts.addMustTransitionBetween(shootingState, readyState, "t");
		kmts.addMustTransitionBetween(shootingState, autoFocusState, "t");
		
		System.out.println(kmts.toString());
		return kmts;
	}
	
	private static KMTS getCameraModelCFourLines()
	{
		KMTS kmts = new KMTS("CAMERA MODEL C''''");
		State shootingState = new State();
		State readyState = new State();
		State autoFocusState = new State();
		
		readyState.setLabel("t0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", false);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", true);
		autoFocusState.addPreposition("fo", false);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		
		shootingState.setLabel("t2");
		shootingState.addPreposition("s", false);
		shootingState.addPreposition("fo", true);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		
		kmts.addMustTransitionBetween(readyState, shootingState, "a");
		kmts.addMustTransitionBetween(shootingState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		kmts.addMustTransitionBetween(shootingState, autoFocusState, "t");
		kmts.addMustTransitionBetween(autoFocusState, readyState, "t");
		
		
		System.out.println(kmts.toString());
		return kmts;
	}
	
	
	private static KMTS getCameraModelD()
	{
		KMTS kmts = new KMTS("CAMERA MODEL D");
		
		State readyState = new State();
		readyState.setLabel("t0");
		readyState.addPreposition("s", false);
		readyState.addPreposition("fo", false);
		readyState.addPreposition("fl", null);
		kmts.addInitialState(readyState);
		
		State autoFocusState = new State();
		autoFocusState.setLabel("t1");
		autoFocusState.addPreposition("s", false);
		autoFocusState.addPreposition("fo", true);
		autoFocusState.addPreposition("fl", null);
		kmts.addState(autoFocusState);
		kmts.addMustTransitionBetween(readyState, autoFocusState, "a");
		kmts.addMayTransitionBetween(autoFocusState, readyState, "c");
		
		State shootingState = new State();
		shootingState.setLabel("t2");
		shootingState.addPreposition("s", false);
		shootingState.addPreposition("fo", false);
		shootingState.addPreposition("fl", null);
		kmts.addState(shootingState);
		kmts.addMustTransitionBetween(autoFocusState, shootingState, "a");
		kmts.addMustTransitionBetween(shootingState, shootingState, "a");
		//kmts.addMustTransitionBetween(shootingState, readyState, "t");
		
		System.out.println(kmts.toString());
		return kmts;
	}
	
	private static void testValuationToExpression() 
	{
		AtomicProposition a, b, c;
		IBooleanExpression expression;
		a = new AtomicProposition("A", false);
		b = new AtomicProposition("B", false);
		c = new AtomicProposition("C", false);
		
		System.out.println("---[BOOLEAN EXPRESSION SOLUTIONS]--");
		//expression = new AndBooleanExpression(new AndBooleanExpression(a, b), new NegBooleanExpression(c));
		//expression = new OrBooleanExpression(new OrBooleanExpression(a, b), c);
		expression = a;
		System.out.println(expression.toString());
		System.out.println("SOLUTIONS:");
		Set<Set<AtomicProposition>> result = ThreeLogicResolver.getValuationsSatisfiesExpression(expression);
		Iterator<Set<AtomicProposition>> it = result.iterator();
		Iterator<AtomicProposition> innerIt;
		AtomicProposition p;
		int solutionNumber = 1;
		while(it.hasNext())
		{
			innerIt = it.next().iterator();
			System.out.println("SOLUTION["+solutionNumber+"]:");
			while(innerIt.hasNext())
			{
				p = innerIt.next();
				System.out.println(p.toStringWithValue());
			}
			solutionNumber++;
		}
		System.out.println("------");
		
	}

	private static void testPermutation() 
	{
 	    char[] possibleValues = {'T', 'F', '?'};
        int numberOfPossibleValues = possibleValues.length;
        int numberOfAtomicPropositions = 3; 
        int i[] = new int[numberOfAtomicPropositions];
        int numberOfCombinations = (int) Math.pow(numberOfPossibleValues,numberOfAtomicPropositions);
        char[][] result = new char[numberOfCombinations][numberOfAtomicPropositions];
        int possibleValueIndex = 0;
        for(int j=0; j<Math.pow(numberOfPossibleValues,numberOfAtomicPropositions); j++)
        {
        	possibleValueIndex=0;
        	while(possibleValueIndex<numberOfAtomicPropositions)
        	{
        		System.out.print(possibleValues[i[possibleValueIndex]] + " ");
        		result[j][possibleValueIndex] = possibleValues[i[possibleValueIndex]]; 
        		possibleValueIndex++;
        	}
        	System.out.println();
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
        System.out.println("hehe");
	 }

	private static void testBooleanExpression()
	{
		//(A^B^!C) = FALSE
		//
		
		testBoolExpression("(A^B^!C) = FALSE", true, false, true);
		testBoolExpression("(A^B^!C) = NULL?", true, null, true);
		testBoolExpression("(A^B^!C) = TRUE", true, true, false);
	}
	
	private static void testBoolExpression(String resolution, Boolean av, Boolean bv, Boolean cv) {
		AtomicProposition a, b, c;
		IBooleanExpression expression;
		a = new AtomicProposition("A", av);
		b = new AtomicProposition("B", bv);
		c = new AtomicProposition("C", cv);
		
		System.out.println("---[BOOLEAN EXPRESSION]--");
		System.out.println("RESOLUTION: "+resolution);
		expression = new AndBooleanExpression(new AndBooleanExpression(a, b), new NegBooleanExpression(c));
		System.out.println("AP VALUES:\n"+ expression.toStringAtomicPropositionsValue());
		System.out.println(expression.toString()+" = "+expression.getValue());
		System.out.println("------");
		System.out.println("UPDATE VALUE A TO: "+false);
		Set<AtomicProposition> propositionsWithNewValues = new HashSet<AtomicProposition>();
		a = new AtomicProposition("A", false);
		propositionsWithNewValues.add(a);
		expression.updateAtomicPropositionValues(propositionsWithNewValues);
		System.out.println("AP VALUES:\n"+ expression.toStringAtomicPropositionsValue());
		System.out.println(expression.toString()+" = "+expression.getValue());
		System.out.println("------");
	}

	private static void testSequenceDiagram()
	{
		SequenceDiagram sd = new SequenceDiagram("Teste");
		Lifeline lifeline1 = new Lifeline("Componente1");
		Lifeline lifeline2 = new Lifeline("Componente2");
		Lifeline lifeline3 = new Lifeline("Componente3");
		sd.addLifeline(lifeline1);
		sd.addLifeline(lifeline2);
		sd.addLifeline(lifeline3);
		Action action1 = new Action("msg1");
		Action action2 = new Action("msg2");
		sd.addMessageBetween(lifeline1, lifeline2, action1);
		sd.addMessageBetween(lifeline1, lifeline3, action1);
		sd.addMessageBetween(lifeline2, lifeline3, action2);
		sd.addMessageBetween(lifeline3, lifeline1, action2);
		System.out.println(sd.toString());
	}
	
	private static void testKMTS()
	{
		KMTS kmts = new KMTS("teste1");
		State state = new State();
		state.setLabel("S0");
		state.addPreposition("p", true);
		state.addPreposition("q", false);
		state.addPreposition("s", null);
		kmts.addInitialState(state);
		State state2 = new State();
		state2.setLabel("S1");
		state2.addPreposition("p", false);
		state2.addPreposition("r", null);
		kmts.addState(state2);
		kmts.addMustTransitionBetween(state, state2, "a");
		kmts.addMayTransitionBetween(state2, state, "b");
		System.out.println(kmts.toString());
	}
	
	private static void testSynthesis()
	{
		List<String> fileInputs = new ArrayList<String>();
		fileInputs.add("");
		Synthesizer synthesizer = new Synthesizer();
		Set<KMTS> generatedKMTSs = synthesizer.createKMTSFromSequenceDiagramsWithOCLConstraints(fileInputs);
		generatedKMTSs.toString();
	}
	
        //Testa o metodo 'ToString' da class 'State'
        private static void testStateToString()
        {
                State s = new State();
                s.setLabel("Q1");
                Transition t = new Transition();
                t.setAction("A");
                t.setMustTransition(true);
                s.addInTrasition(t);
                Transition t1 = new Transition();
                t1.setAction("B");
                s.addOutTrasition(t1);
                s.addPreposition("X", Boolean.TRUE);
                s.addPreposition("Y", Boolean.FALSE);
                s.addPreposition("Z", Boolean.TRUE);
                s.addPreposition("W", Boolean.FALSE);
                State s1 = new State();
                s1.addPreposition("X", Boolean.FALSE);
                s1.addPreposition("Y", Boolean.TRUE);
                s1.addPreposition("Z", Boolean.FALSE);
                s1.addPreposition("W", Boolean.TRUE);
                s1.setLabel("Q2");
                s1.addInTrasition(t1);
                s1.addOutTrasition(t);
                System.out.println(s.toString());
                System.out.println(s1.toString());
                System.out.println(t.toString());
                System.out.println(t1.toString());
        }
}
