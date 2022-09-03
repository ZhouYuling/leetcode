package huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class HJ3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //获取个数
        int num = Integer.parseInt(br.readLine());
        //创建TreeSet进行去重排序
        TreeSet<Integer> set = new TreeSet<>();
        //输入
        for(int i =0 ; i < num ;i++){
            set.add(Integer.parseInt(br.readLine()));
        }

        //输出
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}
