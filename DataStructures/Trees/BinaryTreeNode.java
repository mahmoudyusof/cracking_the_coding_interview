package DataStructures.Trees;

import java.util.Random;

public class BinaryTreeNode<T extends Comparable<T>> {
    public T data;
    public BinaryTreeNode<T> right = null;
    public BinaryTreeNode<T> left = null;
    public BinaryTreeNode<T> parent = null;
    public int right_size = 0;
    public int left_size = 0;

    public BinaryTreeNode(T d) {
        this.data = d;
    }

    public BinaryTreeNode() {
        this.data = null;
    }

    public void insert(T value) {
        if (data == null) {
            data = value;
        } else if (this.right == null) {
            this.right_size++;
            this.right = new BinaryTreeNode<>(value);
            this.right.parent = this;
        } else if (this.left == null) {
            this.left_size++;
            this.left = new BinaryTreeNode<>(value);
            this.left.parent = this;
        } else {
            if(this.right_size > this.left_size){
                this.left.insert(value);
            }else{
                this.right.insert(value);
            }
        }
    }

    public void insert(BinaryTreeNode<T> node) {
        if (data == null) {
            data = node.data;
        } else if (this.right == null) {
            this.right_size++;
            this.right = node;
            this.right.parent = this;
        } else if (this.left == null) {
            this.left_size++;
            this.left = node;
            this.left.parent = this;
        } else {
            if(this.right_size > this.left_size){
                this.left.insert(node);
            }else{
                this.right.insert(node);
            }
        }
    }

    public int getHeight() {
        return getHeightHelper(this);
    }

    private int getHeightHelper(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            int left_height = getHeightHelper(node.left);
            int right_height = getHeightHelper(node.right);
            return left_height > right_height ? 1 + left_height : 1 + right_height;
        }
    }

    public BinaryTreeNode<T> next() {
        if (this.right != null) {
            return getLeftLeafe(this.right);
        }
        BinaryTreeNode<T> current = this;
        BinaryTreeNode<T> current_parent = current.parent;
        // Go up until we're on left instead of right
        while (current_parent != null && current_parent.left != current) {
            current = current_parent;
            current_parent = current_parent.parent;
        }
        return current_parent;

    }

    private BinaryTreeNode<T> getLeftLeafe(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> left_leafe = node;
        while (left_leafe.left != null) {
            left_leafe = left_leafe.left;
        }
        return left_leafe;
    }

    public BinaryTreeNode<T> getLeftLeafe() {
        return getLeftLeafe(this);
    }

    private BinaryTreeNode<T> getRightLeafe(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> right_leafe = node;
        while (right_leafe.right != null) {
            right_leafe = right_leafe.right;
        }
        return right_leafe;
    }

    public BinaryTreeNode<T> getRightLeafe() {
        return getRightLeafe(this);
    }

    public boolean contains(BinaryTreeNode<T> node) {
        return getDepth(node) != 0;
    }

    public int getDepth(BinaryTreeNode<T> node) {
        return getDepthHelper(this, node, 0);
    }

    protected int getDepthHelper(BinaryTreeNode<T> root, BinaryTreeNode<T> node, int level)
            throws NullPointerException {
        if (root == null)
            return 0;
        if (root == node)
            return level + 1;
        return getDepthHelper(root.left, node, level + 1) + getDepthHelper(root.right, node, level + 1);
    }

    public BinaryTreeNode<T> random(){
        Random rand = new Random();
        int random_number = rand.nextInt(left_size + right_size + 1);
        if(random_number == 0){
            return this;
        }else if(random_number <= left_size){
            return this.left.random();
        }else{
            return this.right.random();
        }
    }
}
