package programmers.sort.K번째수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.sort;

public class Main {
    static int arr[];
    static int commands[];

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public List<Integer> solution(int[] array, int[][] commands)
    {
        List<Integer> answer=new ArrayList<>();
        for(int i=0;i<commands.length;i++)
        {
            int start = commands[i][0];
            int end = commands[i][1];
            int select = commands[i][2];
            int temp[] = new int[end-start+1];
            int idx=0;
            for(int j=start-1;j<end;j++)
            {
                temp[idx]=array[j];
                idx++;
            }
            sort(temp);
            answer.add(temp[select-1]);
        }
        return answer;
    }
    public static void main(String[] args) {

    }

}
