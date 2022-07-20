package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class code_460 {

    class Solution {
        class LFUCache {
            // 缓存容量，时间戳
            int capacity, time;
            Map<Integer, Node> key_table;
            TreeSet<Node> S;
            TreeSet<Node> C;

            public LFUCache(int capacity) {
                this.capacity = capacity;
                this.time = 0;
                key_table = new HashMap<Integer, Node>();
                S = new TreeSet<Node>();
                C = new TreeSet<>(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return o1.cnt == o2.cnt ? o1.time - o2.time : o1.cnt - o2.cnt;
                    }
                });
            }

            public int get(int key) {
                if (capacity == 0) {
                    return -1;
                }
                // 如果哈希表中没有键 key，返回 -1
                if (!key_table.containsKey(key)) {
                    return -1;
                }
                // 从哈希表中得到旧的缓存
                Node cache = key_table.get(key);
                // 从平衡二叉树中删除旧的缓存
                S.remove(cache);
                // 将旧缓存更新
                cache.cnt += 1;
                cache.time = ++time;
                // 将新缓存重新放入哈希表和平衡二叉树中
                S.add(cache);
                key_table.put(key, cache);
                return cache.value;
            }

            public void put(int key, int value) {
                if (capacity == 0) {
                    return;
                }
                if (!key_table.containsKey(key)) {
                    // 如果到达缓存容量上限
                    if (key_table.size() == capacity) {
                        // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                        key_table.remove(S.first().key);
                        S.remove(S.first());
                    }
                    // 创建新的缓存
                    Node cache = new Node(1, ++time, key, value);
                    // 将新缓存放入哈希表和平衡二叉树中
                    key_table.put(key, cache);
                    S.add(cache);
                } else {
                    // 这里和 get() 函数类似
                    Node cache = key_table.get(key);
                    S.remove(cache);
                    cache.cnt += 1;
                    cache.time = ++time;
                    cache.value = value;
                    S.add(cache);
                    key_table.put(key, cache);
                }
            }
        }

        class Node implements Comparable<Node> {
            int cnt, time, key, value;

            Node(int cnt, int time, int key, int value) {
                this.cnt = cnt;
                this.time = time;
                this.key = key;
                this.value = value;
            }

            public boolean equals(Object anObject) {
                if (this == anObject) {
                    return true;
                }
                if (anObject instanceof Node) {
                    Node rhs = (Node) anObject;
                    return this.cnt == rhs.cnt && this.time == rhs.time;
                }
                return false;
            }

            // 升序排列
            public int compareTo(Node rhs) {
                return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt;
            }

            public int hashCode() {
                return cnt * 1000000007 + time;
            }
        }
    }

    class Solution2 {
        class LFUCache {
            int minfreq, capacity;
            Map<Integer, Node> keyTable;
            Map<Integer, DoublyLinkedList> freqTable;

            public LFUCache(int capacity) {
                this.minfreq = 0;
                this.capacity = capacity;
                keyTable = new HashMap<Integer, Node>();
                freqTable = new HashMap<Integer, DoublyLinkedList>();
            }

            public int get(int key) {
                if (capacity == 0) {
                    return -1;
                }
                if (!keyTable.containsKey(key)) {
                    return -1;
                }
                Node node = keyTable.get(key);
                int val = node.val, freq = node.freq;
                freqTable.get(freq).remove(node);
                // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
                if (freqTable.get(freq).size == 0) {
                    freqTable.remove(freq);
                    if (minfreq == freq) {
                        minfreq += 1;
                    }
                }
                // 插入到 freq + 1 中
                DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
                list.addFirst(new Node(key, val, freq + 1));
                freqTable.put(freq + 1, list);
                keyTable.put(key, freqTable.get(freq + 1).getHead());
                return val;
            }

            public void put(int key, int value) {
                if (capacity == 0) {
                    return;
                }
                if (!keyTable.containsKey(key)) {
                    // 缓存已满，需要进行删除操作
                    if (keyTable.size() == capacity) {
                        // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                        Node node = freqTable.get(minfreq).getTail();
                        keyTable.remove(node.key);
                        freqTable.get(minfreq).remove(node);
                        if (freqTable.get(minfreq).size == 0) {
                            freqTable.remove(minfreq);
                        }
                    }
                    DoublyLinkedList list = freqTable.getOrDefault(1, new DoublyLinkedList());
                    list.addFirst(new Node(key, value, 1));
                    freqTable.put(1, list);
                    keyTable.put(key, freqTable.get(1).getHead());
                    minfreq = 1;
                } else {
                    // 与 get 操作基本一致，除了需要更新缓存的值
                    Node node = keyTable.get(key);
                    int freq = node.freq;
                    freqTable.get(freq).remove(node);
                    if (freqTable.get(freq).size == 0) {
                        freqTable.remove(freq);
                        if (minfreq == freq) {
                            minfreq += 1;
                        }
                    }
                    DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
                    list.addFirst(new Node(key, value, freq + 1));
                    freqTable.put(freq + 1, list);
                    keyTable.put(key, freqTable.get(freq + 1).getHead());
                }
            }
        }

        class Node {
            int key, val, freq;
            Node prev, next;

            Node() {
                this(-1, -1, 0);
            }

            Node(int key, int val, int freq) {
                this.key = key;
                this.val = val;
                this.freq = freq;
            }
        }

        class DoublyLinkedList {
            Node dummyHead, dummyTail;
            int size;

            DoublyLinkedList() {
                dummyHead = new Node();
                dummyTail = new Node();
                dummyHead.next = dummyTail;
                dummyTail.prev = dummyHead;
                size = 0;
            }

            public void addFirst(Node node) {
                Node prevHead = dummyHead.next;
                node.prev = dummyHead;
                dummyHead.next = node;
                node.next = prevHead;
                prevHead.prev = node;
                size++;
            }

            public void remove(Node node) {
                Node prev = node.prev, next = node.next;
                prev.next = next;
                next.prev = prev;
                size--;
            }

            public Node getHead() {
                return dummyHead.next;
            }

            public Node getTail() {
                return dummyTail.prev;
            }
        }

    }

}
