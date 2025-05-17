package baekJoon.greedy;

import java.io.*;
import java.util.*;

public class 책나눠주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(input.nextToken());
        for (int testcase = 0; testcase < t; testcase++) {
            input = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input.nextToken());
            int m = Integer.parseInt(input.nextToken());

            BookRequest[] requests = new BookRequest[m + 1];
            boolean[] used = new boolean[n + 1];

            for (int i = 1; i <= m; i++) {
                input = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(input.nextToken());
                int b = Integer.parseInt(input.nextToken());
                requests[i] = new BookRequest(a, b);
            }

            Arrays.sort(requests, 1, m + 1, (a, b) -> {
                if (a.endNumber == b.endNumber) {
                    return a.startNumber - b.startNumber;
                }
                return a.endNumber - b.endNumber;
            });

            int answer = 0;
            for (int i = 1; i <= m; i++) {

                // 학생 요청
                BookRequest request = requests[i];
                int start = request.startNumber;
                int end = request.endNumber;

                for (int j = start; j <= end; j++) {
                    if (used[j]) continue;
                    // 사용하지 않은 책 번호인 경우
                    used[j] = true;
                    answer++;
                    break;
                }
            }

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class BookRequest {
        int startNumber;
        int endNumber;

        public BookRequest(int startNumber, int endNumber) {
            this.startNumber = startNumber;
            this.endNumber = endNumber;
        }
    }
}
