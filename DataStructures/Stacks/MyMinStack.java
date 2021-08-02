package DataStructures.Stacks;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyMinStack<T extends Comparable<T>> extends MyStack<T> {

    public StackNode<T> head;
    public StackNode<T> tail;
    public int size = 0;
    private LinkedList<T> mins = new LinkedList<>();
    /**
     * define whatever you want
     */
    public void push(T item){
        super.push(item);
        if(item.compareTo(mins.getLast()) <= 0){
            mins.add(item);
        }
    }


    public void push(StackNode<T> item){
        super.push(item);

        if(item.value.compareTo(mins.getLast()) <= 0){
            mins.add(item.value);
        }
    }

    public T pop() throws EmptyStackException{
        if(size == 0){
            throw new EmptyStackException();
        }
        size--;
        T value = tail.value;
        tail.prev.next = null;
        tail = tail.prev;
        if(value == mins.getLast()){
            mins.removeLast();
        }
        return value;
    }

    public T min() throws EmptyStackException{
        if(size == 0) throw new EmptyStackException();
        return mins.getLast();
    }
}