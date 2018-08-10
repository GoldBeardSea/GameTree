package server.game.Tree;

public class TreeNode {
    public int evaluation;
    public String path;
    public int depth;
    public TreeNode a;
    public TreeNode b;
    public TreeNode c;
    public TreeNode d;
    public TreeNode e;
    public TreeNode f;
    public TreeNode g;

    public void addchildren(){
        this.a = new TreeNode();
        this.a.depth=this.depth+1;
        this.path="";

        this.b = new TreeNode();
        this.b.depth=this.depth+1;
        this.path="";

        this.c = new TreeNode();
        this.c.depth=this.depth+1;
        this.path="";

        this.d = new TreeNode();
        this.d.depth=this.depth+1;
        this.path="";

        this.e = new TreeNode();
        this.e.depth=this.depth+1;
        this.path="";

        this.f = new TreeNode();
        this.f.depth=this.depth+1;
        this.path="";

        this.g = new TreeNode();
        this.g.depth=this.depth+1;
        this.path="";
    }
}