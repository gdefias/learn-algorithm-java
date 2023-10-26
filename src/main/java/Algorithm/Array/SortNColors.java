package Algorithm.Array;
import Lib.Util;
/**
 * Created by Defias on 2017/10/8.

    颜色分类
    给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序
 */

public class SortNColors {

    public static void main(String[] args) {
        int[] colornums = new int[] {1, 0, 1, 2, 0, 0, 2, 2, 1};
        sortColors(colornums, 3);
        Util.printArray(colornums);
    }

    //每次排好左右两端的两种颜色，直到所有颜色都排完
    public static void sortColors(int[] colors, int k) {
        int count = 0;
        int start = 0;
        int end = colors.length-1;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = start; i <= end; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }

            int left = start;
            int right = end;
            int cur = left;
            while(cur <= right) {
                if (colors[cur] == min) {
                    swap(left, cur, colors);
                    cur++;
                    left++;
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    //int tmp = colors[cur];
                    swap(cur, right, colors);
                    right--;
                }
            }
            count += 2;
            start = left;
            end = right;
        }
    }

    private static void swap(int left, int right, int[] colors) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }

}
