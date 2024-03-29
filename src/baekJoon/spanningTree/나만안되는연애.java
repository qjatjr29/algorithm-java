package baekJoon.spanningTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14621
public class 나만안되는연애 {

  private static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int university = Integer.parseInt(st.nextToken());
    int path = Integer.parseInt(st.nextToken());

    int answer = 0;
    University[] universities = new University[university + 1];
    Route[] routes = new Route[path];
    root = new int[university + 1];

    st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= university; i++) {
      char type = st.nextToken().charAt(0);

      universities[i] = new University(type, i);
      root[i] = i;
    }

    for(int i = 0; i < path; i++) {
      st = new StringTokenizer(br.readLine());
      int university1 = Integer.parseInt(st.nextToken());
      int university2 = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());

      routes[i] = new Route(university1, university2, distance);
    }

    Arrays.sort(routes, (r1, r2) -> r1.distance - r2.distance);

    for(Route route : routes) {

      int university1 = route.university1;
      int university2 = route.university2;

      if(isCycle(university1, university2)) continue;
      if(universities[university1].type == universities[university2].type) continue;

      union(university1, university2);
      answer += route.distance;
    }

    boolean isConnect = true;
    int rootNumber = root[1];
    for(int i = 2; i < root.length; i++) {
      if(rootNumber != find(i)) {
        isConnect = false;
        break;
      }
    }

    if(!isConnect) answer = -1;
    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();

  }

  private static class University {
    char type;
    int number;

    public University(char type, int number) {
      this.type = type;
      this.number = number;
    }
  }

  private static class Route {
    int university1, university2;
    int distance;

    public Route(int university1, int university2, int distance) {
      this.university1 = university1;
      this.university2 = university2;
      this.distance = distance;
    }
  }

  private static int find(int x) {
    if(root[x] == x) return x;
    return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    x = find(x);
    y = find(y);
    if(x != y) root[y] = x;
  }

  private static boolean isCycle(int x, int y) {
    x = find(x);
    y = find(y);
    return x == y;
  }
}
