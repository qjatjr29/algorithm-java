package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;

public class 공통조상 {

    private static int[] root;
    private static Map<Integer, List<Integer>> childrenMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(input.nextToken());

        for(int testcase = 1; testcase <= T; testcase++) {

            int answer = -1;

            input = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(input.nextToken());
            int E = Integer.parseInt(input.nextToken());
            int targetNode1 = Integer.parseInt(input.nextToken());
            int targetNode2 = Integer.parseInt(input.nextToken());

            root = new int[V + 1];
            childrenMap = new HashMap<>();

            for(int i = 1; i <= V; i++) {
                root[i] = 1;
            }

            input = new StringTokenizer(br.readLine());
            for(int i = 0; i < E; i++) {
                int parent = Integer.parseInt(input.nextToken());
                int child = Integer.parseInt(input.nextToken());
                union(parent, child);
            }

            List<Integer> parents1 = findParent(targetNode1);
            List<Integer> parents2 = findParent(targetNode2);

            for(int parent : parents1) {
                for(int compare : parents2) {
                    if(parent == compare) {
                        answer = parent;
                        break;
                    }
                }
                if(answer != -1) {
                    break;
                }
            }

            int count = getChildrenCount(answer);

            bw.write("#" + String.valueOf(testcase) + " " + String.valueOf(answer) + " " + String.valueOf(count));
            bw.newLine();
        }


        bw.flush();
        bw.close();
        br.close();

    }

    private static int getChildrenCount(int x) {
        int count = 1;
        List<Integer> children = childrenMap.get(x);

        Queue<Integer> childQueue = new ArrayDeque<>(children);

        while(!childQueue.isEmpty()) {
            count++;
            int child = childQueue.poll();
            List<Integer> nextChildren = childrenMap.getOrDefault(child, new ArrayList<>());
            childQueue.addAll(nextChildren);
        }

        return count;
    }


    private static List findParent(int x) {

        List<Integer> parents = new ArrayList<>();

        while(true) {
            if(root[x] == 1) {
                parents.add(1);
                return parents;
            }
            parents.add(root[x]);
            x = root[x];
        }
    }

    private static void union(int parent, int child) {
        root[child] = parent;
        List<Integer> children = childrenMap.getOrDefault(parent, new ArrayList<>());
        children.add(child);
        childrenMap.put(parent, children);
    }

}
