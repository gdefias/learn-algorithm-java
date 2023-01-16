package Algorithm.Stack;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:  栈的压入弹出序列
 * User: Defias
 * Date: 2018-10

 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 输出：true
 解释：我们可以按以下顺序执行：
 push(1), push(2), push(3), push(4), pop() -> 4,
 push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

 0 <= pushed.length == popped.length <= 1000
 0 <= pushed[i], popped[i] < 1000
 pushed是popped的排列。
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {4,3,5,1,2};
        System.out.println(validateStackSequences(A, B));

    }

    //考虑借用一个辅助栈stack ，模拟压入 / 弹出操作的排列。根据是否模拟成功，即可得到结果
    //入栈操作： 按照压栈序列的顺序执行
    //出栈操作： 每次入栈后，循环判断 “栈顶元素 == 弹出序列的当前元素” 是否成立，将符合弹出序列顺序的栈顶元素全部弹出
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length==0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int p = 0;  //出栈序列索引

        for(int i=0; i<pushed.length; i++) {
            stack.push(pushed[i]);  //入栈

            while(!stack.isEmpty() && stack.peek() == popped[p]) {
                p++;
                if(p == popped.length) {  //出栈序列走完了
                    return true;
                }
                stack.pop();
            }
        }
        return false;
    }
}
