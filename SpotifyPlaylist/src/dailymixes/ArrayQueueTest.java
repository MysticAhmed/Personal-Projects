// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

import queue.EmptyQueueException;
import static org.junit.Assert.assertArrayEquals;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests all methods for edge cases and correct logic.
 * 
 * @author ahmedabdelhady
 * @version April 8, 2024
 */
public class ArrayQueueTest
    extends TestCase
{
    private ArrayQueue<String> queue;
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    /**
     * Sets up testing environment
     */
    public void setUp()
    {
        queue = new ArrayQueue<String>();
    }
    // ~Public Methods ........................................................


    /**
     * Tests size getter
     */
    public void testGetSize()
    {
        // empty
        assertEquals(0, queue.getSize());

        // non empty
        queue.enqueue("Stargazing");
        queue.enqueue("Astroworld");
        queue.enqueue("Utopia");

        assertEquals(3, queue.getSize());

    }


    /**
     * Tests getLengthOfUnderlyingArray
     */
    public void testGetLengthOfUnderlyingArray()
    {
        // Default Queue
        assertEquals(21, queue.getLengthOfUnderlyingArray());

        // Custom capacity
        ArrayQueue<String> queue2 = new ArrayQueue<String>(10);

        assertEquals(11, queue2.getLengthOfUnderlyingArray());
    }


    /**
     * Tests clearing of queue
     */
    public void testClear()
    {
        queue.enqueue("Stargazing");
        queue.enqueue("Astroworld");
        queue.enqueue("Utopia");

        queue.clear();
        assertEquals(0, queue.getSize());
    }


    /**
     * Tests enqueue method
     */
    public void testEnqueue()
    {
        for (int i = 0; i < 21; i++)
        {
            queue.enqueue("song" + i);
        }

        assertEquals(41, queue.getLengthOfUnderlyingArray());

    }


    /**
     * Tests dequeue method
     */
    public void testDequeue()
    {
        Exception exception = null;
        try
        {
            queue.getFront();
        }
        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        queue.enqueue("travis");
        queue.enqueue("the weekend");
        assertEquals(queue.dequeue(), "travis");
        assertEquals(queue.getSize(), 1);

    }


    /**
     * Tests getFront method
     */

    public void testGetFront()
    {
        // empty
        Exception exception = null;
        try
        {
            queue.getFront();
        }
        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        // not empty
        queue.enqueue("travis");
        queue.enqueue("the weekend");
        assertEquals("travis", queue.getFront());

    }


    /**
     * Test is Empty method
     */
    public void testIsEmpty()
    {
        assertTrue(queue.isEmpty());

        queue.enqueue("Travis Scott");
        assertFalse(queue.isEmpty());
    }


    /**
     * Tests toArray method output
     */
    public void testToArray()
    {
        ArrayQueue<String> testQueue = new ArrayQueue<String>(3);

        Exception exception = null;
        try
        {
            testQueue.toArray();
        }
        catch (EmptyQueueException e)
        {
            exception = e;
        }

        assertNotNull(exception);

        testQueue.enqueue("travis");
        testQueue.enqueue("the weekend");

        String[] expected = { "travis", "the weekend" };

        assertArrayEquals(testQueue.toArray(), expected);

        //
        ArrayQueue<String> queue4 = new ArrayQueue<String>(4);
        queue4.enqueue("Stargazing");
        queue4.enqueue("Astroworld");
        queue4.enqueue("Utopia");
        queue4.enqueue("song");

        queue4.dequeue();
        queue4.dequeue();

        queue4.enqueue("Stargazing");
        queue4.enqueue("Astroworld");

        String[] expected2 = { "Utopia", "song", "Stargazing", "Astroworld" };

        // I imported a library that can help me compare 2 arrays
        // This is more efficient that having to loop through both.

        assertArrayEquals(queue4.toArray(), expected2);
    }


    /**
     * Tests toString output
     */
    public void testToString()
    {
        ArrayQueue<Song> testQueue = new ArrayQueue<Song>();

        assertEquals("[]", queue.toString());

        Song song = new Song("My Eyes", 80, 0, 0, "p1");
        Song song2 = new Song("stargazing", 80, 0, 0, "");
        testQueue.enqueue(song);

        assertEquals(
            "[My Eyes Pop:80 Rock:0 Country:0 Suggested: p1]",
            testQueue.toString());

        testQueue.enqueue(song2);
        assertEquals(
            "[My Eyes Pop:80 Rock:0 Country:0 Suggested: p1,"
                + " No-Playlist stargazing Pop:80 Rock:0 Country:0]",
            testQueue.toString());
    }


    /**
     * Tests equals method
     */
    public void testEquals()
    {
        // null
        assertFalse(queue.equals(null));

        // wrong class
        String queue3 = "";
        assertFalse(queue.equals(queue3));
        // Equal
        ArrayQueue<String> queue2 = new ArrayQueue<String>();
        queue.enqueue("Stargazing");
        queue.enqueue("Astroworld");
        queue.enqueue("Utopia");

        queue2.enqueue("Stargazing");
        queue2.enqueue("Astroworld");
        queue2.enqueue("Utopia");

        assertTrue(queue.equals(queue2));

    }


    /**
     * Tests equals method again but for out of order and wrong elements
     */
    public void testEquals2()
    {
        ArrayQueue<String> queue2 = new ArrayQueue<String>();
        // out of order
        queue2.enqueue("Stargazing");
        queue2.enqueue("Utopia");
        queue2.enqueue("Astroworld");
        assertFalse(queue.equals(queue2));

        // not same elements
        queue2.enqueue("Her Loss");
        queue2.enqueue("For All The Dogs");
        assertFalse(queue.equals(queue2));

    }

}
