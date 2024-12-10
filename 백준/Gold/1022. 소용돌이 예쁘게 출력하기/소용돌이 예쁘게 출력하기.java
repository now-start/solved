import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 1022 소용돌이 예쁘게 출력하기
    https://www.acmicpc.net/problem/1022
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int y1 = io.nextInt();
        int x1 = io.nextInt();
        int y2 = io.nextInt();
        int x2 = io.nextInt();
        int[][] result = new int[y2 - y1 + 1][x2 - x1 + 1];
        // 좌표의 값
        int max = Integer.MIN_VALUE;
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                int tmp = getPointValue(y, x);
                max = Math.max(max, tmp);
                result[y - y1][x - x1] = tmp;
            }
        }

        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                for(int k = 0; k < String.valueOf(max).length() - String.valueOf(result[i][j]).length(); k++) {
                    io.append(" ");
                }
                io.append(result[i][j]).append(" ");
            }
            io.append("\n");
        }

        io.print();
    }

    static int getPointValue(int x, int y) {
        int max = Math.max(Math.abs(x), Math.abs(y));
        int area = (int) Math.pow(2 * max - 1, 2);

        if (x == max) {
            return area + 7 * max + y;
        }
        if (y == -max) {
            return area + 5 * max + x;
        }
        if (x == -max) {
            return area + 3 * max - y;
        }
        return area + max - x;
    }

    static class FastIO {

        BufferedReader br;
        BufferedWriter bw;
        StringTokenizer st;
        StringBuilder sb;

        public FastIO() {
            br = new BufferedReader(new InputStreamReader(in));
            bw = new BufferedWriter(new OutputStreamWriter(out));
            sb = new StringBuilder();
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return parseInt(next());
        }

        long nextLong() throws IOException {
            return parseLong(next());
        }

        double nextDouble() throws IOException {
            return parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        FastIO append(Object str) {
            sb.append(str);
            return this;
        }

        FastIO appendln(Object str) {
            sb.append(str).append("\n");
            return this;
        }

        void print() throws IOException {
            bw.write(sb.toString());
            bw.flush();
        }

        void print(Object str) throws IOException {
            sb.append(str);
            bw.write(sb.toString());
            bw.flush();
        }

        void close() throws IOException {
            br.close();
            bw.close();
        }
    }
}