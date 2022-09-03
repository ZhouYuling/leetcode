package offerII;

public class offer_002 {

    // 剑指 Offer II 002. 二进制加法
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0 ;
            carry += i < b.length() ? (b.charAt(b.length() - i - 1) - '0') : 0;
            ans.append((char)(carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0) ans.append('1');
        ans.reverse();
        return ans.toString();
    }

}
