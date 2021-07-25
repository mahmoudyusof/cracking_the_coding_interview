package DataStructures;

public class Node<E> {
    public E value;
    public Node<E> next = null;

    public Node(E v) {
        value = v;
    }
}
