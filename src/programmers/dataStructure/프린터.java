package programmers.dataStructure;

import java.util.*;

public class 프린터 {

  public int solution(int[] priorities, int location) {
    int answer = 0;
    Document[] documents = new Document[priorities.length];
    Queue<Document> waitingQueue = new LinkedList<>();
    boolean[] visited = new boolean[priorities.length];

    for(int i = 0; i < priorities.length; i++) {
      Document d = new Document(i, priorities[i]);
      documents[i] = d;
      waitingQueue.add(d);
    }

    Arrays.sort(documents, (d1, d2 ) -> {
      if(d2.priority == d1.priority) return d1.number - d2.number;
      return d1.priority - d2.priority;
    });

    for(int i = 0; i < documents.length; i++) {
      documents[i].order = i;
    }

    // int order = 1;
    while(true) {

      Document document = waitingQueue.poll();
      int idx = document.order;

      boolean isPrint = true;
      for(int i = idx + 1; i < documents.length; i++) {
        if(documents[i].priority > document.priority && !visited[i]) isPrint = false;
      }

      if(isPrint) {
        answer++;
        visited[idx] = true;
        if(location == document.number) break;
        continue;
      }

      waitingQueue.add(document);
    }

    return answer;
  }

  private class Document {

    int number;
    int priority;
    int order;

    Document(int number, int priority) {
      this.number = number;
      this.priority = priority;
    }

  }
}
