package DataStructures.Stacks;

import java.util.EmptyStackException;

public class MyStack<T extends Comparable<T>> {

    public StackNode<T> head;
    public StackNode<T> tail;
    public int size = 0;
    /**
     * define whatever you want
     */
    public void push(T item){
        size++;
        if (head == null){
            head = new StackNode<>(item);
            tail = head;
        }else{
            StackNode<T> n = new StackNode<>(item);
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
    }


    public void push(StackNode<T> item){
        size++;
        if (head == null){
            head = item;
            tail = head;
        }else{
            item.prev = tail;
            tail.next = item;
            tail = item;
        }
    }

    public T peek(){
        return tail.value;
    }

    public T pop() throws EmptyStackException{
        if(size == 0){
            throw new EmptyStackException();
        }
        size--;
        T value = tail.value;
        tail.prev.next = null;
        tail = tail.prev;
        return value;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}