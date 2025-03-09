package baekJoon.Implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 사탕게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        char[][] candies = new char[n][n];
        List<int[]> checkList = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            String candy = new StringTokenizer(br.readLine()).nextToken();
            for (int j = 0; j < candy.length(); j++) {
                candies[i][j] = candy.charAt(j);

                // 행 - 인접한 사탕의 색이 다른지 확인
                if (i > 0 && candies[i - 1][j] != candies[i][j]) {
                    checkList.add(new int[]{i - 1, j, i, j});
                }

                // 열 - 인접한 사탕의 색이 다른지 확인
                if (j > 0 && candies[i][j - 1] != candies[i][j]) {
                    checkList.add(new int[]{i, j - 1, i, j});
                }
            }
        }


        for (int[] check : checkList) {
            int cx1 = check[0];
            int cy1 = check[1];
            int cx2 = check[2];
            int cy2 = check[3];
            swap(candies, cx1, cy1, cx2, cy2);
            int result = eat(candies);
            answer = Math.max(answer, result);
            swap(candies, cx1, cy1, cx2, cy2);
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void swap(char[][] candies, int cx1, int cy1, int cx2, int cy2) {
        char temp = candies[cx1][cy1];
        candies[cx1][cy1] = candies[cx2][cy2];
        candies[cx2][cy2] = temp;
    }

    private static int eat(char[][] candies) {

        int count = 0;

        for (char[] chars : candies) {
            int c = 1;
            char candy = chars[0];
            for (int j = 1; j < chars.length; j++) {
                if (candy == chars[j]) {
                    c++;
                } else {
                    candy = chars[j];
                    count = Math.max(count, c);
                    c = 1;
                }
            }
            count = Math.max(count, c);
        }

        for (int i = 0; i < candies[0].length; i++) {
            int c = 1;
            char candy = candies[0][i];
            for (int j = 1; j < candies.length; j++) {
                if (candy == candies[j][i]) {
                    c++;
                }
                else {
                    candy = candies[j][i];
                    count = Math.max(count, c);
                    c = 1;
                }
            }
            count = Math.max(count, c);
        }

        return count;
    }
}
