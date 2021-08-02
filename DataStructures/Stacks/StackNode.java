package DataStructures.Stacks;

public class StackNode<T extends Comparable<T>> {
    public T value;
    public StackNode<T> next = null;
    public StackNode<T> prev = null;

    public StackNode(T v) {
        value = v;
    }
}
