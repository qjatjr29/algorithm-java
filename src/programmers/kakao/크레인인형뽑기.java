package programmers.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 크레인인형뽑기 {

    List<Stack<Integer>> dolls;

    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        dolls = new ArrayList<>();

        Stack<Integer> select = new Stack<>();
        for(int i = 0; i < board.length; i++) {
            Stack<Integer> st = new Stack<>();
            for(int j = board.length - 1; j >= 0; j--) {
                if(board[j][i] == 0) continue;
                st.add(board[j][i]);
            }
            dolls.add(st);
        }

        for(int i = 0; i < moves.length; i++) {
            int index = moves[i] - 1;
            Stack<Integer> doll = dolls.get(index);
            if(doll.isEmpty()) continue;
            int get = doll.pop();
            if(!select.isEmpty() && select.peek() == get) {
                select.pop();
                answer += 2;
                continue;
            }
            select.push(get);
        }
        return answer;
    }
    public static void main(String[] args) {

    }
}
