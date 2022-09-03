package huawei;

import java.util.Scanner;

public class HJ15 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String str = Integer.toBinaryString(num);
        int length = str.length();
        String newStr = str.replaceAll("1", "");
        System.out.println(length - newStr.length());
    }

}
