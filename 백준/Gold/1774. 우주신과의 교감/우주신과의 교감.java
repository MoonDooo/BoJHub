import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        new Solution();
    }
}
class Solution extends Algorithm{
    Integer[] head;
    Point[] points;
    double result;
    int resultIdx = 1;
    int N, M;
    private final PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.distance));
    public Solution() {
        N = readInt();
        M = readInt();
        head = new Integer[N];
        points = new Point[N];
        fillSelf(head);
        for (int i = 0; i<N; i++){
            int x = readInt();
            int y = readInt();
            points[i] = new Point(i, x, y);
            for (int j = 0; j < i; j++){
                pq.add(new Route(points[i], points[j], points[i].distanceTo(points[j])));
            }
        }
        for (int i = 0; i < M; i++){
            int x = readInt()-1;
            int y = readInt()-1;
            union(x, y);
        }

        while (resultIdx < N || !pq.isEmpty()){
            Route route = pq.poll();
            union(route);
        }
        System.out.printf("%.2f%n", result);
    }
    public int find(int n){
        if (n == head[n]) return n;
        return head[n] = find(head[n]);
    }
    public void union(Route route){
        int head1 = find(route.p1.idx);
        int head2 = find(route.p2.idx);
        if (head1 != head2) {
            result += route.distance;
            resultIdx++;
            head[head1] = head2;
        }
    }

    public void union(int n1, int n2){
        int head1 = find(n1);
        int head2 = find(n2);
        if (head1 != head2) {
            resultIdx++;
            head[head1] = head2;
        }
    }

}
class Algorithm{
    Buf buf = new Buf();

    public<T> void fill(T input, T[][] array){
        for (T[] ts : array) {
            Arrays.fill(ts, input);
        }
    }

    public void fillSelf(Integer[] input){
        for (int i = 0; i<input.length; i++) {
            input[i] = i;
        }
    }

    public Float readFloat() {
        return buf.readFloat();
    }

    public Double readDouble() {
        return buf.readDouble();
    }

    public int[][] readIntArray(int n, int m){
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                a[i][j] = buf.readInt();
            }
        }
        return a;
    }

    public int[] readIntArray(int n){
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = buf.readInt();
        }
        return a;
    }

    public int readInt(){
        return buf.readInt();
    }

    public String read(){
        return buf.read();
    }

    public char[][] readIndexOf(int n, int m){
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++){
            String s = read();
            a[i] = s.toCharArray();
        }
        return a;
    }

    public boolean checkArrayIndexOut(int arrayMaxX, int arrayMaxY, int x, int y){
        return 0 <= x && x <= arrayMaxX && 0 <= y && y <= arrayMaxY;
    }

    public boolean is(int x, int y){
        return x==y;
    }

    public boolean is(Object x, Object y){
        return x.equals(y);
    }

    public <T,R> boolean is(T T, R R, BiPredicate<T,R> function){
        return function.test(T,R);
    }

    public <T> void applyToArray(T[] array, Function<T,T> function){
        for (int i = 0; i < array.length; i++) {
            array[i] = function.apply(array[i]);
        }
    }

    public void if_(BooleanSupplier b, Runnable consumer){
        if(b.getAsBoolean()){
            consumer.run();
        }
    }

    //최대 값이 여러 개라면 첫 인덱스 반환
    public int findMaxIdx(int[] array){
        int max = Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 0; i<array.length; i++){
            if (array[i] > max){
                max = array[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public int findMax(int[] array){
        //Arrays.stream(array).max().getAsInt();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<array.length; i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        return max;
    }

    public long pow(long value, int n){
        long tmp = 1L;
        for (int i =0;i<n; i++){
            tmp *= value;
        }
        return tmp;
    }

    public int sumArray(int[][] array) {
        int sum = 0;
        for (int[] row : array) {
            for (int value : row) {
                sum += value;
            }
        }
        return sum;
    }

    public int sumArray(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    static class Point{
        int idx;
        int x, y;

        public Point(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void add(int x, int y) {
            this.x += x;
            this.y += y;
        }
        public double distanceTo(Point point) {
            return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "idx=" + idx +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Route{
        Point p1, p2;
        double distance;

        public Route(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }
    }


    static class Factory{
        int n;
        long[] factory;

        Factory(int n){
            factory = new long[n];
            factory[0] = 1L;
        }
        long factory(int n){
            if (factory[n]!=0) return factory[n];
            return factory[n] = factory(n-1) * n;
        }
    }


    static class Buf{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        public String read(){
            while(st == null || !st.hasMoreTokens()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        public Integer readInt(){
            return Integer.parseInt(read());
        }

        public float readFloat(){
            return Float.parseFloat(read());
        }

        public Double readDouble() {
            return Double.parseDouble(read());
        }
    }


    public static class Comb {

        private Integer[][] dp;

        public Comb(int n, int m) {
            dp = new Integer[n+1][m+1];
        }

        public int combination(int n, int k) {
            if (n < k) return 0;
            if (n == k || k == 0) return 1;
            if (dp[n][k] != null) return dp[n][k];
            dp[n][k] = combination(n-1, k-1) + combination(n-1, k);
            return dp[n][k];
        }
    }
}