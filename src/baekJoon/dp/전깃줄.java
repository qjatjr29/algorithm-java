package baekJoon.dp;

import java.io.*;
import java.util.*;

public class 전깃줄 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int lines = Integer.parseInt(input.nextToken());
        PriorityQueue<int[]> lineQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < lines; i++) {
            input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            lineQueue.add(new int[]{a, b});
        }

        int answer = lines - makeLine(lineQueue);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int makeLine(PriorityQueue<int[]> lineQueue) {

        List<Integer> lineList = new ArrayList<>();

        while (!lineQueue.isEmpty()) {

            int[] line = lineQueue.poll();

            if (lineList.isEmpty()) {
                lineList.add(line[1]);
                continue;
            }
            int l = line[1];

            if (lineList.get(lineList.size() - 1) < l) {
                lineList.add(l);
                continue;
            }

            int i = Collections.binarySearch(lineList, l);
            if (i < 0) i = -i - 1;
            lineList.set(i, l);
        }

        return lineList.size();
    }
}
