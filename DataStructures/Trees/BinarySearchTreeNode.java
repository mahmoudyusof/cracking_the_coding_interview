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
            this.right_size++;
            if (this.right != null) {
                this.right.insert(data);
            } else {
                this.right = new BinarySearchTreeNode<T>(data);
                this.right.parent = this;
            }
        } else {
            this.left_size++;
            if (this.left != null) {
                this.left.insert(data);
            } else {
                this.left = new BinarySearchTreeNode<T>(data);
                this.left.parent = this;
            }
        }
    }

    public void insert(BinarySearchTreeNode<T> node) {
        if (this.data == null) {
            this.data = node.data;
            return;
        }

        if (data.compareTo(this.data) >= 0) {
            this.right_size++;
            if (this.right != null) {
                this.right.insert(data);
            } else {
                this.right = node;
                this.right.parent = this;
            }
        } else {
            this.left_size++;
            if (this.left != null) {
                this.left.insert(data);
            } else {
                this.left = node;
                this.left.parent = this;
            }
        }
    }

    protected int getDepthHelper(BinaryTreeNode<T> root, BinaryTreeNode<T> node, int level)
            throws NullPointerException {
        if (root == null)
            return 0;
        if (root == node)
            return level + 1;
        if (node.data.compareTo(root.data) >= 0) {
            return getDepthHelper(root.right, node, level + 1);
        } else {
            return getDepthHelper(root.left, node, level + 1);
        }
    }
}
