package src.stable;

import java.io.*;

public class StableToSat {

    private int nbVariables;
    private int nbClauses;
    private int[][] graph ;
    private int[][] complementaryGraph ;
    private final int size ;
    private final int k ;
    private final String filePath = "stable_to_sat" ;


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


    public StableToSat(int k){
        this.k = k;
        this.size = 3 ;
        this.nbClauses = 0;
        this.nbVariables = 0;

        this.graph = new int[][]{
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        };
        this.complementaryGraph = new int[size][size] ;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                 if(this.graph[i][j] == 0)this.complementaryGraph[i][j] =  1 ;
                 else this.complementaryGraph[i][j] = 0 ;
            }
        }
    }

    public StableToSat(int[][] gr, int s, int k) {
        this.size = s;
        this.graph = gr;
        this.k = k;
        this.nbClauses = 0;
        this.nbVariables = 0;

        this.complementaryGraph = new int[size][size] ;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                this.complementaryGraph[i][j] = this.graph[i][j] == 0 ? 1 : 0 ;
            }
        }
    }



    public void toSat(BufferedWriter w) throws IOException {

        String acc = "";
        //Il existe un i-eme sommet de la clique de taille k
        for(int i = 0 ; i < this.size ; i++){
            for(int j = 0 ; j < k ; j++){
                this.nbVariables++ ;
                if(i!=j) acc += "x" +i + j + " ";
            }
        }
        acc+= "\n" ;
        this.nbClauses++ ;
        //les sommets i et j de la clique sont différents
        for(int v = 0 ; v < this.size ; v++){
            for(int i = 0 ; i < k ; i++){
                for(int j = 0 ; j < k ; j++){
                    if(i!=j) acc+= "-x"+i+v + " -x" +j+v ;
                }
                this.nbClauses++ ;
                acc+="\n" ;
            }
        }
        //n'importe laquelle des 2 arêtes de la clique est connectée
        //cad pour tout i,j de 1 à k, et (u,v) qui est une arête du graphe,
        //il n'existe pas de sommet de celle-ci qui n'est pas dans la clique
        for(int v = 0 ; v < this.size ; v++){
            for(int u = 0 ; u<this.size; v++){
                if(this.complementaryGraph[u][v] == 1){
                    for(int i = 0 ; i < k ; i++){
                        for(int j = 0 ; j < k ; j++){
                            this.nbClauses++ ;
                            acc+= "x " + i + v + "\n" + "x" + j + u ;
                        }
                    }
                }
            }
        }
        w.append(acc) ;
    }


    public void writeToFile() {
        try {
            String content = "p cnf " + this.nbVariables + " " + this.nbClauses + "\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true));
            writer.append(content) ;
            toSat(writer);
            System.out.println("on ferme le fichier") ;
            writer.close() ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args){
        StableToSat s = new StableToSat(2) ;
        s.writeToFile() ;
    }
}
