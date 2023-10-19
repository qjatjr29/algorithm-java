package baekJoon.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 민균이의계략 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(input.nextToken());
        int[] numbers = new int[count];
        List<Integer> sequence = new ArrayList<>();

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < count; i++) numbers[i] = Integer.parseInt(input.nextToken());

        sequence.add(numbers[0]);

        for(int i = 1; i < count; i++) {
            int number = numbers[i];

            if(sequence.get(sequence.size() - 1) < number) sequence.add(number);
            else {
                int pos = lowerBound(sequence, number);
                sequence.set(pos, number);
            }
        }

        int answer = sequence.size();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static int lowerBound(List<Integer> arr, int targetNumber) {
        int start = 0;
        int end = arr.size() - 1;

        while(start < end) {

            int mid = (start + end) / 2;
            int cmp = arr.get(mid);
            if(cmp >= targetNumber) end = mid;
            else start = mid + 1;
        }

        return end;
    }

}
