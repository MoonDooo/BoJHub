import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        new Solution();
    }
}

class Solution {
    private Buf buf = new Buf();
    public Solution(){
        Stack<Integer> stack = new Stack<>();
        int N = buf.readInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<N; i++){
            String input = buf.read();
            switch (input){
                case "push"->{
                    stack.add(buf.readInt());
                }
                case "pop"->{
                    if (stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.pop()).append("\n");
                }
                case "size"->{
                    sb.append(stack.size()).append("\n");
                }
                case "empty"->{
                    if (stack.isEmpty()) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                }
                case "top"->{
                    if (stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.peek()).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }

    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public Long readLong(){
        return Long.parseLong(read());
    }
}