package leetcode.trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {

        List<String> answer = new ArrayList<>();

        Trie root = createTrie(words);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, answer);
            }
        }

        return answer;

    }

    private Trie createTrie(String[] words) {

        Trie root = new Trie();
        for(String word : words) {
            Trie cTrie = root;

            for(char alpha : word.toCharArray()) {
                int idx = alpha - 'a';
                if(cTrie.next[idx] == null) cTrie.next[idx] = new Trie();
                cTrie = cTrie.next[idx];
            }
            cTrie.word = word;
        }

        return root;
    }

    private void dfs(char[][] board, int x, int y, Trie trie, List<String> answer) {
        char boardAlpha = board[x][y];
        int boardAlphaIdx = boardAlpha - 'a';
        if(board[x][y] == '.' || trie.next[boardAlphaIdx] == null) return;

        trie = trie.next[boardAlphaIdx];

        if(trie.word != null) {
            answer.add(trie.word);
            trie.word = null;
        }

        board[x][y] = '.';
        if(x > 0) dfs(board, x - 1, y, trie, answer);
        if(y > 0) dfs(board, x, y - 1, trie, answer);
        if(x < board.length - 1) dfs(board, x + 1, y, trie, answer);
        if(y < board[0].length - 1) dfs(board, x, y + 1, trie, answer);
        board[x][y] = boardAlpha;
    }

    private class Trie {
        // alphabet 은 26개.
        Trie[] next = new Trie[26];
        String word;
    }
}
