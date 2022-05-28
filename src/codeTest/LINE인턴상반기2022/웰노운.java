package codeTest.LINE인턴상반기2022;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

public class 웰노운 {

    public String[] solution(String[] logs) {
        String[] answer = {};

        Map<String, Integer> users = new HashMap<>();
        Map<String, Set<String>> problems = new HashMap<>();

        Set<String> problemTitles = new HashSet<>();

        for (String log : logs) {
            String[] problemInfo = log.split(" ");
            String userName = problemInfo[0];
            String problemTitle = problemInfo[1];

            if(users.containsKey(userName)) {
                Integer cnt = users.get(userName);
                users.replace(userName, cnt + 1);
            } else {
                users.put(userName, 1);
            }

            problemTitles.add(problemTitle);

            if(problems.containsKey(problemTitle)) {
                Set<String> userList = problems.get(problemTitle);
                userList.add(userName);
                problems.replace(problemTitle, userList);
            } else {
                Set<String> userList = new HashSet<>();
                userList.add(userName);
                problems.put(problemTitle, userList);
            }
        }

        int halfCount = (users.size() + 1) / 2;

        List<String> collect = problemTitles.stream()
                .filter(p ->
                        problems.get(p).size() >= halfCount
                ).collect(Collectors.toList());

        sort(collect);

        answer = new String[collect.size()];

        for(int i = 0; i < answer.length; i++) {
            answer[i] = collect.get(i);
        }

        return answer;
    }


    public static void main(String[] args) {



    }
}
