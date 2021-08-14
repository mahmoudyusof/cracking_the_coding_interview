package DataStructures.Trees;

public class BinarySearchTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {

    public BinarySearchTreeNode(T value) {
        data = value;
    }

    public BinarySearchTreeNode() {
        return;
    }

    public void insert(T data) {
        if (this.data == null) {
            this.data = data;
            return;
        }

        if (data.compareTo(this.data) >= 0) {
            if (this.right != null) {
                this.right.insert(data);
            } else {
                this.right = new BinarySearchTreeNode<T>(data);
                this.right.parent = this;
            }
        } else {
            if (this.left != null) {
                this.left.insert(data);
            } else {
                this.left = new BinarySearchTreeNode<T>(data);
                this.left.parent = this;
            }
        }
    }
}
