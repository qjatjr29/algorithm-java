package programmers.문자열;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class 문자열압축 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
    public static int solution(String s){

        int size = s.length();
        int minChk=987654321;
        if(size == 1) return 1;

        for(int i=1;i<=size/2;i++) {
            String str = "";
            String cmp = "";
            int count = 1;
            for (int j = 0; j < size / i; j++) {
                if(cmp.equals(s.substring(j*i,(j*i)+i)))
                {
                    count++;
                    continue;
                }
                if(count>1){
                    str+=count+cmp;
                    count=1;
                }
                else str+=cmp;
                cmp=s.substring(i*j,(i*j)+i);
            }
            if(count>1)
            {
                str+= count + cmp;
                count=1;
            }else
            {
                str+=cmp;
            }
            if(s.length()%i!=0)
            {
                str+=s.substring(s.length()-s.length()%i , s.length());
            }
            minChk=min(minChk,str.length());
        }
        return minChk;
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st= new StringTokenizer(br.readLine());
        String s= st.nextToken();
        int result = solution(s);
        bw.write(String.valueOf(result) + '\n');
        bw.flush();
        bw.close();
    }
}
