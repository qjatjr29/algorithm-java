package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class zero만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 0; testcase < T; testcase++) {
            input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());

            List<String> result = new ArrayList<>();
            dfs(1, 1, N, 0, new char[N + 1], result);

            for (String res : result) {
                bw.write(res);
                bw.newLine();
            }
            if(testcase == T - 1) {
                break;
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int prev, int n, int res, char[] oper, List<String> result) {

        if(index == n) {
            if(res + prev == 0) {
                StringBuilder sb = new StringBuilder();
                for(int i = 1; i < n; i++) {
                    sb.append(i);
                    sb.append(oper[i]);
                }
                sb.append(n);
                result.add(sb.toString());
            }
            return;
        }

        // " "
        oper[index] = ' ';
        int nextPrev = prev;
        if(prev < 0) {
            nextPrev = prev * 10 - (index + 1);
        }
        else {
            nextPrev = prev * 10 + (index + 1);
        }
        dfs(index + 1, nextPrev, n, res, oper, result);

        // "+"
        oper[index] = '+';
        dfs(index + 1, index + 1, n, res + prev, oper, result);

        // "-"
        oper[index] = '-';
        dfs(index + 1, -1 * (index + 1), n, res + prev, oper, result);
    }

}