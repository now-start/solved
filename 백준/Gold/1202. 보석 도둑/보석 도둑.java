import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.in;
import static java.lang.System.out;

/*
    BAEKJOON 1202 보석 도둑
    https://www.acmicpc.net/problem/1202
*/

public class Main {

    static FastIO io;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        // start code
        int n = io.nextInt();
        int k = io.nextInt();
        Queue<Jewel> jewels = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            jewels.add(new Jewel(io.nextInt(), io.nextInt()));
        }

        Queue<Integer> bags = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            bags.add(io.nextInt());
        }

        long result = 0;
        PriorityQueue<Integer> candidates = new PriorityQueue<>(Comparator.reverseOrder());
        while (!bags.isEmpty()) {
            int bag = bags.poll();

            while (!jewels.isEmpty() && jewels.peek().weight <= bag) {
                candidates.add(jewels.poll().value);
            }

            if (!candidates.isEmpty()) {
                result += candidates.poll();
            }
        }

        io.print(result);
    }

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight;
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