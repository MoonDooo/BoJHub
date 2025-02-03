import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        int max = 0;
        for(int i = 0; i < park.length; i++){
            for(int j = 0; j < park[0].length; j++){
                max = Math.max(findMax(park, i, j),max);
            }
        }
        for(int i = 0; i<mats.length; i++){
            if(mats[i] <= max){
                answer = Math.max(mats[i], answer);
            }
        }
        return answer;
    }
    
    public int findMax(String[][] park, int x, int y){
        if(!"-1".equals(park[x][y]))return 0;
        int idx = 1;
        for(; x + idx < park.length&&y + idx < park[0].length; idx++ ){
            for(int i = 0; i <= idx; i++)if ( !"-1".equals(park[x+i][y+idx]) )return idx;
            for(int j = 0; j <= idx; j++)if ( !"-1".equals(park[x+idx][y+j]) )return idx;
        }
        return idx;
    }
}