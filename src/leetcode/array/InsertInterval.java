package leetcode.array;

// https://leetcode.com/problems/insert-interval/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertInterval {

  public int[][] insert(int[][] intervals, int[] newInterval) {

    Node[] nodes = new Node[intervals.length];
    for(int i = 0; i < intervals.length; i++) {
      nodes[i] = new Node(intervals[i][0], intervals[i][1]);
    }

    int s_num = newInterval[0];
    int e_num = newInterval[1];
    boolean[] isRemove = new boolean[intervals.length];

    for(int i = 0; i < nodes.length; i++) {
      int cmp_s = nodes[i].start;
      int cmp_e = nodes[i].end;

      if(s_num >= cmp_s && s_num <= cmp_e) {
        s_num = cmp_s;
        e_num = Math.max(e_num, cmp_e);
        isRemove[i] = true;
      }
      else if(e_num >= cmp_s && e_num <= cmp_e) {
        s_num = Math.min(s_num, cmp_s);
        e_num = cmp_e;
        isRemove[i] = true;
      }
      else if(cmp_s >= s_num && cmp_e <= e_num) {
        isRemove[i] = true;
      }
    }

    List<Node> temp = new ArrayList<>();
    for(int i = 0; i < nodes.length; i++) {
      if(isRemove[i]) continue;
      temp.add(nodes[i]);
    }
    temp.add(new Node(s_num, e_num));

    Collections.sort(temp);

    int[][] output = new int[temp.size()][2];
    for(int i = 0; i < temp.size(); i++) {
      output[i][0] = temp.get(i).start;
      output[i][1] = temp.get(i).end;
    }

    return output;
  }
  private class Node implements Comparable<Node> {
    int start, end;

    public Node(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Node o) {
      if(this.start > o.start) return 1;
      else if(this.start == o.start) {
        return this.end - o.end;
      }
      else return -1;
    }
  }

}
