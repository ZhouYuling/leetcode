package huawei;

import java.util.Scanner;

public class HJ13 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String s[] = str.split(" ");
        for (int i = s.length - 1; i >= 0; i--)
            if (i != 0)
                System.out.print(s[i] + " ");
            else
                System.out.print(s[i]);
    }
}
