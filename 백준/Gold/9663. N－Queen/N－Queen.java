import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 9663 N-Queen
    https://www.acmicpc.net/problem/9663
*/

public class Main {

    static FastIO io;
    static int n;
    static int[][] board;
    static int[][] visit;
    static int result;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // code start

        n = io.nextInt();
        board = new int[n][n];
        visit = new int[n][n];

        back(0);

        io.print(result);
    }

    public static void back(int start) {
        if (start == n) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[start][i] == 0) {
                board[start][i] = 1;
                visit[start][i] = 1;
                check(start, i, 1);
                back(start + 1);
                board[start][i] = 0;
                visit[start][i] = 0;
                check(start, i, -1);
            }
        }
    }

    private static void check(int start, int i, int i1) {
        for (int j = 1; j < n; j++) {
            if(start + j < n) {
                visit[start + j][i] += i1;
            }
            if(start - j >= 0) {
                visit[start - j][i] += i1;
            }
            if(i + j < n) {
                visit[start][i + j] += i1;
            }
            if(i - j >= 0) {
                visit[start][i - j] += i1;
            }
            if (start + j < n && i + j < n) {
                visit[start + j][i + j] += i1;
            }
            if (start + j < n && i - j >= 0) {
                visit[start + j][i - j] += i1;
            }
            if (start - j >= 0 && i + j < n) {
                visit[start - j][i + j] += i1;
            }
            if (start - j >= 0 && i - j >= 0) {
                visit[start - j][i - j] += i1;
            }
        }
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