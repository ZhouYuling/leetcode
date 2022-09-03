package leetcode;

import utils.Node;

import java.util.LinkedList;
import java.util.Queue;

public class code_117 {

    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                if (i != 0) last.next = node;
                last = node;
            }
        }
        return root;
    }

    private Node last = null, nextStart = null;
    public Node connect2(Node root) {
        if (root == null) return null;
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) handle(p.left);
                if (p.right != null) handle(p.right);
            }
            start = nextStart;
        }
        return root;
    }

    private void handle(Node p) {
        if (last != null) last.next = p;
        if (nextStart == null) nextStart = p;
        last = p;
    }

}
