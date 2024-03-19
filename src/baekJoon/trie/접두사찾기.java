package baekJoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 접두사찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        Trie trie = new Trie();
        int answer = 0;

        for(int i = 0; i < N; i++) {
            input = new StringTokenizer(br.readLine());
            trie.insert(input.nextToken());
        }

        for(int i = 0; i < M; i++) {
            input = new StringTokenizer(br.readLine());
            if(trie.isPrefix(input.nextToken())) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    private static class TrieNode {
        Character key;
        TrieNode parent;
        Map<Character, TrieNode> children;
        boolean isTerminating;

        public TrieNode(Character key, TrieNode parent) {
            this.key = key;
            this.parent = parent;
            this.children = new HashMap<>();
            this.isTerminating = false;
        }
    }

    private static class Trie {
        private TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode(null, null);
        }

        public void insert(String word) {
            // 시작 trie node
            TrieNode current = rootNode;

            // 들어온 문자열의 문자들을 모두 확인하면서 이미 있는 경로가 있다면 계속해서 들어간다.
            // 만약 경로가 없다면 해당 경로로 새로 만들어 들어간다.
            for(char alpha : word.toCharArray()) {
                if(!current.children.containsKey(alpha)) {
                    current.children.put(alpha, new TrieNode(alpha, current));
                }
                current = current.children.get(alpha);
            }
            current.isTerminating = true;
        }

        public boolean search(String word) {
            // 시작 trie node
            TrieNode current = rootNode;

            for(char alpha : word.toCharArray()) {
                if(!current.children.containsKey(alpha)) {
                    return false;
                }
                current = current.children.get(alpha);
            }
            return current.isTerminating;
        }

        public void remove(String word) {
            delete(this.rootNode, word, 0);
        }

        private boolean delete(TrieNode node, String word, int index) {
            char alpha = word.charAt(index);

            if(!node.children.containsKey(alpha)) {
                return false;
            }

            TrieNode current = node.children.get(alpha);
            index++;

            // 문자열 끝에 도달
            if(index == word.length()) {

                if(!current.isTerminating) {
                    return false;
                }

                current.isTerminating = false;
                if(current.children.isEmpty()) {
                    node.children.remove(alpha);
                }

                return true;
            }

            // 다시 재귀 호출
            if(!this.delete(current, word, index)) {
                return false;
            }

            // 자식노드가 비어있다면 해당 노드 삭제
            if(!current.isTerminating && current.children.isEmpty()) {
                node.children.remove(alpha);
            }

            return true;
        }

        public boolean isPrefix(String prefix) {
            TrieNode current = this.rootNode;

            for(char alpha : prefix.toCharArray()) {
                if(!current.children.containsKey(alpha)) {
                    return false;
                }
                current = current.children.get(alpha);
            }
            return true;
        }

    }

}
