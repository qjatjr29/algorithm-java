package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;

public class 최대상금 {
	
	private static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer input = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(input.nextToken());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			input = new StringTokenizer(br.readLine());
			
			String initNumberStr = input.nextToken();
			answer = -1;
			int length = initNumberStr.length();
			int[] numbers = new int[length];
			
			int swapCount = Integer.parseInt(input.nextToken());
			swapCount = Math.min(swapCount, length);
			
			for(int i = 0; i < length; i++) {
				int cNum = initNumberStr.charAt(i) - '0';
				numbers[i] = cNum;
			}
			
			swap(0, swapCount, numbers);
			
			bw.write("#" + testcase + " " + answer);
			bw.newLine();
		}
		
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static void swap(int swapCount, int targetSwapCount, int[] numbers) {
		if(swapCount == targetSwapCount) {
			answer = Math.max(answer, concatenateNumbers(numbers));
			return;
		}
		
		for(int i = 0; i < numbers.length; i++) {
			int cNumber = numbers[i];
			for(int j = i + 1; j < numbers.length; j++) {
				int nNumber = numbers[j];
				numbers[i] = nNumber;
				numbers[j] = cNumber;
				swap(swapCount + 1, targetSwapCount, numbers);
				numbers[i] = cNumber;
				numbers[j] = nNumber;
			}
		}
	}
	
	public static int concatenateNumbers(int[] numbers) {
        int result = 0;
        for (int number : numbers) {
            result = result * 10 + number;
        }
        return result;
    }
	
}
