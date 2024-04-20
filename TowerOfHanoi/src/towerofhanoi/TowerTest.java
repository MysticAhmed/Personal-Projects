package towerofhanoi;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests all tower class edge cases for correct implementation
 * 
 * @author ahmedabdelhady
 * @version Mar 16, 2024
 */
public class TowerTest
    extends TestCase
{
    private Tower tower;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Sets up Testing Environment
     */

    public void setUp()
    {
        tower = new Tower(Position.LEFT);
    }


    /**
     * Tests correct position return
     */
    public void testPosition()
    {
        assertEquals(Position.LEFT, tower.position());
    }


    /**
     * Tests disks are correctly pushed
     */

    public void testPush()
    {
        // Case 1: null
        Exception exception = null;
        try
        {
            tower.push(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Case 2: successful
        Disk disk = new Disk(4);
        Disk otherDisk = new Disk(3);
        tower.push(disk);
        tower.push(otherDisk);

        assertEquals(2, tower.size());

        // Case 3: Disk addition doesn't follow rules
        exception = null;
        try
        {
            tower.push(disk);
        }
        catch (IllegalStateException e)
        {
            exception = e;
        }

        assertNotNull(exception);
    }

}
