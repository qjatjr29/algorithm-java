package baekJoon.tree;

import java.io.*;
import java.util.StringTokenizer;

public class 가장가까운공통조상3584 {
    static int T,N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parent;

    public static int findParent(int node)
    {
        return parent[node];
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int testcase=0;testcase<T;testcase++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            parent = new int[N+1];

            for(int i=1;i<=N;i++)
            {
                parent[i]=i;
            }
            for(int i=0;i<N-1;i++)
            {
                st = new StringTokenizer(br.readLine());
                int pNode , cNode;
                pNode = Integer.parseInt(st.nextToken());
                cNode = Integer.parseInt(st.nextToken());
                parent[cNode] = pNode;
            }
            int node1,node2,temp;
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            boolean chk = false;
            while(true)
            {
                if(node1==findParent(node1))
                {
                    break;
                }
                temp = node2;
                while(true)
                {
                    if(temp==findParent(temp)) break;
                    if(temp == node1)
                    {
                        break;
                    }
                    temp = findParent(temp);
                }
                if(temp == node1)
                {
                    bw.write(String.valueOf(node1));
                    bw.newLine();
                    chk=true;
                    break;
                }
                node1= findParent(node1);
            }
            if(!chk)
            {
                bw.write(String.valueOf(node1));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
