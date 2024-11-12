package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 카드섞기 {

    private static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] orders = new int[N];
        cards = new int[N * 2];

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(input.nextToken());
        }

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            orders[i] = Integer.parseInt(input.nextToken());
        }

        int shuffleCount = 0;
        while(true) {

            if(shuffleCount == 1e7) {
                break;
            }

            if(isAnswer()) {
                break;
            }
            shuffle(orders);
            shuffleCount++;
        }
        if(shuffleCount == 1e7) {
            shuffleCount = -1;
        }
        bw.write(String.valueOf(shuffleCount));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void shuffle(int[] shuffleOrder) {
        int size = shuffleOrder.length;
        for(int i = 0; i < size; i++) {
            cards[size + shuffleOrder[i]] = cards[i];
        }
        for(int i = 0; i < size; i++) {
            cards[i] = cards[size + i];
        }
    }

    private static boolean isAnswer() {
        for(int i = 0; i < cards.length / 2; i++) {
            if(cards[i] != i % 3) {
                return false;
            }
        }
        return true;
    }
}
