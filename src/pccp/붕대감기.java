package pccp;


// https://school.programmers.co.kr/learn/courses/30/lessons/250137
public class 붕대감기 {

    private static final int DEAD = -1;

    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;

        Bandage band = new Bandage(bandage[0], bandage[1], bandage[2]);
        Character character = new Character(band, health);

        int attackCount = attacks.length;
        int currentTime = 1;

        for(int i = 0; i < attackCount; i++) {
            int nextAttackTime = attacks[i][0];
            int nextAttackAmount = attacks[i][1];
            int healTime = 0;
            for(; currentTime < nextAttackTime; currentTime++) {
                character.heal();
                healTime++;
                if(character.isSkillDone(healTime)) {
                    healTime = 0;
                    character.additionalHeal();
                }
            }
            currentTime = nextAttackTime + 1;
            character.attacked(nextAttackAmount);
            if(!character.isAlive()) {
                return DEAD;
            }
        }

        answer = character.health;

        return answer;
    }

    private class Bandage {
        private int time;
        private int recoveryPerSecond;
        private int additionalRecoveryAmount;

        public Bandage(int time, int recoveryPerSecond, int additionalRecoveryAmount) {
            this.time = time;
            this.recoveryPerSecond = recoveryPerSecond;
            this.additionalRecoveryAmount = additionalRecoveryAmount;
        }
    }

    private class Character {

        private final int MAX_HEALTH;
        private Bandage bandage;
        private int health;

        public Character(Bandage bandage, int health) {
            this.bandage = bandage;
            this.health = health;
            MAX_HEALTH = health;
        }

        public void attacked(int attackAmount) {
            this.health -= attackAmount;
        }

        public boolean isAlive() {
            return this.health > 0;
        }

        public void heal() {
            this.health += this.bandage.recoveryPerSecond;
            if(this.health > MAX_HEALTH) this.health = MAX_HEALTH;
        }

        public boolean isSkillDone(int time) {
            return time == this.bandage.time;
        }

        public void additionalHeal() {
            this.health += this.bandage.additionalRecoveryAmount;
            if(this.health > MAX_HEALTH) this.health = MAX_HEALTH;
        }
    }
}
