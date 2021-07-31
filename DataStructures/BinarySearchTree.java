package DataStructures;

public class BinarySearchTree {
    public BinaryTreeNode root = new BinaryTreeNode();

    public void insert(Integer data){
        root.insert(data);
    }

    public int getHeight(){
        return getHeightHelper(root);
    }

    private int getHeightHelper(BinaryTreeNode node){
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

    private void inOrderPrintHelper(BinaryTreeNode node){
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

    private void postOrderPrintHelper(BinaryTreeNode node){
        if(node.left != null){
            postOrderPrintHelper(node.left);
        }
        if(node.right != null){
            postOrderPrintHelper(node.right);
        }
        System.out.println(node.data);
    }
}
