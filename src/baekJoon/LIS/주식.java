package baekJoon.LIS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 주식 {

    static int[] arr;
    static List<Integer> dp = new ArrayList<>();

    private static void sol(int N){
        dp.add(arr[1]);
        for(int i = 2; i <= N; i++) {
            if(dp.get(dp.size() - 1) < arr[i]) dp.add(arr[i]);
            else {
                int idx = lowerBound(dp, arr[i]);
                dp.set(idx, arr[i]);
            }
        }
    }
    private static int lowerBound(List<Integer> arr, int target){
        int start = 0;
        int end = arr.size();
        while(start < end) {
            int mid = (start + end) / 2;
            if(arr.get(mid) >= target) {
                end = mid;
            }
            else start = mid + 1;
        }
        return end;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testcase = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= testcase; t++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            arr = new int[N+1];
            dp.clear();
            st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            sol(N);
            bw.write("Case #"+t);
            bw.newLine();
            if(dp.size() >= K){
                bw.write("1");
            }
            else bw.write("0");
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }
}
