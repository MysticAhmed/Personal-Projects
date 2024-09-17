package towerofhanoi;

import student.TestCase;
import java.util.EmptyStackException;

/**
 * // -------------------------------------------------------------------------
 * /** Tests all edge cases of class for correct implementation of methods
 * 
 * @author Ahmed Abdelhady (aabdelhady05)
 * @version 2024-03-17
 */
public class LinkedStackTest
    extends TestCase
{
    // ~ Fields ................................................................
    private LinkedStack<Disk> stack;

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * sets up testing environment for each test case
     */
    public void setUp()
    {
        stack = new LinkedStack<Disk>();
    }


    /**
     * Tests correct string is returned
     */

    public void testToString()
    {
        // Case 1: empty stack
        assertEquals("[]", stack.toString());

        // Case 2: non empty stack
        Disk disk = new Disk(4);
        Disk secondDisk = new Disk(5);
        Disk thirdDisk = new Disk(6);
        stack.push(disk);
        stack.push(secondDisk);
        stack.push(thirdDisk);

        assertEquals("[6, 5, 4]", stack.toString());
    }


    /**
     * tests size updates
     */
    public void testSize()
    {
        // Case 1: empty stack
        assertEquals(0, stack.size());

        // Case 2: one element
        Disk disk = new Disk(4);
        stack.push(disk);
        assertEquals(1, stack.size());
    }


    /**
     * Test stack is successfully cleared
     */
    public void testClear()
    {

        // Case 1: successful clear
        Disk disk = new Disk(4);
        Disk secondDisk = new Disk(4);
        Disk thirdDisk = new Disk(4);
        stack.push(disk);
        stack.push(secondDisk);
        stack.push(thirdDisk);
        stack.clear();

        assertEquals(0, stack.size());
    }


    /**
     * Tests if stack is empty
     */
    public void testIsEmpty()
    {
        // Case 1: empty
        assertTrue(stack.isEmpty());

        // Case 2: not empty
        Disk disk = new Disk(4);
        stack.push(disk);
        assertFalse(stack.isEmpty());
    }


    /**
     * tests correct top node is returned
     */

    public void testPeek()
    {
        // Case 1: empty stack
        Exception exception = null;
        try
        {
            stack.peek();
        }
        catch (EmptyStackException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Case 1: one item
        Disk disk = new Disk(4);
        stack.push(disk);
        assertEquals(disk, stack.peek());

        // Case 2: multiple
        disk = new Disk(4);
        Disk secondDisk = new Disk(4);
        Disk thirdDisk = new Disk(4);
        stack.push(disk);
        stack.push(secondDisk);
        stack.push(thirdDisk);
        assertEquals(disk, stack.peek());

    }


    /**
     * tests if pop removes correct nodes
     */

    public void testPop()
    {
        // Case 1: empty stack
        Exception exception = null;
        try
        {
            stack.pop();
        }
        catch (EmptyStackException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // Case 2: non empty stack

        Disk disk = new Disk(4);
        Disk secondDisk = new Disk(4);
        Disk thirdDisk = new Disk(4);
        stack.push(disk);
        stack.push(secondDisk);
        stack.push(thirdDisk);

        assertEquals(disk, stack.pop());
        assertEquals(2, stack.size());
    }


    /**
     * tests if push adds node
     */
    public void testPush()
    {
        Disk disk = new Disk(4);
        stack.push(disk);
        assertEquals(1, stack.size());
    }

}
