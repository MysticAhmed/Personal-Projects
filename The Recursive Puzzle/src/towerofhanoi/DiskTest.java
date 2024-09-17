package towerofhanoi;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author ahmedabdelhady
 * @version Mar 14, 2024
 */
public class DiskTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Disk disk;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Sets up testing environment for each test
     */
    public void setUp()
    {
        disk = new Disk(4);
    }


    /**
     * Tests disk comparison
     */
    public void testCompareTo()
    {

        // Case 1: null comparison
        Exception exception = null;
        try
        {
            disk.compareTo(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Case 2: bigger disk
        Disk otherDisk = new Disk(5);
        assertEquals(-1, disk.compareTo(otherDisk));

        // Case 3: smaller disk
        otherDisk = new Disk(3);
        assertEquals(1, disk.compareTo(otherDisk));

        // Case 4: equal width disks
        otherDisk = new Disk(4);
        assertEquals(0, disk.compareTo(otherDisk));

    }


    /**
     * Tests toString output
     */
    public void testToString()
    {
        assertEquals("4", disk.toString());

    }


    /**
     * Tests equals method for all cases
     */
    public void testEquals()
    {

        // Case 1: null object
        assertFalse(disk.equals(null));

        // Case 2: different class
        String falseDisk = "";
        assertFalse(disk.equals(falseDisk));

        // Case 3: different widths
        Disk otherDisk = new Disk(5);
        assertFalse(disk.equals(otherDisk));

        // Case 4: equal widths
        otherDisk = new Disk(4);
        assertTrue(disk.equals(otherDisk));

    }

}
