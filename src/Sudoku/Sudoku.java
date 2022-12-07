package src.Sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
    public static int M;
    public static int N;
    //Pour enregistrer toutes les clauses.
    static List<ArrayList<Integer>> arrCls = new ArrayList<>();

    public static int[][] readSudoku(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);
        M = scanner.nextInt();
        N = M * M;
        int[][] arrSud = new int[N][N];
        for (int i = 0; i < arrSud.length; i++) {
            for (int j = 0; j < arrSud.length; j++) {
                arrSud[i][j] = scanner.nextInt();
            }
        }
        return arrSud;
    }

    public static int encode(int r, int c, int v) {
        return (r - 1) * N * N + (c - 1) * N + v;
    }

    public static void printSudoku(int arr[][]) {
        int i, j;
        System.out.println("--------------------------------------------------");
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                if (j % M == 0) System.out.print("|\t");
                if (arr[i][j] != 0)
                    System.out.print(arr[i][j] + "\t");
                else
                    System.out.print("_\t");
                if (j == N-1)
                    System.out.print("|\n");
            }
            if ((i + 1) % M == 0) System.out.println("--------------------------------------------------");
        }
    }

    public static void transferToClause(int[][] arr) throws IOException {
        /***
         * 1. Assurer qu'il y a au moins un chiffre dans chaque cellule.
         */
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                ArrayList<Integer> cls = new ArrayList<Integer>();
                for (int k = 1; k < N + 1; k++) {
                    cls.add(encode(i, j, k));
                }
                arrCls.add(cls);
            }
        }
        /**
         * 2. Assurer qu'il y a au plus un chiffre dans chaque cellule.
         */
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    for (int m = k + 1; m < N + 1; m++) {
                        ArrayList<Integer> cls = new ArrayList<Integer>();
                        cls.add(-encode(i, j, k));
                        cls.add(-encode(i, j, m));
                        arrCls.add(cls);
                    }
                }
            }
        }
        /***
         * 3. Assurer que chaque chiffre n'apparaisse qu'une seule fois sur chaque colonne.
         */

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    for (int n = j + 1; n < N + 1; n++) {
                        ArrayList<Integer> cls = new ArrayList<Integer>();
                        cls.add(-encode(i, j, k));
                        cls.add(-encode(i, n, k));
                        arrCls.add(cls);
                    }
                }
            }
        }
        /***
         * 4. Assurer que chaque chiffre n'apparaisse qu'une seule fois sur chaque ligne.
         */
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    for (int l = i + 1; l < N + 1; l++) {
                        ArrayList<Integer> cls = new ArrayList<Integer>();
                        cls.add(-encode(i, j, k));
                        cls.add(-encode(l, j, k));
                        arrCls.add(cls);
                    }
                }
            }
        }
        /**
         * 5. Assurer que chaque chiffre n'apparaisse qu'une seule fois dans chaque zone.
         */
        for (int z = 1; z < N + 1; z++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    for (int x = 1; x < M + 1; x++) {
                        for (int y = 1; y < M + 1; y++) {
                            for (int k = y + 1; k < M + 1; k++) {
                                ArrayList<Integer> cls = new ArrayList<Integer>();
                                cls.add(-encode(M * i + x, M * j + y, z));
                                cls.add(-encode(M * i + x, M * j + k, z));
                                arrCls.add(cls);
                            }
                        }
                    }
                }
            }
        }
        for (int z = 1; z < N + 1; z++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    for (int x = 1; x < M + 1; x++) {
                        for (int y = 1; y < M + 1; y++) {
                            for (int k = x + 1; k < M + 1; k++) {
                                for (int l = 1; l < M + 1; l++) {
                                    ArrayList<Integer> cls = new ArrayList<Integer>();
                                    cls.add(-encode(M * i + x, M * j + y, z));
                                    cls.add(-encode(M * i + k, M * j + l, z));
                                    arrCls.add(cls);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (arr[i - 1][j - 1] != 0) {
                    ArrayList<Integer> cls = new ArrayList<Integer>();
                    cls.add(encode(i, j, arr[i - 1][j - 1]));
                    arrCls.add(cls);
                }
            }
        }
    }
    public static void buildCNF(List<ArrayList<Integer>> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/Sudoku/Result/SudokuCNF.txt"));
        bw.write("p cnf "+ N*N*N+" "+arrCls.size());
        StringBuilder constraints = new StringBuilder();
        bw.newLine();
        for(List<Integer> element:list){
            for (int val: element) {
                constraints.append(val).append(" ");
            }
            constraints.append("0\n");
        }
        bw.write(constraints.toString());
        bw.close();
    }
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
        printSudoku(arr);
        int[][] arr2 = readSudoku("src/Sudoku/Test/sudoku03.txt");
        printSudoku(arr2);
        long startTime = System.currentTimeMillis();
        transferToClause(arr2);
        long endTime = System.currentTimeMillis();
        buildCNF(arrCls);
        System.out.println("Temps de l'exécution: " + (endTime-startTime)+" ns");
    }
}
