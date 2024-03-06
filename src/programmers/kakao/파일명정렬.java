package programmers.kakao;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 파일명정렬 {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        File[] fileList = new File[files.length];

        for(int i = 0; i < files.length; i++) {
            String filename = files[i];
            fileList[i] =  new File(filename, i);
        }

        Arrays.sort(fileList);
        for(int i = 0; i < fileList.length; i++) {
            answer[i] = fileList[i].filename;
        }
        return answer;
    }
    private class File implements Comparable<File> {

        String filename;
        String header;
        String number;
        String tail;

        int order;

        public File(String filename, int order) {
            this.filename = filename;
            this.order = order;
            parseFilename();
        }

        private void parseFilename() {
            Pattern pattern = Pattern.compile("^(\\D+)(\\d{1,5})(.*)");
            Matcher matcher = pattern.matcher(this.filename);

            if (matcher.find()) {
                header = matcher.group(1);
                number = matcher.group(2);
                tail = matcher.group(3);
            } else {
                throw new IllegalArgumentException("Invalid filename format");
            }
        }

        @Override
        public int compareTo(File o) {

            if(this.header.compareToIgnoreCase(o.header) == 0) {
                int cNumber = Integer.parseInt(this.number);
                int oNumber = Integer.parseInt(o.number);

                if(cNumber == oNumber) {
                    return this.order - o.order;
                }
                return cNumber - oNumber;
           }

            return this.header.compareToIgnoreCase(o.header);
        }
    }
}
