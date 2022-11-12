package programmers.kakao;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;

public class 길찾기게임 {

  private static int[] preNumbers;
  private static int[] postNumbers;
  private static int idx;

  private static class Node implements Comparable<Node> {
    int x, y, index;
    Node left, right;

    public Node(int x, int y, int index) {
      this.x = x;
      this.y = y;
      this.index = index;
      left = null;
      right = null;
    }

    public Node(int x, int y, int index, Node left, Node right) {
      this.x = x;
      this.y = y;
      this.index = index;
      this.left = left;
      this.right = right;
    }

    @Override
    public int compareTo(Node o) {
      if(this.y == o.y){
        return this.x-o.x;
      }
      return o.y-this.y;
    }
  }

  public static int[][] solution(int[][] nodeinfo) {
    int[][] answer = {};

    List<Node> nodeList = new ArrayList<>();
    preNumbers = new int[nodeinfo.length];
    postNumbers = new int[nodeinfo.length];

    for(int i = 0; i < nodeinfo.length; i++) {
      nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
    }

    sort(nodeList);

    Node rootNode = nodeList.get(0);

    for(int i = 1; i < nodeList.size(); i++) {
      addNode(rootNode, nodeList.get(i));
    }

    preOrder(rootNode);
    idx = 0;
    postOrder(rootNode);

    answer = new int[2][nodeinfo.length];
    answer[0] = preNumbers;
    answer[1] = postNumbers;

    return answer;
  }

  private static void addNode(Node parent, Node child) {
    if(parent.x > child.x) {
      if(parent.left == null) parent.left = child;
      else addNode(parent.left, child);
    }
    else {
      if(parent.right == null) parent.right = child;
      else addNode(parent.right, child);
    }
  }

  private static void preOrder(Node node) {
    if(node != null) {
      preNumbers[idx++] = node.index;
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  private static void postOrder(Node node) {
    if(node != null) {
      postOrder(node.left);
      postOrder(node.right);
      postNumbers[idx++] = node.index;
    }
  }

  public static void main(String[] args) {

    int[][] nodeinfo = {
        {5, 3},
        {11, 5},
        {13, 3},
        {3, 5},
        {6, 1},
        {1, 3},
        {8, 6},
        {7, 2},
        {2, 2}
    };

    int[][] solution = solution(nodeinfo);

    for (int[] ints : solution) {
      for (int anInt : ints) {
        System.out.print(anInt + " ");
      }
      System.out.println();
    }

  }

}
