package DataStructures;

import java.util.ArrayList;

public class GraphNode<T> {
    public T value;
    public ArrayList<GraphNode<T>> seenNodes = new ArrayList<>();

    public GraphNode(T v){
        value = v;
    }

    public void appendChild(GraphNode<T> node){
        seenNodes.add(node);
    }
}
