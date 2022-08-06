package codeTest.카카오엔터프라이즈;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 슈퍼로봇대전 {
  private static boolean[][] ranking;
  private static int N, M;
  private static int answer;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    ranking = new boolean[N + 1][N + 1];

    for(int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int winner = Integer.parseInt(st.nextToken());
      int loser = Integer.parseInt(st.nextToken());

      ranking[winner][loser] = true;
    }

    floyd();

    for(int i = 1; i <= N; i++) {
      boolean chk = true;
      for(int j = 1; j <= N; j++) {
        if(i != j && !(ranking[i][j] || ranking[j][i])) {
          chk = false;
          break;
        }
      }
      if(chk) answer++;
    }

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();
  }

  private static void floyd() {
    for(int i = 1; i <= N; i++) {
      for(int j = 1; j <= N; j++) {
        for(int z = 1; z <= N; z++) {
          if(j != z && ranking[j][i] && ranking[i][z]) ranking[j][z] = true;
        }
      }
    }
  }
}
