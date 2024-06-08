package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 드래곤앤던전 {

    private static final int MONSTER_TYPE = 1;
    private static final int HEAL_TYPE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int room = Integer.parseInt(input.nextToken());
        int heroAttack = Integer.parseInt(input.nextToken());
        Hero hero = new Hero(heroAttack);

        for(int i = 0; i < room; i++) {
            input = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(input.nextToken());
            long attack = Integer.parseInt(input.nextToken());
            long hp = Integer.parseInt(input.nextToken());

            if(type == MONSTER_TYPE) {
                long attackCount = hp / hero.attack;
                if(hp % hero.attack == 0) {
                    hero.attacked((attackCount - 1) * attack);
                } else {
                    hero.attacked( attackCount * attack);
                }
            }

            if(type == HEAL_TYPE) {
                hero.drinkPotion(attack, hp);
            }
        }

        long answer = Math.abs(hero.maxHp) + 1;
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Hero {
        long maxHp;
        long hp;
        long attack;

        public Hero(long attack) {
            this.maxHp = 0;
            this.hp = 0;
            this.attack = attack;
        }

        public void attacked(long monsterAttack) {
            this.hp -= monsterAttack;
            this.maxHp = Math.min(this.maxHp, this.hp);
        }

        public void drinkPotion(long attackAmount, long healAmount) {
            this.attack += attackAmount;
            this.hp += healAmount;
            if(this.hp > 0) {
                this.hp = 0;
            }
        }
    }
}
