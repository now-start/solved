import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 27434 팩토리얼 3
    https://www.acmicpc.net/problem/27434
*/

public class Main {

    static FastIO io;

    // 메모리 초과
    //    public static void main(String[] args) throws Exception {
    //        io = new FastIO();
    //        // start code
    //        int n = 100000;
    //        BigInteger[] dp = new BigInteger[n + 1];
    //
    //        dp[0] = BigInteger.ONE;
    //        dp[1] = BigInteger.ONE;
    //        for (int i = 2; i <= n; i++) {
    //            dp[i] = dp[i - 1].multiply(BigInteger.valueOf(i));
    //        }
    //
    //        io.print(dp[n]);
    //    }

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int n = io.nextInt();

        io.print(n == 0 ? 1 : factorial(1, n));
    }

    static BigInteger factorial(int start, int n) {
        if (start == n) {
            return BigInteger.valueOf(n);
        }
        return factorial(start, (start + n) / 2).multiply(factorial((start + n) / 2 + 1, n));
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