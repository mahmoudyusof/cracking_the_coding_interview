package DataStructures.Lists;

public class Node<T extends Comparable<T>> {
    public T value;
    public Node<T> next = null;

    public Node(T v) {
        value = v;
    }
}
