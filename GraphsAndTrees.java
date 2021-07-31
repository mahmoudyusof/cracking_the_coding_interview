import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import DataStructures.BinarySearchTree;
import DataStructures.BinaryTreeNode;
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

    public static void populate(BinaryTreeNode node, ArrayList<Integer> elements, int start, int end){
        // terminal condition
        if(end - start <= 1){
            int mid_index = (start + end) / 2;
            node.data = elements.get(mid_index);
            return;
        }

        int mid_index = (start + end) / 2;
        node.data = elements.get(mid_index);
        node.right = new BinaryTreeNode();
        node.left = new BinaryTreeNode();
        populate(node.right, elements, mid_index+1, end);
        populate(node.left, elements, start, mid_index);
    }

    public static BinarySearchTree makeMinHeightTree(ArrayList<Integer> elements) {
        int size = elements.size();
        BinarySearchTree tree = new BinarySearchTree();
        populate(tree.root, elements, 0, size);
        return tree;
    }

    public static void testMakeMinHeightTree() {
        ArrayList<Integer> elements = new ArrayList<>();
        for(int i=0; i<15; i++){
            elements.add(i);
        }
        BinarySearchTree tree = makeMinHeightTree(elements);
        assert tree.getHeight() == 4;

        elements.add(15);
        tree = makeMinHeightTree(elements);
        assert tree.getHeight() == 5;

    }
    public static void main(String[] args) {
        testRouteExists();
        testMakeMinHeightTree();
    }
}