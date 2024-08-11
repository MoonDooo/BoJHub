import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * 백준 #1103
 * author: MoonDooo
 */
public class Main {

    public static void main(String[] args) {
        new Solution();
    }
}


class Solution extends Algorithm{
    Integer[][] dp;
    char[][] input;
    Integer N, M;
    boolean[][] onPath;
    public Solution(){
        N = readInt();
        M = readInt();
        input = readIndexOf(N, M);
        dp = new Integer[N][M];
        onPath = new boolean[N][M];

        System.out.println(dfs(0, 0)+1);
    }
    public int dfs(int x, int y){
        if (dp[x][y] != null) return dp[x][y];
        dp[x][y] = 0;

        for (int i = 0; i<4; i++){
            int moveX = x+moveX(i, input[x][y]-'0');
            int moveY = y+moveY(i, input[x][y]-'0');
            if (checkArrayIndexOut(N, M, moveX, moveY)&&input[moveX][moveY]!='H'){;
                if (onPath[moveX][moveY]){
                    System.out.println(-1);
                    System.exit(0);
                }
                onPath[x][y] = true;
                dp[x][y] = Math.max(dp[x][y], dfs(moveX, moveY) + 1);
                onPath[x][y] = false;
            }
        }
        return dp[x][y];
    }
}

class Algorithm{
    Buf buf = new Buf();
    int[] dx = {0, 0, -1, 1}; // 2차원 좌우 이동
    int[] dy = {-1, 1, 0, 0}; // 2차원 상하 이동

    public int moveX(int directionIdx, int distance){
        return dx[directionIdx]*distance;
    }
    public int moveY(int directionIdx, int distance){
        return dy[directionIdx]*distance;
    }

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

    public int readInt(){
        return buf.readInt();
    }

    public String read(){
        return buf.read();
    }

    public char[][] readIndexOf(int n, int m){
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++){
            String s = buf.read();
            a[i] = s.toCharArray();
        }
        return a;
    }

    public boolean checkArrayIndexOut(int arrayMaxX, int arrayMaxY, int x, int y){
        return 0 <= x && x < arrayMaxX && 0 <= y && y < arrayMaxY;
    }
    public boolean checkIndexOut(int arrayMax, int x){
        return 0 <= x && x < arrayMax;
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
}