package DataStructures.Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T> {
    private ArrayList<GraphNode<T>> nodes = new ArrayList<GraphNode<T>>();
    private HashMap<T, GraphNode<T>> map = new HashMap<T, GraphNode<T>>();

    public GraphNode<T> getOrCreateNode(T name) {
        if (!map.containsKey(name)) {
            GraphNode<T> node = new GraphNode<>(name);
            nodes.add(node);
            map.put(name, node);
        }
        return map.get(name);
    }

    public void addEdge(T startName, T endName) {
        GraphNode<T> start = getOrCreateNode(startName);
        GraphNode<T> end = getOrCreateNode(endName);
        start.appendChild(end);
    }

    public ArrayList<GraphNode<T>> getNodes() {
        return nodes;
    }
}
