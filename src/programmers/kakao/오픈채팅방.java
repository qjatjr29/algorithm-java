package programmers.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {
    public static String[] solution(String[] record) {
        String[] answer = {};

        Map<String, String> people = new HashMap<>();
        List<String> command = new ArrayList<>();
        List<String> userId = new ArrayList<>();


        for (int i = 0; i < record.length; i++) {
            String[] info = record[i].split(" ");
            if(info[0].equals("Change")) {
                people.replace(info[1], info[2]);
                continue;
            }
            command.add(info[0]);
            userId.add(info[1]);
            if (info[0].equals("Enter")){
                if(!people.containsKey(info[1])) {
                    people.put(info[1], info[2]);
                } else {
                    people.replace(info[1], info[2]);
                }
            }
        }
        answer = new String[command.size()];
        for(int i = 0; i < command.size(); i++) {
            if(command.get(i).equals("Enter")) {
                answer[i] = people.get(userId.get(i)) + "님이 들어왔습니다.";
            } else answer[i] = people.get(userId.get(i)) + "님이 나갔습니다.";
        }
        return answer;
    }
    public static void main(String[] args) {

        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
        };

        String[] result = solution(record);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
