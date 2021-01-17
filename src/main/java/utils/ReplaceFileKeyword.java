package utils;

import java.io.*;

public class ReplaceFileKeyword {

    public static void Test(String oldfilePath,String newfilePath){
        File file1 = new File(oldfilePath);
        //判断文件存在并且是文件
        File[] fs = file1.listFiles();	//遍历path下的文件和目录，放在File数组中
        for(File file:fs){					//遍历File[]数组
            Boolean boo = file.exists()&&file.isFile();
            System.out.println(boo);
            if (boo) {
                BufferedReader bufferedReader = null;
                try {
                    //构造一个BufferedReader类来读取文件
                    bufferedReader = new BufferedReader(new FileReader(file));
                    String linetxt = null;
                    //result用来存储文件内容
                    StringBuilder result = new StringBuilder();
                    //按使用readLine方法，一次读一行
                    while ((linetxt = bufferedReader.readLine()) != null) {
                        System.out.println(linetxt);
                        String newconttent = linetxt.replace("bdp_bj","bdp_bj_test");//替换
                        result.append(newconttent).append("\r\n");
                        File newfile = new File(newfilePath + "\\"+ file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")));
                        PrintStream ps = new PrintStream(new FileOutputStream(newfile, true));
                        ps.println(newconttent);// 往aim.txt文件里写入字符串
                        ps.flush();
                    }
                    //输出读出的所有数据（StringBuilder类型）
                    System.out.println(result);
                    //对文件内容操作


                } catch (Exception e) {
                    System.out.println("读取文件内容出错");
                    e.printStackTrace();
                }finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }else{
                System.out.println("找不到指定的文件");
            }
        }

    }



    public static void main(String argv[]){
        String oldfilePath = "E:\\常用文件\\海鹚科技\\_1.项目\\海南平台\\ods原始\\his_self\\scripts\\";//content.txt路径。记得改

        String newfilePath = "E:\\常用文件\\海鹚科技\\_1.项目\\海南平台\\ods原始\\his_self_new\\scripts\\";//aim.txt路径，记得改
        Test(oldfilePath,newfilePath);
    }


}
