package programmers.kakao;

import java.util.HashMap;
import java.util.Map;

public class 가장많이받은선물 {

    private Map<String, User> userMap;

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        userMap = new HashMap<>();
        for(int i = 0; i < friends.length; i++) {
            String friend = friends[i];
            userMap.put(friend, new User(friend));
        }

        for(String gift : gifts) {
            String[] giftUsers = gift.split(" ");
            User givenUser = userMap.get(giftUsers[0]);
            User receivedUser = userMap.get(giftUsers[1]);

            givenUser.gift(receivedUser);
        }

        for(int i = 0; i < friends.length; i++) {
            int result = 0;
            User user = userMap.get(friends[i]);
            for(int j = 0; j < friends.length; j++) {
                if(i == j) {
                    continue;
                }

                User opponent = userMap.get(friends[j]);
                if(user.isReceive(opponent)) {
                    result++;
                }
            }
            answer = Math.max(answer, result);
        }

        return answer;
    }

    private class User {
        String name;
        int giftPoint;
        Map<String, Integer> giftFriendMap;

        public User(String name) {
            this.name = name;
            this.giftPoint = 0;
            this.giftFriendMap = new HashMap<>();
        }

        public void gift(User receivedUser) {
            receivedUser.receiveGift();
            int count = giftFriendMap.getOrDefault(receivedUser.name, 0);
            giftFriendMap.put(receivedUser.name, count + 1);
            this.giftPoint++;
        }

        public void receiveGift() {
            this.giftPoint--;
        }

        public boolean isReceive(User opponent) {
            int giftCount = this.getGiftHistory(opponent);
            int receivedCount = opponent.getGiftHistory(this);

            if(giftCount == receivedCount) {
                return this.giftPoint > opponent.getGiftPoint();
            }
            return giftCount >= receivedCount;
        }

        public int getGiftHistory(User opponent) {
            return this.giftFriendMap.getOrDefault(opponent.name, 0);
        }

        public int getGiftPoint() {
            return this.giftPoint;
        }

    }

}
