package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class code_341 {

    public class NestedIterator implements Iterator<Integer> {

        private List<Integer> vals;
        private Iterator<Integer> cur;

        public NestedIterator(List<NestedInteger> nestedList) {
            vals = new ArrayList<Integer>();
            dfs(nestedList);
            cur = vals.iterator();
        }

        @Override
        public Integer next() {
            return cur.next();
        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    vals.add(nest.getInteger());
                } else {
                    dfs(nest.getList());
                }
            }
        }
    }


    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

}
