package baekJoon.bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class 개미굴 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int infoCount = Integer.parseInt(input.nextToken());

        Map<String, Food> foodMap = new HashMap<>();

        for(int i = 0; i < infoCount; i++) {
            input = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(input.nextToken());
            String rootFood = input.nextToken();

            Food root = foodMap.getOrDefault(rootFood, new Food(rootFood));
            foodMap.put(rootFood, root);
            for(int j = 1; j < count; j++) {
                String childFood = input.nextToken();
                root = root.addChild(childFood);
            }
        }

        List<String> keySet = foodMap.keySet()
            .stream()
            .sorted()
            .collect(Collectors.toList());

        for(String key : keySet) {
            Food root = foodMap.get(key);
            bw.write(root.getName());
            bw.newLine();
            int height = 1;
            for(Food child : root.getChildFood()) {
                bw.write(print(height, child));
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static String print(int height, Food food) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height; i++) {
            sb.append("--");
        }
        sb.append(food.getName());
        sb.append("\n");
        for(Food child : food.getChildFood()) {
            sb.append(print(height + 1, child));
        }
        return sb.toString();
    }

    private static class Food {
        String name;

        Set<Food> childFood;

        Food(String name) {
            this.name = name;
            this.childFood = new HashSet<>();
        }

        public Food addChild(String child) {
            Food food = childFood
                .stream()
                .filter(f -> f.name.equals(child))
                .findFirst()
                .orElseGet(() -> new Food(child));
            childFood.add(food);
            return food;
        }

        public List<Food> getChildFood() {
            List<Food> foods = new ArrayList<>(this.childFood);
            foods.sort(new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return o1.name.compareTo(o2.name);
                }
            });
            return foods;
        }
        public String getName() {
            return this.name;
        }
    }


}
