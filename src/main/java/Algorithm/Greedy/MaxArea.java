package Algorithm.Greedy;

/**
 * @author: Felix
 * @date: 2023/1/3
 * @description: 盛最多水的容器

   https://leetcode.cn/problems/container-with-most-water/

   给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
   找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
   返回容器可以储存的最大水量。

   输入：[1,8,6,2,5,4,8,3,7]
   输出：49

   输入：height = [1,1]
   输出：1
 */
public class MaxArea {

   public static void main(String[] args) {
      MaxArea O = new MaxArea();
      int[] height = new int[] {1,8,6,2,5,4,8,3,7};
      System.out.println(O.maxArea(height));
   }

   //双指针
   //时间复杂度：O(N)，双指针总计最多遍历整个数组一次
   //空间复杂度：O(1)，只需要额外的常数级别的空间
   public int maxArea(int[] height) {

      int p = 0;
      int q = height.length-1;
      return maxArea(height, p, q);
   }

   public int maxArea(int[] height, int p, int q) {
      int ans = 0;

      while(p < q) {
         int area = (q-p) * Math.min(height[p], height[q]);
         ans = Math.max(ans, area);

         if(height[p] < height[q]) {
            p++;
         } else{
            q--;
         }
      }

      return ans;
   }

}
