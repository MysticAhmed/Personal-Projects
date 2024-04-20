// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)
package dailymixes;

// -------------------------------------------------------------------------
/**
 * Creates a playlist class which takes specific minimum and maximum values that
 * fit the genre or vibe of said playlist. All songs wihtin the playlist will
 * have that "vibe" to them.
 * 
 * @author ahmedabdelhady
 * @version Apr 8, 2024
 */
public class Playlist
    implements Comparable<Playlist>
{
    // ~ Fields ................................................................

    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * The Playlist constructor should take 8 parameters, the playlist's name,
     * the minimum percent composition required in all three areas, the maximum
     * percent composition required in all three areas, and the maximum capacity
     * for that playlist.
     * 
     * @param playlistName
     *            is name of playlist
     * @param minPop
     *            is minimum pop percentage in playlist
     * @param minRock
     *            is minimum rock percentage in playlist
     * @param minCountry
     *            is minimum country percentage in playlist
     * @param maxPop
     *            is maximum pop percentage in playlist
     * @param maxRock
     *            is maximum rock percentage in playlist
     * @param maxCountry
     *            is maximum country percentage in playlist
     * @param playlistCap
     *            is the playlist capacity
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        numberOfSongs = 0;
        capacity = playlistCap;
        songs = new Song[capacity];
        name = playlistName;

    }


    /**
     * Getter method for min genre set
     * 
     * @return minGenreSet of playlist
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    /**
     * Getter method for max genre set
     * 
     * @return maxGenreSet of playlist
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    /**
     * Sets a playlist's name
     * 
     * @param name
     *            is new name of playlist
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Getter method for playlist name
     * 
     * @return name of playlist
     */
    public String getName()
    {
        return name;
    }


    /**
     * This method should return the number of available places left in the
     * playlist, by using the playlist’s capacity and current number of songs on
     * it.
     * 
     * @return number of spaces left in playlist
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    /**
     * Getter method for songs array
     * 
     * @return songs in playlist
     */

    public Song[] getSongs()
    {
        return songs;
    }


    /**
     * getter method for playlist capacity
     * 
     * @return capacity of playist
     */
    public int getCapacity()
    {
        return capacity;
    }


    /**
     * Getter method for number of songs in playlist
     * 
     * @return numberOfSongs in playlist
     */
    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    /**
     * The compareTo method should order the playlists based on capacity. A
     * playlist with more capacity will be greater than one with less. In the
     * event that 2 playlists have equal capacities, then they will be ordered
     * based on spaces left. A playlist with more spaces left will be greater
     * than one with less.In the event that the 2 playlists also have an equal
     * number of slots available, then they will be ordered based on
     * MinGenreSet. If 2 playlists have all three of those attributes the same,
     * then they will be ordered based on MaxGenreSet. Playlists that have all
     * of the aforementioned attributes in common will be ordered based on name
     * 
     * @param other
     *            is the playlist we compare to
     * @return positive number indicating this playlist is bigger than
     *             parameter, negative if its smaller than, and 0 if it's equal.
     */
    public int compareTo(Playlist other)
    {
        if (this.capacity < other.capacity)
        {
            return -1;
        }
        else if (this.capacity > other.capacity)
        {
            return 1;
        }
        else
        {
            if (this.getSpacesLeft() < other.getSpacesLeft())
            {
                return -1;
            }
            else if (this.getSpacesLeft() > other.getSpacesLeft())
            {
                return 1;
            }
            else
            {
                if (this.getMinGenreSet()
                    .compareTo(other.getMinGenreSet()) != 0)
                {
                    return this.getMinGenreSet()
                        .compareTo(other.getMinGenreSet());
                }

                else if (this.getMaxGenreSet()
                    .compareTo(other.getMaxGenreSet()) != 0)
                {
                    return this.getMaxGenreSet()
                        .compareTo(other.getMaxGenreSet());
                }
                else
                {
                    return this.name.compareTo(other.name);
                }
            }
        }
    }


    /**
     * adds song to playlist after checking if theres space and that it's within
     * range of min and max genreSets
     * 
     * @return boolean indicating song addition
     * @param song
     *            takes the song we want to add
     */
    public boolean addSong(Song song)
    {
        if (numberOfSongs < songs.length && isQualified(song))
        {
            songs[numberOfSongs] = song;
            numberOfSongs++;
            return true;
        }

        return false;

    }


    /**
     * String representation of playlist status
     * 
     * @return string indicating name of playlist, capacity, songs, and required
     *             genre set
     */
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(
            "Pop:" + minGenreSet.getPop() + "%-" + maxGenreSet.getPop()
                + "%, ");
        builder.append(
            "Rock:" + minGenreSet.getRock() + "%-" + maxGenreSet.getRock()
                + "%, ");
        builder.append(
            "Country:" + minGenreSet.getCountry() + "%-"
                + maxGenreSet.getCountry() + "%");
        return "Playlist: " + name + ", # of songs: " + numberOfSongs
            + " (cap: " + capacity + "), Requires: " + builder.toString();

    }


    /**
     * This method should return true if the playlist’s number of songs has
     * reached max capacity.
     * 
     * @return boolean indicating fullness playlist
     */
    public boolean isFull()
    {
        return numberOfSongs == capacity;
    }


    /**
     * This method checks if 2 playlists are equal
     * 
     * @param obj
     *            takes the object we want to compare to
     * @return boolean indicating equality
     */
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == this.getClass())
        {
            Playlist other = (Playlist)obj;

            if (this.minGenreSet.equals(other.minGenreSet)
                && this.maxGenreSet.equals(other.maxGenreSet)
                && this.name.equals(other.name)
                && this.capacity == other.capacity
                && this.numberOfSongs == other.numberOfSongs)
            {
                int j = 0;
                for (int i = 0; i < this.numberOfSongs; i++)
                {
                    if (!this.songs[i].equals(other.songs[j]))
                    {
                        return false;
                    }
                    j++;
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if a song can be added to playlist based on the min and max
     * genreSets
     * 
     * @param song
     *            we want to check
     * @return boolean indicating if song can be added
     */
    public boolean isQualified(Song song)
    {
        return song.getGenreSet().isWithinRange(minGenreSet, maxGenreSet);
    }

}
