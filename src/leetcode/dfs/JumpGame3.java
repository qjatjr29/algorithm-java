package leetcode.dfs;

public class JumpGame3 {

    private static boolean answer;

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        visited[start] = true;
        answer = false;
        jump(start, 0, visited, arr);
        return answer;
    }

    private void jump(int index, int target, boolean[] visited, int[] arr) {

        if(answer) return;
        int value = arr[index];
        if(value == target) {
            answer = true;
            return;
        }
        int size = arr.length;

        if(index + value < size && !visited[index + value]) {
            visited[index + value] = true;
            jump(index + value, target, visited, arr);
            visited[index + value] = false;
        }
        if(index - value >= 0 && !visited[index - value]) {
            visited[index - value] = true;
            jump(index - value, target, visited, arr);
            visited[index - value] = false;
        }
    }

}
