package baekJoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리 {

  private static int n, m, answer;
  private static int rank[], root[];
  private static int chk[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int cnt = 1;
    while(true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      answer = 0;

      if(n == 0 && m == 0) break;

      setup();

      for(int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        union(a, b);
      }

      for(int i = 1; i <= n; i++) {
        int here = find(i);
        if(root[here] != 0 && chk[find(i)] == 0) {
          chk[find(i)]++;
          answer++;
        }
      }

      if(answer == 0) {
        bw.write("Case " + String.valueOf(cnt) + ": No trees.");
      } else if(answer == 1) {
        bw.write("Case " + String.valueOf(cnt) + ": There is one tree.");
      } else {
        bw.write("Case " + String.valueOf(cnt) + ": A forest of " + String.valueOf(answer) + " trees.");
      }
      bw.newLine();
      cnt++;
    }
    bw.flush();
    bw.close();
    br.close();
  }

  private static void setup() {
    rank = new int[n + 1];
    root = new int[n + 1];
    chk = new int[n + 1];

    for(int i = 0; i <= n; i++) {
      rank[i] = 0;
      root[i] = i;
    }
  }
  private static int find(int x){
    if(root[x] == x) return x;
    else return root[x] = find(root[x]);
  }
  private static void union(int x, int y) {
    int rx= find(x);
    int ry= find(y);
    if(ry < rx) {
      int tmp = rx;
      rx = ry;
      ry = tmp;
    }
    // cycle
    if(rx == ry) {
      root[rx] = 0;
    }
    else {
      root[ry] = rx;
    }
  }

}
