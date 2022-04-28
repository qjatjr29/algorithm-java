package programmers.kakao;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class 신규아이디추천 {
    public String solution(String new_id) {
        StringBuilder answer = new StringBuilder();

        new_id = new_id.toLowerCase();

        System.out.println(new_id);

        for(int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            int size = answer.length();
            if (c == '-' || c == '_') answer.append(c);
            if ('a' <= c && c <= 'z') answer.append(c);
            String number = "^[0-9]*$";
            boolean matches = Pattern.matches(number, String.valueOf(c));
            if(matches) answer.append(c);
            if(c == '.') {
                if(size == 0) continue;
                if(answer.charAt(size - 1) == '.') continue;
                answer.append(c);
            }

            if(answer.length() == 15) {
                char chk = answer.charAt(14);
                if(chk == '.') answer.deleteCharAt(14);
                break;
            }

        }

        char mark = answer.charAt(answer.length() - 1);
        if(mark == '.') {
            answer.deleteCharAt(answer.length() - 1);
        }

        if(answer.length() < 3) {
            while(true) {
                if(answer.length() == 0) break;
                if(answer.length() == 3) break;
                char c = answer.charAt(answer.length() - 1);
                if(c == '.') {
                    answer.deleteCharAt(answer.length() - 1);
                }
                else answer.append('c');
            }
        }

        if(answer.length() == 0) {
            answer = new StringBuilder("aaa");
        }

        return answer.toString();
    }
}
