package main.game;

import main.game.Tree.TreeNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int [][] ga = {
                {0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        TreeNode node = new TreeNode();
        node.path="";

        search(node,3,ga);
    }

    public static int search (TreeNode node, int depth, int[][] gameArray) {
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(node);
        qq = search(qq, depth, gameArray);

        int column=0;
        while (!qq.isEmpty()){
            node = qq.remove();
            column++;
        }

        System.out.println("final state had " + column + " leafs");
        return column;
    }

    private static Queue<TreeNode> search (Queue<TreeNode> qq, int depth, int[][] gameArray){
        Queue<TreeNode>qw = new LinkedList<>();
        HashSet<int [][]> hash = new HashSet<>();
        hash.add(gameArray);
        while (!qq.isEmpty()){
            TreeNode node = qq.remove();
            if (node.depth < depth) {
                node.addchildren();
                hash = appendchildren(hash, node, qq, gameArray);
            } else {
                qw.add(node);
            }
        }
        System.out.println("final hash set size: " + hash.size());
        return qw;
    }

    private static HashSet<int[][]> appendchildren (HashSet<int[][]> hash, TreeNode node, Queue<TreeNode> qq, int[][] gameArray){
        TreeNode[] arrayOfNodes = {node.a, node.b};
        //, node.c, node.d, node.e, node.f, node.g

        for(int i = 0; i < arrayOfNodes.length; i++){
            int[][] temp = SerializationUtils.clone(gameArray);
            TreeNode evaluating = arrayOfNodes[i];
            evaluating.path=node.path+i;
            for (int j=0; j < evaluating.path.length(); j++) {
                int col = evaluating.path.charAt(j)-48;
                temp = GameMethods.MakeMove(col,temp);
            }
            if (!hash.contains(temp)){
                hash.add(temp);
                qq.add(evaluating);
            }else{
                System.out.println("found a duplicate!");
            }
        }
        return hash;
    }

}
