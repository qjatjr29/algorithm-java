package programmers.kakao;

import java.util.*;

public class 괄호변환 {

    private static String checkString(String p) {
        // 1단계
        if(p.equals("")) return "";

        // 2단계


        int left = 0;
        int right = 0;

        Stack<Character> checkCorrect = new Stack<>();



        boolean correct = true;
        int index = 0;
        for(int i = 0; i < p.length(); i++) {
            char present = p.charAt(i);
            if(present == '(') {
                left++;
                checkCorrect.push(present);
            }
            else {
                right++;
                if(checkCorrect.isEmpty()) {
                    correct = false;
                } else {
                    checkCorrect.pop();
                }
            }

            if(left == right) {
                index = i + 1;
                break;
            };
        }

        String leftString = p.substring(0, index);
        String rightString = p.substring(index, p.length());

        // 3단계
        if(correct) {
            return leftString + checkString(rightString);
        } else {
            // 4단계
            StringBuilder temp = new StringBuilder();
            temp.append("(");
            temp.append(checkString(rightString));
            temp.append(")");

            for(int i = 1; i < leftString.length() - 1; i++) {
                if(leftString.charAt(i) == '(') temp.append(")");
                else temp.append("(");
            }
            return temp.toString();
        }

    }


    public static String solution(String p) {
        String answer = "";

        answer = checkString(p);

        return answer;
    }
    public static void main(String[] args) {
        String result = solution(")()(");
        System.out.println(result);
    }
}
