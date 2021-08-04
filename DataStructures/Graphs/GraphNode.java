package DataStructures.Graphs;

import java.util.ArrayList;

public class GraphNode<T> {
    public T value;
    public ArrayList<GraphNode<T>> seenNodes = new ArrayList<>();

    /**
     * constructor of graph node
     * @param data of type T. the data stored in the graph node
     */
    public GraphNode(T data){
        value = data;
    }

    /**
     * creates a directed edge from this node to the parameter node
     * @param node of type GraphNode<T>. the node at the end of the directed edge
     */
    public void appendChild(GraphNode<T> node){
        seenNodes.add(node);
    }

    /**
     * creates a double edge (non-directed edge)
     * @param node of type GraphNode<T>. the sibling node.
     */
    public void addDoubleEdge(GraphNode<T> node){
        appendChild(node);
        node.appendChild(this);
    }
}
