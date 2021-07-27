package DataStructures;

public class StackNode<E> {
    public E value;
    public StackNode<E> next = null;
    public StackNode<E> prev = null;

    public StackNode(E v) {
        value = v;
    }
}
