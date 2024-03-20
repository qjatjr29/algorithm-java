package baekJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 수강신청 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(input.nextToken());
        int clicks = Integer.parseInt(input.nextToken());

        Map<String, Student> studentMap = new HashMap<>();

        for(int i = 0; i < clicks; i++) {
            input = new StringTokenizer(br.readLine());
            String studentId = input.nextToken();
            Student student = studentMap.getOrDefault(studentId, new Student(studentId, i));
            student.order = i;
            studentMap.put(studentId, student);
        }

        List<Student> students = new ArrayList<>();

        for(String id : studentMap.keySet()) {
            students.add(studentMap.get(id));
        }

        Collections.sort(students);

        if(students.size() < K) {
            for(int i = 0; i < students.size(); i++) {
                bw.write(students.get(i).id);
                bw.newLine();
            }
        }

        else {
            for(int i = 0; i < K; i++) {
                bw.write(students.get(i).id);
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static class Student implements Comparable<Student> {
        String id;
        int order;

        public Student(String id, int order) {
            this.id = id;
            this.order = order;
        }

        @Override
        public int compareTo(Student o) {
            return this.order - o.order;
        }
    }
}
