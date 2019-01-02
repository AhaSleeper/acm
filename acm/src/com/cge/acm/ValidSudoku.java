package com.cge.acm;

import java.util.HashMap;
import java.util.Map;

public class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
        //check rows & clos
        int currRowIdx, currColIdx;
        for(int i=0; i<9; i++){
            HashMap<Character, Integer> rowsMap = new HashMap<Character, Integer>(9);
            HashMap<Character, Integer> colsMap = new HashMap<Character, Integer>(9);
            HashMap<Character, Integer> subBoxMap = new HashMap<Character, Integer>(9);
            for(int j=0; j<9; j++){
                if(!checkRepeatition(rowsMap, board[i][j])) return false;
                if(!checkRepeatition(colsMap, board[j][i])) return false;
                int rowIndex = 3*(i/3);
                int colIndex = 3*(i%3);
                currRowIdx = rowIndex+j/3;
                currColIdx = colIndex+j%3;
                if(!checkRepeatition(subBoxMap, board[currRowIdx][currColIdx])) return false;
            }
        }
        return true;
    }
    public boolean checkRepeatition(Map<Character, Integer> map, char c){
        if(c!='.' && null != map.get(c))
            return false;
        map.put(c, 1);
        return true;
    }
}
