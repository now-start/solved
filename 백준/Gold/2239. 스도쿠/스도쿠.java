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
    BAEKJOON 2239 스도쿠
    https://www.acmicpc.net/problem/2239
*/

public class Main {

    static FastIO io;
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        for (int i = 0; i < 9; i++) {
            String line = io.nextLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        back(0, 0);

        io.print();
    }

    static void back(int x, int y) throws IOException {
        if (x == 9) {
            for (int[] row : board) {
                for (int num : row) {
                    io.append(num);
                }
                io.appendln("");
            }
            io.print();
            System.exit(0);
        }

        if (board[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(x, y, i)) {
                    board[x][y] = i;
                    if (y == 8) {
                        back(x + 1, 0);
                    } else {
                        back(x, y + 1);
                    }
                    board[x][y] = 0;
                }
            }
        } else {
            if (y == 8) {
                back(x + 1, 0);
            } else {
                back(x, y + 1);
            }
        }
    }

    private static boolean check(int x, int y, int i) {
        int x0 = x / 3 * 3;
        int y0 = y / 3 * 3;

        for (int j = 0; j < 9; j++) {
            if (board[x][j] == i || board[j][y] == i) {
                return false;
            }
        }

        for (int j = x0; j < x0 + 3; j++) {
            for (int k = y0; k < y0 + 3; k++) {
                if (board[j][k] == i) {
                    return false;
                }
            }
        }

        return true;
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