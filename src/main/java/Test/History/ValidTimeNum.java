package Test.History;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Felix
 * @date: 2023/8/3
 * @description:
 */
public class ValidTimeNum {

    public static void main(String[] args) {
        ValidTimeNum O = new ValidTimeNum();
        int A = 6;
        int B = 2;
        int C = 4;
        int D = 7;

        int res = O.solution(A, B, C, D);
        System.out.println(res);

    }

    public int solution(int A, int B, int C, int D) {
        int result = 0;
        int[] array= {A, B, C, D};

        List<List<Integer>> ress = permuteUnique(array);
        System.out.println(ress);

        for(List<Integer> res : ress) {
            if(isValid(res)) {
                result++;
            }
        }

        return result;
    }


    public boolean isValid(List<Integer> res) {
        int A = res.get(0);
        int B = res.get(1);
        int C = res.get(2);
        int D = res.get(3);

        boolean high = false;
        boolean low = false;
        if(A==0 || A*10+B < 24) {
            high = true;
        }

        if(C==0 || C*10+D < 60) {
            low = true;
        }

        if(high && low) {
            return true;
        }

        return false;
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new LinkedList<>();
        Arrays.sort(nums);

        backstrace(nums, res, visited, path);

        return res;
    }

    public void backstrace(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> path) {
        if(path.size()==nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(visited[i]) {
                continue;
            }

            if(i>0 && nums[i]==nums[i-1] && !visited[i-1]) {
                continue;
            }

            visited[i] = true;
            path.add(nums[i]);

            backstrace(nums, res, visited, path);

            path.remove(path.size()-1);
            visited[i] = false;
        }
    }
}
