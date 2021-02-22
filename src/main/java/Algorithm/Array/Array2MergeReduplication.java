package Questions.Array;
import java.util.*;
/**
 * Created by Defias on 2017/10/8.

 合并区间
 给出若干闭合区间，合并所有重叠的部分

 https://leetcode-cn.com/problems/merge-intervals/

 输入: [[1,3],[2,6],[8,10],[15,18]]
 输出: [[1,6],[8,10],[15,18]]
 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

 输入: [[1,4],[4,5]]
 输出: [[1,5]]
 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

 */


public class Array2MergeReduplication {

    public static void main(String[] args) {
//        ArrayList<Interval> intervals = new ArrayList<Interval>();
//        intervals.add(new Interval(1,3));
//        intervals.add(new Interval(2,6));
//        intervals.add(new Interval(8,10));
//        intervals.add(new Interval(15,18));
//        ArrayList<Interval> result = merge(intervals);
//        for(Interval item: result) {
//            System.out.print(item.start+" "+item.end+"\n");
//        }
//
//        ArrayList<int[]> res = new ArrayList<>();
//        res.toArray();


        int[][] intervals = new int[][] {{2,6},{1,3},{15,18},{8,10}};

        int[][] res = merge(intervals);
        for(int[] i : res) {
            System.out.println(i[0]+" "+i[1]);
        }
    }




    //数组形式
    public static int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length<=1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));  //按左端点升序

        ArrayList<int[]> res = new ArrayList<>();
        int[] last = intervals[0];

        for(int i=1; i<=intervals.length-1; i++) {
            if(intervals[i][1] > last[1] && intervals[i][0] > last[1]) {  //无需合并的情况
                res.add(last);
                last = intervals[i];
            } else {  //需要合并的情况
                last[0] = Math.min(last[0], intervals[i][0]);
                last[1] = Math.max(last[1], intervals[i][1]);
            }
        }
        res.add(last);

        return res.toArray(new int[res.size()][]);  //ArrayList<int[]>转int[][]的正确姿态
    }


    //类形式
    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());

        ArrayList<Interval> result = new ArrayList<Interval>();

        Interval last = intervals.get(0);
        for (int i=1; i<intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end ) {  //需要合并的情况
                last.end = Math.max(last.end, curt.end);
            } else {  //无需合并的情况
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;
    }

    //比较器：按左端点升序
    private static class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

    //区间类
    public static class Interval {
        int start;
        int end;
        Interval() {
            start = 0; end = 0;
        }

        Interval(int s, int e) {
            start = s; end = e;
        }
    }
}
