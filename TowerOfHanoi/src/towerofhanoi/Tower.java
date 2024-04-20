package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author ahmedabdelhady
 * @version Mar 12, 2024
 */
public class Tower
    extends LinkedStack<Disk>
{
    private Position position;

    /**
     * Creates new tower object
     * 
     * @param newPosition
     *            takes the position we want to create a tower at
     */
    public Tower(Position newPosition)
    {
        super();
        position = newPosition;

    }


    /**
     * returns the position of the tower
     * 
     * @return position of tower
     */
    public Position position()
    {
        return position;

    }


    /**
     * Adds disk onto tower
     * 
     * @param disk
     *            is the disk we want to add
     */
    @Override
    public void push(Disk disk)
    {
        if (disk == null)
        {
            throw new IllegalArgumentException();

        }
        if (isEmpty() || this.peek().compareTo(disk) == 1)
        {
            super.push(disk);
        }
        else
        {
            throw new IllegalStateException();
        }

    }

}
