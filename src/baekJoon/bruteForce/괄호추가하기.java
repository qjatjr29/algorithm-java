package baekJoon.bruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 괄호추가하기 {

    private static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        input = new StringTokenizer(br.readLine());
        String str = input.nextToken();

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (i % 2 == 0) {
                numbers.add(c - '0');
            }
            else operators.add(c);
        }
        dfs(0, 0, numbers, operators);
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int sum, List<Integer> numbers, List<Character> operators) {

        if (index > operators.size()) {
            answer = Math.max(answer, sum);
            return;
        }

        char pOperator = '+';
        if (index != 0) pOperator = operators.get(index - 1);

        // (1) 괄호 사용 - (1 + 2) * 3
        // 괄호를 한 번 사용할 경우 다음 연산자에서는 괄호 사용 못함.
        // 따라서, index += 2
        if (index + 1 <= operators.size()) {
            char operator = operators.get(index);
            int cResult = calculate(operator, numbers.get(index), numbers.get(index + 1));
            int nSum = calculate(pOperator, sum, cResult);
            dfs(index + 2, nSum, numbers, operators);
        }

        // (2) 괄호 사용하지 않음
        int nSum = calculate(pOperator, sum, numbers.get(index));
        dfs(index + 1, nSum, numbers, operators);
    }
    private static int calculate(char operator, int left, int number) {
        if (operator == '+') {
            return left + number;
        }
        else if (operator == '-') {
            return left - number;
        }
        else if (operator == '*') {
            return left * number;
        }
        return 0;
    }
}
