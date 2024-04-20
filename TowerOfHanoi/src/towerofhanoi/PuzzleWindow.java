package towerofhanoi;

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Ahmed Abdelhady (aabdelhady05)

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The main front-end work and the view for the Tower of Hanoi puzzle
 *
 * @author Name
 * @version Date
 */
public class PuzzleWindow
    implements Observer
{

    private HanoiSolver game;
    private Shape left;
    private Shape middle;
    private Shape right;
    private Window window;
    /**
     * A factor in which the width of the disks are multiplied by
     */
    public static final int WIDTH_FACTOR = 15;
    /**
     * The vertical gap between each disk on the tower
     */
    public static final int DISK_GAP = 0;
    /**
     * The height of each disk on the tower
     */
    public static final int DISK_HEIGHT = 15;

    /**
     * Creates a new PuzzleWindow view for a given HanoiSolver game
     *
     * @param g
     *            the game to create a view for
     */
    public PuzzleWindow(HanoiSolver g)
    {
        // TODO: implement the instructions given
        game = g;
        game.addObserver(this);
        window = new Window("Tower of Hanoi");
        window.setSize(1500, 1000);

        // The height and Y location of each pole are the same
        int poleHeight = 400;
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape(
            (200 - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        middle = new Shape(
            ((window.getGraphPanelWidth() / 2) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        right = new Shape(
            ((window.getGraphPanelWidth() - 200) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));

        for (int width =
            (game.disks() + 1) * WIDTH_FACTOR; width > WIDTH_FACTOR; width -=
                WIDTH_FACTOR)
        {
            /*
             * TODO: create a new disk, and add it to the left tower. Make sure
             * to add the disk to the window
             */

            Disk disk = new Disk(5 * width);
            game.getTower(Position.LEFT).push(disk);
            this.moveDisk(Position.LEFT);
            window.addShape(disk);

        }
        window.addShape(left);
        window.addShape(middle);
        window.addShape(right);

        Button solve = new Button("Solve");
        window.addButton(solve, WindowSide.NORTH);
        solve.onClick(this, "clickedSolve");
    }


    /**
     * Updates the front end to reflect movement of disks
     * 
     * @param position
     *            takes the position we want to move the disk to
     */
    private void moveDisk(Position position)
    {
        // TODO: Implement this method yourself. See instructions for tips.
        Disk currentDisk = game.getTower(position).peek();
        Shape currentPole = middle;
        switch (position)
        {
            case LEFT:
                currentPole = left;
                break;
            case MIDDLE:
                currentPole = middle;
                break;
            case RIGHT:
                currentPole = right;
                break;

        }

        int poleX = currentPole.getX();
        int poleY = currentPole.getY();
        int poleWidth = currentPole.getWidth();
        int poleHeight = currentPole.getHeight();
        int diskWidth = currentDisk.getWidth();

        int changeInX = (poleWidth - diskWidth) / 2;
        int diskHeights = game.getTower(position).size() * DISK_HEIGHT;

        int changeInY = poleHeight - diskHeights;

        currentDisk.setX(poleX + changeInX);
        currentDisk.setY(poleY + changeInY);

    }


    /**
     * Updates the view whenever a disk is moved in the back-end
     *
     * @param o
     *            The observable that triggered the update
     * @param arg
     *            arguments sent by the game; should be a position
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if (arg.getClass() == Position.class)
        {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }


    /**
     * Runs when the Solve button is clicked, tells the puzzle to start solving
     *
     * @param button
     *            the button that was clicked
     */
    public void clickedSolve(Button button)
    {
        button.disable();
        new Thread() {
            public void run()
            {
                game.solve();
            }
        }.start();
    }


    /**
     * Allows for time to see disks move
     */
    private void sleep()
    {
        try
        {
            Thread.sleep(500);
        }
        catch (Exception e)
        {
        }
    }
}
