package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 사전순최대공통부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int aLength = Integer.parseInt(input.nextToken());
        List<Integer> arrA = new ArrayList<>();
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < aLength; i++) {
            arrA.add(Integer.parseInt(input.nextToken()));
        }

        input = new StringTokenizer(br.readLine());
        int bLength = Integer.parseInt(input.nextToken());
        List<Integer> arrB = new ArrayList<>();
        input = new StringTokenizer(br.readLine());
        for(int i = 0; i < bLength; i++) {
            arrB.add(Integer.parseInt(input.nextToken()));
        }

        // 두 수열의 공통되는 숫자 집합 구하기
        Set<Integer> commonNumbers = new TreeSet<>(arrA);
        commonNumbers.retainAll(arrB);

        // 공통되는 숫자가 없으면 0 출력 후 종료
        if (commonNumbers.isEmpty()) {
            bw.write("0");
            bw.newLine();
        }

        else {
            List<Integer> result = new ArrayList<>();

            while (!commonNumbers.isEmpty()) {
                // 공통 숫자들 중 최대값 찾기
                int maxVal = ((TreeSet<Integer>) commonNumbers).pollLast();
                result.add(maxVal);

                // 각각의 A와 B 수열에서 최대값 이후의 부분 수열로 업데이트
                arrA = arrA.subList(arrA.indexOf(maxVal) + 1, arrA.size());
                arrB = arrB.subList(arrB.indexOf(maxVal) + 1, arrB.size());

                // 새로운 공통 숫자 집합 구하기
                commonNumbers = new TreeSet<>(arrA);
                commonNumbers.retainAll(arrB);
            }

            // 결과 출력
            bw.write(String.valueOf(result.size()));
            bw.newLine();
            for (int res : result) {
                bw.write(String.valueOf(res) + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
