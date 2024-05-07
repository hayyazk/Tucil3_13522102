
public class Word {
    public String name;
    public int g, h;

    public Word(String name) {
        this.name = name;
        this.g = 0;
        this.h = 0;
    }

    public void calculateH(String end) {
        int res = 0;
        for (int i=0; i<this.name.length(); i++) {
            if (this.name.charAt(i) != end.charAt(i)) {
                res++;
            }
        }
        this.h = res;
    }
}
