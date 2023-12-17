package leetcode.trie;

public class DesignAddAndSearchWordsDataStructure {
    Letter root;

    public DesignAddAndSearchWordsDataStructure() {
        root = new Letter();
    }

    public void addWord(String word) {

        Letter cLetter = root;

        for(char alpha : word.toCharArray()) {
            int idx = alpha - 'a';

            if(cLetter.next[idx] == null) cLetter.next[idx] = new Letter();
            cLetter= cLetter.next[idx];
        }
        cLetter.isEndOfWord = true;
    }

    public boolean search(String word) {
        return dfs(0, word, root);
    }

    private boolean dfs(int index, String word, Letter letter) {

        // 찾고자하는 단어의 마지막까지 온 경우
        if(index == word.length()) return letter.isEndOfWord;

        char alpha = word.charAt(index);
        int idx = alpha - 'a';

        if(alpha == '.') {
            for(Letter next : letter.next) {
                if(next != null && dfs(index + 1, word, next)) {
                    return true;
                }
            }
            return false;
        }

        Letter next = letter.next[idx];
        if(next == null) return false;

        return dfs(index + 1, word, next);
    }

    private class Letter {
        Letter[] next = new Letter[26];
        boolean isEndOfWord;
    }
}
