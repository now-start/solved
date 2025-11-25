import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    BAEKJOON 1106 호텔
    https://www.acmicpc.net/problem/1106
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code

        int targetPeople = io.nextInt();
        int n = io.nextInt();
        List<hotel> hotels = new ArrayList<>();
        while (n-- > 0) {
            hotel h = new hotel();
            h.money = io.nextInt();
            h.person = io.nextInt();
            hotels.add(h);
        }

        int[] dp = new int[targetPeople + 101];
        for (int i = 1; i <= targetPeople + 100; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 0; i <= targetPeople; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (hotel h : hotels) {
                if (i + h.person < dp.length) {
                    dp[i + h.person] = Math.min(dp[i + h.person], dp[i] + h.money);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = targetPeople; i < dp.length; i++) {
            minCost = Math.min(minCost, dp[i]);
        }

        io.print(minCost);

    }

    static class hotel {
        int money;
        int person;
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