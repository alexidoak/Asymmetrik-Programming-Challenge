import java.util.Comparator;

/**
 * Asymmetrik Programming Challenges - Mobile Device Keyboard
 * 01/28/2017
 * @author Alexandra Doak
 *
 * Used to sort the candidate list by confidence.
 */
public class CandidateComparator implements Comparator<Candidate>
{
	@Override
	public int compare(Candidate o1, Candidate o2) 
	{
		// if candidate o1 has a greater confidence, return -1
		if (o1.getConfidence() > o2.getConfidence())
		{
			return -1;
		}
		else if (o1.getConfidence() < o2.getConfidence()) // if candidate o2 has a greater confidence, return 1
		{
			return 1;
		}
		else // if they both have the same confidence, return 0
		{
			return 0;
		}
	}
}