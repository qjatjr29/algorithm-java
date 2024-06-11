package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 이전순열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] numbers = new int[N];

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(input.nextToken());
        }

        if(checkPrevPermutation(numbers)) {
            for(int i = 0; i < N; i++) {
                bw.write(String.valueOf(numbers[i]) + " ");
            }
        }
        else {
            bw.write("-1");
        }

        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean checkPrevPermutation(int[] numbers) {

        int point = numbers.length - 1;

        while(point > 0 && numbers[point - 1] <= numbers[point]) {
            point--;
        }
        if(point <= 0) {
            return false;
        }

        int i = numbers.length - 1;

        while(numbers[i] >= numbers[point - 1]){
            i--;
        }

        swap(numbers, point - 1, i);

        i = numbers.length - 1;
        while(point < i) {
            swap(numbers, point, i);
            point++;
            i--;
        }
        return true;
    }

    private static void swap(int[] numbers, int x, int y) {
        int temp = numbers[x];
        numbers[x] = numbers[y];
        numbers[y] = temp;
    }
}
