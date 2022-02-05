package baekJoon.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.util.Collections.sort;

public class 세용액2473 {
    static int N;
    static Long answer;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Long[] result = new Long[3];
    static Long[] liquid;

    public static void findLiquid(int index)
    {
        int left = index+1;
        int right = liquid.length-1;

        while(true)
        {
            if(left >= right )break;
            long sum = liquid[left] + liquid[index] + liquid[right];
            long absNum = abs(sum);

            if(answer > absNum)
            {
                result[0] = liquid[index];
                result[1] = liquid[left];
                result[2] = liquid[right];
                answer = absNum;
            }
            // 합이 0보다 클 경우 뒤의 수를 더 작게 한다.
            if(sum > 0) right--;
            // 합이 0보다 작은 경우는 0에 가까워 지기 위해서 앞의 수를 크게 한다.
            else left++;
        }
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        liquid= new Long[N];
        st = new StringTokenizer(br.readLine());
        answer = 3000000001L;
        for(int i=0;i<N;i++)
        {
            liquid[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liquid);
        for(int i=0;i<N-1;i++)
        {
            findLiquid(i);
        }

        for(int i=0;i<3;i++)
        {
            bw.write(String.valueOf(result[i])+" ");
        }
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
