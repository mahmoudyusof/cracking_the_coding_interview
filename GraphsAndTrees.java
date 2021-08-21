import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import DataStructures.Graphs.*;
import DataStructures.Trees.*;

public class GraphsAndTrees {
    /**
     * takes two nodes in a graph and checks if there is a route between them
     * 
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
            for (GraphNode<Integer> node : current.outgoing) {
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
     * takes an array, start index, end index and recursively adds elements to a
     * binary search tree mid first
     * 
     * @param node
     * @param elements
     * @param start
     * @param end
     */
    public static <T extends Comparable<T>> void populate(BinaryTree<T> tree, List<T> elements, int start, int end) {
        // terminal condition
        if (start > end)
            return;
        int mid_index = (start + end) / 2;
        tree.insert(elements.get(mid_index));
        populate(tree, elements, mid_index + 1, end);
        populate(tree, elements, start, mid_index - 1);
    }

    /**
     * creates a binary search tree of minimum height
     * 
     * @param elements
     * @return
     */
    public static <T extends Comparable<T>> BinarySearchTree<T> makeMinHeightTree(List<T> elements) {
        int size = elements.size();
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        populate(tree, elements, 0, size - 1);
        return tree;
    }

    @Test
    public void testMakeMinHeightTree() {
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            elements.add(i);
        }
        BinarySearchTree<Integer> tree = makeMinHeightTree(elements);
        // show(tree);
        assertEquals(4, tree.getHeight());

        elements.add(14);
        elements.add(15);
        tree = makeMinHeightTree(elements);
        // System.out.println("==================");
        // show(tree);
        assertEquals(5, tree.getHeight());

