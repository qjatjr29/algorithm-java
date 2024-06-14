package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 십자가찾기 {

    private static List<int[]> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        answer = new ArrayList();
        char[][] grid = new char[N][M];
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int size = 0;
                if (grid[i][j] == '*') {
                    for(int d = 1; ; d++) {
                        if(i - d < 0 || i + d >= N || j - d < 0 || j + d >= M) {
                            break;
                        }

                        if (grid[i - d][j] == '*' && grid[i][j - d] == '*' &&
                                    grid[i + d][j] == '*' && grid[i][j + d] == '*') {
                                size = d;
                        }
                        else {
                            break;
                        }
                    }
                }

                if (size != 0) {
                    answer.add(new int[] {i + 1, j + 1, size});
                    visited[i][j] = true;
                    for(int s = 1; s <= size; s++) {
                        visited[i - s][j] = true;
                        visited[i][j - s] = true;
                        visited[i + s][j] = true;
                        visited[i][j + s] = true;
                    }
                }
            }
        }

        boolean isFail = false;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (grid[i][j] == '*' && !visited[i][j]) {
                    isFail = true;
                    break;
                }
            }
            if (isFail) {
                break;
            }
        }

        if (isFail) {
            bw.write("-1");
            bw.newLine();
        }
        else {
            bw.write(String.valueOf(answer.size()));
            bw.newLine();
            for(int[] ans : answer) {
                bw.write(String.valueOf(ans[0]) + " ");
                bw.write(String.valueOf(ans[1]) + " ");
                bw.write(String.valueOf(ans[2]));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();

    }

}
