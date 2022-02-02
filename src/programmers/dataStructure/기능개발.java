package programmers.dataStructure;

import java.io.*;
import java.util.*;

public class 기능개발 {

    //static int progresses[];
    //static int speeds[];


    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static ArrayList<Integer> solution(int[] progresses,int[] speeds) throws IOException {
        ArrayList<Integer> answer= new ArrayList<>();

        Queue<Integer> works = new LinkedList<>();
        for(int i=0;i<progresses.length;i++)
        {
            works.add(i);
        }
        while(true)
        {
            int count=1;
            int idx = 0;
            if(works.isEmpty())break;

            // 현재 작업을 먼저 처리해야 하는 인덱스
            idx = works.peek();
            int restWork=100-progresses[idx];
            int day = restWork/speeds[idx];
            if(restWork % speeds[idx] !=0 )day++;
            //System.out.println("day : " +day);
            works.poll();

            Boolean check = false;
            int size= works.size();

            while(true)
            {
                if(size<=0) break;
                int qIdx = works.peek();
                progresses[qIdx] += (day * speeds[qIdx]);
                if(!check && progresses[qIdx]>=100)
                {
                    count++;
                    works.poll();
                    size--;
                    continue;
                }
                else if(!check && progresses[qIdx]<100)
                {
                    check=true;
                }
                int temp=qIdx;
                works.poll();
                works.add(temp);
                size--;
            }
            answer.add(count);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        //StringTokenizer st = new StringTokenizer(br.readLine());

        int[] progresses = {5,5,5};
        int[] speeds ={21,25,20};
        ArrayList<Integer> ans=solution(progresses,speeds);
        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }


}
