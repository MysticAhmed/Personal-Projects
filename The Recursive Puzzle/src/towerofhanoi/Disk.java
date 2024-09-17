package towerofhanoi;

import java.awt.Color;
import cs2.Shape;
import student.TestableRandom;

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Ahmed Abdelhady (aabdelhady05)

// -------------------------------------------------------------------------
/**
 * Creates Disks used on each tower. Holds methods that help decide whether disk
 * placement between 2 disks is valid
 * 
 * @author ahmedabdelhady
 * @version Mar 17, 2024
 */
public class Disk
    extends Shape
    implements Comparable<Disk>

{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................
    /**
     * Creates Disk object
     * 
     * @param width
     *            takes width of disk
     */
    public Disk(int width)
    {
        super(0, 0, width, PuzzleWindow.DISK_HEIGHT);
        TestableRandom random = new TestableRandom();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color randomColor = new Color(r, g, b);
        this.setBackgroundColor(randomColor);

    }


    // ~Public Methods ........................................................
    /**
     * If otherDisk is null, be sure to throw an IllegalArgumentException.
     * Otherwise, compare widths and return a negative number if this Disk is
     * smaller than the disk parameter, a positive number for the opposite, and
     * a zero if their widths are equal.
     * 
     * @param otherDisk
     *            takes disk we want to compare
     * @return value indicating if move is bigger smaller or equal
     */
    public int compareTo(Disk otherDisk)
    {
        if (otherDisk == null)
        {
            throw new IllegalArgumentException();
        }

        if (this.getWidth() < otherDisk.getWidth())
        {
            return -1;
        }
        else if (this.getWidth() > otherDisk.getWidth())
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }


    /**
     * returns string representation of disk width
     * 
     * @return string width
     */
    public String toString()
    {
        return "" + this.getWidth();
    }


    /**
     * Checks if the object and disk are equal
     * 
     * @param obj
     *            takes in the object we want to compare 2
     * @return boolean indicating if equal or not
     */
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj.getClass() == this.getClass())
        {
            Disk otherDisk = (Disk)obj;

            if (otherDisk.getWidth() == this.getWidth())
            {
                return true;
            }
        }
        return false;
    }
}
