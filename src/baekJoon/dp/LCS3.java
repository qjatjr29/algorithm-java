package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class LCS3 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    String str1 = st.nextToken();
    st = new StringTokenizer(br.readLine());
    String str2 = st.nextToken();
    st = new StringTokenizer(br.readLine());
    String str3 = st.nextToken();

    int[][][] lcs = new int[101][101][101];

    for(int i = 1; i <= str1.length(); i++) {
      char first = str1.charAt(i - 1);
      for(int j = 1; j <= str2.length(); j++) {
        char sec = str2.charAt(j - 1);
        for(int z = 1; z <= str3.length(); z++) {
          if(first == sec && sec == str3.charAt(z - 1)) {
            lcs[i][j][z] = lcs[i - 1][j - 1][z - 1] + 1;
          }
          else lcs[i][j][z] = Math.max(lcs[i - 1][j][z], Math.max(lcs[i][j - 1][z], lcs[i][j][z - 1]));
        }
      }
    }

    int answer = lcs[str1.length()][str2.length()][str3.length()];

    bw.write(String.valueOf(answer));
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

}
