package baekJoon.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 가계부Hard {

    private static final int ADD = 1;
    private static final int PRINT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int livingDay = Integer.parseInt(input.nextToken());
        int queryCount = Integer.parseInt(input.nextToken());

        int height = (int) Math.ceil(Math.log(livingDay) / Math.log(2));
        long[] accountBook = new long[(int) Math.pow(2, height + 1)];

        for(int i = 0; i < queryCount; i++) {
            input = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(input.nextToken());

            if(command == ADD) {
                int day = Integer.parseInt(input.nextToken());
                int balance = Integer.parseInt(input.nextToken());
                update(1, day, balance, 1, livingDay, accountBook);
            }

            else if(command == PRINT) {
                int startDay = Integer.parseInt(input.nextToken());
                int endDay = Integer.parseInt(input.nextToken());
                long result = sum(1, 1, livingDay, startDay, endDay, accountBook);
                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void update(int node, int index, int balance, int start, int end, long[] accountBook) {

        if(start > index || end < index) return;

        accountBook[node] += balance;

        if(start == end) return;

        int mid = (start + end) / 2;

        update(node * 2, index, balance, start, mid, accountBook);
        update(node * 2 + 1, index, balance, mid + 1, end, accountBook);
    }

    private static long sum(int node, int start, int end, int left, int right, long[] accountBook) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return accountBook[node];

        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right, accountBook) +
            sum(node * 2 + 1, mid + 1, end, left, right, accountBook);
    }

}
