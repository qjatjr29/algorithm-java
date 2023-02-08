package programmers.kakao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class 프렌즈4블록 {

  private static Set<Block> removeBlock;
  private static Character[][] board;

  public int solution(int m, int n, String[] input) {
    int answer = 0;

    board = new Character[n][m];

    for(int i = 0; i < input.length; i++) {
      String rowStr = input[i];
      for(int j = 0; j < rowStr.length(); j++) {
        char block = rowStr.charAt(j);
        board[j][i] = block;
      }
    }

    while(true) {

      removeBlock = new HashSet<>();

      // 2x2 블록 형태 찾기
      for(int i = 0; i < m; i++) {
        for(int j = 0; j < n; j++) {
          if(board[j][i] == null) continue;
          findGrid(j, i);
        }
      }

      if(removeBlock.isEmpty()) break;

      // 찾았다면 board 정보 갱신
      for (Block block : removeBlock) {
        board[block.x][block.y] = null;
        answer++;
      }

      lowerElement();
    }

    return answer;
  }

  private class Block {
    int x, y;

    public Block(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Block block = (Block) o;
      return x == block.x && y == block.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  private void findGrid(int x, int y) {

    int[] dx = {0, 1, 1};
    int[] dy = {1, 0, 1};

    boolean isFind = true;
    for(int i = 0; i < 3; i++) {

      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length ||
          board[nx][ny] == null || board[nx][ny] != board[x][y]) {
        isFind = false;
        break;
      }
    }

    if(isFind) {
      removeBlock.add(new Block(x, y));
      for(int i = 0; i < 3; i++) {
        removeBlock.add(new Block(x + dx[i], y + dy[i]));
      }
    }
  }

  private void lowerElement() {

    for(int col = 0; col < board.length; col++) {

      List<Character> remainChar = new ArrayList<>();

      for(int i = 0; i < board[col].length; i++) {
        if(board[col][i] != null) remainChar.add(board[col][i]);
      }

      int remainSize = remainChar.size();
      int i = 0;

      for(int index = board[col].length - remainSize; index < board[col].length; index++) {
        board[col][index] = remainChar.get(i);
        i++;
      }

      for(int index = 0; index < board[col].length - remainSize; index++) {
        board[col][index] = null;
      }

    }
  }

//  public static void main(String[] args) {
////    int m = 4;
////    int n = 5;
////    String[] input = {
////        "CCBDE", "AAADE", "AAABF", "CCBBF"
////    };
//
//    int m = 6;
//    int n = 6;
//    String[] input = {
//        "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"
//    };
//
//    System.out.println(solution(m, n ,input));
//  }

}
