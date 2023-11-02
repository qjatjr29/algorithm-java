package baekJoon.graph.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Arrays;
import java.util.StringTokenizer;

public class 뮤탈리스크 {

    private static final int[][] mutaliskAttack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};

    private static boolean[][][] checked;
    private static int attackCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int scvCount = Integer.parseInt(input.nextToken());
        attackCount = Integer.MAX_VALUE;

        int[] scv = new int[3];
        checked = new boolean[61][61][61];

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < scvCount; i++) {
            scv[i] = Integer.parseInt(input.nextToken());
        }
        dfs(scv, 0);
        bw.write(String.valueOf(attackCount));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int[] scv, int count) {

        if(attackCount <= count) return;
        int scv1 = scv[0];
        int scv2 = scv[1];
        int scv3 = scv[2];

        if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
            attackCount = Math.min(attackCount, count);
            return;
        }

        int[] sortArray = {scv1, scv2, scv3};
        Arrays.sort(sortArray);
        scv3 = sortArray[0];
        scv2 = sortArray[1];
        scv1 = sortArray[2];

        if(checked[scv1][scv2][scv3]) return;
        checked[scv1][scv2][scv3] = true;

        for (int[] attackDamage : mutaliskAttack) {

            int[] nextScv = new int[] {
                    Math.max(scv1 - attackDamage[0], 0),
                    Math.max(scv2 - attackDamage[1], 0),
                    Math.max(scv3 - attackDamage[2], 0)
            };
            dfs(nextScv, count + 1);
        }

    }
}
