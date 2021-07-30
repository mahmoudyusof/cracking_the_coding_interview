import java.util.HashSet;
import java.util.Stack;

import DataStructures.GraphNode;

public class GraphsAndTrees{
    public static boolean routeExists(GraphNode<Integer> source, GraphNode<Integer> sink) {
        HashSet<GraphNode<Integer>> visited_set = new HashSet<>();
        Stack<GraphNode<Integer>> traverse_stack = new Stack<>();
        GraphNode<Integer> current;

        traverse_stack.push(source);
        visited_set.add(source);
        while(!traverse_stack.isEmpty()){
            current = traverse_stack.pop();
            if(current == sink){
                return true;
            }
            for(GraphNode<Integer> node : current.seenNodes){
                if(!visited_set.contains(node)){
                    traverse_stack.push(node);
                    visited_set.add(node);
                }
            }
        }
        return false;
    }

    public static void testRouteExists() {
        GraphNode<Integer> node1 = new GraphNode<>(0);
        GraphNode<Integer> node2 = new GraphNode<>(0);
        GraphNode<Integer> node3 = new GraphNode<>(0);
        GraphNode<Integer> node4 = new GraphNode<>(0);
        GraphNode<Integer> node5 = new GraphNode<>(0);
        GraphNode<Integer> node6 = new GraphNode<>(0);

        node1.appendChild(node2);
        node2.appendChild(node3);
        node3.appendChild(node4);
        node4.appendChild(node5);
        node5.appendChild(node6);
        node6.appendChild(node2);

        assert routeExists(node1, node6) : "cannot reach node 6 from node 1";
        assert !routeExists(node6, node1) : "can reach node 1 from node 6";
        
        
    }
    public static void main(String[] args) {
        testRouteExists();
    }
}