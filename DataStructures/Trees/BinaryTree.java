package DataStructures.Trees;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
    public BinaryTreeNode<T> root;

    public BinaryTree(){
        root = new BinaryTreeNode<>();
    }

    /**
     * inserts a new element to the tree
     * @param data
     */
    public void insert(T data) {
        root.insert(data);
    }

    /**
     * gets the height of the tree
     * @return height
     */
    public int getHeight() {
        return getHeightHelper(root);
    }

    /**
     * return the height from a given node to the bottom
     * @param node
     * @return
     */
    private int getHeightHelper(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            int left_height = getHeightHelper(node.left);
            int right_height = getHeightHelper(node.right);
            return left_height > right_height ? 1 + left_height : 1 + right_height;
        }
    }

    /**
     * array representation of in order traversal of a tree
     * @return
     */
    public ArrayList<T> getInOrderArray() {
        ArrayList<T> in_order_sort = new ArrayList<>();
        getInOrderArrayHelper(root, in_order_sort);
        return in_order_sort;
    }

    private void getInOrderArrayHelper(BinaryTreeNode<T> node, ArrayList<T> in_order_sort) {
        if (node.left != null) {
            getInOrderArrayHelper(node.left, in_order_sort);
        }
        in_order_sort.add(node.data);
        if (node.right != null) {
            getInOrderArrayHelper(node.right, in_order_sort);
        }
    }

    /**
     * array representation of postorder traversal
     * @return
     */
    public ArrayList<T> getPostOrderArray() {
        ArrayList<T> post_order_sort = new ArrayList<>();
        getPostOrderArrayHelper(root, post_order_sort);
        return post_order_sort;
    }

    private void getPostOrderArrayHelper(BinaryTreeNode<T> node, ArrayList<T> post_order_sort) {
        if (node.left != null) {
            getPostOrderArrayHelper(node.left, post_order_sort);
        }
        if (node.right != null) {
            getPostOrderArrayHelper(node.right, post_order_sort);
        }
        post_order_sort.add(node.data);
    }

    public boolean contains(BinaryTreeNode<T> node){
        return root.contains(node);
    }

    public int getDepth(BinaryTreeNode<T> node){
        return root.getDepth(node);
    }

    public BinaryTreeNode<T> getLeftLeafe(){
        return this.root.getLeftLeafe();
    }

    public BinaryTreeNode<T> getRightLeafe(){
        return this.root.getRightLeafe();
    }
}
