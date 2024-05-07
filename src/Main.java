public class Main {
    public static void main(String[] args) {
        String startWord = args[0].toLowerCase();
        String endWord = args[1].toLowerCase();
        String algorithm = args[2].toLowerCase();
        if (startWord.length() != endWord.length()) {
            System.out.println("Start and end words must have the same length.");
            return;
        }

        String filepath = String.format("words//%dletter.txt", startWord.length());
        Words words = new Words(filepath);

        if (!words.hasWord(startWord)) {
            System.out.println("Start word does not exist in dictionary.");
        } else if (!words.hasWord(endWord)) {
            System.out.println("End word does not exist in dictionary.");
        } else {
            long start = System.currentTimeMillis();
            if (algorithm.equals("astar")) {
                Astar astar = new Astar(startWord, endWord, words);
                astar.search();
                System.out.printf("Time elapsed: %dms\n", System.currentTimeMillis() - start);
            } else if (algorithm.equals("ucs")) {
                UCS ucs = new UCS(startWord, endWord, words);
                ucs.search();
                System.out.printf("Time elapsed: %dms\n", System.currentTimeMillis() - start);
            } else if (algorithm.equals("gbfs")) {
                GBFS gbfs = new GBFS(startWord, endWord, words);
                gbfs.search();
                System.out.printf("Time elapsed: %dms\n", System.currentTimeMillis() - start);
            } else {
                System.out.println("Algorithm input invalid.");
            }
        }
    }
}