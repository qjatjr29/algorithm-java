package leetcode.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {

  private static Point[] pointArray;
  private static int[] root;

  public int minCostConnectPoints(int[][] points) {
    int answer = 0;
    pointArray = new Point[points.length];
    root = new int[points.length];
    List<Connection> connections = new ArrayList<>();
    setRoot();

    for(int i = 0; i < points.length; i++) {
      int[] point = points[i];
      int x = point[0];
      int y = point[1];
      pointArray[i] = new Point(i, x, y);
    }

    for(int i = 0; i < pointArray.length; i++) {
      Point p1 = pointArray[i];
      for(int j = i + 1; j < pointArray.length; j++) {
        Point p2 = pointArray[j];
        Connection con = new Connection(p1, p2);
        connections.add(con);
      }
    }

    Collections.sort(connections);

    for(Connection connection : connections) {
      if(isFinish()) break;
      Point p1 = connection.p1;
      Point p2 = connection.p2;
      if(isCycle(p1.id, p2.id)) continue;
      union(p1.id, p2.id);
      answer += connection.distance;
    }

    return answer;

  }

  private boolean isFinish() {
    int rt = findRoot(0);
    boolean chk = true;
    for(int i = 1; i < root.length; i++) {
      if(findRoot(i) != rt) {
        chk = false;
        break;
      }
    }

    return chk;
  }

  private void setRoot() {
    for(int i = 0; i < root.length; i++) {
      root[i] = i;
    }
  }

  private int findRoot(int x) {
    if(root[x] == x) return x;
    return root[x] = findRoot(root[x]);
  }

  private boolean isCycle(int x, int y) {
    x = findRoot(x);
    y = findRoot(y);
    return x == y;
  }

  private void union(int x, int y) {
    x = findRoot(x);
    y = findRoot(y);
    if(x != y) root[y] = x;
  }

  private class Point {
    int id;
    int x, y;

    Point(int id, int x, int y) {
      this.id = id;
      this.x = x;
      this.y = y;
    }
  }

  private class Connection implements Comparable<Connection> {
    Point p1, p2;
    int distance;

    Connection(Point p1, Point p2) {
      this.p1 = p1;
      this.p2 = p2;
      calculateDistance();
    }

    private void calculateDistance() {
      int x1 = p1.x;
      int y1 = p1.y;
      int x2 = p2.x;
      int y2 = p2.y;

      this.distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    @Override
    public int compareTo(Connection o) {
      return this.distance - o.distance;
    }
  }

}
