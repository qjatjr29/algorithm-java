package sw마에스트로;

import java.io.*;
import java.util.StringTokenizer;

public class 메시지복호화 {
    static String s;
    static Integer a,b;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String answer;
    public static void sol(char c)
    {
        int next = Integer.valueOf(c);

        next -= 97;
        next -= b;
        if(next<0)
        {
            next += 26;
        }
        while(true)
        {
            if(next % a ==0)
            {
               next = next / a;
               break;
            }
            next += 26;
        }
        next += 97;
        char nextChar = (char) next;
        answer+=nextChar;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        s = st.nextToken();
        answer = "";
       // System.out.println(Integer.valueOf('z'));

        for(int i=0;i<s.length();i++)
        {
            sol(s.charAt(i));
        }
        bw.write(answer);
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
