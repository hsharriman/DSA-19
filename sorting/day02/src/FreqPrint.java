import java.util.Comparator;

public class FreqPrint implements Comparable {
    private int key;
    private String val;

    public FreqPrint(int key, String val){
        super();
        this.key = key;
        this.val = val;
    }

    public int getKey(){
        return this.key;
    }

    public String getVal(){
        return this.val;
    }

    public static Comparator<FreqPrint> FreqComparator = new Comparator<FreqPrint>() {
        @Override
        public int compare(FreqPrint o1, FreqPrint o2) {
            return o1.compareTo(o2);
        }
    };

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof FreqPrint)){
            return 0;
        }
        FreqPrint obj = (FreqPrint)(o);
        int compare = (obj).getKey();
        return this.key - compare;
    }
}