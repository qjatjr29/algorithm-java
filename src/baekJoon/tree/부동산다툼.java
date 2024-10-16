package baekJoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 부동산다툼 {

    private static final int SUCCESS = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int Q = Integer.parseInt(input.nextToken());
        int[] tree = new int[N + 1];

        for(int i = 0; i < Q; i++) {
            input = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(input.nextToken());
            int res = check(number, tree);
            if(res == -1) {
                bw.write(String.valueOf(SUCCESS));
            }
            else bw.write(String.valueOf(res));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int check(int number, int[] tree) {

        int res = -1;
        int start = number;
        while(true) {
            if(number == 0) {
                break;
            }
            if(tree[number] == 1) {
                res = number;
            }

            if(number % 2 == 0) {
                number = number / 2;
            }
            else number = (number - 1) / 2;
        }

        if(res == -1) {
            tree[start] = 1;
        }
        return res;
    }
}
