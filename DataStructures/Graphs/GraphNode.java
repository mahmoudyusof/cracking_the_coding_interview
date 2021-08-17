package DataStructures.Graphs;

import java.util.ArrayList;

public class GraphNode<T> {
    public T value;
    public ArrayList<GraphNode<T>> outgoing = new ArrayList<>();
    public ArrayList<GraphNode<T>> incomming = new ArrayList<>();

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
        outgoing.add(node);
        node.incomming.add(this);
    }

    /**
     * creates a double edge (non-directed edge)
     * @param node of type GraphNode<T>. the sibling node.
     */
    public void addDoubleEdge(GraphNode<T> node){
        appendChild(node);
        node.appendChild(this);
    }

    public void removeChild(GraphNode<T> child){
        outgoing.remove(child);
        child.incomming.remove(this);
    }

    public void removeDoubleEdge(GraphNode<T> sibling){
        sibling.removeChild(this);
        this.removeChild(sibling);
    }
}
