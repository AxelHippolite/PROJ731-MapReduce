import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Mapper extends Thread{
    private String filePath;
    private ArrayList<Map<String, Integer>> res;

    public Mapper(String filePath){
        this.filePath = filePath;
        this.res = new ArrayList<>();
    }

    @Override
    public void run(){
        Map<String, Integer> odd = new HashMap<>();
        Map<String, Integer> even = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if(word.length() % 2 == 0){
                        even.put(word, even.getOrDefault(word, 0) + 1);
                    } else{
                        odd.put(word, odd.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch(Exception e){}

        this.res.add(even); this.res.add(odd);
    }

    public ArrayList<Map<String,Integer>> getRes() {
        return this.res;
    }

}
