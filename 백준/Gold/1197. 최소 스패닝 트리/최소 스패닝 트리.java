import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    BAEKJOON 1197 최소 스패닝 트리
    https://www.acmicpc.net/problem/1197
*/

public class Main {

    static FastIO io;
    static Map<Long, List<Edge>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        
        Long v = io.nextLong();
        Long e = io.nextLong();

        graph = new HashMap<>();
        visited = new boolean[v.intValue() + 1];

        for (int i = 1; i <= v; i++) {
            graph.put((long) i, new ArrayList<>());
        }

        while (e-- > 0) {
            Long from = io.nextLong();
            Long to = io.nextLong();
            Long weight = io.nextLong();
            graph.get(from).add(new Edge(from, to, weight));
            graph.get(to).add(new Edge(to, from, weight));
        }

        Long result = 0L;
        Queue<Edge> pq = new PriorityQueue<>();
        visited[1] = true;

        graph.get(1L).forEach(pq::add);

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to.intValue()]) {
                continue;
            }

            visited[current.to.intValue()] = true;
            result += current.weight;

            graph.get(current.to).forEach(next -> {
                if (!visited[next.to.intValue()]) {
                    pq.add(next);
                }
            });
        }

        io.print(result);
    }

    static class Edge implements Comparable<Edge> {
        Long from;
        Long to;
        Long weight;

        Edge(Long from, Long to, Long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight.compareTo(o.weight);
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