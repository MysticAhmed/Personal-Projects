// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Exception thrown if data is incorrect in the input files
 * 
 * @author ahmedabdelhady
 * @version Apr 5, 2024
 */
public class DailyMixDataException
    extends Exception
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * The constructor should call super and pass it the string message.
     * 
     * @param string
     *            takes the message
     */
    public DailyMixDataException(String string)
    {
        super(string);
    }
}
