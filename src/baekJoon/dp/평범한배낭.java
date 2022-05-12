package baekJoon.dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 평범한배낭 {

    private static int N, K;
    private static Item[] items;
    private static int[] bag;

    private static class Item{
        int weight, value;
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        items = new Item[N];
        bag = new int[K + 1];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for(int j = K; j >= weight; j--) {
                bag[j] = max(bag[j], bag[j - weight] + value);
                answer = max(answer , bag[j]);
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
