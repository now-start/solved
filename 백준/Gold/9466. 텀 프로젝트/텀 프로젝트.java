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
import java.util.StringTokenizer;

/*
    BAEKJOON 9466 텀 프로젝트
    https://www.acmicpc.net/problem/9466
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int t = io.nextInt();

        while (t-- > 0) {
            int n = io.nextInt();
            List<List<Integer>> arr = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                arr.add(new ArrayList<>());
            }

            int[] selected = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int x = io.nextInt();
                selected[i] = x;
            }

            boolean[] visited = new boolean[n + 1];
            boolean[] finished = new boolean[n + 1];
            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    count += dfs(i, selected, visited, finished);
                }
            }

            io.appendln(n - count);
        }

        io.print();
    }

    private static int dfs(int curr, int[] selected, boolean[] visited, boolean[] finished) {
        visited[curr] = true;
        int next = selected[curr];
        int count = 0;

        if (!visited[next]) {
            count += dfs(next, selected, visited, finished);
        } else if (!finished[next]) {
            int temp = next;
            count++;
            while (temp != curr) {
                count++;
                temp = selected[temp];
            }
        }

        finished[curr] = true;
        return count;
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