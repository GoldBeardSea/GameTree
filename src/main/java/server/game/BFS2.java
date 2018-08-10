package server.game;

import server.pojos.AppendChildrenReturn;
import server.game.Tree.TreeNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS2 {
    public static void main(String[] args) {
        int[][] gamearray = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};
        search(gamearray);
    }

    public static int search (int[][] gameArray) {
        int column =3;
        TreeNode node = new TreeNode();
        node.path = "";
        int depth = 4;
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(node);

        while (qq.peek().depth < depth) {
            node = qq.remove();
            node.addchildren();
            TreeNode[] arrayOfNodes = {node.a, node.b, node.c, node.d, node.e, node.f, node.g};
            for (int i = 0; i < arrayOfNodes.length; i++) {
                TreeNode adding = arrayOfNodes[i];
                adding.depth = node.depth + 1;
                adding.path = node.path + i;
                qq.add(adding);
            }
        }
        System.out.println("Leaves: " + qq.size());

        int bestscore =-10000;
        while (!qq.isEmpty()) {
        node = qq.remove();
            int[][] temp = SerializationUtils.clone(gameArray);
            for (int j=0; j < node.path.length(); j++) {
                int col = node.path.charAt(j)-48;
                temp = GameMethods.MakeMove(col,temp);
            }
            int score = GameEngine.evaluatePosition(temp);
            int col = node.path.charAt(0)-48;
            if (score == bestscore && col <4){
                column = col;
            }
            if(score > bestscore) {
                bestscore=score;
                column = col;
            }
        }
        System.out.println(column);
        return column;
    }
}
