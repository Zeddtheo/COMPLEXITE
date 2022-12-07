package src.Graphic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class export {
    static public void toCSV(int[][] data) throws IOException {
        File csvOutputFile = new File("for_graphic.csv");
        List<int[]> dataAsList = Arrays.asList(data);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataAsList.stream()
                    .map(export::convertToCSV)
                    .forEach(pw::println);
        }
        assert(csvOutputFile.exists());
    }

    static public void toCSV(List<int[]> data) throws IOException {
        File csvOutputFile = new File("for_graphic.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .map(export::convertToCSV)
                    .forEach(pw::println);
        }
        assert(csvOutputFile.exists());
    }

    static public String convertToCSV(int[] data) {

        return Stream.of(Arrays.stream(data)
                .mapToObj(String::valueOf).toArray(String[]::new))
                .collect(Collectors.joining(","));
    }

}

