package baekJoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 회사문화1 {

    private static Employee[] employees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        input = new StringTokenizer(br.readLine());
        employees = new Employee[n + 1];
        for(int i = 1; i <= n; i++) {
            employees[i] = new Employee(i);
            int boss = Integer.parseInt(input.nextToken());
            if(boss == -1) continue;
            employees[boss].addSubordinate(i);
        }

        for(int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(input.nextToken());
            int amount = Integer.parseInt(input.nextToken());
            employees[number].addComplimentAmount(amount);
        }

        employees[1].calculateComplimentAmount();

        for(int i = 1; i <= n; i++) {
            Employee employee = employees[i];
            long amount = employee.getComplimentAmount();
            bw.write(String.valueOf(amount) + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }
    private static class Employee {

        int number;
        List<Integer> subordinate;
        long complimentAmount;

        Employee(int number) {
            this.number = number;
            this.subordinate = new ArrayList<>();
            this.complimentAmount = 0;
        }

        public void addSubordinate(int number) {
            this.subordinate.add(number);
        }

        public void addComplimentAmount(long amount) {
            this.complimentAmount += amount;
        }

        public void calculateComplimentAmount() {
            for(int sub : subordinate) {
                employees[sub].addComplimentAmount(this.complimentAmount);
                employees[sub].calculateComplimentAmount();
            }
        }
        public long getComplimentAmount() {
            return this.complimentAmount;
        }

    }
}
