package baekJoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 랭킹전대기열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int players = Integer.parseInt(input.nextToken());
        int limit = Integer.parseInt(input.nextToken());

        List<PlayRoom> playRoomList = new ArrayList<>();

        for(int i = 0; i < players; i++) {

            input = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(input.nextToken());
            String nickname = input.nextToken();

            if(!playRoomList.isEmpty()) {

                boolean isParticipate = false;

                for(int j = 0; j < playRoomList.size(); j++) {
                    PlayRoom playRoom = playRoomList.get(j);
                    int requiredMinLevel = playRoom.minLevel;
                    int requiredMaxLevel = playRoom.maxLevel;

                    // 방에 들어갈 조건 충족
                    if(requiredMinLevel <= level && level <= requiredMaxLevel && playRoom.participatedPlayers.size() < limit) {
                        playRoom.participate(level, nickname);
                        isParticipate = true;
                        break;
                    }
                }

                if(!isParticipate) {
                    playRoomList.add(new PlayRoom(level, nickname));
                }
            }

            else playRoomList.add(new PlayRoom(level, nickname));
        }


        for(PlayRoom playRoom : playRoomList) {
            Collections.sort(playRoom.participatedPlayers);
            if(playRoom.participatedPlayers.size() == limit) {
                bw.write("Started!");
            }
            else {
                bw.write("Waiting!");
            }
            bw.newLine();

            for (Player participatedPlayer : playRoom.participatedPlayers) {

                bw.write(String.valueOf(participatedPlayer.level) + " " + participatedPlayer.nickname);
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static class PlayRoom {
        int minLevel;
        int maxLevel;

        List<Player> participatedPlayers;

        public PlayRoom(int level, String nickName) {
            this.minLevel = level - 10;
            this.maxLevel = level + 10;
            this.participatedPlayers = new ArrayList<>();
            this.participatedPlayers.add(new Player(level, nickName));
        }

        public void participate(int level, String nickname) {
            this.participatedPlayers.add(new Player(level, nickname));
        }

    }

    private static class Player implements Comparable<Player> {
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }
    }

}
