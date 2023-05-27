package programmers.구현;

import java.util.Arrays;

public class 요격시스템 {

  public int solution(int[][] targets) {
    int answer = 0;

    AttackMissile[] missiles = new AttackMissile[targets.length];

    for(int i = 0; i < targets.length; i++) {
      int[] missile = targets[i];
      int sx = missile[0];
      int ex = missile[1];
      missiles[i] = new AttackMissile(sx, ex);
    }

    Arrays.sort(missiles);


    int start = missiles[0].sx;
    int end = missiles[0].ex;
    answer = 1;

    for(int i = 1; i < missiles.length; i++) {

      AttackMissile cMissile = missiles[i];

      int sx = cMissile.sx;
      int ex = cMissile.ex;

      // 다른 요격 미사일 사용해야 하는 경우
      if(end <= sx) {
        start = sx;
        end = ex;
        answer++;
        continue;
      }

      end = Math.min(end, ex);
    }

    return answer;
  }


  private class AttackMissile implements Comparable<AttackMissile> {
    int sx, ex;
    AttackMissile (int sx, int ex) {
      this.sx = sx;
      this.ex = ex;
    }

    @Override
    public int compareTo(AttackMissile o) {
      if(this.sx == o.sx) return this.ex - o.ex;
      return this.sx - o.sx;
    }
  }

}
