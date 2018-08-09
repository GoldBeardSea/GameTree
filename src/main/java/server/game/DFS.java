package server.game;

import server.game.Tree.SeptinaryTree;
import server.game.Tree.TreeNode;
import server.pojos.ReturnTree;
import org.apache.commons.lang3.SerializationUtils;
import java.util.HashSet;



public class DFS {
    public static void main(String[] args) {
        int [][] ga = {
                {0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        findmove(ga);
    }

    public static int findmove(int[][]gameArray){
        SeptinaryTree gametree = new SeptinaryTree();
        TreeNode root = new TreeNode();
        gametree.setroot(root);
        ReturnTree data = new ReturnTree();
        data.path="";
        data.hash = new HashSet<>();
        data.depth=3;
        data = buildtree(gameArray, gametree.getRoot(), data);
        int column = data.column;
        System.out.println("Hash Size: " + data.hash.size());
        return column;
    }

    private static ReturnTree buildtree(int[][]gameArray, TreeNode node, ReturnTree data){
        data.depth ++;

        if (data.depth < GameEngine.plydepth) {
            //  If we're below plydepth, we keep drilling down to brute force all the possibilities.
            node.addchildren();



            //  An array of the children nodes, to conveniently iterate across.
            TreeNode[] arrayOfNodes = {node.a, node.b, node.c, node.d, node.e, node.f, node.g};

            for (int i =0; i < arrayOfNodes.length; i++) {
                // grab a node from the array.
                TreeNode thisnode = arrayOfNodes[i];

                // deep clone the game array, and make a move, corresponding to the tree
                // node that we're on.
                int[][] temp = SerializationUtils.clone(gameArray);
                if (i!=0) {
                    data.path = data.path.substring(0,data.path.length()-1);
                }

                data.path +=i;

                for (int j =0; j < data.path.length(); j++){
                    System.out.println(data.path.charAt(j));
                }
                System.out.println("//////////////////////////");

                temp = GameMethods.MakeMove(i, temp);

                //  Check the hash-set to see if our new-cloned game aray is inside.   If so,
                // we can mark this node as terminal and ignore it.  If not, we recur on the node
                // to hit it's children.
                if (data.hash.contains(temp)) {
//                    thisnode.isterminal = true;
                    System.out.println("found a terminal node");
                } else {
                    data.hash.add(temp);
                    buildtree(temp, thisnode, data);
                }
            }
        } else { //  triggered when data.depth >= ply-depth, meaning that we're at
            // our target depth, and it's time to analyze each node.
        }
        return data;
    }
}
