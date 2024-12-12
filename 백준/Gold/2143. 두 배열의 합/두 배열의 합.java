import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 2143 두 배열의 합
    https://www.acmicpc.net/problem/2143
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        long t = io.nextLong();

        int n = io.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = io.nextLong();
        }
        long[] sumA = new long[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                sumA[idx++] = sum;
            }
        }

        int m = io.nextInt();
        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
            b[i] = io.nextLong();
        }
        long[] sumB = new long[m * (m + 1) / 2];
        idx = 0;
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                sumB[idx++] = sum;
            }
        }

        Map<Long, Long> map = new HashMap<>();
        for (long sum : sumA) {
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }

        long result = 0;
        for (long sum : sumB) {
            result += map.getOrDefault(t - sum, 0L);
        }

        io.print(result);
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