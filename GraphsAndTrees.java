import java.util.*;

import DataStructures.Graphs.*;
import DataStructures.Trees.*;

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

    public static void populate(BinaryTreeNode<Integer> node, ArrayList<Integer> elements, int start, int end){
        // terminal condition
        if(end - start <= 1){
            int mid_index = (start + end) / 2;
            node.data = elements.get(mid_index);
            return;
        }

        int mid_index = (start + end) / 2;
        node.data = elements.get(mid_index);
        node.right = new BinaryTreeNode<>();
        node.left = new BinaryTreeNode<>();
        populate(node.right, elements, mid_index+1, end);
        populate(node.left, elements, start, mid_index);
    }

    public static BinarySearchTree<Integer> makeMinHeightTree(ArrayList<Integer> elements) {
        int size = elements.size();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        populate(tree.root, elements, 0, size);
        return tree;
    }

    public static void testMakeMinHeightTree() {
        ArrayList<Integer> elements = new ArrayList<>();
        for(int i=0; i<15; i++){
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        assert tree.getHeight() == 4;

        elements.add(15);
        tree = makeMinHeightTree(elements);
        assert tree.getHeight() == 5;

    }

    public static ArrayList<LinkedList<Integer>> arrayOfDepths(BinarySearchTree<Integer> tree){
        ArrayList<LinkedList<Integer>> arr = new ArrayList<>();
        arrayOfDepthsHelper(arr, 0, tree.root);
        return arr;
    }

    public static void arrayOfDepthsHelper(ArrayList<LinkedList<Integer>> arr, int index, BinaryTreeNode<Integer> node) {
        if(arr.size() < index + 1){
            LinkedList<Integer> level = new LinkedList<>();
            level.add(node.data);
            arr.add(level);
        }else{
            LinkedList<Integer> level = arr.get(index);
            level.add(node.data);
        }
        if(node.right != null){
            arrayOfDepthsHelper(arr, index + 1, node.right);
        }
        if(node.left != null){
            arrayOfDepthsHelper(arr, index + 1, node.left);
        }
        if(node.left == null && node.right == null){
            return;
        }
    }

    public static void testArrayOfDepths() {
        ArrayList<Integer> elements = new ArrayList<>();
        for(int i=0; i<15; i++){
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        ArrayList<LinkedList<Integer>> array_of_depths = arrayOfDepths(tree);
        for(LinkedList<Integer> level : array_of_depths){
            Iterator<Integer> iter = level.iterator();
            while(iter.hasNext()){
                System.out.print(iter.next());
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static boolean checkBalanced(BinaryTreeNode<Integer> node) {
        // recurrence ending
        if(node.left == null && node.right == null) return true;

        if(node.left == null && node.right.getHeight() > 1){
            return false;
        }

        if(node.right == null && node.left.getHeight() > 1){
            return false;
        }

        int diff = node.right.getHeight() - node.left.getHeight();
        if(diff > 1 || diff < -1) return false;

        return checkBalanced(node.right) && checkBalanced(node.left);
    }

    public static void testCheckBalanced() {
        ArrayList<Integer> elements = new ArrayList<>();
        for(int i=0; i<15; i++){
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        assert checkBalanced(tree.root);

        tree = new BinarySearchTree<>();
        for(int i=0; i<15; i++){
            tree.insert(i);
        }
        assert !checkBalanced(tree.root);
    }

    public static void main(String[] args) {
        testRouteExists();
        testMakeMinHeightTree();
        testArrayOfDepths();
        testCheckBalanced();
    }
}