import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Astar {
    private String start;
    private String end;
    private Words words;
    private Map<Word, Word> parent;

    public Astar(String start, String end, Words words) {
        this.start = start;
        this.end = end;
        this.words = words;
    }

    public void printPath(Word word) {
        Word current = word;
        List<String> path = new ArrayList<String>();
        while (!parent.get(current).name.equals("ROOT")) {
            path.add(0, current.name);
            current = parent.get(current);
        }
        path.add(0, current.name);
        System.out.printf("Path: %d words\n", path.size()-1);
        System.out.println(path);
    }

    public void search() {
        Word root = new Word("ROOT");
        Word startWord = new Word(start);
        startWord.calculateH(end); 

        PriorityQueue<Word> pq = new PriorityQueue<>(new CompareF());
        pq.add(startWord);

        parent = new HashMap<Word, Word>();
        parent.put(startWord, root);

        Set<String> visited = new HashSet<String>();

        while (!pq.isEmpty()) {
            Word current = pq.poll();
            visited.add(current.name);
            if (current.name.equals(end)) {
                this.printPath(current);
                System.out.printf("Words visited: %d\n", visited.size());
                // Runtime runtime = Runtime.getRuntime();
                // runtime.gc();
                // System.out.print("Memory used: ");
                // System.out.println(runtime.totalMemory() - runtime.freeMemory());
                break;
            }
            for (String child : words.getNeighbors(current.name)) {
                if (!visited.contains(child)) {
                    Word next = new Word(child);
                    next.g = current.g + 1;
                    next.calculateH(end);
                    parent.put(next, current);
                    pq.add(next);
                }
            }
        }

        if (pq.isEmpty()) {
            System.out.println("Path not found.");
        }
    }
}

class CompareF implements Comparator<Word> {
    public int compare(Word a, Word b) {
        return a.g + a.h - b.g - b.h;
    }
}