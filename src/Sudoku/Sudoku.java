package src.Sudoku;

import java.util.ArrayList;

public class Sudoku {
    public static final int M = 3;
    public static final int N = M * M;
    int[][] s = new int[N][N];

    public Sudoku() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s[i][j] = 0;
            }
        }
    }

    public static void printSudoku(int arr[][]) {
        int i, j;
        System.out.println("-------------------------");
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (j % 3 == 0) System.out.print("| ");
                if (arr[i][j] != 0)
                    System.out.print(arr[i][j] + " ");
                else
                    System.out.print("_ ");
                if (j == 8)
                    System.out.print("|\n");
            }
            if ((i + 1) % 3 == 0) System.out.println("-------------------------");
        }
        System.out.println("-------------------------");
    }

    public static void createSudoku() {
    }

    public static void readSudoku() {

    }

    public void transferToClause(){
        ArrayList<Integer> clause = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                clause.add(0);
            }
        }
    }
    public boolean isSolved() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (s[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
