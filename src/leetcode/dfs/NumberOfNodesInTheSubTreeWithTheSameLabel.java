package leetcode.dfs;

// https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfNodesInTheSubTreeWithTheSameLabel {

  private static Node[] nodes;

  public int[] countSubTrees(int n, int[][] edges, String labels) {

    nodes = new Node[n];

    List<Integer>[] connectionInfo = new ArrayList[n];

    for(int i = 0; i < n; i++) {
      nodes[i] = new Node(labels.charAt(i));
      connectionInfo[i] = new ArrayList<>();
    }

    for(int i = 0; i < edges.length; i++) {
      int node1 = edges[i][0];
      int node2 = edges[i][1];

      connectionInfo[node1].add(node2);
      connectionInfo[node2].add(node1);
    }

    connectSubTree(-1, 0, connectionInfo, labels);

    return Arrays.stream(nodes)
        .mapToInt(node -> node.countSubTree)
        .toArray();
  }

  private class Node {
    int countSubTree;
    char label;

    public Node(char label) {
      this.label = label;
      countSubTree = 0;
    }
  }

  private int[] connectSubTree(int prev, int nodeNumber, List<Integer>[] connectionInfo, String label) {

    int[] result = new int[26];
    char cLabel = nodes[nodeNumber].label;

    for(int i = 0; i < connectionInfo[nodeNumber].size(); i++) {

      int nNumber = connectionInfo[nodeNumber].get(i);

      if(nNumber != prev) {
        int[] res = connectSubTree(nodeNumber, nNumber, connectionInfo, label);

        for(int j = 0; j < res.length; j++) {
          result[j] += res[j];
        }
      }
    }

    nodes[nodeNumber].countSubTree = ++result[label.charAt(nodeNumber) - 'a'];
    return result;
  }

}
