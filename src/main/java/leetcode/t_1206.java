package leetcode;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

public class t_1206 {

    Node head = new Node(null, null, 0);

    public t_1206() {

    }

    public boolean search(int target) {
        for(Node p = head; p != null; p = p.down){
            while(p.right != null && p.right.val < target){
                p = p.right;
            }
            if(p.right != null && p.right.val == target){
                return true;
            }
        }
        return false;
    }

    Random rand = new Random();

    Node[] stack = new Node[64];

    public void add(int num) {
        int lv = -1;
        for(Node p = head; p != null; p = p.down){
            while(p.right != null && p.right.val < num){
                p = p.right;
            }
            stack[++lv] = p;
        }
        boolean insertUp = true;
        Node downNode = null;
        while(insertUp && lv >= 0){
            Node insert = stack[lv --];
            insert.right = new Node(insert.right, downNode, num);
            downNode = insert.right;
            insertUp = (rand.nextInt() & 1) == 0;
        }
        if(insertUp){
            head = new Node(new Node(null, downNode, num), head, 0);
        }
    }

    public boolean erase(int num) {
        boolean exists = false;
        for(Node p = head; p != null; p = p.down){
            while(p.right != null && p.right.val < num){
                p = p.right;
            }
            if(p.right != null && p.right.val <= num){
                exists = true;
                p.right = p.right.right;
            }
        }
        return exists;
    }

    static class Node{
        int val;
        Node right, down;

        public Node(Node r, Node d, int val){
            right = r;
            down = d;
            this.val = val;
        }
    }

    public static void main(String[] args) {

        ConcurrentSkipListMap<String, String> head = new ConcurrentSkipListMap<>();

        t_1206 t_1206 = new t_1206();
        t_1206.add(1);
        t_1206.add(2);
        t_1206.add(3);

        boolean search = t_1206.search(0);
        System.out.println(search);
        t_1206.add(4);

        boolean search1 = t_1206.search(1);
        System.out.println(search1);

        boolean erase = t_1206.erase(0);
        System.out.println(erase);

        boolean erase1 = t_1206.erase(1);
        System.out.println(erase1);

        boolean search2 = t_1206.search(1);
        System.out.println(search2);

    }

}
