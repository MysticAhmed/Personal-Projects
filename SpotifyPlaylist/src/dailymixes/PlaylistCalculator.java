// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

import java.util.Arrays;
import list.AList;

// -------------------------------------------------------------------------
/**
 * Does the calculations to determine the right playlists for songs.
 * 
 * @author ahmedabdelhady
 * @version Apr 5, 2024
 */
public class PlaylistCalculator
{
    // ~ Fields ................................................................

    private Playlist[] playlists;
    /**
     * number of playlists in our array
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * Used in reader
     */
    public static final int MIN_PERCENT = 0;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;
    /**
     * Used in reader
     */
    public static final int MAX_PERCENT = 100;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Create a new PlaylistCalculator object.
     * 
     * @param queue
     *            takes the song queue
     * @param playlist
     *            takes the list that will hold all our playlists
     */
    PlaylistCalculator(ArrayQueue<Song> queue, Playlist[] playlist)
    {
        if (queue == null)
        {
            throw new IllegalArgumentException();
        }

        songQueue = queue;
        rejectedTracks = new AList<Song>();
        playlists = playlist;
    }
    // ~Public Methods ........................................................


    /**
     * Next song in line should be put on the list of rejected tracks.
     */
    public void reject()
    {
        rejectedTracks.add(songQueue.dequeue());
    }


    private Playlist getPlaylistWithMaximumCapacity(Song song)
    {
        Playlist[] shallowCopy = Arrays.copyOf(playlists, playlists.length);
        Arrays.sort(shallowCopy);
        for (int i = shallowCopy.length - 1; i >= 0; i--)
        {
            if (!shallowCopy[i].isFull() && shallowCopy[i].isQualified(song))
            {
                return shallowCopy[i];
            }
        }
        return null;
    }


    /**
     * Attempt to add the next song in queue
     * 
     * @return boolean indicating if attempt was successful
     */
    public boolean addSongToPlaylist()
    {
        if (!songQueue.isEmpty())
        {
            if (this.getPlaylistForSong(songQueue.getFront()) != null)
            {
                this.getPlaylistForSong(songQueue.getFront())
                    .addSong(songQueue.dequeue());
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * Determine the playlist the next song can be added to
     * 
     * @param song
     *            takes song we want to add
     * @return the playlist suited for the song
     */
    public Playlist getPlaylistForSong(Song song)
    {
        Playlist maxCapPlaylist = null;
        if (song == null)
        {
            return null;
        }
        String suggestedPlaylist = song.getPlaylistName();
        if (!suggestedPlaylist.equals(""))
        {
            for (Playlist playlist : playlists)
            {
                if (suggestedPlaylist.equals(playlist.getName())
                    && !playlist.isFull() && playlist.isQualified(song))
                {
                    return playlist;
                }

            }

            maxCapPlaylist = getPlaylistWithMaximumCapacity(song);
        }
        else
        {
            maxCapPlaylist = getPlaylistWithMaximumCapacity(song);
        }
        return maxCapPlaylist;

    }


    /**
     * Getter method for song queue
     * 
     * @return the song queue
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    /**
     * Return the int representation for the given playlist name.
     * 
     * @param string
     *            takes the playlist we want to find
     * @return returns -1 if playlist name is not found in all playlists
     */
    public int getPlaylistIndex(String string)
    {
        for (int i = 0; i < playlists.length; i++)
        {
            if (string.equals(playlists[i].getName()))
            {
                return i;
            }
        }
        return -1;
    }


    /**
     * Getter method for playlists array
     * 
     * @return playlist array of playlists
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }

}
