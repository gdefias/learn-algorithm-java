package Algorithm.DP.SubSequence;

import java.util.HashSet;

/**
 * @author: Felix
 * @date: 2023/1/11
 * @description: 最长重复子数组

    https://leetcode.cn/problems/maximum-length-of-repeated-subarray/description/

    给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。

    输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
    输出：3
    解释：长度最长的公共子数组是 [3,2,1] 。

    输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
    输出：5


    提示：
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 100
 */
public class FindLength {
    public static void main(String[] args) {
        FindLength O = new FindLength();
        int[] nums1 = new int[] {1,2,3,2,1,8,5};
        int[] nums2 = new int[] {3,2,1,4,7,5};

        System.out.println(O.findLength2(nums1, nums2));
    }

    //方法1：DP
    //时间复杂度：O(N×M)
    //空间复杂度：O(N×M)
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] dp = new int[len1][len2];  //dp[i][j]: nums1中以下标i开始，nums2中以下标j开始的最长重复子数组长度

        int ans = 0;
        for(int i=len1-1; i>=0; i--) {
            for(int j=len2-1; j>=0; j--) {
                if(nums1[i] == nums2[j]) {
                    if(i<len1-1 && j<len2-1) {
                        dp[i][j] = dp[i+1][j+1]+1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 0;
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }


    //方法2：滑动窗口
    //时间复杂度：大于O(N×M)
    //空间复杂度：O(1)
    public int findLength2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int ans = 0;
        int i = 0;
        int j = 0;
        while(j<len1) {
            for(int k=0; k<len2; k++) {
                if(nums2[k] == nums1[j]) {
                    int p = k;
                    while(p<len2 && j<len1 && nums2[p]==nums1[j]) {
                        p++;
                        j++;
                    }
                    ans = Math.max(ans, j-i);
                    j = i;
                }
            }
            i++;
            j = i;
        }

        return ans;
    }


    //方法3：二分查找 + 哈希
    //时间复杂度：O((M+N)log(min(M,N)))
    //空间复杂度：O(N)
    public int findLength3(int[] nums1, int[] nums2) {
        //双指针夹逼
        int left=0;
        int right=Math.min(nums1.length,nums2.length);//最短长度
        while(left<right){
            //二分查找，left保存
            int mid=(left+right+1)>>>1;// /2取得中间值
            if(!isok(nums1,nums2,mid)){ //判断是否重叠
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left;//长度

    }


    public boolean isok(int[] nums1, int[] nums2,int length){
        //哈希set集合去重
        HashSet<Long> set=new HashSet<>();//集合，没有重复
        //定义一个质数
        long p=101;//0 <= nums1[i], nums2[i] <= 100

        //计算哈希值nums1,长度哈希值
        long hashA=0;
        long pw=1;
        for(int i=0;i<length;i++){
            hashA=(hashA*p+nums1[i]);
            pw*=p;
        }
        set.add(hashA);//保存哈希值A 最终哈希值
        for(int i=length;i<nums1.length;i++){
            hashA=hashA*p-nums1[i-length]*pw+nums1[i]; //计算阶段性的哈希值
            set.add(hashA);
        }


        //计算哈希值nums2,长度
        long hashB=0;
        for(int i=0;i<length;i++){
            hashB=(hashB*p+nums2[i]);
        }
        if(set.contains(hashB)){
            return true;//存在相等的最大值
        }
        for(int i=length;i<nums2.length;i++){
            hashB=hashB*p-nums2[i-length]*pw+nums2[i];//计算阶段性的哈希值

            if(set.contains(hashB)){
                return true;//存在相等的最大值
            }
        }

        return false;//没有
    }
}
