import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Asymmetrik Programming Challenges - Mobile Device Keyboard
 * 01/28/2017
 * @author Alexandra Doak
 */
public class AutocompleteProvider 
{
	/**
	 * Used to sort the list of candidates according to confidence value.
	 */
	private static CandidateComparator comp = new CandidateComparator();
	
	/**
	 * Data structure used to hold word and confidence information.
	 */
	private static ConcurrentHashMap<String, ConcurrentHashMap<String, Candidate>> map = new ConcurrentHashMap<String, ConcurrentHashMap<String, Candidate>>();	
	
	/**
	 * Using a given word fragment, returns a list of autocomplete candidates.
	 * 
	 * @param fragment - used to determine the list of autocomplete candidates
	 * @return a list of candidates ordered by confidence
	 */
	public static List<Candidate> getWords(String fragment)
	{
		// process the fragment to ignore capitalization and remove any punctuation
		fragment = fragment.toLowerCase().trim().replaceAll("[^a-z ]", "");

		// if the map of words contains the fragment sort and return all candidates associated with that fragment
		if (map.containsKey(fragment))
		{
			LinkedList<Candidate> canidates = new LinkedList<Candidate>(map.get(fragment).values());

			Collections.sort(canidates, comp);

			return canidates;
		}
		else // return an empty list
		{
			return new LinkedList<Candidate>();
		}
	}

	/**
	 * Using a given passage, trains the autocomplete algorithm.
	 * 
	 * @param passage - used to train the algorithm
	 */
	public static void train(String passage)
	{
		// process the fragment to ignore capitalization and remove any punctuation
		passage = passage.toLowerCase().trim().replaceAll("[^a-z ]", "");
		
		// separate each word in the passage
		String[] words = passage.split(" ");

		// iterate through each word in the passage and all possible substrings for each word
		for (String word : words)
		{	
			for (int i = 1; i <= word.length(); i++)
			{
				String substring = word.substring(0, i);

				// if substring is already in the map
				if (map.containsKey(substring))
				{
					// retrieve its list of candidates
					ConcurrentHashMap<String, Candidate> canidateList = map.get(substring);
					
					// if the candidate list contains the given word in the passage
					if (canidateList.containsKey(word))
					{
						// get the word from the candidate list and update its confidence, and 
						// put it back into the candidate list
						Candidate canidate = canidateList.get(word);

						int confidence = canidate.getConfidence();
						canidate.confidence = confidence + 1;

						canidateList.put(canidate.getWord(), canidate);
					}
					else // if the list does not contain the given word
					{
						// create a new candidate for the given word, and
						//add it to the candidate list
						Candidate canidate = new Candidate(word);
						canidateList.put(canidate.getWord(), canidate);
					}
					// put the candidate list back in the map
					map.put(substring, canidateList);
				}
				else // if the substring is not in the map
				{
					// create a new candidate list, create a candidate for the given 
					// word, and add the put the candidate list back in the map
					ConcurrentHashMap<String, Candidate> canidateList = new ConcurrentHashMap<String, Candidate>();
					Candidate canidate = new Candidate(word);
					canidateList.put(canidate.getWord(), canidate);
					map.put(substring, canidateList);
				}
			}
		}	
	}

	/**
	 * Created for testing purposes. Displays the list of candidates and their respective confidences according to the
	 * problem specification.
	 * 
	 * @param candidates - List of candidates that is to be displayed
	 * @return a formatted String representation of the given list
	 */
	public static String display(List<Candidate> candidates)
	{
		// if there are possible candidates
		if (!candidates.isEmpty())
		{
			// create a StringBuilder and concatenate each candidate and its respective confidence
			StringBuilder sb = new StringBuilder();
			for (Candidate candidate : candidates)
			{
				sb.append("\"" + candidate.getWord() + "\"" + " (" + candidate.getConfidence() + "), ");
			}
			// remove ", " from the end of the string
			sb.delete(sb.length() - 2, sb.length());
			
			// convert the StringBuilder to a String and return
			return sb.toString();
		}
		// if there are no candidates, return an empty string
		return "";
	}
	
	/**
	 * A simple interface to train and use the algorithm.   
	 */
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Input \"quit\" to exit the program.\n");
		String input = "";
		
		// while the user does not want to quit the program
		while (!input.equalsIgnoreCase("quit"))
		{
			// ask the user whether he/she would like to train the algorithm or request autocomplete candidates
			System.out.print("Are you training? Please input \"yes\" or \"no\". ");
			input = in.nextLine();
			
			// if the user would like to train the algorithm
			if (input.equalsIgnoreCase("yes"))
			{
				// prompt the user to enter in the training passage
				System.out.print("Train: ");
				String passage = in.nextLine();
				
				// use the passage to train the algorithm
				train(passage);
			}
			else if (input.equalsIgnoreCase("no")) // if the user wants to request autocomplete candidates
			{
				// wait for the user to input a fragment
				String fragment = in.nextLine();
				
				// get and display the possible candidates for the given fragment
				List<Candidate> candidates = getWords(fragment);
				System.out.print("Input: \"" + fragment + "\""+ "--> ");
				System.out.println(display(candidates));
			}
		}
		// when the user chooses to quit the program, close the scanner
		in.close();
	}
}