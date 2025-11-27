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
import java.util.StringTokenizer;

/*
    BAEKJOON 1806 부분합
    https://www.acmicpc.net/problem/1806
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code

        int n = io.nextInt();
        int s = io.nextInt();
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = io.nextInt(); // 배열 순서대로 입력
        }

        // S가 0이면 최소 구간 길이 1
        if (s == 0) {
            io.appendln(1);
            io.print();
            return;
        }


        while (true) {
            if (sum >= s) {
                // 최소 길이 갱신, right-left+1 중요
                minLen = Math.min(minLen, right - left);
                sum -= arr[left++];
            } else {
                if (right == n) {
                    break; // 배열 끝 체크
                }
                sum += arr[right++];
            }
        }

        io.print(minLen == Integer.MAX_VALUE ? 0 : minLen);
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