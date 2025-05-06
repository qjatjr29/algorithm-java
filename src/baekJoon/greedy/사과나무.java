package baekJoon.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class 사과나무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int[] treeHeights= new int[n];

        input = new StringTokenizer(br.readLine());

        long treeHeightSum = 0;
        int modOne = 0;
        int modTwo = 0;
        for (int i = 0; i < n; i++) {
            treeHeights[i] = Integer.parseInt(input.nextToken());

            modOne += treeHeights[i] % 2; // 나머지가 1인 경우 - 결국 1을 채워주는 물뿌리개를 사용해야함
            modTwo += treeHeights[i] / 2; // 2로 나눈 몫 -> 2를 사용하는 물 뿌리개 사용

            treeHeightSum += treeHeights[i];
        }

        // 2를 사용하는 물 뿌리개보다 1을 사용하는 물뿌리개를 더 많이 사용해야 한다면 불가능 -> 두 개를 함께 써야 하기 때문

        if (treeHeightSum % 3 == 0 && modOne <= modTwo ) bw.write("YES");
        else bw.write("NO");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
