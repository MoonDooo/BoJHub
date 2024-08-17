import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    int N;
    int[] p;
    List<List<Integer>> linked = new ArrayList<>();
    int total;
    boolean[] isSelected;
    int result;
    public Solution() {
        N = readInt();
        p = readIntArray(N);
        total = sumArray(p);
        result = Integer.MAX_VALUE;
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++) {
            int tmp = readInt();
            ArrayList<Integer> l = new ArrayList<>();
            for (int j =0; j < tmp; j++){
                l.add(readInt());
            }
            linked.add(l);
        }
        // --
        comb(0);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else System.out.println(result);
    }
    public void comb(int n){
        if (n == N-1){
            int var = check();
            if (var!=-1){
                result = Math.min(result, var);
            }
            return;
        }
        isSelected[n] = true;
        comb(n+1);
        isSelected[n] = false;
        comb(n+1);
    }
    public int check(){
        int idx1 = -1;
        int idx2 = -1;
        for (int i = 0; i < N; i++){
            if (isSelected[i]){
                idx1 = i;
            }else idx2 = i;
        }
        if (idx1==-1 || idx2==-1) return -1;
        boolean[] isVisited1 = new boolean[N];
        int count1 = dfs(idx1, isVisited1, isSelected[idx1]);
        boolean[] isVisited2 = new boolean[N];
        int count2 = dfs(idx2, isVisited2, isSelected[idx2]);
        if (count1+count2!=N)return-1;

        int var1 = 0;
        for (int i = 0; i < N; i++) {
            if (isSelected[i] == isVisited1[i]){
                if (isVisited1[i]){
                    var1+=p[i];
                }
            }else{
                return -1;
            }
        }
        return Math.abs(total - 2*var1);
    }

    private int dfs(int node, boolean[] visited, boolean selected) {
        visited[node] = true;
        int count = 1;

        for (int var1 : linked.get(node)) {
            if (!visited[var1 - 1] && isSelected[var1 - 1] == selected) {
                count += dfs(var1 - 1, visited, selected);
            }
        }
        return count;
    }

}
class Algorithm{
    Buf buf = new Buf();

    public<T> void fill(T input, T[][] array){
        for (T[] ts : array) {
            Arrays.fill(ts, input);
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
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
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