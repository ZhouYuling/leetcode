package leetcode;

public class code_121 {

    // 暴力法
    public int maxProfit(int[] prices) {
        //最大利润
        int maxprofit = 0;
        //外层遍历所有股票
        for (int i = 0; i < prices.length - 1; i++) {
            //内存遍历所有卖出的可能性
            for (int j = i + 1; j < prices.length; j++) {
                //计算利润
                int profit = prices[j] - prices[i];
                //获取最大利润
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }

    // 一次遍历
    public int maxProfit1(int prices[]) {
        //最低价格
        int minprice = Integer.MAX_VALUE;
        //最大利润
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            //更新最低价格
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) { // 更新最大利润
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    // 手写一次遍历
    public int maxProfit3(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) minPrice = price;
            else if (price - minPrice > maxProfit) maxProfit = price - minPrice;
        }
        return maxProfit;
    }


}
