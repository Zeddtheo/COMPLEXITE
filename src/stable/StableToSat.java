package src.stable;

import java.io.*;

public class StableToSat {

    private int[][] graph ;
    private int[][] complementaryGraph ;
    private final int size ;

    public int[][] getGraph() {
        return graph;
    }
    public void setGraph(int[][] gr){
        this.graph = gr ;
    }

    public int[][] getComplementaryGraph(){
        return this.complementaryGraph ;
    }
    public void setComplementaryGraph(int[][] cg){
        this.complementaryGraph = cg ;
    }

    public int getSize(){
        return this.size ;
    }


    public StableToSat(){
        this.size = 3 ;
        this.graph = new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        };
        this.complementaryGraph = new int[size][] ;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                this.complementaryGraph[i][j] = this.graph[i][j] == 0 ? 1 : 0 ;
            }
        }
    }

    public StableToSat(int[][] gr , int s) {
        this.size = s;
        this.graph = gr;
        this.complementaryGraph = new int[size][] ;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                this.complementaryGraph[i][j] = this.graph[i][j] == 0 ? 1 : 0 ;
            }
        }
    }



    public void toSat(){
        for(int i = 0 ; i < this.size ; i++){
            for(int j = 0 ; j <= i ; j++){
                if(this.graph[i][j] == 1){
                    return ;
                }
            }
        }
    }

    public static void writeToFile(String path, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(content) ;
            writer.close() ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args){
        writeToFile("src/stable/test", "ceci est un test\n" +
                "allons voir si on retourne à la ligne") ;
        System.out.println("on a lancé le truc") ;
    }
}
