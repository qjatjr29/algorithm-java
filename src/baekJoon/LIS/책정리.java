package baekJoon.LIS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 책정리 {

    static int[] arr;
    static List<Integer> dp = new ArrayList<>();
    static void sol(int N) {
        dp.add(arr[1]);
        for(int i = 2; i <= N; i++) {
            if(dp.get(dp.size() - 1) < arr[i] ){
                dp.add(arr[i]);
            }
            else {
                int idx = lowerBound(dp,arr[i]);
                dp.set(idx,arr[i]);
            }
        }
    }
    private static int lowerBound(List<Integer> arr, int target){
        int start = 0;
        int end = arr.size();
        while(start < end){
            int mid = (start + end) / 2;
            if(arr.get(mid) >= target){
                end = mid;
            }
            else start = mid + 1;
        }
        return end;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sol(N);
        int answer = N - dp.size();
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }

}
