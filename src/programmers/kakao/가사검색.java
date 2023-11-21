package programmers.kakao;

import java.util.HashMap;
import java.util.Map;

public class 가사검색 {
    private static final char WILD_CARD = '?';
    private static final int MAX_SIZE = 100001;

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie[] tries = new Trie[MAX_SIZE];

        for (String word : words) {
            int len = word.length();
            if (tries[len] == null) tries[len] = new Trie();
            tries[len].insert(word);
        }

        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (tries[len] == null) {
                answer[i] = 0;
                continue;
            }
            answer[i] = tries[len].getMatchCount(queries[i]);
        }

        return answer;
    }


    private class Trie {
        Node front;
        Node back;

        Trie() {
            this.front = new Node();
            this.back = new Node();
        }

        public void insert(String word) {
            insertFront(word);
            insertBack(word);
        }

        public int getMatchCount(String query) {
            if(query.charAt(0) == WILD_CARD) return getMatchCountBack(query);
            return getMatchCountFront(query);
        }

        private void insertFront(String word) {
            Node cNode = front;
            for(int i = 0; i < word.length(); i++) {
                cNode.count++;
                int index = convertToInt(word.charAt(i));
                cNode = cNode
                        .childrenNodes
                        .computeIfAbsent(index, idx -> new Node());
            }
        }

        private void insertBack(String word) {
            Node cNode = back;
            for(int i = word.length() - 1; i >= 0; i--) {
                cNode.count++;
                int index = convertToInt(word.charAt(i));
                cNode = cNode
                        .childrenNodes
                        .computeIfAbsent(index, idx -> new Node());
            }
        }

        private int getMatchCountFront(String query) {
            Node cNode = front;
            for(int i = 0; i < query.length(); i++) {
                char alpha = query.charAt(i);
                if(alpha == WILD_CARD) break;
                int index = convertToInt(alpha);
                if(!cNode.childrenNodes.containsKey(index)) return 0;
                cNode = cNode.childrenNodes.get(index);
            }
            return cNode.count;
        }

        private int getMatchCountBack(String query) {
            Node cNode = back;
            for(int i = query.length() - 1; i >= 0; i--) {
                char alpha = query.charAt(i);
                if(alpha == WILD_CARD) break;
                int index = convertToInt(alpha);
                if(!cNode.childrenNodes.containsKey(index)) return 0;
                cNode = cNode.childrenNodes.get(index);
            }
            return cNode.count;
        }

        private int convertToInt(char alpha) {
            return alpha - 'a';
        }
    }

    private class Node {
        Map<Integer, Node> childrenNodes;
        int count;

        Node() {
            this.childrenNodes = new HashMap<>();
            this.count = 0;
        }
    }
}
