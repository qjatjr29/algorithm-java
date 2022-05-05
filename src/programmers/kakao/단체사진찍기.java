package programmers.kakao;


import static java.lang.Math.abs;

public class 단체사진찍기 {

    String[] data;
    int answer;
    boolean[] visited;
    char[] alpha;

    private boolean validation(String s) {
        for (String d : data) {
            int person1 = s.indexOf(String.valueOf(d.charAt(0)));
            int person2 = s.indexOf(String.valueOf(d.charAt(2)));
            char operate = d.charAt(3);
            int count = Integer.parseInt(String.valueOf(d.charAt(4)));

            int gap = abs(person1 - person2) - 1;

            if(operate == '=' && gap != count) return false;
            if(operate == '<' && gap >= count) return false;
            if(operate == '>' && gap <= count) return false;
        }
        return true;
    }

    private void dfs(String s, int len) {
        if(len == alpha.length) {
            if(validation(s)) answer++;
            return;
        }
        for(int i = 0; i < alpha.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(s + alpha[i] , len + 1);
            visited[i] = false;
        }
    }

    public int solution(int n, String[] data) {
        this.data = data;
        this.answer = 0;
        alpha = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        visited = new boolean[alpha.length];
        dfs("", 0);
        return answer;
    }
    public static void main(String[] args) {

    }
}
