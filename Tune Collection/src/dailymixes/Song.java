// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * Songs are what are added to playlists and get parsed based on their
 * composition of pop rock and country
 * 
 * @author ahmedabdelhady
 * @version Mar 30, 2024
 */
public class Song
{
    // ~ Fields ................................................................
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    // ----------------------------------------------------------
    /**
     * Create a new Song object.
     * 
     * @param name
     *            takes song name
     * @param pop
     *            takes song percentage
     * @param rock
     *            takes rock percentage
     * @param country
     *            takes country percentage
     * @param suggested
     *            takes suggested playlist placement
     */
    // ~ Constructors ..........................................................

    public Song(String name, int pop, int rock, int country, String suggested)
    {
        genreSet = new GenreSet(pop, rock, country);
        this.name = name;
        this.suggestedPlaylist = suggested;
    }


    /**
     * Getter method for suggestPlaylist field
     * 
     * @return suggestedplaylist name
     */
    // ~Public Methods ........................................................

    public String getPlaylistName()
    {
        return suggestedPlaylist;

    }


    /**
     * Returns the name of the song
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * String representation of song
     * 
     * @return song string
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        if (suggestedPlaylist.length() > 0)
        {
            output.append(
                this.name + " " + genreSet.toString() + " " + "Suggested: "
                    + suggestedPlaylist);
        }
        else
        {
            output
                .append("No-Playlist " + this.name + " " + genreSet.toString());
        }

        return output.toString();

    }


    /**
     * Compares 2 songs to see if they're equal or not. Two Song objects are
     * considered equal when their name, genreset, and suggested playlist values
     * are the same. You will need to write your own equals method.
     * 
     * @param obj
     *            we want to compare
     * @return boolean value
     */
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == this.getClass())
        {
            Song other = (Song)obj;

            if (other.name == this.name && other.genreSet.equals(this.genreSet)
                && other.suggestedPlaylist == this.suggestedPlaylist)
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Getter method for song's genre set
     * 
     * @return genreSet
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }
}
