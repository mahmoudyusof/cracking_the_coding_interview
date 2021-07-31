package DataStructures;

public class BinaryTreeNode {
    public Integer data;
    public BinaryTreeNode right = null;
    public BinaryTreeNode left = null;

    public BinaryTreeNode(Integer d){
        this.data = d;
    }

    public BinaryTreeNode(){
        this.data = null;
    }

    public void insert(Integer data){
        if(this.data == null){
            this.data = data;
            return;
        }

        if(data >= this.data){
            if(this.right != null){
                this.right.insert(data);
            }else{
                this.right = new BinaryTreeNode(data);
            }
        }else{
            if(this.left != null){
                this.left.insert(data);
            }else{
                this.left = new BinaryTreeNode(data);
            }
        }
    }
}
