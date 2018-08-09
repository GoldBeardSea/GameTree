package server.game.Tree;

public class SeptinaryTree {

    private TreeNode root;
    public TreeNode getRoot(){
        return root;
    }

    public void setroot(TreeNode node){
        this.root=node;
    }

    public void add(int column) {
        if (this.root == null) {
//            this.root = new TreeNode(column);
            return;
        }
        this.add(column, this.root);
    }

    private void add(int column, TreeNode current) {

    //Do Stuff


    }
}
