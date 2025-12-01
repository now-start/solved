import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 2623 음악프로그램
    https://www.acmicpc.net/problem/2623
*/

public class Main {

    static FastIO io;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] visited;
    static boolean hasCycle;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        int n = io.nextInt();
        int m = io.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new int[n + 1];
        result = new ArrayList<>();
        hasCycle = false;

        for (int i = 0; i < m; i++) {
            int count = io.nextInt();
            int prev = io.nextInt();
            for (int j = 1; j < count; j++) {
                int curr = io.nextInt();
                graph.get(prev).add(curr);
                prev = curr;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        if (hasCycle) {
            io.print(0);
        } else {
            for (int i = result.size() - 1; i >= 0; i--) {
                io.appendln(result.get(i));
            }
            io.print();
        }
    }

    static void dfs(int node) {
        visited[node] = 1;

        for (int next : graph.get(node)) {
            if (visited[next] == 1) {
                hasCycle = true;
                return;
            }
            if (visited[next] == 0) {
                dfs(next);
            }
        }

        visited[node] = 2;
        result.add(node);
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