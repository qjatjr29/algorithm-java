package baekJoon.divideConquer;

import java.io.*;
import java.util.StringTokenizer;

public class 행렬제곱 {
    static int N;
    static Long B;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int matrixs[][];
    static int answer[][];
    public static int[][] multiply(int matrix1[][],int matrix2[][])
    {
        int result[][] = new int[N][N];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                for(int k=0;k<N;k++)
                {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }
    public static int[][] divideConquer(Long size)
    {
        if(size==1) return matrixs;
        int temp[][];
        if(size%2==0)
        {
            temp=divideConquer(size/2);
            return multiply(temp,temp);
        }
        else
        {
            temp=divideConquer(size-1);
            return multiply(temp,matrixs);
        }
    }
    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        B=Long.parseLong(st.nextToken());

        matrixs = new int [N][N];
        answer = new int [N][N];

        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                matrixs[i][j]=Integer.parseInt(st.nextToken());
                answer[i][j]=matrixs[i][j];
            }
        }
        answer = divideConquer(B);
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                bw.write(answer[i][j] % 1000 + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }
}
