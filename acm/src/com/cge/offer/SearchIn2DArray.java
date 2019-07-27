package com.cge.offer;

/**
 * 面试题3： 二维数组中的查找
 * 在一个二维数组中，
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，
 * 输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 * 例如下面的二维数组就是每行、每列都递增排序。
 * 如果在这个数组中查找数字7，则返回true；如果查找数字5，
 * 由于数组不含有该数字，则返回false。
 *
 * 1    2   8   9
 * 2    4   9   12
 * 4    7   10  13
 * 6    8   11  15
 *
 */
public class SearchIn2DArray {
    /**
     * 从右上角取值
     * @param matrix
     * @param rows
     * @param cols
     * @param number
     * @return
     */
    public static boolean findNumFromRightTop(int[][] matrix, int rows, int cols, int number){
        boolean found = false;
        int row = 0, col = cols-1;
        while(row<rows && col>=0){
            if(matrix[row][col] == number){
                found = true;
                break;
            } else if(matrix[row][col] > number){
                col--;
            } else
                row++;
        }
        return found;
    }

    /**
     * 从左下角取值
     * @param matrix
     * @param rows
     * @param cols
     * @param number
     * @return
     */
    public static boolean findNumFromLeftBottom(int[][] matrix, int rows, int cols, int number){
        boolean found = false;
        int row = rows-1, col = 0;
        while(row>=0 && col < cols){
            if(matrix[row][col] == number) {
                found = true;
                break;
            } else if(matrix[row][col]<number){
                col++;
            } else
                row--;
        }
        return found;
    }

    public static void main(String[] args){
        int[][] matrix = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8 ,11,15}
        };
        System.out.println("right-top="+findNumFromRightTop(matrix, 4, 4, 1));
        System.out.println("left-bottom="+findNumFromLeftBottom(matrix, 4, 4, 1));
        System.out.println("right-top="+findNumFromRightTop(matrix, 4, 4, 7));
        System.out.println("left-bottom="+findNumFromLeftBottom(matrix, 4, 4, 7));
        System.out.println("right-top="+findNumFromRightTop(matrix, 4, 4, 100));
        System.out.println("left-bottom="+findNumFromLeftBottom(matrix, 4, 4, 100));
    }
}
