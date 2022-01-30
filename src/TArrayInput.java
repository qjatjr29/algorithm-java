import java.io.*;
import java.util.StringTokenizer;

public class TArrayInput {
    static int N,M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer st= new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N= Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                bw.write(String.valueOf(arr[i][j]));
                bw.write(" ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();


    }
}
