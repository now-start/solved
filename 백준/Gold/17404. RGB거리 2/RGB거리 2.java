import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.bitCount;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 17404 RGB거리 2
    https://www.acmicpc.net/problem/17404
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int n = io.nextInt();
        int[][] cost = new int[n][3];

        for (int i = 0; i < n; i++) {
            cost[i][0] = io.nextInt();
            cost[i][1] = io.nextInt();
            cost[i][2] = io.nextInt();
        }

        int[][] dp = new int[n][3];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    dp[0][j] = cost[0][j];
                } else {
                    dp[0][j] = 1000 * 1000 + 1;
                }
            }

            for (int j = 1; j < n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                min = Math.min(min, dp[n - 1][j]);
            }
        }

        io.print(min);
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