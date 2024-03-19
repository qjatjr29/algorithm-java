package baekJoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class 휴대폰자판 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input;

        while((input = br.readLine()) != null) {

            Dictionary dictionary = new Dictionary();

            int N = Integer.parseInt(input);
            String[] words = new String[N];

            for(int i = 0; i < N; i++) {
                String word = br.readLine();
                words[i] = word;
                dictionary.insert(word);
            }

            int sum = 0;
            for(int i = 0; i < N; i++) {
                sum += dictionary.sol(words[i]);
            }

            double result = (double) sum / N;

            String res = String.format("%.2f", result);
            bw.write(res);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Dictionary {
        private TrieNode root;

        public Dictionary() {
            this.root = new TrieNode(null, null);
        }

        public void insert(String word) {
            TrieNode current = root;

            for(char alphabet : word.toCharArray()) {
                if(!current.children.containsKey(alphabet)) {
                    current.children.put(alphabet, new TrieNode(alphabet, current));
                }
                current = current.children.get(alphabet);
            }

            current.isTerminating = true;
        }

        public boolean search(String word) {
            TrieNode current = root;

            for(char alphabet : word.toCharArray()) {
                if(!current.children.containsKey(alphabet)) {
                    return false;
                }
                current = current.children.get(alphabet);
            }
            return current.isTerminating;
        }

        public int sol(String word) {
            return solution(root, word, 0);
        }

        private int solution(TrieNode node, String word, int index) {
            int count = 0;

            // 단어의 끝까지 찾은 경우
            if(index == word.length()) {
                return count;
            }
            char alpha = word.charAt(index);

            // 더 찾아야하는 경우

            if(!node.children.containsKey(alpha)) return 0;

            // 첫 글자는 입력해야 함.
            if(index == 0) {
                return solution(node.children.get(alpha), word, index + 1) + 1;
            }

            // 직접 입력해주어야 한다.
            if(node.children.size() > 1) {
                return solution(node.children.get(alpha), word, index + 1) + 1;
            }

            if(node.children.size() == 1) {
                if(node.isTerminating) return solution(node.children.get(alpha), word, index + 1) + 1;
                return solution(node.children.get(alpha), word, index + 1);
            }

            return count;
        }

    }

    private static class TrieNode {

        private Character key;
        private TrieNode parent;
        private Map<Character, TrieNode> children;
        private boolean isTerminating;

        public TrieNode(Character key, TrieNode parent) {
            this.key = key;
            this.parent = parent;
            this.children = new HashMap<>();
            this.isTerminating = false;
        }
    }
}
