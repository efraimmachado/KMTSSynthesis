import java.util.ArrayList;
import java.util.List;

import kmts.KMTS;
import synthesizer.Synthesizer;


public class MainApp {

	public static void main()
	{
		test1();
	}
	
	
	private static void test1()
	{
		List<String> fileInputs = new ArrayList<String>();
		fileInputs.add("");
		Synthesizer synthesizer = new Synthesizer();
		KMTS generatedKMTS = synthesizer.createKMTSFromSequenceDiagramsWithOCLConstraints(fileInputs);
		generatedKMTS.toString();
	}
	
}
