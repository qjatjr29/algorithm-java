package programmers.구현;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class 호텔대실 {
  public int solution(String[][] book_time) {
    int answer = 0;

    List<Book> books = new ArrayList<>();

    for(String[] bookInfo : book_time) {
      Book book = new Book(bookInfo[0], bookInfo[1]);
      books.add(book);
    }

    Collections.sort(books);

    PriorityQueue<Integer> room = new PriorityQueue<>();

    for(int i = 0; i < books.size(); i++) {

      Book book = books.get(i);

      if(room.isEmpty()) {
        room.add(book.nextAvailableTime());
        continue;
      }

      int nextBookStartTime = book.startTime;

      int nextRoomAvailableTime = room.peek();

      if(nextBookStartTime < nextRoomAvailableTime) {
        room.add(book.nextAvailableTime());
      }
      else {
        room.poll();
        room.add(book.nextAvailableTime());
      }

    }
    answer = room.size();

    return answer;
  }

  private class Book implements Comparable<Book> {
    int startTime;
    int endTime;

    Book(String start, String end) {
      this.startTime = convertTimeFromString(start);
      this.endTime = convertTimeFromString(end);
    }

    private int convertTimeFromString(String time) {
      String[] times = time.split(":");
      return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    public int nextAvailableTime() {
      return this.endTime + 10;
    }

    @Override
    public int compareTo(Book o) {
      if(this.startTime == o.startTime) return this.endTime - o.endTime;
      return this.startTime - o.startTime;
    }

  }


}
