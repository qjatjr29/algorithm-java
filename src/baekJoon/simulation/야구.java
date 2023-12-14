package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 17281
public class 야구 {

    private static int answer = 0;
    private static final int OUT = 0;
    private static int innings;
    private static int[][] results;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        innings = Integer.parseInt(input.nextToken());
        results = new int[innings][9];

        for(int i = 0; i < innings; i++) {
            input = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                results[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        boolean[] checked = new boolean[9];
        int[] order = new int[9];
        selectOrder(0, checked, order);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }


    private static void play(int[] order) {
        int score = 0;
        int fielder = 0;

        for(int inning = 0; inning < innings; inning++) {
            // 0 : 홈, 1 : 1루, 2 : 2루, 3 : 3루
            boolean[] base = new boolean[4];
            int outCount = 0;

            while(outCount < 3) {
                base[0] = true;
                int move = results[inning][order[fielder++]];
                if(fielder == 9) fielder = 0;
                if(move == OUT) {
                    outCount++;
                    continue;
                }

                for(int i = 3; i >= 0; i--) {
                    if(!base[i]) continue;

                    if(i + move >= 4) score++;
                    else base[i + move] = true;
                    base[i] = false;
                }
            }
        }
        answer = Math.max(answer, score);
    }

    private static void selectOrder(int count, boolean[] checked, int[] order) {
        if(count == 9) {
            play(order);
            return;
        }

        // 항상 4번타자는 1번 플레이어야한다.
        if(count == 3) {
            selectOrder(count + 1, checked, order);
            return;
        }

        for(int i = 1; i < 9; i++) {
            if(checked[i]) continue;
            order[count] = i;
            checked[i] = true;
            selectOrder(count + 1, checked, order);
            checked[i] = false;
        }
    }


}
