package programmers.bruteForce;

import com.sun.source.tree.LiteralTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class 모의고사 {
    static int[] answers;
    static int[] thirds = {3,1,2,4,5};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int chkSolution(int index,int[] arr)
    {
        int count = 0;
        int number;
        if(index == 1)
        {
            number = 1;
            for(int i=0;i<arr.length;i++)
            {
                if(number == arr[i]) count++;
                number++ ;
                if(number == 6 ) number = 1;
            }
        }
        else if(index == 2)
        {
            number = 1;
            for(int i=0;i<arr.length;i++)
            {
                if(i % 2 ==0)
                {
                    if(2 == arr[i])count++;
                }
                else
                {
                    if(number == arr[i]) count++;
                    number++;
                    if(number==2)number=3;
                    if(number == 6 ) number = 1;
                }
            }
        }
        else if(index == 3)
        {
            number = 0;
            int chk = 1;
            for(int i=0;i<arr.length;i++)
            {
                if(thirds[number] == arr[i])count++;
                if(chk == 2)
                {
                    chk = 1;
                    number = (number+1) % 5;
                }
                else chk++;
            }
        }
        return count;
    }
    public static List<Integer> solution(int[] answers)
    {
        List<Integer> answer = new ArrayList<>();
        int max = 0;
        for(int i=1;i<=3;i++)
        {
            int correct = chkSolution(i,answers);
            if(correct > max)
            {
                max = correct;
                answer.clear();
                answer.add(i);
            }
            else if(correct==max)
            {
                answer.add(i);
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> result;

        int[] answers={1,3,2,4,2};
        //int[] answers={1,2,3,4,5};
        result = solution(answers);

        for(int i=0;i<result.size();i++)
        {
            bw.write(String.valueOf(result.get(i))+" ");
        }
        bw.newLine();
        bw.flush();
        bw.close();

    }
}
