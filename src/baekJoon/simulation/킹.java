package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 킹 {

  private static final int MAX_LENGTH = 8;

  private static Map<String, Integer> commandMap;
  private static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
  private static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
  private static int kx, ky, sx, sy;

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    String kingStr = st.nextToken();
    String stoneStr = st.nextToken();
    int moveCount = Integer.parseInt(st.nextToken());

    setMap();

    kx = kingStr.charAt(1) - '0';
    ky = (int) kingStr.charAt(0) - 65;

    sy = (int) stoneStr.charAt(0) - 65;
    sx = (int) stoneStr.charAt(1) - '0';


    for(int i = 0; i < moveCount; i++) {
      st = new StringTokenizer(br.readLine());
      String cmd = st.nextToken();
      int idx = commandMap.get(cmd);
      move(dx[idx], dy[idx]);
    }

    char kxStr = (char) (ky + 65);
    char sxStr = (char) (sy + 65);

    String kanswer = kxStr + String.valueOf(kx);
    String sanswer = sxStr + String.valueOf(sx);

    bw.write(kanswer);
    bw.newLine();
    bw.write(sanswer);
    bw.newLine();
    bw.flush();
    bw.close();
    br.close();

  }

  private static void setMap() {
    commandMap = new HashMap<>();
    commandMap.put("R", 0);
    commandMap.put("L", 1);
    commandMap.put("B", 2);
    commandMap.put("T", 3);
    commandMap.put("RT", 4);
    commandMap.put("LT", 5);
    commandMap.put("RB", 6);
    commandMap.put("LB", 7);
  }

  private static void move(int mx, int my) {

    int nkx = kx + mx;
    int nky = ky + my;
    int nsx = sx + mx;
    int nsy = sy + my;

    if(nkx <= 0 || nkx > MAX_LENGTH || nky < 0 || nky >= MAX_LENGTH) return;
    else {
      if(sx == nkx && sy == nky) {
        if(nsx <= 0 || nsx > MAX_LENGTH || nsy < 0 || nsy >= MAX_LENGTH) return;
        else {
          sx = nsx;
          sy = nsy;
        }
      }
      kx = nkx;
      ky = nky;
    }
    return;
  }

}
