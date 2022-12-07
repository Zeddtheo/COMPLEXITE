package src.Graphic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class export {
    static public void toCSV(int[][] data, String path) throws IOException {
        File csvOutputFile = new File(path);
        List<int[]> dataAsList = Arrays.asList(data);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataAsList.stream()
                    .map(export::convertToCSV)
                    .forEach(pw::println);
        }
        assert (csvOutputFile.exists());
    }

    static public void toCSV(List<int[]> data, String path) throws IOException {
        File csvOutputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .map(export::convertToCSV)
                    .forEach(pw::println);
        }
        assert (csvOutputFile.exists());
    }

    static public String convertToCSV(int[] data) {

        return Stream.of(Arrays.stream(data)
                        .mapToObj(String::valueOf).toArray(String[]::new))
                .collect(Collectors.joining(","));
    }

    static public void writeRandomClauses(int nbClause, int clauseLenght, int nbVariable, String path) throws IOException {
        Random rand = new Random();
        FileWriter myFile = new FileWriter(path);
        myFile.write("p cnf ".concat(Integer.toString(nbVariable)).concat(" ").concat(Integer.toString(nbClause)).concat("\n"));
        for (int i = 0; i < nbClause; i++) {
            int count = clauseLenght;
            for (int j = 0; j < nbVariable; j++) {
                if (count >= nbVariable - j || rand.nextInt(2) == 1){
                    if (rand.nextInt(2) == 1){
                        myFile.write("-");
                    }
                    myFile.write(Integer.toString(j).concat(" "));
                    count--;
                }
            }
            myFile.write("\n");
        }
        myFile.close();
    }

    static public void writeRandomAffectations(int nbVariable, String path) throws IOException {
        Random rand = new Random();
        FileWriter myFile = new FileWriter(path);
        for (int j = 0; j < nbVariable; j++) {
            if (rand.nextInt(2) == 1){
                myFile.write("-");
            }
            myFile.write(Integer.toString(j).concat(" "));
        }
        myFile.write("\n");
        myFile.close();
    }
}


