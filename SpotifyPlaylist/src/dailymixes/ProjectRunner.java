// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)
package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

// -------------------------------------------------------------------------
/**
 * Runs Project by calling playlist reader to read files and call corresponding
 * classes.
 * 
 * @author ahmedabdelhady
 * @version Apr 8, 2024
 */
public class ProjectRunner
{
    // ----------------------------------------------------------
    /**
     * Create a new ProjectRunner object.
     */
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................
    public ProjectRunner()
    {

    }


    // ----------------------------------------------------------
    /**
     * Runs project.
     * 
     * @param args
     * @throws DailyMixDataException
     * @throws ParseException
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException
    {
        if (args.length == 2)
        {
            PlaylistReader reader = new PlaylistReader(args[0], args[1]);
        }
        else
        {
            PlaylistReader reader =
                new PlaylistReader("input.txt", "playlists.txt");
        }
    }
    // ~Public Methods ........................................................

}
