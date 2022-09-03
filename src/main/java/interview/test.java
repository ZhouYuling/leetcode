package interview;

import java.util.*;

public class test {

    public int[] getMissingTickets (int[] remainTickets) {
        // write code here
        if (remainTickets == null || remainTickets.length == 0) return new int[0];
        List<Integer> list = new ArrayList<>();
        Arrays.sort(remainTickets);
        if (remainTickets[0] > 1) {
            for (int i = 1; i < remainTickets[0]; i++) {
                list.add(i);
            }
        }

        int flag;
        for (int i = 0; i < remainTickets.length - 1; i++) {
            flag = remainTickets[i] + 1;
            int remainTicket = remainTickets[i + 1];
            for (int j = flag; j < remainTicket; j++) {
                if (flag < remainTicket) {
                    list.add(flag);
                    flag ++;
                }
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int[] getMissingTickets2 (int[] remainTickets) {
        if (remainTickets == null || remainTickets.length == 0) return new int[0];
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int ticket : remainTickets) {
            set.add(ticket);
            if (max < ticket)
                max = ticket;
        }
        int[] res = new int[max - set.size()];
        int index = 0;
        for (int i = 1; i < max; i++) {
            if (!set.contains(i)) {
                res[index] = i;
                index ++;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        test test = new test();
        int[] t = {1, 2, 4, 6, 100};
        int[] missingTickets = test.getMissingTickets2(t);
        System.out.println(Arrays.toString(missingTickets));
    }

}
