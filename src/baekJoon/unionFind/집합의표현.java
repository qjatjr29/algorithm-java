package baekJoon.unionFind;

import java.io.*;
import java.util.StringTokenizer;

public class 집합의표현
{

    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,m;
    // 트리의 높이 -> 누가 더 높게 있나.
    static int[] rank;
    // 자신의 부모..? -> 집합 파악.
    static int[] root;
    public static void setUnion()
    {
        rank = new int[n+1];
        root = new int[n+1];
        for(int i=0;i<=n;i++)
        {
            rank[i] = 0;
            root[i] = i;
        }
    }
    public static int find(int x)
    {
        if(root[x] == x) return x;
        return root[x] = find(root[x]);
    }
    public static void union(int x, int y)
    {
        x = find(x);
        y = find(y);

        // 같은 집합인경우
        // 안 합쳐도 이미 합쳐져 있음.
        if(x==y)return ;

        // 같은 집합이 아닌데 합칠 때

        // 항상 높이가 낮은 노드를
        // 높이가 높은 노드 아래로 넣는다.
        if(rank[x] < rank[y]) root[x] = y;
        else
        {
            root[y] = x;
            // 트리의 높이가 같은 경우. 합치고 높이 +1
            if(rank[x] == rank[y] ) rank[x]++;
        }

    }

    public static void main(String[] args) throws IOException
    {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int cmd,a,b;
        setUnion();
        for(int i=0;i<m;i++)
        {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(cmd == 1)
            {
                int firstV = find(a);
                int secondV = find(b);
                if(firstV == secondV)
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
            else
            {
                union(a,b);
            }

        }

        bw.flush();
        bw.close();
    }
}
