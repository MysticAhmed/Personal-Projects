package towerofhanoi;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests all edge cases of the HanoiSolver for correct implementation
 * 
 * @author Ahmed Abdelhady (aabdelhady05)
 * @version 2024-03-17
 */
public class HanoiSolverTest
    extends TestCase
{
    private HanoiSolver solver;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * sets up testing environment
     */
    public void setUp()
    {
        solver = new HanoiSolver(3);
    }


    /**
     * Tests disks amount
     */
    public void testDisk()
    {
        assertEquals(3, solver.disks());
    }


    /**
     * Tests correct tower is returned
     */

    public void testGetTower()
    {

        assertEquals(solver.getTower(Position.LEFT).position(), Position.LEFT);
        assertEquals(
            solver.getTower(Position.RIGHT).position(),
            Position.RIGHT);
        assertEquals(
            solver.getTower(Position.MIDDLE).position(),
            Position.MIDDLE);
        assertEquals(
            solver.getTower(Position.DEFAULT).position(),
            Position.MIDDLE);
    }


    /**
     * Tests returned string
     */
    public void testToString()
    {
        Disk disk = new Disk(4);
        Tower leftTower = solver.getTower(Position.LEFT);
        leftTower.push(disk);

        Disk secondDisk = new Disk(5);
        Tower middleTower = solver.getTower(Position.MIDDLE);
        middleTower.push(secondDisk);

        Disk thirdDisk = new Disk(6);
        Tower rightTower = solver.getTower(Position.RIGHT);
        rightTower.push(thirdDisk);

        assertEquals("[4][5][6]", solver.toString());

    }


    /**
     * tests if disks moves to correct towers
     */

    public void testSolveTowers()
    {
        Disk disk = new Disk(4);
        Tower leftTower = solver.getTower(Position.LEFT);
        leftTower.push(disk);
        Disk otherDisk = new Disk(3);
        leftTower.push(otherDisk);
        Disk thirdDisk = new Disk(2);
        leftTower.push(thirdDisk);

        solver.solve();
        assertEquals(solver.toString(), "[][][2, 3, 4]");

    }

}
