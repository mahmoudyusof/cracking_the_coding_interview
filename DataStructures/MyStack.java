package DataStructures;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyStack {

    public StackNode<Integer> head;
    public StackNode<Integer> tail;
    public int size = 0;
    private LinkedList<Integer> mins = new LinkedList<>();
    /**
     * define whatever you want
     */
    public void push(Integer item){
        size++;
        if (head == null){
            head = new StackNode<>(item);
            tail = head;
        }else{
            StackNode<Integer> n = new StackNode<>(item);
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
    }


    public void push(StackNode<Integer> item){
        size++;
        if (head == null){
            head = item;
            tail = head;
        }else{
            item.prev = tail;
            tail.next = item;
            tail = item;
        }

        if(item.value < mins.getLast()){
            mins.add(item.value);
        }
    }

    public Integer peek(){
        return tail.value;
    }

    public Integer pop() throws EmptyStackException{
        if(size == 0){
            throw new EmptyStackException();
        }
        size--;
        Integer value = tail.value;
        tail.prev.next = null;
        tail = tail.prev;
        if(value == mins.getLast()){
            mins.removeLast();
        }
        return value;
    }

    public Integer min() throws EmptyStackException{
        if(size == 0) throw new EmptyStackException();
        return mins.getLast();
    }
}