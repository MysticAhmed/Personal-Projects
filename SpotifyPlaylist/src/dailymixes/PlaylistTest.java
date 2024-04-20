// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

import student.TestCase;
import static org.junit.Assert.assertArrayEquals;

// -------------------------------------------------------------------------
/**
 * Tests all methods for edge cases and correct implementation
 * 
 * @author ahmedabdelhady
 * @version Apr 8, 2024
 */
public class PlaylistTest
    extends TestCase
{
    private Playlist playlist;

    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Sets up testing environment
     */
    public void setUp()
    {
        playlist = new Playlist("playlist", 10, 10, 10, 80, 80, 80, 3);
    }
    // ~Public Methods ........................................................


    /**
     * Tests getMinGenreSet method
     */
    public void testGetMinGenreSet()
    {
        GenreSet minGenreSet = new GenreSet(10, 10, 10);
        assertEquals(minGenreSet, playlist.getMinGenreSet());
    }


    /**
     * Tests getMaxGenreSet method
     */
    public void testGetMaxGenreSet()
    {
        GenreSet maxGenreSet = new GenreSet(80, 80, 80);
        assertEquals(maxGenreSet, playlist.getMaxGenreSet());
    }


    /**
     * Tests setName method
     */
    public void testSetName()
    {
        playlist.setName("p1");
        assertEquals(playlist.getName(), "p1");
    }


    /**
     * Tests getName method
     */
    public void testgetName()
    {
        assertEquals("playlist", playlist.getName());
    }


    /**
     * Tests getSpacesLeft method
     */
    public void testGetSpacesLeft()
    {
        // no songs
        assertEquals(playlist.getSpacesLeft(), 3);
        // songs present
        Song song = new Song("My Eyes", 30, 30, 30, "p1");
        playlist.addSong(song);
        assertEquals(2, playlist.getSpacesLeft());

    }


    /**
     * Tests getSongs method
     */
    public void testGetSongs()
    {
        // empty
        Song[] songs = new Song[3];

        assertArrayEquals(playlist.getSongs(), songs);

        // add song
        Song song = new Song("My Eyes", 30, 30, 30, "p1");
        playlist.addSong(song);
        songs[0] = song;
        assertArrayEquals(playlist.getSongs(), songs);

    }


    /**
     * Tests getCapacity method
     */
    public void testGetCapacity()
    {
        assertEquals(playlist.getCapacity(), 3);
    }


    /**
     * Tests getNumberOfSongs method
     */
    public void testGetNumberOfSongs()
    {
        // empty
        assertEquals(playlist.getNumberOfSongs(), 0);
        Song song = new Song("My Eyes", 30, 30, 30, "p1");
        Song song2 = new Song("Astroworld", 30, 30, 30, "p1");
        Song song3 = new Song("Stargazing", 30, 30, 30, "p1");
        playlist.addSong(song);
        playlist.addSong(song2);
        playlist.addSong(song3);
        assertEquals(playlist.getNumberOfSongs(), 3);
    }


    /**
     * Tests compareTo method
     */
    public void testCompareTo()
    {
        // smaller capacity
        Playlist playlist2 =
            new Playlist("playlist2", 50, 20, 10, 80, 50, 20, 2);
        assertEquals(1, playlist.compareTo(playlist2));

        // larger capacity
        playlist2 = new Playlist("playlist2", 50, 20, 10, 80, 50, 20, 4);
        assertEquals(-1, playlist.compareTo(playlist2));

        // same capacity, bigger number of spaces left
        Song song = new Song("My Eyes", 20, 20, 20, "p1");
        Song song2 = new Song("Astroworld", 20, 20, 20, "p1");
        playlist.addSong(song);
        playlist.addSong(song2);
        playlist2 = new Playlist("playlist2", 50, 20, 10, 80, 50, 20, 3);
        assertEquals(-1, playlist.compareTo(playlist2));

        // same capacity, smaller number of spaces left
        song = new Song("My Eyes", 15, 15, 15, "p1");
        song2 = new Song("Astroworld", 15, 15, 15, "p1");
        Song song3 = new Song("Stargazing", 15, 15, 15, "p1");
        playlist2 = new Playlist("playlist2", 11, 11, 11, 80, 50, 20, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(1, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, bigger minGenreSet
        playlist.addSong(song3);
        playlist2 = new Playlist("playlist2", 60, 40, 50, 80, 50, 20, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(-1, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, smaller minGenreSet
        playlist2 = new Playlist("playlist2", 0, 0, 0, 80, 50, 20, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(1, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, same minGenreSets, bigger
        // maxGenreSet
        playlist2 = new Playlist("playlist2", 10, 10, 10, 90, 90, 90, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(-1, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, same minGenreSets, smaller
        // maxGenreSet
        playlist2 = new Playlist("playlist2", 10, 10, 10, 30, 30, 30, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(1, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, same maxGenreSet and
        // minGenreSets, lower name order
        playlist2 = new Playlist("a", 10, 10, 10, 80, 80, 80, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(15, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, same maxGenreSet and
        // minGenreSets, higher name order
        playlist2 = new Playlist("z", 10, 10, 10, 80, 80, 80, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(-10, playlist.compareTo(playlist2));

        // same capacity, same number of spaces left, same maxGenreSet and
        // minGenreSets, equal name order
        playlist2 = new Playlist("playlist", 10, 10, 10, 80, 80, 80, 3);
        playlist2.addSong(song);
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        assertEquals(0, playlist.compareTo(playlist2));
    }


    /**
     * Tests addSong method
     */
    public void testAddSong()
    {
        // not full and qualified
        Song song = new Song("My Eyes", 60, 30, 20, "p1");
        Song song2 = new Song("Astroworld", 60, 30, 20, "p1");
        assertTrue(playlist.addSong(song));
        assertTrue(playlist.addSong(song2));
        assertEquals(2, playlist.getNumberOfSongs());

        // not qualified
        Song song3 = new Song("Stargazing", 90, 90, 90, "p1");
        assertFalse(playlist.addSong(song3));
        assertEquals(2, playlist.getNumberOfSongs());

        // full
        song3 = new Song("Stargazing", 60, 30, 20, "p1");
        playlist.addSong(song3);
        Song song4 = new Song("Astroworld", 60, 30, 20, "p1");
        assertFalse(playlist.addSong(song4));
    }


    /**
     * Tests toString
     */
    public void testToString()
    {
        Song song = new Song("My Eyes", 30, 30, 30, "p1");
        playlist.addSong(song);

        String expected =
            "Playlist: playlist, # of songs: 1 (cap: 3), Requires: "
                + "Pop:10%-80%, " + "" + "Rock:10%-80%, Country:10%-80%";
        assertEquals(expected, playlist.toString());
    }


    /**
     * Tests isQualified method
     */

    public void testIsQualified()
    {
        Song song = new Song("My Eyes", 30, 30, 30, "p1");
        assertTrue(playlist.isQualified(song));

        // not qualified
        song = new Song("My Eyes", 90, 30, 20, "p1");
        assertFalse(playlist.isQualified(song));

        song = new Song("My Eyes", 10, 0, 0, "p1");
        assertFalse(playlist.isQualified(song));
    }


    /**
     * Test equals method
     */
    public void testEquals()
    {
        // wrong type
        String list = "";
        assertFalse(playlist.equals(list));

        // same playlists
        Playlist playlist1 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        Playlist playlist2 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        assertTrue(playlist1.equals(playlist2));

        // different min genre set playlists
        playlist1 = new Playlist("Test", 0, 10, 10, 50, 50, 50, 5);
        playlist2 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        assertFalse(playlist1.equals(playlist2));

        // different max genre set playlists
        playlist1 = new Playlist("Test", 10, 10, 10, 0, 50, 50, 5);
        playlist2 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        assertFalse(playlist1.equals(playlist2));

        // different songs and different number
        playlist1 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        playlist2 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        playlist2.addSong(new Song("Song", 40, 40, 40, "Test"));
        playlist1.addSong(new Song("Song2", 40, 40, 40, "Test"));
        assertFalse(playlist1.equals(playlist2));

        // different songs and different number
        playlist1 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        playlist2 = new Playlist("Test", 10, 10, 10, 50, 50, 50, 5);
        playlist2.addSong(new Song("Song", 40, 40, 40, "Test"));
        assertFalse(playlist1.equals(playlist2));
    }

}
