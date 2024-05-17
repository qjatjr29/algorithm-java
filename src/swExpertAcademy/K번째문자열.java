package swExpertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;

public class K번째문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer input = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(input.nextToken());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			
			String answer = "";
			
			input = new StringTokenizer(br.readLine());
			int targetIndex = Integer.parseInt(input.nextToken());
			
			input = new StringTokenizer(br.readLine());
			String word = input.nextToken();
			
			List<String> suffixes = findSuffix(word);
			int[] lcp = setLCP(suffixes);
			answer = findKthString(suffixes, lcp, targetIndex);
				
			bw.write("#" + String.valueOf(testcase) + " " + answer);
			bw.newLine();	
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	private static String findKthString(List<String> suffixes, int[] lcp, int targetIndex) {
		int count = 0;
		for(int i = 0; i < suffixes.size(); i++) {
			StringBuilder sb = new StringBuilder();
			String suffix = suffixes.get(i);
			for(int j = 0; j < suffix.length(); j++) {
				sb.append(suffix.charAt(j));
				if(j >= lcp[i]) {
					count++;
					if(count == targetIndex) {
						return sb.toString();
					}
				}
			}
		}
		return "none";
	}

	private static List<String> findSuffix(String word) {
		
		List<String> suffixes = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		for(int i = word.length() - 1; i >= 0; i--) {
			sb.insert(0, word.charAt(i));
			suffixes.add(sb.toString());
		}
		Collections.sort(suffixes);
		return suffixes;
	}
	
	private static int[] setLCP(List<String> suffixes) {
		
		int[] lcp = new int[suffixes.size()];
		
		for(int i = 1; i < suffixes.size(); i++) {
			
			String pSuffix = suffixes.get(i - 1);
			String cSuffix = suffixes.get(i);
			
			int cIdx = 0;
			int count = 0;
			
			while(true) {
				
				if(cIdx >= pSuffix.length() || cIdx >= cSuffix.length() || pSuffix.charAt(cIdx) != cSuffix.charAt(cIdx)) {
					lcp[i] = count;
					break;
				}
				
				cIdx++;
				count++;
			}
		}
		
		return lcp;
		
	}
}
