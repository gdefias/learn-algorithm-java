package Algorithm.String;

/**
 * @author: Felix
 * @date: 2022/12/31
 * @description: 回文排列

 https://leetcode.cn/problems/palindrome-permutation-lcci/description/

给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
回文串不一定是字典当中的单词。

输入："tactcoa"
输出：true（排列有"tacocat"、"atcocta"，等等）

 */
public class CanPermutePalindrome {

    public boolean canPermutePalindrome(String s) {
        int[] nums = new int[128];
        for(int i=0; i<s.length(); i++) {
            nums[(int)s.charAt(i)]++;
        }

        boolean oddn = false;
        for(int i=0; i<128; i++) {
            if(nums[i]!=0 && nums[i]%2==1) {
                if(oddn) {
                    return false;
                }
                oddn = true;
            }
        }

        return true;
    }
}
