package huawei;

import java.util.HashSet;
import java.util.Scanner;

public class HJ9 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // 使用HashSet来判断是否是不重复的
            HashSet<Integer> hs = new HashSet<>();
            int target = sc.nextInt();// 获取代求解的值
            while(target != 0){ // 求解每位上面的整数
                int temp = target % 10;
                if(hs.add(temp)) // 如果能加入，就是说明没有重复
                    System.out.print(temp);
                target /= 10;// 除10能去掉最右边的数字
            }
            System.out.println();
        }
    }

}
