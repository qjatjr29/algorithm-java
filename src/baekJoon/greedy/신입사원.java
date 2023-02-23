package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1946
public class 신입사원 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken());

    for(int testcase = 0; testcase < T; testcase++) {

      st = new StringTokenizer(br.readLine());
      int numberOfApplicants = Integer.parseInt(st.nextToken());
      int answer = 0;

      Applicant[] applicants = new Applicant[numberOfApplicants];

      for(int i = 0; i < numberOfApplicants; i++) {
        st = new StringTokenizer(br.readLine());

        int paper = Integer.parseInt(st.nextToken());
        int interview = Integer.parseInt(st.nextToken());

        applicants[i] = new Applicant(paper, interview);
      }

      // 서류순으로 정렬
      Arrays.sort(applicants);

      // 자신보다 서류등수가 높은 사람중에 자신보다 면접 등수가 높은 사람이 있다면 그 사람은 불합격
      int topInterviewGrade = applicants[0].interviewGrade;
      answer = 1;
      for(int i = 1; i < numberOfApplicants; i++) {
        // 이미 i 번째 사람은 서류등수가 전 사람들보다 낮다
        // 면접등수는 다른 사람들보다 높은 경우
        // 현재 까지 높은 면접 등수를 다시 설정해준다.
        if(topInterviewGrade > applicants[i].interviewGrade) {
          topInterviewGrade = applicants[i].interviewGrade;
          answer++;
        }
      }
      bw.write(String.valueOf(answer));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }

  private static class Applicant implements Comparable<Applicant> {
    int paperGrade, interviewGrade;

    public Applicant(int paperGrade, int interviewGrade) {
      this.paperGrade = paperGrade;
      this.interviewGrade = interviewGrade;
    }

    @Override
    public int compareTo(Applicant o) {
      return this.paperGrade - o.paperGrade;
    }
  }

}
