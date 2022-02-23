package baekJoon.divideConquer;

import java.io.*;
import java.util.StringTokenizer;

public class 곱셈 {
    static Long A,B,C;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static Long dq(Long multi){

        if(multi == 1l )return A % C;
        Long next = dq(multi/2);

        if(multi %2 ==1) return (next * next) % C * A % C;
        return next * next % C;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());


        Long answer = dq(B) % C;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
