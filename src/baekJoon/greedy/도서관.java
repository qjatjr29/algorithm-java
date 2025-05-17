package baekJoon.greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 도서관 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int longest = 0;
        int answer = 0;
        boolean chk = false;

        input = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int position = Integer.parseInt(input.nextToken());
            if (position < 0) {
                longest = Math.max(longest, -1 * position);
                left.add(position * -1);
                continue;
            }
            longest = Math.max(longest, position);
            right.add(position);
        }

        Collections.sort(left);
        Collections.sort(right);

        for (int i = left.size() - 1; i >= 0; i -= m) {

            int length = left.get(i);
            if (length == longest && !chk) {
                answer += length;
                chk = true;
            }
            else answer += length * 2;
        }

        for (int i = right.size() - 1; i >= 0; i -= m) {
            int length = right.get(i);
            if (length == longest && !chk) {
                answer += length;
                chk = true;
            }
            else answer += length * 2;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
        // 39 + 29 * 2 + 6 * 2 + 11 * 2
        // == 39 + 58 + 12 + 22 = 97 + 34 = 131
    }
}
