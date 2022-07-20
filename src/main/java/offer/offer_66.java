package offer;

public class offer_66 {

    class Solution {
        public int[] constructArr(int[] a) {

            int length = a.length;
            int[] L = new int[length];
            int[] R = new int[length];
            int[] answer = new int[length];

            L[0] = 1;
            for (int i = 1; i < length; i++) {
                L[i] = a[i - 1] * L[i - 1];
            }
            R[length - 1] = 1;
            for (int i = length - 2; i >= 0; i--) {
                R[i] = a[i + 1] * R[i + 1];
            }

            for (int i = 0; i < length; i++) {
                answer[i] = L[i] * R[i];
            }

            return answer;
        }
    }

    class Solution2 {
        public int[] constructArr(int[] a) {
            if (a == null || a.length == 0) return new int[0];
            int length = a.length;
            int[] answer = new int[length];

            answer[0] = 1;
            for (int i = 1; i < length; i++) {
                answer[i] = a[i - 1] * answer[i - 1];
            }

            int R = 1;
            for (int i = length - 1; i >= 0; i--) {
                answer[i] = answer[i] * R;
                R *= a[i];
            }

            return answer;
        }
    }

}
