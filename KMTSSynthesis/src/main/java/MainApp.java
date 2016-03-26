import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kmts.KMTS;
import kmts.element.State;
import synthesizer.Synthesizer;
import uml.sequencediagram.Action;
import uml.sequencediagram.Lifeline;
import uml.sequencediagram.SequenceDiagram;


public class MainApp {

	public static void main(String args[])
	{
		testKMTS();
		testSequenceDiagram();
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
	
}
