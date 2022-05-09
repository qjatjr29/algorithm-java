package programmers.kakao.kakaoTechInternship2022;

import java.util.HashMap;
import java.util.Map;

public class 성격유형 {
    public String solution(String[] survey, int[] choices) {

        Map<Character, Integer> score = new HashMap<>();

        Character[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

        for(int i = 0; i < 8; i++) {
            score.put(types[i], 0);
        }

        StringBuilder answer = new StringBuilder();

        for(int i = 0; i < survey.length; i++) {
            String type = survey[i];
            int typeScore = choices[i];
            char type1 = type.charAt(0);
            char type2 = type.charAt(1);

            if(typeScore == 1) {
                setScore(score, type1, 3);
            } else if (typeScore == 2){
                setScore(score, type1, 2);
            }  else if (typeScore == 3){
                setScore(score, type1, 1);
            }  else if (typeScore == 5){
                setScore(score, type2, 1);
            } else if (typeScore == 6){
                setScore(score, type2, 2);
            } else if (typeScore == 7){
                setScore(score, type2, 3);
            }
        }

        for(int i = 0; i < 8; i+=2) {
            Integer a = score.get(types[i]);
            Integer n = score.get(types[i + 1]);
            if(a >= n) {
                answer.append(types[i]);
            } else {
                answer.append(types[i + 1]);
            }

        }

        return answer.toString();
    }

    private void setScore(Map<Character, Integer> score, char type1, int value) {
        int valueNumber = score.get(type1);
        valueNumber += value;
        score.replace(type1, valueNumber);
    }

    public static void main(String[] args) {

    }
}
