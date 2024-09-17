package towerofhanoi;

import java.util.Observable;

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Ahmed Abdelhady (aabdelhady05)

// -------------------------------------------------------------------------
/**
 * Moves the disks around to get the desired result. The class is used by the
 * puzzle window and hides its implementation from it
 * 
 * @author Ahmed Abdelhady (aabdelhady05)
 * @version 2024-03-17
 */
public class HanoiSolver
    extends Observable
{
    private Tower left;
    private Tower middle;
    private Tower right;
    private int numDisks;

    // ----------------------------------------------------------
    /**
     * Create a new HanoiSolver object.
     * 
     * @param numDisks
     *            takes in the number of disks in the towers
     */
    public HanoiSolver(int numDisks)
    {
        this.numDisks = numDisks;
        left = new Tower(Position.LEFT);
        right = new Tower(Position.RIGHT);
        middle = new Tower(Position.MIDDLE);
    }


    // ----------------------------------------------------------
    /**
     * returns disk number
     * 
     * @return number of disks
     */
    public int disks()
    {
        return numDisks;
    }


    // ----------------------------------------------------------
    /**
     * Returns the tower at specified position
     * 
     * @param pos
     *            takes the position for which we want to get the tower for
     * @return tower linked to position
     */
    public Tower getTower(Position pos)
    {
        switch (pos)
        {
            case LEFT:
                return left;

            case RIGHT:
                return right;

            case MIDDLE:
                return middle;

            default:
                return middle;
        }

    }


    /**
     * Creates String representation of disks on all towers
     */
    @Override
    public String toString()
    {
        return left.toString() + middle.toString() + right.toString();

    }


    private void move(Tower source, Tower destination)
    {
        Disk moveDisk = source.pop();
        destination.push(moveDisk);
        setChanged();
        notifyObservers(destination.position());

    }


    private void solveTowers(
        int currentDisks,
        Tower startPole,
        Tower tempPole,
        Tower endPole)
    {
        if (currentDisks == 1)
        {
            move(startPole, endPole);
        }
        else
        {
            solveTowers(currentDisks - 1, startPole, endPole, tempPole);
            move(startPole, endPole);
            solveTowers(currentDisks - 1, tempPole, startPole, endPole);
        }
    }


    // ----------------------------------------------------------
    /**
     * Starts first recursion call for solveTowers
     */
    public void solve()
    {
        solveTowers(numDisks, left, middle, right);
    }

}
