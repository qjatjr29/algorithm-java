package programmers.문자열;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 나라124의숫자 {
    static Long n;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Stack<Character> ans;

    public static StringBuilder solution(Long n){
        StringBuilder answer = new StringBuilder();
        // 맨 뒤의 숫자 - > n %3
        // 그 다음..?  ( n-1 / 3 ) %3
        while(true)
        {
            if(n==0) break;
            Long temp = n%3;
            if (temp == 0) {
                answer.append(4);
            } else if (temp == 1) {
                answer.append(1);
            } else if (temp == 2) {
                answer.append(2);
            }
            n = (n-1)/3;

        }
        answer.reverse();
        return answer;
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Long.parseLong(st.nextToken());
        sb = solution(n);
        bw.write(String.valueOf(sb));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
