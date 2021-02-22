package Questions.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Defias
 * @date: 2020/12/24
 * @description: 柠檬水找零
 *
 * https://leetcode-cn.com/problems/lemonade-change/
 */
public class LemonadeChange {

    public static void main(String[] args) {
        int[] A = new int[] {5,5,10,10,20};  //5,5,10,10,20
        System.out.println(lemonadeChange(A));

    }

    public static boolean lemonadeChange(int[] bills) {
       int count5 = 0;
       int count10 = 0;

        for(int i=0; i<bills.length; i++) {
            int cur = bills[i];
            if(cur==5) {
                count5++;
            } else if(cur==10) {
                if(count5<=0) {
                    return false;
                }
                count5--;
                count10++;
            } else {
               if(count10>0 && count5>0) {
                   count10--;
                   count5--;
               } else if(count5>=3) {
                   count5 -= 3;
               } else {
                   return false;
               }
            }
        }

        return true;
    }

    public boolean lemonadeChange2(int[] bills) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(5, 0);
        map.put(10, 0);
        map.put(20, 0);

        for(int i=0; i<bills.length; i++) {
            if(bills[i]==5) {
                map.put(5, map.get(5)+1);
            } else if(bills[i]==10) {
                if(map.get(5)==0) {
                    return false;
                }
                map.put(5, map.get(5)-1);
                map.put(10, map.get(10)+1);
            } else {
                if(map.get(5)>=1 && map.get(10)>=1) {
                    map.put(5, map.get(5)-1);
                    map.put(10, map.get(10)-1);
                    map.put(20, map.get(20)+1);
                    continue;
                }

                if(map.get(5)>=3) {
                    map.put(5, map.get(5)-3);
                    map.put(20, map.get(20)+1);
                    continue;
                }
                return false;
            }

        }

        return true;
    }
}
