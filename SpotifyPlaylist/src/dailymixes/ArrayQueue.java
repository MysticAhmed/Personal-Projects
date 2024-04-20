// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ahmed Abdelhady (aabdelhady05)

package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

// -------------------------------------------------------------------------
/**
 * Creates the logic for a queue which takes songs. Queue utilizes a circular
 * array and grows in capacity when capacity is reached.
 * 
 * @author ahmedabdelhady
 * @version April 8, 2024
 * @param <T>
 */
public class ArrayQueue<T>
    implements QueueInterface<T>
{
    /**
     * Constant DEFAULT_CAPACITY sets default capacity of queue
     */
    // ~ Fields ................................................................
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object.
     * 
     * @param capacity
     */
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    /**
     * Create a new ArrayQueue object.
     */
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }


    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object.
     * 
     * @param capacity
     *            takes the capacity of new queue object
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        queue = (T[])new Object[capacity + 1];
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
        size = 0;
    }

    // ----------------------------------------------------------


    /**
     * s Getter method for sise
     * 
     * @return size field
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Should return the length of the underlying array. Client code shouldn't
     * be concerned about the underlying array but we have included this to use
     * for testing purposes when the array length gets changed. Remember the
     * capacity of the queue is one less than the length of the underlying
     * array.
     * 
     * @return int indicating length of underlying array
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    /**
     * This helper method can be used to upgrade the length of the underlying
     * array when the queue is full.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        T[] oldQueue = queue;
        int oldSize = oldQueue.length;
        queue = (T[])new Object[2 * oldSize - 1];

        int j = dequeueIndex;
        for (int i = 0; i < oldSize - 1; i++)
        {
            queue[i] = oldQueue[j];
            j = (j + 1) % oldSize;
        }

        dequeueIndex = 0;
        enqueueIndex = oldSize - 2;
    }


    /**
     * Clears queue
     */
    @SuppressWarnings("unchecked")
    public void clear()
    {
        queue = (T[])new Object[queue.length];
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
        size = 0;
    }


    /**
     * Gives client code the option of accessing the data in the queue without
     * interfering with the integrity of the queue.
     * 
     * @return the array representation of queue
     */
    public Object[] toArray()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        @SuppressWarnings("unchecked")
        T[] newArr = (T[])new Object[this.size];

        int j = dequeueIndex;
        for (int i = 0; i < newArr.length; i++)
        {
            newArr[i] = queue[j];
            j = (j + 1) % queue.length;
        }

        return newArr;

    }


    /**
     * Transforms queue to String
     * 
     * @return the string
     */
    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        int current = dequeueIndex;
        for (int i = 0; i < size; i++)
        {
            builder.append(queue[current].toString());

            if (i < size - 1)
            {
                builder.append(", ");
            }
            current = (current + 1) % queue.length;
        }

        builder.append("]");
        return builder.toString();
    }


    /**
     * Checks if queue is empty
     * 
     * @return true if queue is empty and false otherwise
     */
    public boolean isEmpty()
    {
        return (enqueueIndex + 1) % queue.length == dequeueIndex;

    }


    /**
     * Compares 2 Arrays to see if they're equal. They have to contain the same
     * elements in the same order.
     * 
     * @param obj
     *            takes in the object we want to compare
     * @return boolean indicating if both equal
     */
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == this.getClass())
        {

            @SuppressWarnings("unchecked")
            ArrayQueue<T> other = (ArrayQueue<T>)obj;
            int thisIndex = dequeueIndex;
            int otherIndex = other.dequeueIndex;
            if (this.size != other.size)
            {
                return false;
            }

            for (int i = 0; i < this.size; i++)
            {
                if (!this.queue[thisIndex].equals(other.queue[otherIndex]))
                {
                    return false;
                }
                thisIndex = (thisIndex + 1) % this.queue.length;
                otherIndex = (otherIndex + 1) % other.queue.length;
            }

            return true;
        }

        return false;
    }


    /**
     * Removes from front of queue
     * 
     * @return front of the queue removed
     * @throws EmptyQueueException
     *             if removing while empty
     */
    public T dequeue()
    {

        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = (dequeueIndex + 1) % queue.length;
        size--;

        return front;
    }


    /**
     * @param newEntry
     *            is the entry we want to add to the end of the queue
     */
    public void enqueue(T newEntry)
    {
        if (isFull())
        {
            ensureCapacity();
        }

        enqueueIndex = (enqueueIndex + 1) % queue.length;
        queue[enqueueIndex] = newEntry;
        size++;
    }


    /**
     * Gets first element of queue
     * 
     * @return the front of the queue
     */
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        return queue[dequeueIndex];
    }


    /**
     * Determines if queue is full
     * 
     * @return true if size is same size as capacity
     */
    private boolean isFull()
    {
        return ((enqueueIndex + 2) % queue.length == dequeueIndex);
    }

}
