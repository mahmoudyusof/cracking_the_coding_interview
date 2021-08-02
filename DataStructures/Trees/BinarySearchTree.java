package DataStructures.Trees;

public class BinarySearchTree<T extends Comparable<T>> {
    public BinarySearchTreeNode<T> root = new BinarySearchTreeNode<T>();

    public void insert(T data){
        root.insert(data);
    }

    public int getHeight(){
        return getHeightHelper(root);
    }

    private int getHeightHelper(BinarySearchTreeNode<T> node){
        if(node == null){
            return 0;
        }else{
            int left_height = getHeightHelper(node.left);
            int right_height = getHeightHelper(node.right);
            return left_height > right_height ? 1 + left_height : 1 + right_height;
        }
    }

    public void inOrderPrint(){
        inOrderPrintHelper(root);
    }

    private void inOrderPrintHelper(BinarySearchTreeNode<T> node){
        if(node.left != null){
            inOrderPrintHelper(node.left);
        }
        System.out.println(node.data);
        if(node.right != null){
            inOrderPrintHelper(node.right);
        }
    }

    public void postOrderPrint(){
        postOrderPrintHelper(root);
    }

    private void postOrderPrintHelper(BinarySearchTreeNode<T> node){
        if(node.left != null){
            postOrderPrintHelper(node.left);
        }
        if(node.right != null){
            postOrderPrintHelper(node.right);
        }
        System.out.println(node.data);
    }
}
