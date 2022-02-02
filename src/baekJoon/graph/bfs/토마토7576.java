package baekJoon.graph.bfs;

import java.io.*;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토7576 {
    static int N,M,number,day;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] tomatoes;
    static boolean[][] visited;
    static Queue<Pair> problems = new LinkedList<>();
    static class Pair{
        int x,y,days;
        Pair(int x, int y,int days)
        {
            this.x = x;
            this.y = y;
            this.days=days;
        }
    }
    public static boolean chk(int x,int y)
    {
        if(x<0 || x>=M || y<0 || y>=N) return false;
        else if(visited[x][y]==true) return false;
        else if(tomatoes[x][y]==-1) return false;
        return true;
    }
    public static void bfs()
    {
        while(true)
        {
            if(problems.isEmpty()) break;
            int cx = problems.peek().x;
            int cy =problems.peek().y;
            int days=problems.peek().days;
            problems.poll();
            for(int i=0;i<4;i++)
            {
                int nx = cx + dx[i];
                int ny = cy + + dy[i];
                if(chk(nx,ny))
                {
                    number--;
                    problems.add(new Pair(nx,ny,days+1));
                    day = days + 1 ;
                    visited[nx][ny]=true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        tomatoes = new int[M][N];
        visited = new boolean[M][N];

        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                tomatoes[i][j]=Integer.parseInt(st.nextToken());
                if(tomatoes[i][j]==1)
                {
                    problems.add(new Pair(i,j,0));
                    visited[i][j]=true;
                }
                else if(tomatoes[i][j]==0)
                {
                    number++;
                }
            }
        }

        bfs();
        if(number>0)
        {
            bw.write("-1");
            bw.newLine();
            bw.flush();
            bw.close();
        }
        else
        {
            bw.write(String.valueOf(day));
            bw.newLine();
            bw.flush();
            bw.close();
        }


    }
}
