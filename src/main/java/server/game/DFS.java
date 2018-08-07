package main.game;

import main.game.Tree.SeptinaryTree;
import main.game.Tree.TreeNode;
//import org.apache.commons.lang3.SerializationUtils;


public class DFS {
    public static void main(String[] args) {
        int [][] ga = {
                {0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0}
        };

//        findmove(ga);
    }

    public static int findmove(int[][]gameArray){
        SeptinaryTree logictree = new SeptinaryTree();
        TreeNode root = new TreeNode();
        logictree.setroot(root);
        int column = buildtree(gameArray,logictree.getRoot(),-1);
        return column;
    }

    private static int buildtree(int[][]gameArray, TreeNode node, int depth){

        //  Array of future nodes to be appended to tree.   Set up to be for-loop iterable
        TreeNode[] nodearray = {node.a, node.b, node.c, node.d, node.e, node.f, node.g};

        depth++;
        if (depth < GameEngine.plydepth) {

            //  For each node, create the next node, and wire it up to our tree.

            for (int i = 0; i < nodearray.length; i++) {
                //  Create the next node, attach it to the tree.
                TreeNode nextnode = new TreeNode();
                nodearray[i] = nextnode;

                //  Copy the path so we can reconstruct the moves that got us here.
                nextnode.path = node.path;
                nextnode.path.add(i);

                //  Recursively build out the tree
                buildtree(gameArray, nextnode, depth);

            }
        } else{
            //  If depth >= Gamengine.plydepth, we're at our targeted search depth.
            //  Each node at this depth is a leaf, and it's time for us to analyze.


        }

        return 2;
    }
}
