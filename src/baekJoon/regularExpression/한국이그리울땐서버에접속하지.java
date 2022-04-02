package baekJoon.regularExpression;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class 한국이그리울땐서버에접속하지 {
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        String[] splitStr = str.split("\\*");
        String pattern = "^" + splitStr[0] + "[a-z]*" +splitStr[1] +"$";

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String cmp= st.nextToken();
            if(Pattern.matches(pattern,cmp)) bw.write("DA");
            else bw.write("NE");
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }
}
