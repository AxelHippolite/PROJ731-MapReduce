import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException{
        File folder = new File("./data");
        File[] listOfFiles = folder.listFiles();

        double start = System.nanoTime();

        ArrayList<Mapper> mapThreads = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                Mapper map = new Mapper("data/"+file.getName());
                map.start();
                mapThreads.add(map);
            }
        }

        for (Mapper map : mapThreads){map.join();}

        ArrayList<ArrayList<Map<String, Integer>>> maps = new ArrayList<>();
        for (Mapper map : mapThreads){
            maps.add(map.getRes());
        }

        ArrayList<Reducer> reduceThreads = new ArrayList<>();
        for(int i = 0; i < 2; i++){Reducer reducer = new Reducer(maps, i); reducer.start(); reduceThreads.add(reducer);}

        for (Reducer reducer : reduceThreads){reducer.join();}

        ArrayList<Map<String, Integer>> res = new ArrayList<>();
        for(Reducer reducer : reduceThreads){
            res.add(reducer.getRes());
        }

        Map<String, Integer> output = new TreeMap<>();
        for(Map<String, Integer> map : res){output.putAll(map);}
        System.out.println(output);

        double duration = (System.nanoTime() - start)/1000000.0;
        System.out.println("Execution Time : " + duration + "ms");
    }
} 