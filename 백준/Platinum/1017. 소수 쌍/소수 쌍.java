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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
    BAEKJOON 1017 소수 쌍
    https://www.acmicpc.net/problem/1017
*/

public class Main {

    static FastIO io;

    static void sieve() {
        isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    static boolean dfs(int here) {
        if (visited[here]) {
            return false;
        }
        visited[here] = true;

        for (int there : graph[here]) {
            if (matchRight[there] == -1 || dfs(matchRight[there])) {
                matchLeft[here] = there;
                matchRight[there] = here;
                return true;
            }
        }
        return false;
    }

    static final int MAX = 2000;
    static boolean[] isPrime;
    static List<Integer>[] graph;
    static int[] matchLeft, matchRight;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int n = io.nextInt();
        List<Integer> list = new ArrayList<>();
        while (n-- > 0) {
            list.add(io.nextInt());
        }

        sieve();
        List<Integer> result = new ArrayList<>();
        int first = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (!isPrime[first + list.get(i)]) {
                continue;
            }

            List<Integer> even = new ArrayList<>();
            List<Integer> odd = new ArrayList<>();

            for (int j = 0; j < list.size(); j++) {
                if (j == 0 || j == i) {
                    continue;
                }
                if (list.get(j) % 2 == 0) {
                    even.add(j);
                } else {
                    odd.add(j);
                }
            }

            if (even.size() != odd.size()) {
                continue;
            }

            graph = new List[list.size()];
            for (int j = 0; j < list.size(); j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j : even) {
                for (int k : odd) {
                    if (isPrime[list.get(j) + list.get(k)]) {
                        if (first % 2 == 0) {
                            graph[j].add(k);
                        } else {
                            graph[k].add(j);
                        }
                    }
                }
            }

            matchLeft = new int[list.size()];
            matchRight = new int[list.size()];
            Arrays.fill(matchLeft, -1);
            Arrays.fill(matchRight, -1);

            int matches = 0;
            for (int j = 0; j < list.size(); j++) {
                visited = new boolean[list.size()];
                if (dfs(j)) {
                    matches++;
                }
            }

            if (matches == even.size()) {
                result.add(list.get(i));
            }
        }

        if (result.isEmpty()) {
            io.print(-1);
        } else {
            Collections.sort(result);
            for (int i = 0; i < result.size(); i++) {
                if (i > 0) {
                    io.append(" ");
                }
                io.append(result.get(i));
            }
            io.print();
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