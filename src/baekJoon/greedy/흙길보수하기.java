package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 흙길보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int L = Integer.parseInt(input.nextToken());
        List<int[]> pool = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());
            pool.add(new int[] {start, end});
        }

        int count = 0;
        if(!pool.isEmpty()) {
            pool.sort((a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });

            int start = pool.get(0)[0];
            int end = pool.get(0)[1];

            for(int i = 1; i < N; i++) {
                int[] cPool = pool.get(i);
                int cStart = cPool[0];
                int cEnd = cPool[1];

                int length = end - start;

                if(length % L != 0) {
                    int remainLength = start + (length / L) * L;
                    if(remainLength + L >= cStart) {
                        end = cEnd;
                        continue;
                    }
                }

                count += (int) Math.ceil((double) length / L);
                start = cStart;
                end = cEnd;
            }

            int length = end - start;
            count += (int) Math.ceil((double) length / L);
        }

        bw.write(String.valueOf(count));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
}
