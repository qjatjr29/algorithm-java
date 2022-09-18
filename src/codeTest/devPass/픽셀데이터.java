package codeTest.devPass;

import java.util.HashMap;
import java.util.Map;

public class 픽셀데이터 {

  private static final String[] numbers = {
      "111101101101111",
      "110010010010111",
      "111001111100111",
      "111001111001111",
      "101101111001001",
      "111100111001111",
      "111100111101111",
      "111101001001001",
      "111101111101111",
      "111101111001111"
  };

  public String solution(String[] pixels) {
    String answer = "";
    StringBuilder answerBuilder = new StringBuilder();

    Map<String, Integer> numberMap = new HashMap<>();

    for(int i = 0; i < numbers.length; i++) {
      numberMap.put(numbers[i], i);
    }

    StringBuilder sb;
    int index = 0;

    while(true) {
      if(index >= pixels[0].length()) break;

      sb = new StringBuilder();
      for(int i = 0; i < pixels.length; i++) {
        String pixel = pixels[i];

        String substring = pixel.substring(index, index + 3);
        sb.append(substring);
      }

      index += 3;
      answerBuilder.append(numberMap.get(sb.toString()));
    }

    answer = answerBuilder.toString();

    return answer;
  }

  public static void main(String[] args) {

  }

}