        elements = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            elements.add(i / 2);
        }
        tree = makeMinHeightTree(elements);
        // System.out.println("=================");
        // show(tree);
        assertEquals(5, tree.getHeight());
        elements.add(15 / 2);
        tree = makeMinHeightTree(elements);
        System.out.println("=================");
        show(tree);
        assertEquals(5, tree.getHeight());
    }

    public static <T extends Comparable<T>> void show(BinaryTree<T> tree) {
        for (LinkedList<T> level : arrayOfDepths(tree)) {
            for (T i : level) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    /**
     * takes a tree and creates an array for each level of the tree
     * 
     * @param tree
     * @return
     */
    public static <T extends Comparable<T>> ArrayList<LinkedList<T>> arrayOfDepths(BinaryTree<T> tree) {
        ArrayList<LinkedList<T>> arr = new ArrayList<>();
        arrayOfDepthsHelper(arr, 0, tree.root);
        return arr;
    }

    /**
     * recursive helper function takes an array of linkedlists and the index of the
     * linked list to which we should add the element which is also the level of the
     * node in the tree and adds the element to the list
     * 
     * @param arr
     * @param index
     * @param node
     */
    public static <T extends Comparable<T>> void arrayOfDepthsHelper(ArrayList<LinkedList<T>> arr, int index,
            BinaryTreeNode<T> node) {
        if (arr.size() < index + 1) {
            LinkedList<T> level = new LinkedList<>();
            level.add(node.data);
            arr.add(level);
        } else {
            LinkedList<T> level = arr.get(index);
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
     * 
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

    /**
     * check if a binary tree is a binary search tree
     * 
     * @param root root node of tree in question
     * @return
     */
    public static boolean isBinarySearchTree(BinaryTreeNode<Integer> root) {
        boolean right_condition = false, left_condition = false;
        if (root.right == null) {
            right_condition = true;
        } else {
            right_condition = (root.right.data >= root.data);
            right_condition = right_condition && isBinarySearchTree(root.right);
        }

        if (root.left == null) {
            left_condition = true;
        } else {
            left_condition = (root.left.data < root.data) && isBinarySearchTree(root.left);
        }

        return right_condition && left_condition;
    }

    @Test
    public void testIsBinarySearchTree() {
        ArrayList<Integer> elements = new ArrayList<>();
        BinaryTree<Integer> is_binary_search_tree = new BinaryTree<>();
        BinaryTree<Integer> not_binary_search_tree = new BinaryTree<>();

        for (int i = 0; i < 7; i++) {
            elements.add(i);
            not_binary_search_tree.insert(i);
        }
        is_binary_search_tree = makeMinHeightTree(elements);

        assertTrue(isBinarySearchTree(is_binary_search_tree.root));
        assertFalse(isBinarySearchTree(not_binary_search_tree.root));
    }

    @Test
    public void testNext() {
        ArrayList<Integer> elements = new ArrayList<>();
        BinaryTree<Integer> is_binary_search_tree;
        BinaryTree<Integer> not_binary_search_tree = new BinarySearchTree<>();
        Integer count = 0;

        for (int i = 0; i < 7; i++) {
            elements.add(i);
            not_binary_search_tree.insert(i);
        }
        is_binary_search_tree = makeMinHeightTree(elements);

        BinaryTreeNode<Integer> search_iterator = is_binary_search_tree.root.getLeftLeafe();
        BinaryTreeNode<Integer> not_search_iterator = not_binary_search_tree.root.getLeftLeafe();

        while (search_iterator != null) {
            assertEquals(search_iterator.data, not_search_iterator.data);
            assertEquals(search_iterator.data, count);
            count++;
            search_iterator = search_iterator.next();
            not_search_iterator = not_search_iterator.next();
        }
    }

    /**
     * given a list of project and a list of lists defining the dependencies between
     * projects, this function decides what is the execution order if any
     * 
     * @param projects
     * @param dependencies
     * @return
     */
    public static ArrayList<GraphNode<Character>> getBuildOrder(Character[] projects, Character[][] dependencies) {
        Graph<Character> graph = new Graph<>();
        ArrayList<GraphNode<Character>> project_nodes = new ArrayList<>();
        for (Character project : projects) {
            project_nodes.add(graph.getOrCreateNode(project));
        }
        for (Character[] dependency : dependencies) {
            Character first = dependency[0];
            Character second = dependency[1];
            graph.addEdge(first, second);
        }
        ArrayList<GraphNode<Character>> order = new ArrayList<>();
        for (int i = 0; i < projects.length; i++) {
            order.add(null);
        }
        int end_of_list = addNonDependent(order, project_nodes, 0);
        int to_be_processed = 0;
        while (to_be_processed < order.size()) {
            GraphNode<Character> current = order.get(to_be_processed);
            if (current == null) {
                return null;
            }
            while (current.outgoing.size() > 0) {
                current.removeChild(current.outgoing.get(0));
            }

            end_of_list = addNonDependent(order, project_nodes, end_of_list);
            to_be_processed++;
        }
        return order;
    }

    /**
     * get non dependent projects and add them to the order array
     * 
     * @param order
     * @param projects
     * @param offset
     * @return
     */
    public static int addNonDependent(ArrayList<GraphNode<Character>> order, ArrayList<GraphNode<Character>> projects,
            int offset) {
        for (GraphNode<Character> project : projects) {
            if (project.incomming.size() == 0 && !order.contains(project)) { // should be a hashset for better
                                                                             // performance
                // another solution would be to remove the node from the hash table of the graph
                // but I don't want to do that
                order.set(offset, project);
                offset++;
            }
        }
        return offset;
    }

    @Test
    public void testGetBuildOrder() {
        Character[] projects = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
        Character[][] dependencies = new Character[][] { { 'a', 'e' }, { 'b', 'e' }, { 'b', 'a' }, { 'f', 'a' },
                { 'c', 'a' }, { 'f', 'c' }, { 'f', 'b' }, { 'd', 'g' }, };
        ArrayList<GraphNode<Character>> order = getBuildOrder(projects, dependencies);
        assertEquals(7, order.size());
        assertEquals(Character.valueOf('e'), order.get(6).value);
    }

    /**
     * given a binary tree and two nodes in the tree get the most deepest common
     * ancestor
     * 
     * @param root
     * @param node_a
     * @param node_b
     * @return
     */
    public static BinaryTreeNode<Integer> getMostRecentAncestor(BinaryTreeNode<Integer> root,
            BinaryTreeNode<Integer> node_a, BinaryTreeNode<Integer> node_b) {
        if (root == null || node_a == null || node_b == null)
            return root;
        boolean right_contains_a = root.right.contains(node_a);
        boolean right_contains_b = root.right.contains(node_b);

        if (right_contains_a != right_contains_b)
            return root;

        if (right_contains_a)
            return getMostRecentAncestor(root.right, node_a, node_b);

        return getMostRecentAncestor(root.left, node_a, node_b);
    }

    @Test
    public void testGetMostRecentAncestor() {
        BinaryTree<Integer> is_binary_search_tree;
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            elements.add(i);
        }
        is_binary_search_tree = makeMinHeightTree(elements);

        BinaryTreeNode<Integer> left = is_binary_search_tree.getLeftLeafe();
        BinaryTreeNode<Integer> right = is_binary_search_tree.getRightLeafe();

        assertSame(is_binary_search_tree.root, getMostRecentAncestor(is_binary_search_tree.root, left, right));
        assertNotNull(left.parent);
        assertNotNull(left.parent.right);
        assertSame(left.parent, getMostRecentAncestor(is_binary_search_tree.root, left, left.parent.right));
    }

    /**
     * given a binary search tree node, get all insertion sequences that can result
     * in this tree
     * 
     * @param node
     * @return
     */
    public static ArrayList<LinkedList<Integer>> allSequences(BinaryTreeNode<Integer> node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    /**
     * given two arrays, weave them togeather and add result to results array
     * 
     * @param first
     * @param second
     * @param results
     * @param prefix
     */
    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    @Test
    public void testAllSequences() {
        BinarySearchTree<Integer> simple = new BinarySearchTree<>();
        BinarySearchTree<Integer> balanced;
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            elements.add(i);
            simple.insert(i);
        }
        balanced = makeMinHeightTree(elements);
        show(balanced);

        assertEquals(2, allSequences(balanced.root).size());
        assertEquals(1, allSequences(simple.root).size());
    }

    public static boolean containsTree(BinaryTreeNode<Character> t1, BinaryTreeNode<Character> t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    public static void getOrderString(BinaryTreeNode<Character> node, StringBuilder sb) {
        if (node == null) {
            sb.append("X");
            return;
        }
        sb.append(node.data + " ");
        getOrderString(node.left, sb);
        getOrderString(node.right, sb);
    }

    // TODO: test containsTree

    public static int countPathsWithSum(BinaryTreeNode<Integer> root, int target_sum) {
        return countPathsWithSum(root, target_sum, 0, new HashMap<Integer, Integer>());
    }

    public static int countPathsWithSum(BinaryTreeNode<Integer> node, int target_sum, int running_sum,
            HashMap<Integer, Integer> path_count) {
        if (node == null)
            return 0;

        running_sum += node.data;
        int sum = running_sum - target_sum;
        int totalPaths = path_count.getOrDefault(sum, 0);

        if (running_sum == target_sum) {
            totalPaths++;
        }

        incrementHashTable(path_count, running_sum, 1);
        totalPaths += countPathsWithSum(node.left, target_sum, running_sum, path_count);
        totalPaths += countPathsWithSum(node.right, target_sum, running_sum, path_count);
        incrementHashTable(path_count, running_sum, -1);
        return totalPaths;
    }

    public static void incrementHashTable(HashMap<Integer, Integer> hash_table, int key, int delta) {
        int new_count = hash_table.getOrDefault(key, 0) + delta;
        if (new_count == 0) {
            hash_table.remove(key);
        } else {
            hash_table.put(key, new_count);
        }
    }
}