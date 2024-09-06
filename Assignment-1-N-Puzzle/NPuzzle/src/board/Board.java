package board;

import util.InversionCounter;

import java.util.HashMap;
import java.util.Map;

public class Board {
    public int k, n;
    public int[][] grid;   // 2D array containing the board with blank as 0
    public int[] arr;   // flattened 1D array
    public Map<Integer, int[]> numToRowColMap = new HashMap<>();

    public Board(int k, int[][] input) {
        this.k = k;
        this.n = k*k-1;
        this.grid = new int[k][k];
        this.arr = new int[k*k];
        int c = 0;
        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                this.grid[i][j] = input[i][j];
                numToRowColMap.put(grid[i][j], new int[]{i, j});
                this.arr[c] = input[i][j];
                c++;
            }
        }
    }

    public int[] getRowCol(int num){
        if(numToRowColMap.containsKey(num)){
            return numToRowColMap.get(num);
        }
        return new int[]{-1,-1};
    }

    public int getInvCount(){
        int[] a = new int[n];
        int i = 0;
        for(int x: arr){
            if(x != 0){
                a[i++] = x;
            }
        }
        return InversionCounter.countInversions(a);
    }


}

