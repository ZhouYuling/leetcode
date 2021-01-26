package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;

public class MyComparator implements Comparable {

    public static void main(String[] args) {

        Collections.sort(new ArrayList<String>(), new Comparator<String>() {
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        AtomicLong mAtoLong = new AtomicLong();




    }

    public int compareTo(Object o) {
        return 0;
    }
}
