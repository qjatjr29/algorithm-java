package programmers.kakao.카카오채용연계형2021;

import java.util.*;

public class 표편집 {

    public static class Item {
        int pre, next;
        boolean state;
        Item (int pre, int  next) {
            this.pre = pre;
            this.next = next;
            state = true;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        Stack<Integer> removeItems = new Stack<>();
        List<Item> items = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            items.add(new Item(i - 1, i + 1));
        }

        int here = k;

        for(int i = 0; i < cmd.length; i++) {
            String[] command = cmd[i].split(" ");
            String operation = command[0];

            if(operation.equals("D")) {
                int step = Integer.parseInt(command[1]);

                int next = here;

                for(int j = 0; j < step; j++) {
                    next = items.get(next).next;
                }
                here = next;
            }
            else if(operation.equals("U")) {
                int step = Integer.parseInt(command[1]);

                int next = here;

                for(int j = 0; j < step; j++) {
                    next = items.get(next).pre;
                }
                here = next;
            }
            else if (operation.equals("C")) {
                removeItems.push(here);
                Item hereItem = items.get(here);

                int preNumber = hereItem.pre;
                int nextNumber = hereItem.next;

                if(preNumber >= 0) {
                    items.get(preNumber).next = nextNumber;
                }
                if(nextNumber < n) {
                    items.get(nextNumber).pre = preNumber;
                }
                items.get(here).state = false;

                if(nextNumber >= n) here = preNumber;
                else here = nextNumber;

            }
            else {
                int removeItem = removeItems.pop();
                Item insert = items.get(removeItem);

                int preNumber = insert.pre;
                int nextNumber = insert.next;

                if(preNumber >= 0 ) {
                    items.get(preNumber).next = removeItem;
                }
                if(nextNumber < n) {
                    items.get(nextNumber).pre = removeItem;
                }
                items.get(removeItem).state = true;
            }
        }

        for(int i = 0; i < n; i++) {
            if(items.get(i).state) answer.append("O");
            else answer.append("X");
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String[] a = {
                "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"
        };
        String answer = solution(8, 2, a);
        System.out.println(answer);
    }
}
