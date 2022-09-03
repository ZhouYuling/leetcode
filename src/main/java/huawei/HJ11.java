package huawei;

import java.util.Scanner;

// HJ12题和11题解法一致
public class HJ11 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        StringBuffer strb = new StringBuffer(str);
        strb.reverse();
        System.out.println(strb.toString());
    }

}
