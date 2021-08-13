package DataStructures.Stacks;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyMinStack<T extends Comparable<T>> extends MyStack<T> {
    private LinkedList<T> mins = new LinkedList<>();


    public void push(T item) {
        super.push(item);
        // record minimum value in history linked list
        if (mins.size() == 0 || item.compareTo(mins.getLast()) <= 0) {
            mins.add(item);
        }
    }

    public void push(StackNode<T> item) {
        super.push(item);
        // same as above
        if (item.value.compareTo(mins.getLast()) <= 0) {
            mins.add(item.value);
        }
    }
    
    public T pop() throws EmptyStackException {
        if (size == 0) {
            throw new EmptyStackException();
        }
        size--;
        T value = tail.value;
        tail.prev.next = null;
        tail = tail.prev;
        // if this value is the minimum remove it
        // its previous value will be the new minimum
        if (value == mins.getLast()) {
            mins.removeLast();
        }
        return value;
    }

    /**
     * gets the minimum element of the stack
     * @return
     * @throws EmptyStackException
     */
    public T min() throws EmptyStackException {
        if (size == 0)
            throw new EmptyStackException();
        return mins.getLast();
    }
}