package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class code_146 {

    class solution1 {

        class LRUCache extends LinkedHashMap<Integer, Integer> {
            private int capacity;

            public LRUCache(int capacity) {
                //accessOrder:true访问顺序，false插入顺序
                super(capacity, 0.75F, true);
                //LinkedHashMap使用HashMap#put，但是重写了afterNodeAccess和newNode方法
                this.capacity = capacity;
            }

            public int get(int key) {
                return super.getOrDefault(key, -1);
            }

            public void put(int key, int value) {
                super.put(key, value);
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        }

    }

    class solution2 {

        public class LRUCache {

            class DLinkedNode {
                //定义key
                int key;
                //定义值
                int value;
                //定义前置节点
                DLinkedNode prev;
                //定义后置节点
                DLinkedNode next;
                public DLinkedNode() {}
                public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
            }

            //使用hash表存储节点信息，减少遍历复杂度，复杂度实际上只有O(1)
            private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
            //用于存储链表的节点个数
            private int size;
            //定义容量，用于扩容
            private int capacity;
            //定义头节点和尾节点
            private DLinkedNode head, tail;

            public LRUCache(int capacity) {
                this.size = 0;
                this.capacity = capacity;
                // 使用伪头部和伪尾部节点
                head = new DLinkedNode();
                tail = new DLinkedNode();
                //初始化后继节点
                head.next = tail;
                //初始化前驱节点
                tail.prev = head;
            }

            public int get(int key) {
                DLinkedNode node = cache.get(key);
                if (node == null) {
                    return -1;
                }
                // 如果 key 存在，先通过哈希表定位，再移到头部
                moveToHead(node);
                return node.value;
            }

            public void put(int key, int value) {
                DLinkedNode node = cache.get(key);
                //key存在则只更新值value，否则添加新节点
                if (node == null) {
                    // 如果 key 不存在，创建一个新的节点
                    DLinkedNode newNode = new DLinkedNode(key, value);
                    // 添加进哈希表
                    cache.put(key, newNode);
                    // 添加至双向链表的头部
                    addToHead(newNode);
                    ++size;
                    if (size > capacity) {
                        // 如果超出容量，删除双向链表的尾部节点
                        DLinkedNode tail = removeTail();
                        // 删除哈希表中对应的项
                        cache.remove(tail.key);
                        --size;
                    }
                }
                else {
                    // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                    node.value = value;
                    moveToHead(node);
                }
            }

            //添加节点到头部
            private void addToHead(DLinkedNode node) {
                //设置新增节点的前驱和后继
                node.prev = head;
                node.next = head.next;
                //更新头节点的信息
                head.next.prev = node;
                head.next = node;
            }

            //移除该节点
            private void removeNode(DLinkedNode node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            //把节点移动到头节点
            private void moveToHead(DLinkedNode node) {
                //移除节点
                removeNode(node);
                //添加节点到头部
                addToHead(node);
            }

            //移除节点
            private DLinkedNode removeTail() {
                //获取尾部节点
                DLinkedNode res = tail.prev;
                //删除该节点
                removeNode(res);
                return res;
            }
        }

    }

}
