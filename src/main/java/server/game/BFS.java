package server.game;

import server.pojos.AppendChildrenReturn;
import server.game.Tree.TreeNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int[][] gamearray = {
                {-1, 1, 1,-1,-1, 0, 0},
                {-1, 1, 1, 1, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0}};
        search(gamearray);
    }

    public static int search (int[][] gameArray) {
        TreeNode node = new TreeNode();
        int depth = 4;
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(node);
        qq = search(qq, depth, gameArray);

        int leaves=0;
        int column=3;
        int bestscore=0;
        while (!qq.isEmpty()){
            node = qq.remove();
            int[][] temp = SerializationUtils.clone(gameArray);
            for (int j=0; j < node.path.length(); j++) {
                int col = node.path.charAt(j)-48;
                temp = GameMethods.MakeMove(col,temp);
            }
            int score = GameEngine.evaluatePosition(temp);
            if(score > bestscore) {
                bestscore=score;
                column = node.path.charAt(0)-48;
            }

            leaves++;
        }

        System.out.println("final state had " + leaves + " leafs");
        System.out.println(column);
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
                AppendChildrenReturn acr = new AppendChildrenReturn();
                acr = appendchildren(hash, node, qq, gameArray);
                hash=acr.hash;
                qq = acr.qq;
                System.out.println("cde");
            } else {
                qw.add(node);
            }
        }
        System.out.println("final hash set size: " + hash.size());
        return qw;
    }

    private static AppendChildrenReturn appendchildren (HashSet<int[][]> hash, TreeNode node, Queue<TreeNode> qq, int[][] gameArray){
        AppendChildrenReturn acr = new AppendChildrenReturn();

        TreeNode[] arrayOfNodes = {node.a, node.b, node.c, node.d, node.e, node.f, node.g};
        for(int i = 0; i < arrayOfNodes.length; i++){
            int[][] CurrentBoard = SerializationUtils.clone(gameArray);
            TreeNode evaluating = arrayOfNodes[i];
            evaluating.path = node.path+i;
            for (int j=0; j < evaluating.path.length(); j++) {
                int col = evaluating.path.charAt(j)-48;
                CurrentBoard = GameMethods.MakeMove(col,CurrentBoard);
            }

            boolean alreadyseen = false;
            for (int[][] hasharray : hash){
                if (Arrays.deepEquals(CurrentBoard, hasharray)){
                    alreadyseen = true;
                }
            }
            if (!alreadyseen){
                hash.add(CurrentBoard);
                qq.add(evaluating);
            }
        }

        acr.hash=hash;
        acr.qq=qq;
        return acr;
    }
}
