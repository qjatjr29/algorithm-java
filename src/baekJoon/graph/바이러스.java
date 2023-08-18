package baekJoon.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 바이러스 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int computerCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int connectCnt = Integer.parseInt(st.nextToken());

        List<Integer>[] connects = new List[computerCnt + 1];

        for(int i = 1; i <= computerCnt; i++) connects[i] = new ArrayList<Integer>();

        for(int i = 0; i < connectCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            connects[c1].add(c2);
            connects[c2].add(c1);
        }

        int answer = virusSpread(1, connects);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static int virusSpread(int start, List<Integer>[] connects) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[connects.length];
        int rtn = 0;

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int here = queue.poll();

            if(here != start) rtn += 1;

            for(int i = 0; i < connects[here].size(); i++) {
                int next = connects[here].get(i);
                if(visited[next]) continue;
                visited[next] = true;
                queue.add(next);
            }
        }

        return rtn;
    }

}
