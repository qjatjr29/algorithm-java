package baekJoon.twoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 두개의탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(input.nextToken());
        int[] distances = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());

            int distance = Integer.parseInt(input.nextToken());
            // i + 1 까지 가는 거리합(누적)
            distances[i] = distances[i - 1] + distance;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                // j 까지의 누적 거리 = distances[j - 1]
                // i 까지의 누적 거리 = distances[i - 1]
                // 그 사이의 값이 시계방향으로 이동한 거리
                int clockSide = distances[j - 1] - distances[i - 1];

                // 원형의 전체 거리에서 시계방향으로 이동한 거리를 빼면 반 시계 방향으로 이동한 거리가 나옴.
                int reverseClockSide = distances[N] - clockSide;
                int result = Math.min(clockSide, reverseClockSide);
                answer = Math.max(answer, result);

                // 시계방향의 거리가 반시계방향의 거리보다 더 길다 = 앞으로는 무조건 i, j 지점의 거리는 반 시계 방향의 거리이다.
                // 이미 앞의 두 지점의 거리가 더 길다. 따라서 더 확인할 필요 없음.
                if(clockSide >= reverseClockSide) break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
