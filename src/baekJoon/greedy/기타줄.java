package baekJoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기타줄 {

    private static int PACKAGE_LINE = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int lines = Integer.parseInt(input.nextToken());
        int brandCount = Integer.parseInt(input.nextToken());
        int answer = 0;
        Brand[] brands = new Brand[brandCount];

        for(int i = 0; i < brandCount; i++) {
            input = new StringTokenizer(br.readLine());
            int packagePrice = Integer.parseInt(input.nextToken());
            int price = Integer.parseInt(input.nextToken());
            brands[i] = new Brand(packagePrice, price);
        }

        Arrays.sort(brands, (b1, b2) -> (b1.packagePrice - b2.packagePrice));
        int minPackagePrice = brands[0].packagePrice;
        Arrays.sort(brands, (b1, b2) -> (b1.price - b2.price));
        int minPerPrice = brands[0].price;

        if(PACKAGE_LINE * minPerPrice >= minPackagePrice) {
            while(true) {
                if(lines < PACKAGE_LINE) {
                    break;
                }
                answer += minPackagePrice;
                lines -= PACKAGE_LINE;
            }

            if(minPerPrice * lines > minPackagePrice) {
                lines = 0;
                answer += minPackagePrice;
            }
        }

        answer += lines * minPerPrice;

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();

    }

    private static class Brand {
        int packagePrice;
        int price;

        public Brand(int packagePrice, int price) {
            this.packagePrice = packagePrice;
            this.price = price;
        }
    }

}
