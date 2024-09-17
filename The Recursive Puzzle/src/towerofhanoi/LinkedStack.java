package towerofhanoi;

import stack.StackInterface;
import java.util.EmptyStackException;

// Virginia Tech Honor Code Pledge:
//
// Project 3
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.

// -------------------------------------------------------------------------
/**
 * Creates the LinkedStack which represents a tower and updates depending on
 * movement of disks. Hides implementation from the HanoiSolver and Tower Class
 * 
 * @author Ahmed Abdelhady (aabdelhady05)
 * @version 2024-03-17
 * @param <T>
 *            generic type
 */
public class LinkedStack<T>
    implements StackInterface<T>
{
    private int size;
    private Node topNode;

    // ----------------------------------------------------------

    /**
     * Creates new linkedStack object
     */
    public LinkedStack()
    {
        topNode = null;
    }


    /**
     * Returns size field
     * 
     * @return size of disks
     */

    public int size()
    {
        return size;
    }


    /**
     * Creates String representation of disks in tower
     * 
     * @return string
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node current = topNode;
        while (current != null)
        {
            if (s.length() > 1)
            {
                s.append(", ");
            }
            s.append(current.getData());
            current = current.getNextNode();
        }
        s.append("]");
        return s.toString();
    }


    /**
     * clears the stack of all nodes
     */
    @Override
    public void clear()
    {
        topNode = null;
        size = 0;
    }

    // ----------------------------------------------------------


    /**
     * checks if stack is empty
     * 
     * @return boolean indicating empty or not
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the top node data
     * 
     * @return top node data
     */
    @Override
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        return topNode.getData();
    }

    // ----------------------------------------------------------


    /**
     * Removes node from stack
     * 
     * @return removed node
     */
    public T pop()
    {
        if (size == 0)
        {
            throw new EmptyStackException();
        }

        T temp = topNode.getData();
        topNode = topNode.getNextNode();
        size--;
        return temp;
    }

    // ----------------------------------------------------------


    /**
     * adds node to stack
     */
    @Override
    public void push(T anEntry)
    {
        Node entry = new Node(anEntry, topNode);
        topNode = entry;
        size++;
    }

    private class Node
    {
        private T data;
        private Node next;

        /**
         * Creates new node object
         * 
         * @param entry
         *            takes data of new node
         * @param node
         *            creates the node the new node will look at
         */
        public Node(T entry, Node node)
        {
            this(entry);
            this.setNextNode(node);
        }


        /**
         * Sets next node of a node
         * 
         * @param node
         *            sets current node to the next
         */
        public void setNextNode(Node node)
        {
            next = node;
        }


        /**
         * Creates new node object with one data parameter
         * 
         * @param data
         *            takes data in a node
         */
        public Node(T data)
        {
            this.data = data;
        }


        /**
         * Gets the nextNode
         * 
         * @return next node
         */
        public Node getNextNode()
        {
            return next;
        }


        /**
         * returns node data
         * 
         * @return data of node
         */
        public T getData()
        {
            return data;
        }
    }
}
