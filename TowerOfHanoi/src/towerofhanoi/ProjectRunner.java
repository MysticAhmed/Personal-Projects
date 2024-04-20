package towerofhanoi;
// -------------------------------------------------------------------------

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Ahmed Abdelhady (aabdelhady05)]

/**
 * Instantiates solver and window to run project
 * 
 * @author Ahmed Abdelhady (aabdelhady05)
 * @version 2024-03-17
 */

public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Runs the project
     */
    public static void main(String[] args)
    {
        int disks = 6;
        if (args.length == 1)
        {
            disks = Integer.parseInt(args[0]);

        }
        HanoiSolver solver = new HanoiSolver(disks);
        PuzzleWindow window = new PuzzleWindow(solver);
    }
}
