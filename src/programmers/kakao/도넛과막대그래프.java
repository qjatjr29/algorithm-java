package programmers.kakao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class 도넛과막대그래프 {

    private static final int MAX_VERTEX_NUMBER = 1000001;
    private Map<Integer, Node> nodeMap;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        nodeMap = new HashMap<>();
        int vertexId = -1;

        for(int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];

            int startNodeId = edge[0];
            int destinationNodeId = edge[1];

            if(startNodeId == destinationNodeId) {
                Node node = nodeMap.getOrDefault(edge[0], new Node(edge[0]));
                node.connectSelf();
                nodeMap.put(startNodeId, node);
                continue;
            }

            Node node = nodeMap.getOrDefault(startNodeId, new Node(edge[0]));
            Node opponent = nodeMap.getOrDefault(destinationNodeId, new Node(edge[1]));

            node.connect(opponent);

            nodeMap.put(startNodeId, node);
            nodeMap.put(destinationNodeId, opponent);
        }

        for(int key : nodeMap.keySet()) {
            Node node = nodeMap.get(key);
            if(node.isVertex()) {
                vertexId = key;
                answer[0] = key;
                break;
            }
        }

        Node vertex = nodeMap.get(vertexId);
        List<Integer> connectedNodes = vertex.connections;
        boolean[] checked = new boolean[MAX_VERTEX_NUMBER];
        checked[vertexId] = true;

        for(int connectedNode : connectedNodes) {
            int nodeCount = 0;
            int edgeCount = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(connectedNode);
            checked[connectedNode] = true;

            while(!queue.isEmpty()) {
                int nodeId = queue.poll();

                Node cNode = nodeMap.get(nodeId);

                nodeCount += 1;
                edgeCount += cNode.outgoingEdge;

                for(int nextNodeId : cNode.connections) {
                    if(checked[nextNodeId]) {
                        continue;
                    }
                    checked[nextNodeId] = true;
                    queue.add(nextNodeId);
                }
            }

            Shape shape = Shape.getShape(nodeCount, edgeCount);
            if(shape != Shape.NONE) {
                answer[shape.number] ++;
            }
        }
        return answer;
    }

    private class Node {
        int id;
        int outgoingEdge;
        int incomingEdge;
        List<Integer> connections;

        public Node(int id) {
            this.id = id;
            this.outgoingEdge = 0;
            this.incomingEdge = 0;
            this.connections = new ArrayList<>();
        }

        public void connect(Node opponent) {
            this.outgoingEdge++;
            connections.add(opponent.id);
            opponent.connected(this.id);
        }

        public void connected(int id) {
            this.incomingEdge++;
            connections.add(id);
        }

        public boolean isVertex() {
            return outgoingEdge >= 2 && incomingEdge == 0;
        }

        public void connectSelf() {
            this.outgoingEdge++;
        }
    }

    public enum Shape {
        DONUT(1),
        ROD(2),
        EIGHT(3),
        NONE(0);

        int number;

        Shape(int number) {
            this.number = number;
        }

        public static Shape getShape(int nodeCount, int edgeCount) {
            if(nodeCount == edgeCount) {
                return DONUT;
            }

            if(nodeCount == edgeCount + 1) {
                return ROD;
            }

            if(nodeCount == edgeCount - 1) {
                return EIGHT;
            }
            return NONE;
        }
    }

}
