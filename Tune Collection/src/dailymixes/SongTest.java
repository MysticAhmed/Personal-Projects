
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests all methods for edge cases and correct implementation
 * 
 * @author ahmedabdelhady
 * @version Mar 30, 2024
 */
public class SongTest
    extends TestCase
{
    private Song song;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Sets up testing environment
     */
    public void setUp()
    {
        song = new Song("My Eyes", 80, 0, 0, "p1");
    }
    // ~Public Methods ........................................................


    /**
     * Tests getName method
     */
    public void testGetName()
    {
        assertEquals("My Eyes", song.getName());
    }


    /**
     * Tests getPlaylistName method
     */
    public void testGetPlaylistName()
    {
        assertEquals("p1", song.getPlaylistName());

        Song song2 = new Song("My Eyes", 80, 0, 0, "");
        assertEquals("", song2.getPlaylistName());
    }


    /**
     * Tests toString output
     */
    public void testToString()
    {
        // playlist not present
        Song song2 = new Song("My Eyes", 80, 0, 0, "");
        assertEquals(
            "No-Playlist My Eyes Pop:80 Rock:0 Country:0",
            song2.toString());

        // playlist not present
        assertEquals(
            "My Eyes Pop:80 Rock:0 Country:0 Suggested: p1",
            song.toString());

    }


    /**
     * Tests equals method
     */
    public void testEquals()
    {
        Song song2 = new Song("My Eyes", 80, 0, 0, "p1");
        assertTrue(song.equals(song2));

        song2 = new Song("My Legs", 80, 0, 0, "");

        assertFalse(song.equals(song2));
    }


    /**
     * Tests getGenreSet method
     */

    public void testGetGenreSet()
    {
        GenreSet set = new GenreSet(80, 0, 0);

        assertEquals(song.getGenreSet(), set);
    }

}
