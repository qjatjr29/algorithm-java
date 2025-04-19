package baekJoon.Implementation;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 싸지방에간준하 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());

        PriorityQueue<int[]> timeQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        // 현재 사용중인 컴퓨터
        PriorityQueue<Computer> usedComputer = new PriorityQueue<>((a, b) -> {
            return a.endTime - b.endTime;
        });

        // 사용하고 있지 않은 컴퓨터
        PriorityQueue<Computer> unUsedComputer = new PriorityQueue<>((a, b) -> {
            return a.id - b.id;
        });

        for (int i = 0; i < n; i++) {
            input = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(input.nextToken());
            int et = Integer.parseInt(input.nextToken());
            timeQueue.add(new int[] {st, et});
        }

        while (!timeQueue.isEmpty()) {
            int[] time = timeQueue.poll();
            int startTime = time[0];
            int endTime = time[1];

            // 다른 유저가 컴퓨터를 사용하게 될 때의 시간에 반납된 컴퓨터가 있는지 확인
            while (!usedComputer.isEmpty()) {
                if (usedComputer.peek().endTime <= startTime) {
                    Computer computer = usedComputer.poll();
                    unUsedComputer.add(computer);
                }
                else break;
            }

            // 사용할 수 있는 컴퓨터가 없다면 => 컴퓨터 추가
            if (unUsedComputer.isEmpty()) {
                int nextComputerId = usedComputer.size() + 1;
                Computer newComputer = new Computer(nextComputerId, endTime, 1);
                usedComputer.add(newComputer);
            }

            // 사용 가능한 컴퓨터가 있다면 해당 컴퓨터 사용
            // 이때, 컴퓨터 번호가 낮은 순으로 먼저 사용
            else {
                Computer computer = unUsedComputer.poll();
                computer.count++;
                computer.endTime = endTime;
                usedComputer.add(computer);
            }
        }

        while (!usedComputer.isEmpty()) {
            Computer computer = usedComputer.poll();
            unUsedComputer.add(computer);
        }

        int requiredComputerCount = unUsedComputer.size();
        bw.write(String.valueOf(requiredComputerCount));
        bw.newLine();

        while (!unUsedComputer.isEmpty()) {
            Computer computer = unUsedComputer.poll();
            bw.write(computer.count + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Computer {
        int id;
        int endTime;
        int count;

        Computer(int id, int endTime, int count) {
            this.id = id;
            this.endTime = endTime;
            this.count = count;
        }
    }

}
