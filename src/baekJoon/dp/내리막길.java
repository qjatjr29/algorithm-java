package baekJoon.dp;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 내리막길 {
    static int M,N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] map;
    static int[][] dp;
    static int[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static class Pair implements Comparable<Pair>{
        int cost;
        Coord c;

        public Pair(int i, Coord coord) {
            cost = i;
            c = coord;
        }


        @Override
        public int compareTo(Pair o) {
            return this.cost <= o.cost ? 1 : -1;
        }
    }
    public static class Coord{
        int x,y;
        Coord(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void sol()
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(map[1][1],new Coord(1,1)));
        visited[1][1]=1;
        while(true)
        {
            if(pq.isEmpty()) break;

            int here = pq.peek().cost;
            int cx = pq.peek().c.x;
            int cy = pq.peek().c.y;
            pq.remove();
            for(int i=0;i<4;i++)
            {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<=0 || ny<=0 || nx>M || ny>N)continue;
                if(here < map[nx][ny])
                {
                    dp[cx][cy] += dp[nx][ny];
                }

                if(here > map[nx][ny] && visited[nx][ny]==0)
                {
                    Coord next = new Coord(nx,ny);
                    pq.add(new Pair(map[nx][ny],next));
                    visited[nx][ny] = 1;
                }
            }
            //System.out.println("x , yy : "+cx +" "+cy+" => "+dp[cx][cy]);
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        dp = new int[M+1][N+1];
        visited = new int[M+1][N+1];
        for(int i=1;i<=M;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = 1;
        for(int i=0;i<=M;i++)
        {
            map[i][0] = Integer.MAX_VALUE;
        }
        for(int i=0;i<=N;i++)
        {
            map[0][i] = Integer.MAX_VALUE;
        }
        sol();

        bw.write(String.valueOf(dp[M][N]));
        bw.newLine();
        bw.flush();
        bw.close();

    }

}
