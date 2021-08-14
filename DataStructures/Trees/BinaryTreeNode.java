package DataStructures.Trees;

public class BinaryTreeNode<T extends Comparable<T>> {
    public T data;
    public BinaryTreeNode<T> right = null;
    public BinaryTreeNode<T> left = null;
    public BinaryTreeNode<T> parent = null;

    public BinaryTreeNode(T d) {
        this.data = d;
    }

    public BinaryTreeNode() {
        this.data = null;
    }

    public void insert(T value) {
        if(data == null){
            data = value;
        }else if(right == null){
            right = new BinaryTreeNode<>(value);
            right.parent = this;
        }else if(left == null){
            left = new BinaryTreeNode<>(value);
            left.parent = this;
        }else{
            right.insert(value);
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

    public BinaryTreeNode<T> next(){
        if(this.right != null){
            return getLeftLeafe(this.right);
        }

        if(this.parent == null){
            return null;
        }

        if(this == this.parent.left){
            return this.parent;
        }else{
            return getLeftLeafe(this);
        }

    }

    private BinaryTreeNode<T> getLeftLeafe(BinaryTreeNode<T> node){
        BinaryTreeNode<T> left_leafe = node;
        while(left_leafe.left != null){
            left_leafe = left_leafe.left;
        }
        return left_leafe;
    }

    public BinaryTreeNode<T> getLeftLeafe(){
        return getLeftLeafe(this);
    }
}
