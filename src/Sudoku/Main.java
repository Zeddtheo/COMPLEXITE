package src.Sudoku;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int arr[][] = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 0, 7, 8, 9},
                {1, 2, 3, 4, 0, 6, 7, 8, 9},
                {1, 2, 3, 0, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9}};
        new Sudoku().printSudoku(arr);
    }

}
