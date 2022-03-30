package baekJoon.dataStructure;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());
        for(int testcase = 0; testcase < T; testcase++){
            st = new StringTokenizer(br.readLine());
            TreeMap<Integer,Integer> tm = new TreeMap<>();
            int k = Integer.parseInt(st.nextToken());

            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if(tm.isEmpty() && cmd.equals("D"))continue;

                if(cmd.equals("I")){
                    tm.put(number,tm.getOrDefault(number,0)+1);
                    continue;
                }
                int removeNumber;
                if(number == -1){
                    removeNumber = tm.firstKey();
                }else removeNumber = tm.lastKey();
                if(tm.put(removeNumber,tm.get(removeNumber)-1)==1){
                    tm.remove(removeNumber);
                }


            }

            if(!tm.isEmpty()){
                bw.write(String.valueOf(tm.lastKey())+ " " + String.valueOf(tm.firstKey()));
                bw.newLine();
            }else{
                bw.write("EMPTY");
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
