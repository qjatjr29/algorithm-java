package baekJoon.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N과M2 {

    private static List<List<Integer>> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        answer = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            dfs(N, i, M, temp);
        }

        for(List<Integer> ans : answer) {
            for(int number : ans) {
                bw.write(String.valueOf(number) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int size, int number, int limit, List<Integer> numbers) {

        if(numbers.size() == limit) {
            List<Integer> temp = new ArrayList<>(numbers);
            answer.add(temp);
            return;
        }

        for(int i = number + 1; i <= size; i++) {
            numbers.add(i);
            dfs(size, i, limit, numbers);
            numbers.remove(Integer.valueOf(i));
        }
    }
}
