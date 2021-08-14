package DataStructures.Trees;

public class BinarySearchTree<T extends Comparable<T>>  extends BinaryTree<T>{

    public BinarySearchTree() {
        root = new BinarySearchTreeNode<>();
    }

    public void insert(T data) {
        root.insert(data);
    }
}
