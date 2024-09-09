import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    Integer[][] dp;
    int[][] rgb;
    public Solution(){
        N = readInt();
        rgb = new int[N][3];
        for (int i = 0; i < N; i++){
            rgb[i] = readIntArray(3);
        }
        dp = new Integer[N][3];
        dp[0][0] = rgb[0][0];
        dp[0][1] = rgb[0][1];
        dp[0][2] = rgb[0][2];

        for (int i = 1; i<N; i++){
            for (int j=0; j<3; j++){
                dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + rgb[i][j];
            }
        }

        System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
    }

}


class Algorithm{
    private final Buf buf = new Buf();

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

    public void soutArray(Object[] array){
        System.out.println(Arrays.toString(array));
    }

    public Object nvl(Object n){
        if (n==null) return 0;
        else return n;
    }
    public  void soutArray(Object[][] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
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
            String s = buf.read();
            a[i] = s.toCharArray();
        }
        return a;
    }

    public boolean checkArrayIndexOut(int arrayMaxX, int arrayMaxY, int x, int y){
        return 0 <= x && x < arrayMaxX && 0 <= y && y < arrayMaxY;
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