package baekJoon.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 열차정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int[] cars = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            input = new StringTokenizer(br.readLine());
            cars[i] = Integer.parseInt(input.nextToken());
        }

        int answer = setTrain(cars);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int setTrain(int[] cars) {
        int result = 0;

        for(int i = 1; i < cars.length; i++) {
            int lds = getLdsSize(i, cars);
            int lis = getLisSize(i, cars);
            result = Math.max(result, lds + lis - 1);
        }
        return result;
    }

    private static int getLisSize(int start, int[] cars) {
        List<Integer> lis = new ArrayList<>();
        lis.add(cars[start]);

        for(int i = start + 1; i < cars.length; i++) {

            int weight = cars[i];

            if(lis.get(lis.size() - 1) < weight) {
                lis.add(weight);
                continue;
            }

            int index = lisLowerBound(lis, weight);
            if(index == 0) {
                continue;
            }
            lis.set(index, weight);
        }
        return lis.size();
    }

    private static int getLdsSize(int start, int[] cars) {
        List<Integer> lds = new ArrayList<>();
        lds.add(cars[start]);

        for(int i = start + 1; i < cars.length; i++) {

            int weight = cars[i];

            if(lds.get(lds.size() - 1) > weight) {
                lds.add(weight);
                continue;
            }

            int index = ldsLowerBound(lds, weight);
            if(index == 0) {
                continue;
            }
            lds.set(index, weight);
        }
        return lds.size();
    }

    private static int lisLowerBound(List<Integer> dp, int targetNumber) {
        int start = 0;
        int end = dp.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (dp.get(mid) >= targetNumber) {
                end = mid;
                continue;
            }
            start = mid + 1;
        }
        return end;
    }

    private static int ldsLowerBound(List<Integer> dp, int targetNumber) {
        int start = 0;
        int end = dp.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (dp.get(mid) <= targetNumber) {
                end = mid;
                continue;
            }
            start = mid + 1;
        }
        return end;
    }

}


