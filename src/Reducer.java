import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reducer extends Thread{
    private Map<String, Integer> res;
    private ArrayList<ArrayList<Map<String, Integer>>> input;
    private int id;

    public Reducer(ArrayList<ArrayList<Map<String, Integer>>> input, int id){
        this.input = input;
        this.id = id;
    }

    @Override
    public void run(){
        ArrayList<Map<String, Integer>> maps = new ArrayList<>();
        for(int k = 0; k < this.input.size(); k++){
            maps.add(this.input.get(k).get(this.id));
        }
        this.res = maps.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (i, j) -> i + j)
                );
    }

    public Map<String,Integer> getRes() {
        return this.res;
    }
}