import src.Graphic.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static List<List<Integer>> clauses ;
    static List<Integer> Affections;

    public static void main(String[] args) throws IOException {
        List<int[]> list = new ArrayList<>();
        for (int i = 20; i < 100; i+=10) {
            export.writeRandomClauses(i, 10, i, "Clauses.txt");
            export.writeRandomAffectations(i, "Affections.txt");

            long startTime = System.currentTimeMillis();

            ReadDataFile("Clauses.txt");
            ReadAffectionFile("Affections.txt");
//            System.out.println(clauses);
//            System.out.println(Affections);
            if(SAT(clauses,Affections))
                System.out.println("satisfaisable");
            else
                System.out.println("Non satisfaisable");
            long endTime = System.currentTimeMillis();

            list.add(new int[]{i, (int)(endTime - startTime)});
            export.toCSV(list, "for_graphic.csv");
        }
    }
    public static void ReadDataFile(String FileName) throws IOException {
         clauses=Files.lines(Paths.get(FileName))
                .map(line -> line.trim().replaceAll("\\s+", " ").trim())
                .filter(line -> line.endsWith(" 0"))
                 .map(line -> Arrays.stream(line.substring(0, line.length() - 2).trim().split("\\s+"))
                         .map(Integer::parseInt)
                         .collect(Collectors.toList())
                 ).collect(Collectors.toList());
    }
    public static void ReadAffectionFile(String FileName)
    {
        try {
            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine().trim();
            List<String> DataS= List.of(data.split(" "));
            Affections=DataS.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean SAT(List<List<Integer>> F, List<Integer> I)
    {
        for(int i=0;i<F.size();i++)
        {
            boolean verify=false;
            for(int k=0;k<F.get(i).size();k++)
            {
                int l=Math.abs(F.get(i).get(k));
                if(I.get(l-1)>0 & F.get(i).get(k)==-l )
                {
                    verify=true;
                    break;
                }
                if(I.get(l-1)<0 & F.get(i).get(k)==l )
                {
                    verify=true;
                    break;
                }

            }
            if(!verify) return false;
        }
        return true;
    }
}
