package leetcode;

public class code_208 {

    class Trie {

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            //Trie树为26个字母开头
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                //查看word第一开头字母
                int index = ch - 'a';
                //不存在这个字母开头
                if (node.children[index] == null) {
                    //创建一个新的
                    node.children[index] = new Trie();
                }
                //把子节点赋值给当前节点
                node = node.children[index];
            }
            //单词最末尾标记为结束
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }

}
