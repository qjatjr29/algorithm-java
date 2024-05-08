package baekJoon.누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두배열의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        long T = Long.parseLong(input.nextToken());

        input = new StringTokenizer(br.readLine());
        int aCount = Integer.parseInt(input.nextToken());
        int[] A = new int[aCount];
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < aCount; i++) {
            A[i] = Integer.parseInt(input.nextToken());
        }

        input = new StringTokenizer(br.readLine());
        int bCount = Integer.parseInt(input.nextToken());

        int[] B = new int[bCount];
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < bCount; i++) {
            B[i] = Integer.parseInt(input.nextToken());
        }

        for(int i = 1; i < aCount; i++) {
            A[i] += A[i - 1];
        }

        for(int i = 1; i < bCount; i++) {
            B[i] += B[i - 1];
        }

        int aSize = aCount * (aCount + 1) / 2;
        int bSize = bCount * (bCount + 1) / 2;

        long[] aSum = new long[aSize];
        long[] bSum = new long[bSize];

        int idx = 0;
        for(int i = 0; i < aCount; i++) {
            for(int j = i; j < aCount; j++) {
                int value = A[j];
                if(i > 0) {
                    value -= A[i - 1];
                }
                aSum[idx++] = value;
            }
        }

        idx = 0;
        for(int i = 0; i < bCount; i++) {
            for(int j = i; j < bCount; j++) {
                int value = B[j];
                if(i > 0) {
                    value -= B[i - 1];
                }
                bSum[idx++] = value;
            }
        }

        Arrays.sort(aSum);
        Arrays.sort(bSum);

        long answer = 0;
        int left = 0;
        int right = bSize - 1;

        while(left < aSize && right >= 0) {

            long lValue = aSum[left];
            long rValue = bSum[right];
            long sum = lValue + rValue;

            if(sum == T) {

                // 같은 값이 있을 수 있다.
                long aCnt = 0;
                long bCnt = 0;
                while(left < aSize && lValue == aSum[left]) {
                    left++;
                    aCnt++;
                }

                while(right >= 0 && rValue == bSum[right]) {
                    right--;
                    bCnt++;
                }
                answer += (aCnt * bCnt);
            }

            if(sum > T) {
                right--;
            }

            if(sum < T) {
                left++;
            }
        }
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
