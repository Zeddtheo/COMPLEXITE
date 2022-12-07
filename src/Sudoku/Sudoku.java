package src.Sudoku;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sudoku {
    public static final int M = 3;
    public static final int N = M * M;
    int[][] s = new int[N][N];

    public Sudoku() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s[i][j] = 0;
            }
        }
    }

    int encode(int r, int c, int v){
        return (r-1)*N*N + (c-1)*N + v;
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

    public void transferToClause() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Sudoku.txt"));
        ArrayList<Integer> cls = new ArrayList<Integer>();
        // 1. Assurer qu'il y a au moins un chiffre dans chaque cellule.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 1; k < N+1; k++) {
                    cls.add(encode(i,j,k));
                    bw.write(encode(i,j,k));
                }
                bw.write("0\n");
            }
        }
        // 2. Assurer qu'il y a au plus un chiffre dans chaque cellule.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k =1; k < N+1; k++) {
                    for (int m = k+1; m < N+1; m++) {
                        cls.add(-encode(i,j,k));
                        cls.add(-encode(i,j,m));
                        bw.write(-encode(i,j,k));
                        bw.write(-encode(i,j,m));
                        bw.write("0\n");
                    }
                }
            }
        }
        // 3. Assurer que chaque chiffre n'apparaisse qu'une seule fois sur chaque colonne.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 1; k < N+1; k++) {
                    for (int n = j+1; n < N+1; n++) {
                        cls.add(-encode(i,j,k));
                        cls.add(-encode(i,n,k));
                        bw.write(-encode(i,j,k));
                        bw.write(-encode(i,n,k));
                        bw.write("0\n");
                    }
                }
            }
        }
        // 4. Assurer que chaque chiffre n'apparaisse qu'une seule fois sur chaque ligne.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                for (int k = 1; k < N+1; k++) {
                    for (int l = i+1; l < N+1; l++) {
                        cls.add(-encode(i,j,k));
                        cls.add(-encode(l,j,k));
                        bw.write(-encode(i,j,k));
                        bw.write(-encode(l,j,k));
                        bw.write("0\n");
                    }
                }
            }
        }
        // 5. Assurer que chaque chiffre n'apparaisse qu'une seule fois dans chaque zone.
        for (int z = 1; z < N+1; z++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    for (int x = 1; x < M+1; x++) {
                        for (int y = 1; y <M+1; y++) {
                            for (int k = y+1; k < M+1; k++) {
                                cls.add(-encode(M*i+x,M*j+y,z));
                                cls.add(-encode(M*i+x,M*j+k,z));
                                bw.write(-encode(M*i+x,M*j+y,z));
                                bw.write(-encode(M*i+x,M*j+y,z));
                                bw.write("0\n");
                            }
                        }
                    }
                }
            }
        }
        for (int z = 1; z < N+1; z++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    for (int x = 1; x < M+1; x++) {
                        for (int y = 1; y <M+1; y++) {
                            for (int k = x+1; k < M+1; k++) {
                                for (int l = 1; l < M+1; l++) {
                                    cls.add(-encode(M*i+x,M*j+y,z));
                                    cls.add(-encode(M*i+k,M*j+l,z));
                                    bw.write(-encode(M*i+x,M*j+y,z));
                                    bw.write(-encode(M*i+k,M*j+l,z));
                                    bw.write("0\n");
                                }
                            }
                        }
                    }
                }
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
