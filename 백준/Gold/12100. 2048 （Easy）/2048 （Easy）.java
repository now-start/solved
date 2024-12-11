import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.arraycopy;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 12100 2048 (Easy)
    https://www.acmicpc.net/problem/12100
*/

public class Main {

    static FastIO io;
    static int n;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        n = io.nextInt();
        map = new int[n][n];
        result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = io.nextInt();
            }
        }

        back(0);
        io.print(result);
    }

    public static void back(int depth) {
        if (depth == 5) {
            for (int[] arr : map) {
                for (int i : arr) {
                    result = Math.max(result, i);
                }
            }
            return;
        }

        int[][] originalBoard = copyBoard(map);

        for (int i = 0; i < 4; i++) {
            move(i);
            back(depth + 1);
            map = copyBoard(originalBoard);
        }
    }

    private static int[][] copyBoard(int[][] original) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            arraycopy(original[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    public static void move(int d) {
        if (d == 0) {
            moveUp();
        } else if (d == 1) {
            moveDown();
        } else if (d == 2) {
            moveLeft();
        } else if (d == 3) {
            moveRight();
        }
    }

    private static void moveUp() {
        for (int col = 0; col < n; col++) {
            int[] temp = new int[n];
            int index = 0;

            for (int row = 0; row < n; row++) {
                if (map[row][col] != 0) {
                    temp[index++] = map[row][col];
                }
            }

            for (int i = 0; i < n - 1; i++) {
                if (temp[i] != 0 && temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                }
            }

            index = 0;
            for (int i = 0; i < n; i++) {
                if (temp[i] != 0) {
                    map[index++][col] = temp[i];
                }
            }
            while (index < n) {
                map[index++][col] = 0;
            }
        }
    }

    private static void moveDown() {
        rotateBoard();
        rotateBoard();
        moveUp();
        rotateBoard();
        rotateBoard();
    }

    private static void moveLeft() {
        rotateBoard();
        moveUp();
        rotateBoard();
        rotateBoard();
        rotateBoard();
    }

    private static void moveRight() {
        rotateBoard();
        rotateBoard();
        rotateBoard();
        moveUp();
        rotateBoard();
    }

    private static void rotateBoard() {
        int[][] rotated = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                rotated[col][n - row - 1] = map[row][col];
            }
        }
        map = rotated;
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