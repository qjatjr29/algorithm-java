package KDT웹데브코스;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class 기차 {
    static int n;
    static int[][] dp;
    static ArrayList<Integer>[] station;
    public static void dfs(int index,int parent,int[] passenger)
    {
        dp[index][0] = passenger[index-1];
        dp[index][1] = index;
        int count = 0;
        int idx = index;


        for(int i=0;i<station[index].size();i++)
        {
            int nIdx = station[index].get(i);
            if(nIdx == parent) continue;

            dfs(nIdx,index,passenger);

            if(count<=dp[nIdx][0])
            {
                idx = nIdx;
                count = dp[nIdx][0];
            }
        }
        dp[index][0] += count;
        dp[index][1] = dp[idx][1];

    }
    public static int[] solution(int n, int[] passenger, int[][] train) {
        // 역 번호,  이용객 수
        int[] answer = new int[2];
        answer[1] = passenger[0];
        answer[0] = 1;

        dp = new int[n+1][2];

        station = new ArrayList[n+1];

        for(int i=0;i<n+1;i++)
        {
            station[i] = new ArrayList<>();
        }
        // 역들의 연결 정보 저장.
        for(int i=0;i<train.length;i++)
        {
            int node1 = train[i][0];
            int node2 = train[i][1];

            station[node1].add(node2);
            station[node2].add(node1);
        }

        dfs(1,-1,passenger);
        answer[0] = dp[1][1];
        answer[1] = dp[1][0];

        return answer;
    }
    public static void main(String[] args) {
        int [] ans = new int[2];

        int[] pass ={1,1,1,1,1,1};
        int[][] trains = new int[5][2];

        trains[0][0] =1;
        trains[0][1] =2;
        trains[1][0] =1;
        trains[1][1] =3;
        trains[2][0] =1;
        trains[2][1] =4;
        trains[3][0] =3;
        trains[3][1] =5;
        trains[4][0] =3;
        trains[4][1] =6;

        ans = solution(6,pass,trains);
        System.out.println(ans[0]+" "+ans[1]);

    }

}
