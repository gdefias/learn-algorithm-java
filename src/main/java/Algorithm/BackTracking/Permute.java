package Algorithm.BackTracking;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Defias on 2016/6/5

 全排列
 https://leetcode-cn.com/problems/permutations/

 数组全排列
 给定一个 没有重复 数字的序列，返回其所有可能的全排列

 示例：
 输入: [1,2,3]
 输出: [
           [1,2,3],
           [1,3,2],
           [2,1,3],
           [2,3,1],
           [3,1,2],
           [3,2,1]
        ]
 */


public class Permute {

    public static void main(String[] args) {
        int[] array={1,2,3};
        List<List<Integer>> res = permute4(array);
        System.out.println(res);
    }

    //回溯算法 O(n∗n!)
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();  //存放所有排列结果
        boolean[] visited = new boolean[nums.length];  //标识已经遍历的数的索引
        List<Integer> path = new LinkedList<>(); //存放单个排列结果

        backtrack(nums, res, visited, path);

        return res;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> path) {
        if(path.size()==nums.length) {
            res.add(new ArrayList<>(path));
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            path.add(nums[i]);

            backtrack(nums, res, visited, path);

            path.remove(path.size()-1);
            visited[i] = false;
        }

    }


    //版本2：回溯  使用双端队列Deque（相当于栈）
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();  //存放所有排列结果

        Deque<Integer> path = new ArrayDeque<>();  //栈 只在同一端进和出  记录已经遍历的数

        boolean[] visited = new boolean[nums.length];  //记录某个索引是否已经被遍历了
        int depth = 0;  //深度：相当于记录已遍历的数的个数

        dfs(nums, depth, path, visited, res);

        return  res;
    }

    public static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] visited, List<List<Integer>> res) {
        if(depth==nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            path.addLast(nums[i]);

            dfs(nums, depth+1, path, visited, res);

            //回溯算法的精髓
            path.removeLast();
            visited[i] = false;
        }
    }


    //版本3：对回溯算法的空间优化 节省visited数组
    public static List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();  //存放所有排列结果

        List<Integer> path =  new ArrayList<>();
        for(int num: nums) {
            path.add(num);
        }

        int first = 0;  //从左往右填到第first个位置 当first==n时说明已经填完了n个位置
        backtrack3(path, res, 0);
        return res;
    }

    //没有按字典序存储各排列
    public static void backtrack3(List<Integer> path, List<List<Integer>> res, int first) {
        if(first==path.size()) {  //所有的数都填完了，形成了1个排列，加入结果中
            res.add(new ArrayList<>(path));
            return;
        }

        //已经填到第first个位置，那么nums[]数组中[0,first−1]是已填过的数的集合，[first,n−1] 是待填的数的集合
        for(int i=first; i<path.size(); i++) {
            Collections.swap(path, first, i);

            backtrack3(path, res, first+1);

            Collections.swap(path, first, i);  //交换回来
        }
    }


    //版本4：递归 ？？
    public static List<List<Integer>> permute4(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());

        helper4(numslist, res);

        return res;
    }

    public static void helper4(List<Integer> numslist, LinkedList<List<Integer>> res) {
        int n = numslist.size();
        if(numslist.size()==1) {
            res.addLast(new ArrayList<>(numslist));
            return;
        }

        //被删掉的数
        int lastnum = numslist.remove(numslist.size()-1);

        helper4(numslist, res);

        int size = res.size();
        for(int i=0; i<size; i++) {
            System.out.println("res: " + res);
            List<Integer> tmplist = new ArrayList<Integer>(res.getFirst());
            res.removeFirst();
            System.out.println("n: " + n);
            System.out.println("lastnum: " + lastnum);

            //向不同位置插入原来被删掉的数
            for(int j=0; j<n; j++) {
                tmplist.add(j, lastnum);
                res.addLast(new ArrayList<>(tmplist));
                tmplist.remove(j);
            }
            System.out.println("res: " + res);
        }
        return;
    }
}
