import java.util.ArrayList;
import java.util.List;

import kmts.KMTS;
import kmts.element.State;
import synthesizer.Synthesizer;


public class MainApp {

	public static void main()
	{
		test1();
		test11();
	}
	
	private static void test1()
	{
		KMTS kmts = new KMTS();
		State state = new State();
		state.setLabel("S01");
		state.addPreposition("p", true);
		state.addPreposition("q", false);
		state.addPreposition("s", null);
		kmts.addInitialState(state);
		State state2 = new State();
		state2.setLabel("S02");
		state2.addPreposition("p", false);
		state2.addPreposition("r", null);
		kmts.addState(state2);
		kmts.addMustTransitionBetween(state, state2, "a");
		kmts.addMayTransitionBetween(state2, state, "b");
		kmts.toString();
	}
	
	private static void test11()
	{
		List<String> fileInputs = new ArrayList<String>();
		fileInputs.add("");
		Synthesizer synthesizer = new Synthesizer();
		KMTS generatedKMTS = synthesizer.createKMTSFromSequenceDiagramsWithOCLConstraints(fileInputs);
		generatedKMTS.toString();
	}
	
}