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
 * Tests all methods for edge cases and correct logic.
 * 
 * @author ahmedabdelhady
 * @version Mar 30, 2024
 */
public class GenreSetTest
    extends TestCase
{
    private GenreSet set;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Sets up testing environment
     */
    public void setUp()
    {
        set = new GenreSet(50, 50, 50);
    }


    // ----------------------------------------------------------
    /**
     * Test getter method for rock
     */
    public void testGetRock()
    {
        assertEquals(50, set.getRock());
    }


    /**
     * Test getter method for pop
     */
    public void testGetPop()
    {
        assertEquals(50, set.getPop());
    }


    /**
     * Test getter method for country
     */
    public void testGetCountry()
    {
        assertEquals(50, set.getCountry());
    }


    /**
     * Tests toString method
     */
    public void testToString()
    {
        assertEquals("Pop:50 Rock:50 Country:50", set.toString());
    }


    /**
     * Tests isWithinRange method
     */
    public void testIsWithinRange()
    {
        // true
        GenreSet max = new GenreSet(80, 80, 80);
        GenreSet min = new GenreSet(20, 20, 20);

        assertTrue(set.isWithinRange(min, max));

        // true (equal to it)
        max = new GenreSet(50, 50, 50);
        min = new GenreSet(40, 50, 50);

        assertTrue(set.isWithinRange(min, max));

        // false
        max = new GenreSet(40, 40, 50);
        min = new GenreSet(20, 50, 20);

        assertFalse(set.isWithinRange(min, max));
    }


    /**
     * Tests equals method
     */
    public void testEquals()
    {
        GenreSet other = new GenreSet(50, 50, 50);

        assertTrue(set.equals(other));

        other = new GenreSet(40, 50, 50);

        assertFalse(set.equals(other));
    }


    /**
     * Test compareTo method
     */
    public void testCompareTo()
    {

        // equal
        GenreSet other = new GenreSet(50, 50, 50);

        assertEquals(0, set.compareTo(other));

        // greater
        other = new GenreSet(60, 50, 50);

        assertEquals(-1, set.compareTo(other));

        // smaller
        other = new GenreSet(40, 50, 50);

        assertEquals(1, set.compareTo(other));
    }

}
