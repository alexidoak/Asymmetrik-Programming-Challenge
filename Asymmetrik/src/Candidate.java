/**
 * Asymmetrik Programming Challenges - Mobile Device Keyboard
 * 01/28/2017
 * @author Alexandra Doak
 */
public class Candidate 
{
	/**
	 * String representation of the candidate.
	 */
	final String word;
	
	/**
	 * Integer representation of how likely the user is to use this candidate.
	 */
	int confidence;
	
	/**
	 * Constructs the Candidate given a word and sets the initial confidence to 1.
	 * 
	 * @param word - candidate word
	 */
	public Candidate(String word)
	{
		this.word = word.toLowerCase();
		this.confidence = 1;
	}
	
	/**
	 * Returns the candidate word
	 * 
	 * @return the candidate word
	 */
	public String getWord()
	{
		return this.word;
	}
	
	/**
	 * Returns the confidence of this candidate
	 * 
	 * @return the confidence
	 */
	public int getConfidence()
	{
		return this.confidence;
	}
}