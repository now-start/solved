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
import java.util.*;

/*
    BAEKJOON 1005 ACM Craft
    https://www.acmicpc.net/problem/1005
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        
        int t = io.nextInt();

        while (t-- > 0) {
            int n = io.nextInt();
            int k = io.nextInt();

            int[] times = new int[n + 1];
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            int[] inDegree = new int[n + 1];
            int[] dp = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                times[i] = io.nextInt();
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < k; i++) {
                int x = io.nextInt();
                int y = io.nextInt();
                graph[x].add(y);
                inDegree[y]++;
            }

            int w = io.nextInt();
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                    dp[i] = times[i];
                }
            }

            while (!q.isEmpty()) {
                int current = q.poll();

                for (int next : graph[current]) {
                    dp[next] = Math.max(dp[next], dp[current] + times[next]);
                    if (--inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            io.appendln(dp[w]);
        }

        io.print();
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