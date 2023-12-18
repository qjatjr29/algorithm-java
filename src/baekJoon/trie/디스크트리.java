package baekJoon.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class 디스크트리 {

    private static final String SEPARATOR = "\\\\";

    private static Directory root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer input = new StringTokenizer(br.readLine());

        int paths = Integer.parseInt(input.nextToken());
        root = new Directory();

        for(int i = 0; i < paths; i++) {
            input = new StringTokenizer(br.readLine());
            String[] path = input.nextToken().split(SEPARATOR);
            addDirectory(path);
        }

        root.sortDirectory();

        for (Directory subDirectory : root.subDirectories) {
            bw.write(subDirectory.getName(0));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void addDirectory(String[] path) {
        Directory cDirectory = root;

        int idx = 0;

        for(; idx < path.length; idx++) {
            Directory subDirectory = cDirectory.isContainDirectory(path[idx]);
            if(subDirectory == null) break;
            cDirectory = subDirectory;
        }

        for(; idx < path.length; idx++) {
            Directory subDirectory = new Directory(path[idx]);
            cDirectory.addSubDirectory(subDirectory);
            cDirectory = subDirectory;
        }
    }

    private static class Directory implements Comparable<Directory> {

        String name;
        List<Directory> subDirectories;

        public Directory() {
            this.subDirectories = new ArrayList<>();
        }

        public Directory(String name) {
            this.name = name;
            this.subDirectories = new ArrayList<>();
        }

        public void addSubDirectory(Directory directory) {
            subDirectories.add(directory);
        }

        public Directory isContainDirectory(String name) {
            for(Directory sub : subDirectories) {
                if(Objects.equals(sub.name, name)) {
                    return sub;
                }
            }
            return null;
        }

        public void sortDirectory() {

            Collections.sort(subDirectories);

            for(Directory directory : subDirectories) {
                directory.sortDirectory();
            }
        }

        public String getName(int depth) {
            StringBuilder directoryName = new StringBuilder();
            directoryName.append(" ".repeat(Math.max(0, depth)));
            directoryName.append(name);
            directoryName.append("\n");

            for(Directory sub : subDirectories) {
                directoryName.append(sub.getName(depth + 1));
            }

            return directoryName.toString();
        }

        @Override
        public int compareTo(Directory o) {
            return this.name.compareTo(o.name);
        }
    }
}
