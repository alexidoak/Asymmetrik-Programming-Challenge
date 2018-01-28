import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 * Asymmetrik Programming Challenges - Mobile Device Keyboard
 * 01/28/2017
 * @author Alexandra Doak
 *
 * Used to test my implementation.
 */
public class AutocompleteTest 
{
	private Candidate candidate1;
	private Candidate candidate2;
	private Candidate candidate3;

	private String trainer1;
	private String trainer2;
	private String trainer3;
	private String trainer4;

	@Before
	public void setUp()
	{
		candidate1 = new Candidate("gettysburg");
		candidate2 = new Candidate("College");
		candidate3 = new Candidate("pennsylvania");

		trainer1 = "The third thing that I need to tell you is that this thing does not think thoroughly.";
		trainer2 = "";
		trainer3 = "the the the the the the the the the";
		trainer4 = ".; , the.! ";
	}

	@Test
	public void testGetWord() // test the Candidate method getWord()
	{
		assertEquals(candidate1.getWord(), "gettysburg");
		assertEquals(candidate2.getWord(), "college");
		assertEquals(candidate3.getWord(), "pennsylvania");
	}

	@Test
	public void testGetConfidence() // test the Candidate method getConfidence()
	{
		assertEquals(candidate1.getConfidence(), 1);
	}

	@Test
	public void testAutocompleteAlgorithm() // tests my implementation of the autocomplete algorithm using the given sample test cases 
	{
		// test after training with passage 1
		AutocompleteProvider.train(trainer1);

		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("thi")),
				"\"thing\" (2), \"think\" (1), \"third\" (1), \"this\" (1)"); 
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("nee")),
				"\"need\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("Nee")),
				"\"need\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("th")),
				"\"that\" (2), \"thing\" (2), \"the\" (1), \"think\" (1), \"thoroughly\" (1), \"third\" (1), \"this\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("h")), "");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("")), "");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords(" ")), "");

		// test after training with passage 2
		AutocompleteProvider.train(trainer2);

		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("thi")),
				"\"thing\" (2), \"think\" (1), \"third\" (1), \"this\" (1)"); 
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("nee")),
				"\"need\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("th")),
				"\"that\" (2), \"thing\" (2), \"the\" (1), \"think\" (1), \"thoroughly\" (1), \"third\" (1), \"this\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("h")), "");	
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("")), "");	
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords(" ")), "");

		// test after training with passage 3
		AutocompleteProvider.train(trainer3);

		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("thi")),
				"\"thing\" (2), \"think\" (1), \"third\" (1), \"this\" (1)"); 
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("nee")),
				"\"need\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("th")),
				"\"the\" (10), \"that\" (2), \"thing\" (2), \"think\" (1), \"thoroughly\" (1), \"third\" (1), \"this\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("h")), "");		
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("")), "");		
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords(" ")), "");
		
		// test after training with passage 4
		AutocompleteProvider.train(trainer4);

		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("thi")),
				"\"thing\" (2), \"think\" (1), \"third\" (1), \"this\" (1)"); 
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("nee")),
				"\"need\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("th")),
				"\"the\" (11), \"that\" (2), \"thing\" (2), \"think\" (1), \"thoroughly\" (1), \"third\" (1), \"this\" (1)");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("h")), "");
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords("")), "");	
		assertEquals(AutocompleteProvider.display(AutocompleteProvider.getWords(" ")), "");
	}
}