package programmers.kakao.카카오채용연계형2021;

import java.util.HashMap;
import java.util.Map;

public class 숫자문자열과영단어 {

    public int solution(String s) {
        String[] numbers = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i = 0; i < 10; i++) {
            s = s.replace(numbers[i], Integer.toString(i));
        }
        int answer = Integer.parseInt(s);
        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution("147eight"));
    }
}
