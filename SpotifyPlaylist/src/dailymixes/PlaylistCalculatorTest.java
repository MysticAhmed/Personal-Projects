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
 * Tests all methods for edge cases and correct implementation.
 * 
 * @author ahmedabdelhady
 * @version Apr 7, 2024
 */
public class PlaylistCalculatorTest
    extends TestCase
{
    // ~ Fields ................................................................

    private PlaylistCalculator calculator;
    private ArrayQueue<Song> queue;
    private Playlist[] playlists;
    private Playlist playlist1;
    private Playlist playlist2;
    private Playlist playlist3;
    private Song song1;
    private Song song2;
    private Song song3;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Sets up testing environment
     */
    public void setUp()
    {
        song1 = new Song("My Eyes", 30, 30, 30, "playlist1");
        song2 = new Song("Stargazing", 30, 30, 30, "playlist2");
        song3 = new Song("Utopia", 30, 30, 30, "");

        queue = new ArrayQueue<Song>();

        playlists = new Playlist[3];

        playlist1 = new Playlist("playlist1", 0, 0, 0, 100, 100, 100, 1);
        playlist2 = new Playlist("playlist2", 0, 0, 0, 100, 100, 100, 1);
        playlist3 = new Playlist("playlist3", 0, 0, 0, 100, 100, 100, 2);

        playlists[0] = playlist1;
        playlists[1] = playlist2;
        playlists[2] = playlist3;
    }


    /**
     * Tests reject method
     */
    public void testReject()
    {

        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);
        calculator = new PlaylistCalculator(queue, playlists);
        calculator.reject();
        assertEquals(2, queue.getSize());
    }


    /**
     * Tests addSongToPlaylist method
     */
    public void testAddSongToPlaylist()
    {
        calculator = new PlaylistCalculator(queue, playlists);
        // empty queue
        assertFalse(calculator.addSongToPlaylist());
        // can add
        queue.enqueue(song1);
        calculator = new PlaylistCalculator(queue, playlists);
        assertTrue(calculator.addSongToPlaylist());
        assertEquals(1, playlists[0].getNumberOfSongs());

        // can't add, not qualified
        song2 = new Song("Stargazing", 110, 110, 110, "playlist2");
        queue.enqueue(song2);
        calculator = new PlaylistCalculator(queue, playlists);
        assertFalse(calculator.addSongToPlaylist());

        // can't add due to full capacity in playlist
        queue.enqueue(song1);
        calculator = new PlaylistCalculator(queue, playlists);
        assertFalse(calculator.addSongToPlaylist());
    }


    /**
     * Tests addSongToPlaylist method but for songs with no playlists
     */
    public void testAddSongToPlaylist2()
    {
        // no playlist, adds to greater capacity
        queue.enqueue(song3);
        calculator = new PlaylistCalculator(queue, playlists);
        assertTrue(calculator.addSongToPlaylist());
        assertEquals(1, playlists[2].getNumberOfSongs());

        // no playlist, all others full
        playlist1.addSong(song1);
        playlist2.addSong(song2);
        playlist3.addSong(song3);
        queue.enqueue(song3);
        calculator = new PlaylistCalculator(queue, playlists);
        assertFalse(calculator.addSongToPlaylist());
    }


    /**
     * Test getPlaylistForSong method
     */
    public void testGetPlaylistForSong()
    {

        // null object
        Exception exception = null;
        try
        {
            calculator.getPlaylistForSong(null);
        }
        catch (NullPointerException e)
        {
            exception = e;
        }
        assertNotNull(exception);

        // valid
        song1 = new Song("My Eyes", 30, 30, 30, "playlist1");
        song2 = new Song("Stargazing", 10, 0, 0, "playlist2");
        song3 = new Song("Utopia", 20, 20, 20, "playlist3");
        calculator = new PlaylistCalculator(queue, playlists);

        assertEquals(playlist1, calculator.getPlaylistForSong(song1));

        // no playlist, all others are full
        playlist1.addSong(song1);
        playlist2.addSong(song2);
        playlist3.addSong(song3);
        playlist3.addSong(song3);
        queue.enqueue(song3);
        calculator = new PlaylistCalculator(queue, playlists);
        assertNull(calculator.getPlaylistForSong(song3));
    }


    /**
     * Test getPlaylistForSong method edge case
     */
    public void testGetPlaylistForSong2()
    {
        // has playlist that doen't exist
        song1 = new Song("My Eyes", 30, 30, 30, "playlist1");
        song2 = new Song("Stargazing", 10, 0, 0, "playlist2");
        song3 = new Song("Utopia", 20, 20, 20, "playlist3");
        playlist1 = new Playlist("playlist1", 0, 0, 0, 100, 100, 100, 1);
        playlist2 = new Playlist("playlist2", 0, 0, 0, 100, 100, 100, 1);
        playlists = new Playlist[2];
        playlists[0] = playlist1;
        playlists[1] = playlist2;
        playlist1.addSong(song1);
        calculator = new PlaylistCalculator(queue, playlists);
        assertEquals(playlist2, calculator.getPlaylistForSong(song3));
    }


    /**
     * Test getQueue method
     */
    public void testGetQueue()
    {
        queue.enqueue(song3);
        calculator = new PlaylistCalculator(queue, playlists);
        assertEquals(queue, calculator.getQueue());
        assertEquals(1, queue.getSize());
    }


    /**
     * Test getPlaylistIndex method
     */
    public void testGetPlaylistIndex()
    {
        calculator = new PlaylistCalculator(queue, playlists);
        assertEquals(0, calculator.getPlaylistIndex("playlist1"));
        assertEquals(1, calculator.getPlaylistIndex("playlist2"));
        assertEquals(2, calculator.getPlaylistIndex("playlist3"));
        assertEquals(-1, calculator.getPlaylistIndex("playlist4"));
    }


    /**
     * Tests getPlaylists method
     */
    public void testGetPlaylists()
    {
        calculator = new PlaylistCalculator(queue, playlists);
        assertEquals(playlists, calculator.getPlaylists());

    }

}
