package baekJoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 색상환 {

    private static final int INF = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int colorCount = Integer.parseInt(input.nextToken());
        int[][] colors = new int[colorCount + 1][colorCount + 1];

        input = new StringTokenizer(br.readLine());
        int targetCount = Integer.parseInt(input.nextToken());

        colors[0][1] = 1;
        colors[1][1] = 1;

        for(int i = 0; i <= colorCount; i++) {
            colors[i][1] = i;
            colors[i][0] = 1;
        }

        colors[2][2] = 0;

        for(int i = 4; i <= colorCount; i++) {
            for(int j = 2; j <= colorCount; j++) {
                colors[i][j] = (colors[i - 2][j - 1] + colors[i - 1][j]) % INF;
            }
        }

        int answer = colors[colorCount][targetCount];

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
