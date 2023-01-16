package Algorithm.Array;

import java.util.Stack;

/**
 * @author: Felix
 * @date: 2023/1/12
 * @description: 每日温度

https://leetcode.cn/problems/daily-temperatures/description/

给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
如果气温在这之后都不会升高，请在该位置用 0 来代替。


输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]

输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]

提示：
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures O = new DailyTemperatures();
        int[] temperatures = new int[] {73,74,75,71,69,72,76,73};

        int[] ans = O.dailyTemperatures(temperatures);
        for(int a : ans) {
            System.out.print(a + " ");
        }
    }


    //单调栈 栈底到栈顶单调非递减
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<len; i++) {
            int t = temperatures[i];
            if(stack.isEmpty() || t <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                while(!stack.isEmpty() && t > temperatures[stack.peek()]) {
                    int index = stack.pop();
                    ans[index] = i - index;
                }
                stack.push(i);
            }
        }

        while(!stack.isEmpty()) {
            int index = stack.pop();
            ans[index] = 0;
        }

        return ans;
    }
}
