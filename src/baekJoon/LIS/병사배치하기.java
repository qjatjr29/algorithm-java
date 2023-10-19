package baekJoon.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 병사배치하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int totalSize = Integer.parseInt(input.nextToken());
        int[] soldiers = new int[totalSize];
        List<Integer> dp = new ArrayList<>();

        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < totalSize; i++) {
            soldiers[i] = Integer.parseInt(input.nextToken());
        }

        dp.add(soldiers[0]);

        for(int i = 1; i < totalSize; i++) {

            int combatPower = soldiers[i];

            if(dp.get(dp.size() - 1) > combatPower) dp.add(combatPower);
            else {
                int pos = lowerBound(dp, combatPower);
                dp.set(pos, combatPower);
            }
        }

        int answer = totalSize - dp.size();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lowerBound(List<Integer> arr, int target){
        int start = 0;
        int end = arr.size();
        while(start < end) {
            int mid = (start + end) / 2;
            if(arr.get(mid) <= target) {
                end = mid;
            }
            else start = mid + 1;
        }
        return end;
    }

}
