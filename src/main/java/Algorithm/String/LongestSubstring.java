package Algorithm.String;
import java.util.Arrays;
/**
 * @author: Felix
 * @date: 2023/1/3
 * @description:   至少有 K 个重复字符的最长子串

   https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/

    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。

    输入：s = "aaabb", k = 3
    输出：3
    解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。


    输入：s = "ababbc", k = 2
    输出：5
    解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。

    提示：
    1 <= s.length <= 104
    s 仅由小写英文字母组成
    1 <= k <= 105
 */
public class LongestSubstring {

    public static void main(String[] args) {
        LongestSubstring O = new LongestSubstring();
        String s = "aaabb";
        int k = 3;
        System.out.println(O.longestSubstring2(s, k));
    }


    //方法1：分治
    //当某个字符在整个字符串中出现的次数大于0且小于k时，则该最长子串中一定不包含该字符，以该字符划分的不同段进行分治递归求解
    //时间复杂度：O(N⋅∣Σ∣) 其中 N 为字符串的长度，Σ为字符集，本题中字符串仅包含小写字母，因此∣Σ∣=26。由于每次递归调用都会完全去除某个字符，因此递归深度最多为∣Σ∣
    //空间复杂度：O(∣Σ∣^2) 递归的深度为O(∣Σ∣)，每层递归需要开辟O(∣Σ∣)的额外空间
    public int longestSubstring(String s, int k) {

        char[] sc = s.toCharArray();

        return longestSubstring(sc, 0, sc.length-1, k);
    }

    public int longestSubstring(char[] sc, int left, int right, int k) {
        int ans = 0;

        //找划分点
        int[] scs = new int[26];
        for(int i=left; i<=right; i++) {
            scs[sc[i]-'a']++;
        }

        char pivot = 0;
        for(int i=0; i<26; i++) {
            if(scs[i]>0 && scs[i]<k) {
                pivot = (char)(i+'a');
                break;
            }
        }

        if(pivot == 0) {
            return right-left+1;
        }

        //对划分点划分的不同段进行分治处理 用双指针标识每一段的起始位置
        int p = left;
        int q = left;
        while(q <= right) {

            while(p<=right && sc[p]==pivot) {
                p++;
            }

            if(p>right) {
                break;
            }

            q = p;
            while(q<=right && sc[q]!=pivot) {
                q++;
            }
            q--;

            int len = longestSubstring(sc, p, q, k);
            ans = Math.max(ans, len);

            p = q+1;
            q = p;
        }

        return ans;
    }

    //方法二：滑动窗口
    //枚举字符出现种类数(范围1~26) 每指定一个字符出现种类数进行一轮的滑窗处理
    public int longestSubstring2(String s, int k) {
        int ans = 0;
        char[] sc = s.toCharArray();
        int[] scs = new int[26];
        int len = s.length();

        int m = 1;  //字符出现种类数
        while(m <= 26) { //枚举字符出现种类数
            Arrays.fill(scs,0);  //注意：每次种类数改变后，需要重新计数
            int p = 0;
            int q = 0;
            int sum = 0;  //滑窗中出现的字符种类数
            int tsum = 0;  //滑窗中出现的合格字符种类数
            while(q < len) {
                int qi = sc[q]-'a';
                scs[qi]++;
                if(scs[qi] == 1) {  //从无到有，字符种类数加1
                    sum++;
                }

                if(scs[qi] == k) { //达到符合条件的最小值，合格字符种类数加1
                    tsum++;
                }

                //当滑窗中的字符种类数超出约定时 窗口左边界滑动一格
                while(sum > m) {
                    int pi = sc[p]-'a';
                    scs[pi]--;

                    if(scs[pi] == 0) {  //从有到无，字符种类数减1
                        sum--;
                    }

                    if(scs[pi] == k-1) { //刚刚不符合条件，合格字符种类数减1
                        tsum--;
                    }
                    p++;
                }

                //字符种类数都符合条件时获得一个子串，比较一次长度 （此时字符种类数sum不一定等于约定的m）
                if(sum == tsum) {
                    ans = Math.max(ans, q-p+1);
                }

                q++;
            }

            m++;
        }

        return ans;
    }

}
