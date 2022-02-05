package baekJoon.dataStructure;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class 집합의표현1717 {

    static int n,m;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] parent;

    public static void setParent(int a,int b)
    {
        int preA=findParent(a);
        int preB=findParent(b);
        if(preA <= preB)
        {
            parent[preB] = preA;
        }
        else parent[preA] = preB;
    }
    public static int findParent(int index)
    {
        if(parent[index]==index) return index;
        return parent[index] = findParent(parent[index]);
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            parent[i]=i;
        }

        for(int i=0;i<m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());

            if(cmd == 0)
            {
                setParent(number1,number2);
            }
            else if(cmd==1)
            {
                int parent1 = findParent(number1);
                int parent2 = findParent(number2);
                if(parent1 == parent2)
                {
                    bw.write("YES");
                    bw.newLine();
                }
                else
                {
                    bw.write("NO");
                    bw.newLine();
                }
            }
        }
        bw.flush();
        bw.close();

    }
}
