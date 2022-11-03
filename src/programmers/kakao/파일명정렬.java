package programmers.kakao;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 파일명정렬 {

  private static final String NUMBER_REGEX_EXPRESSION = "[0-9]+";

  public static String[] solution(String[] files) {
    String[] answer = {};

    Pattern pattern = Pattern.compile(NUMBER_REGEX_EXPRESSION);

    List<File> answerList = new ArrayList<>();
    for(int i = 0; i < files.length; i++) {
      String currentFile = files[i];
      Matcher matcher = pattern.matcher(currentFile);

      matcher.find();
      String numberStr = matcher.group();
      Long number = Long.parseLong(numberStr);
      int index = matcher.start();

      String head = currentFile.substring(0, index).toLowerCase();
      String tail = currentFile.substring(index, currentFile.length());
      answerList.add(new File(head, number, tail, currentFile));
    }
    sort(answerList);
    answer = new String[answerList.size()];

    for(int i = 0; i < answerList.size(); i++) {
      answer[i] = answerList.get(i).file;
    }

    return answer;
  }

  private static class File implements Comparable<File> {
    String head;
    Long number;
    String tail;
    String file;

    public File(String head, Long number, String tail, String file) {
      this.head = head;
      this.number = number;
      this.tail = tail;
      this.file = file;
    }

    @Override
    public int compareTo(File o) {
      // 현재 head 값이 더 큰 경우
      if(this.head.compareTo(o.head) > 0) {
        return 1;
      }
      else if(this.head.compareTo(o.head) == 0) {
        if(this.number > o.number) return 1;
        else if(this.number.equals(o.number)) return 0;
        return -1;
      }
      else return -1;
    }
  }
  
  public static void main(String[] args) {

    String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };
//    String[] files = {"img12.pn3g", "img10" };

    String[] solution = solution(files);

    for (String s : solution) {
      System.out.println(s);
    }
  }
}
