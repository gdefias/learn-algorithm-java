package Algorithm.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Defias
 * @date: 2020/12/24
 * @description: 柠檬水找零

   https://leetcode-cn.com/problems/lemonade-change/

    在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
    每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

    注意，一开始你手头没有任何零钱。
    给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

    输入：bills = [5,5,5,10,20]
    输出：true

    输入：bills = [5,5,10,10,20]
    输出：false
 */
public class LemonadeChange {

    public static void main(String[] args) {
        int[] A = new int[] {5,5,10,10,20};  //5,5,10,10,20
        System.out.println(lemonadeChange(A));

    }

    //贪心
    //尽量留多5美元的在手头
    public static boolean lemonadeChange(int[] bills) {
       int five = 0;  //当前手头5美元的张数
       int ten = 0;  //当前手头10美元的张数

        for(int i=0; i<bills.length; i++) {
            int cur = bills[i];
            if(cur==5) {
                five++;
            } else if(cur==10) {
                if(five<=0) {
                    return false;
                }
                five--;
                ten++;
            } else {
               if(ten>0 && five>0) {
                   ten--;
                   five--;
               } else if(five>=3) {
                   five -= 3;
               } else {
                   return false;
               }
            }
        }
        return true;
    }
}
