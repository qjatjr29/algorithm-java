package baekJoon.greedy;

import java.io.*;
import java.util.*;

public class 레이스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());

        int[] pos = new int[k];

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            pos[i] = Integer.parseInt(input.nextToken());
        }

        String answer = binarySearch(pos, n, m);
        bw.write(answer);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static String binarySearch(int[] pos, int n, int m) {
        String res = "";

        int minDistance = 1;
        int maxDistance = n;

        while (minDistance <= maxDistance) {

            int mid = (minDistance + maxDistance) / 2;

            String chk = check(mid, pos, m);

            // 해당 길이는 안된다.
            if (chk.isEmpty()) {
                maxDistance = mid - 1;
                continue;
            }

            res = chk;
            minDistance = mid + 1;
        }
        return res;
    }

    // 각 심판별 거리가 target 만큼의 길이로 최대가 되는지 확인
    private static String check(int targetDistance, int[] pos, int m) {

        StringBuilder sb = new StringBuilder();

        int prevPoint = pos[0]; // 심판을 위치시킨 자리
        int referee = 1;
        sb.append("1");

        for (int i = 1; i < pos.length; i++) {
            // 모든 심판을 배치한 경우
            if (referee == m) {
                sb.append("0");
            }

            else {
                // 해당 자리에 심판을 배치했을 때 심판간 거리
                int dist = pos[i] - prevPoint;

                // target 거리보다 두 개의 간격 사이의 거리가 짧다면 불가능
                // => 해당 위치에 심판을 배치하지 않고 넘어간다.
                // 최소 같거나 길어야 한다.
                if (dist >= targetDistance) {
                    sb.append("1");
                    referee++;
                    prevPoint = pos[i];
                }
                else sb.append("0");
            }

        }
        if (referee == m) {
            return sb.toString();
        }
        return "";
    }

}
