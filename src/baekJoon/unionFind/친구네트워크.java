package baekJoon.unionFind;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 친구네트워크 {

    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T;
    static int F;
    static int[] root;
    static int[] friendCount;
    static int[] rank;
    static int MAX = 200001;
    static Map<String,Integer> friends = new HashMap<>();


    static void setUnion()
    {
        friends.clear();
        root = new int[MAX];
        rank = new int[MAX];
        friendCount = new int[MAX];
        for(int i=0;i<MAX;i++)
        {
            friendCount[i] = 1;
            root[i] = i;
            rank[i] = 0;
        }
    }
    static int find(int x)
    {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if(x==y) return;

        if(rank[x] < rank[y]) root[x] = y;
        else
        {
            root[y] =x;
            if(rank[x]==rank[y]) rank[x] ++;
        }

    }
    static int findCount(int x,int y)
    {
        x = find(x);
        y = find(y);
        if(x != y)
        {
            root[y] =x;
            friendCount[x] += friendCount[y];
            friendCount[y] = 1;
        }
        return friendCount[x];
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int testcase = 0; testcase<T;testcase++)
        {
            st = new StringTokenizer(br.readLine());
            F = Integer.parseInt(st.nextToken());
            setUnion();
            String friend1,friend2;
            for(int i=0;i<F;i++) {
                st = new StringTokenizer(br.readLine());
                friend1 = st.nextToken();
                friend2 = st.nextToken();
                if (friends.get(friend1) == null) {
                    friends.put(friend1, friends.size());
                }
                if (friends.get(friend2) == null) {
                    friends.put(friend2, friends.size());
                }
//                System.out.println(friends.get(friend1));
//                System.out.println(friends.get(friend2));
                bw.write(String.valueOf(findCount(friends.get(friend1), friends.get(friend2))));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
