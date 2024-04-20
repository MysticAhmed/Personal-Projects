// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

// -------------------------------------------------------------------------
/**
 * A Genre Set is the composition of music within a playlist and is what song
 * addition is based on. Songs need to be within a certain genre range.
 * 
 * @author ahmedabdelhady
 * @version Mar 30, 2024
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    // ~ Fields ................................................................

    private int rock;
    private int pop;
    private int country;

    // ----------------------------------------------------------

    // ~ Constructors ..........................................................

    /**
     * Create a new GenreSet object.
     * 
     * @param pop
     *            shows percentage of pop
     * @param rock
     *            shows percentage of rock
     * @param country
     *            shows percentage of country
     */
    public GenreSet(int pop, int rock, int country)
    {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }


    // ----------------------------------------------------------
    /**
     * Returns rock field value
     * 
     * @return rock value
     */
    public int getRock()
    {
        return rock;
    }


    /**
     * Returns pop field value
     * 
     * @return pop value
     */
    public int getPop()
    {
        return pop;
    }


    /**
     * Returns country field value
     * 
     * @return country value
     */
    public int getCountry()
    {
        return country;
    }


    /**
     * A GenreSet object is within range of the minimum genre set and the
     * maximum genre set if all attributes in the genre set are greater than or
     * equal to the attributes in the minimum genre set and less than or equal
     * to the attributes in the maximum genre set.
     * 
     * @return boolean indicating if within range
     * @param minGenreSet
     *            takes genre set we want to be larger than
     * @param maxGenreSet
     *            takes genre set we want to be smaller than
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return this.isLessThanOrEqualTo(maxGenreSet)
            && (this.pop >= minGenreSet.pop && this.rock >= minGenreSet.rock
                && this.country >= minGenreSet.country);
    }


    private boolean isLessThanOrEqualTo(GenreSet other)
    {
        return this.pop <= other.pop && this.rock <= other.rock
            && this.country <= other.country;

    }


    /**
     * Two GenreSet objects are equal if all three fields, pop, rock, and
     * country, are equal.
     * 
     * @param obj
     *            takes in the object we want to compare
     * @return boolean to indicate if equal or not
     */
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == this.getClass())
        {
            GenreSet other = (GenreSet)obj;

            if (other.pop == this.pop && other.rock == this.rock
                && other.country == this.country)
            {
                return true;
            }
        }
        return false;

    }


    /**
     * a negative integer, positive integer, or zero are returned based on
     * whether the sum of the genre percent composition of the current object
     * are less than, greater than, or equal to the sum of the genre percent
     * composition of the parameter object.
     * 
     * @param other
     *            object we want to compare
     * @return integer value
     */
    public int compareTo(GenreSet other)
    {
        int otherSum = other.pop + other.rock + other.country;
        int sum = this.pop + this.rock + this.country;

        if (otherSum > sum)
        {
            return -1;
        }
        else if (otherSum < sum)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    /**
     * String reprentation of genre set
     * 
     * @return string representation
     */
    @Override
    public String toString()
    {
        return "Pop:" + this.pop + " Rock:" + this.rock + " Country:"
            + this.country;
    }

}
