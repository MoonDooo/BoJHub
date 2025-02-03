import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
    
        while(Math.min(wallet[0], wallet[1]) < Math.min(bill[0], bill[1]) 
              || Math.max(wallet[0], wallet[1]) < Math.max(bill[0], bill[1]) ){
            bill[ bill[0]<bill[1] ? 1:0 ] /= 2;
            answer++;
        }
        
        return answer;
    }
}