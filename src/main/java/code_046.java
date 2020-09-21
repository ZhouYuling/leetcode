public class code_046 {

    public int translateNum(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for(int i = 0; i < src.length(); ++i){
            p = q;
            q = r;
            r = 0;
            r += q;
            if(i == 0){
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if(pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0){
                r += p;
            }
        }

        return r;
    }

    public static void main(String[] args) {

        code_046 code_046 = new code_046();
        int i = code_046.translateNum(12258);
        System.out.println(i);


    }


}
