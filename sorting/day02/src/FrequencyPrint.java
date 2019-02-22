import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        System.out.println(s);
        System.out.printf("\n");
        int i = 0;
        String fin = "";
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> flip = new HashMap<>();
        String[] words = s.split(" ");

        //Count up all occurrences of words in string
        for (String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //Build new hash with counts as keys, proper string as value.
        Set<String> keys = map.keySet();
        for (String key : keys){
            String frag = flip.getOrDefault(map.get(key), "");
            for (int n=0; n<map.get(key); n++){
                frag += " " + key;
            }
            flip.put(map.get(key), frag);
        }
        //Put counts/vals into an arraylist of class objects and sort by value
        Set<Integer> counts = flip.keySet();
        ArrayList<FreqPrint> fp = new ArrayList<>();
        for (int count : counts){
            fp.add(new FreqPrint(count, flip.get(count)));
            i++;
        }
        //Sort the collection
        Collections.sort(fp);

        //Print the final result
        for (FreqPrint temp : fp){
            fin += temp.getVal() + " ";
        }
        return fin.trim();
    }
}

