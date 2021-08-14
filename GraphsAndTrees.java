import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import DataStructures.Graphs.*;
import DataStructures.Trees.*;

public class GraphsAndTrees {
    /**
     * takes two nodes in a graph and checks if there is a route between them
     * @param source
     * @param sink
     * @return
     */
    public static boolean routeExists(GraphNode<Integer> source, GraphNode<Integer> sink) {
        HashSet<GraphNode<Integer>> visited_set = new HashSet<>();
        Stack<GraphNode<Integer>> traverse_stack = new Stack<>();
        GraphNode<Integer> current;

        traverse_stack.push(source);
        visited_set.add(source);
        while (!traverse_stack.isEmpty()) {
            current = traverse_stack.pop();
            if (current == sink) {
                return true;
            }
            for (GraphNode<Integer> node : current.seenNodes) {
                if (!visited_set.contains(node)) {
                    traverse_stack.push(node);
                    visited_set.add(node);
                }
            }
        }
        return false;
    }

    @Test
    public void testRouteExists() {
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

        assertTrue(routeExists(node1, node6));
        assertFalse(routeExists(node6, node1));
    }

    /**
     * takes an array, start index, end index and recursively adds elements to
     * a binary search tree mid first
     * @param node
     * @param elements
     * @param start
     * @param end
     */
    public static void populate(BinaryTreeNode<Integer> node, ArrayList<Integer> elements, int start, int end) {
        // terminal condition
        if (end - start <= 1) {
            try{
                int mid_index = (start + end) / 2;
                node.data = elements.get(mid_index);
                return;
            }catch(IndexOutOfBoundsException e){
                return;
            }
        }

        int mid_index = (start + end) / 2;
        node.data = elements.get(mid_index);
        node.right = new BinaryTreeNode<>();
        node.left = new BinaryTreeNode<>();
        populate(node.right, elements, mid_index + 1, end);
        populate(node.left, elements, start, mid_index);
    }

    /**
     * creates a binary search tree of minimum height
     * @param elements
     * @return
     */
    public static BinarySearchTree<Integer> makeMinHeightTree(ArrayList<Integer> elements) {
        int size = elements.size();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        populate(tree.root, elements, 0, size);
        return tree;
    }

    @Test
    public void testMakeMinHeightTree() {
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        assertEquals(4, tree.getHeight());

        elements.add(14);
        elements.add(15);
        tree = makeMinHeightTree(elements);
        assertEquals(5, tree.getHeight());


        elements = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            elements.add(i / 2);
        }
        tree = makeMinHeightTree(elements);
        assertEquals(4, tree.getHeight());

        elements.add(15 / 2);
        tree = makeMinHeightTree(elements);
        assertEquals(5, tree.getHeight());
    }

    /**
     * takes a tree and creates an array for each level of the tree
     * @param tree
     * @return
     */
    public static ArrayList<LinkedList<Integer>> arrayOfDepths(BinarySearchTree<Integer> tree) {
        ArrayList<LinkedList<Integer>> arr = new ArrayList<>();
        arrayOfDepthsHelper(arr, 0, tree.root);
        return arr;
    }

    /**
     * recursive helper function
     * takes an array of linkedlists and the index of the linked list to which we should add the element
     * which is also the level of the node in the tree and adds the element to the list
     * @param arr
     * @param index
     * @param node
     */
    public static void arrayOfDepthsHelper(ArrayList<LinkedList<Integer>> arr, int index,
            BinaryTreeNode<Integer> node) {
        if (arr.size() < index + 1) {
            LinkedList<Integer> level = new LinkedList<>();
            level.add(node.data);
            arr.add(level);
        } else {
            LinkedList<Integer> level = arr.get(index);
            level.add(node.data);
        }
        if (node.right != null) {
            arrayOfDepthsHelper(arr, index + 1, node.right);
        }
        if (node.left != null) {
            arrayOfDepthsHelper(arr, index + 1, node.left);
        }
        if (node.left == null && node.right == null) {
            return;
        }
    }

    @Test
    public void testArrayOfDepths() {
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        ArrayList<LinkedList<Integer>> array_of_depths = arrayOfDepths(tree);

        assertEquals(4, array_of_depths.size());
        int count = 1;
        for (LinkedList<Integer> level : array_of_depths) {
            assertEquals(count, level.size());
            count *= 2;
        }
    }

    /**
     * check if a tree which's root is the given node is balanced
     * @param node
     * @return
     */
    public static boolean checkBalanced(BinaryTreeNode<Integer> node) {
        // recurrence ending
        if (node.left == null && node.right == null)
            return true;

        if (node.left == null && node.right.getHeight() > 1) {
            return false;
        }

        if (node.right == null && node.left.getHeight() > 1) {
            return false;
        }

        int diff = node.right.getHeight() - node.left.getHeight();
        if (diff > 1 || diff < -1)
            return false;

        return checkBalanced(node.right) && checkBalanced(node.left);
    }

    @Test
    public void testCheckBalanced() {
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        assertTrue(checkBalanced(tree.root));

        tree = new BinarySearchTree<>();
        for (int i = 0; i < 15; i++) {
            tree.insert(i);
        }
        assertFalse(checkBalanced(tree.root));
    }

    public static boolean isBinarySearchTree(BinaryTreeNode<Integer> root){
        boolean right_condition = false, left_condition = false;
        if(root.right == null) {
            right_condition = true;
        }else{
            right_condition = (root.right.data >= root.data);
            right_condition = right_condition && isBinarySearchTree(root.right);
        }

        if(root.left == null) {
            left_condition = true;
        }else{
            left_condition = (root.left.data < root.data) && isBinarySearchTree(root.left);
        }

        return right_condition && left_condition;
    }

    @Test
    public void testIsBinarySearchTree(){
        ArrayList<Integer> elements = new ArrayList<>();
        BinaryTree<Integer> is_binary_search_tree = new BinaryTree<>();
        BinaryTree<Integer> not_binary_search_tree = new BinaryTree<>();

        for(int i=0; i<7; i++){
            elements.add(i);
            not_binary_search_tree.insert(i);
        }
        is_binary_search_tree = makeMinHeightTree(elements);

        assertTrue(isBinarySearchTree(is_binary_search_tree.root));
        assertFalse(isBinarySearchTree(not_binary_search_tree.root));
    }
}