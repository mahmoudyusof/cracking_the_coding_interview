package DataStructures.Trees;

public class BinaryTreeNode<T extends Comparable<T>> {
    public T data;
    public BinaryTreeNode<T> right = null;
    public BinaryTreeNode<T> left = null;

    public BinaryTreeNode(T d){
        this.data = d;
    }

    public BinaryTreeNode(){
        this.data = null;
    }


    public void insert(T value){}

    public int getHeight(){
        return getHeightHelper(this);
    }

    private int getHeightHelper(BinaryTreeNode<T> node){
        if(node == null){
            return 0;
        }else{
            int left_height = getHeightHelper(node.left);
            int right_height = getHeightHelper(node.right);
            return left_height > right_height ? 1 + left_height : 1 + right_height;
        }
    }
}
