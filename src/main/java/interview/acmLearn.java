package interview;

import java.util.Arrays;
import java.util.Scanner;

public class acmLearn {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //System.out.println(in.nextInt());
//            String next = in.next();
//            System.out.println(next);
//            System.out.println(next instanceof String);

//            String s = in.nextLine();
//            String[] tmp = s.split(" ");
//            System.out.println(Arrays.toString(tmp));

            String res = String.format("你好中国：%s", in.next());
            System.out.println(res);

            String str = String.format("%.2f",3.555);
            System.out.println(str);
        }
    }

}
