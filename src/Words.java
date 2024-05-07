import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Words {
    private Map<String, List<String>> wordList;

    public Words(String filepath) {
        try {
            wordList = new HashMap<String, List<String>>();
            File file = new File(filepath);
            Scanner s1 = new Scanner(file);

            while (s1.hasNextLine()) {
                String word = s1.nextLine();
                String neighborStr = s1.nextLine();
                List<String> neighbors = new ArrayList<String>();
                if (!neighborStr.isEmpty()) {
                    Scanner s2 = new Scanner(neighborStr);
                    s2.useDelimiter(" ");
                    while (s2.hasNext()) {
                        neighbors.add(s2.next());
                    }
                    s2.close();
                }
                // Word w = new Word(word, neighbors);
                wordList.put(word, neighbors);
            }
            s1.close();
        } catch (FileNotFoundException e) {
            System.err.println("Dictionary only contains words up to 8 characters in length.");
        }
        
    }

    public List<String> getNeighbors(String word) {
        return wordList.get(word);
    }

    public boolean hasWord(String word) {
        return wordList.containsKey(word);
    }
}
