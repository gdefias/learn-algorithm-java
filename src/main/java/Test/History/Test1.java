package Test.History;

import java.util.*;

/**
 * @author: Felix
 * @date: 2023/8/3
 * @description:
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 O = new Test1();
        int[] A =  {1, 3, 6, 4, 1, 2, 8, 9, 24, 45};

        List<Integer> result = O.solution2(A);
        for(Integer item: result) {
            System.out.println(item);
        }
    }

    public List<Integer> solution(int[] A) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<A.length; i++) {
            pq.offer(A[i]);
        }

        for(int i=0; i<5; i++) {
            result.add(pq.poll());
        }

        return result;
    }

    public List<Integer> solution2(int[] A) {
        List<Integer> result = new ArrayList<>();
        if(A == null || A.length < 5) {
            return result;
        }

        buildHeap(A);
        for(int i=A.length-1; i>=A.length-5; i--) {
            result.add(A[0]);
            swap(A, 0, i);
            reBuildHeap(A, 0, i-1);
        }

        return result;
    }

    public void buildHeap(int[] A) {
        if(A==null || A.length <= 1) {
            return;
        }

        for(int i=1; i<A.length; i++) {
            int child = i;
            int parent = getParent(child);

            while(A[child] > A[parent]) {
                swap(A, child, parent);

                child = parent;
                if(child <= 0) {
                    break;
                }
                parent = getParent(child);
            }
        }
    }

    public void reBuildHeap(int[] A, int parent,  int last) {
        if(parent == last) {
            return;
        }

        int[] childs = getChilds(parent);
        int left = childs[0];
        int right = childs[1];

        int best = parent;
        if(left <= last && A[left] > A[best]) {
            best = left;
        }

        if(right <= last && A[right] > A[best]) {
            best = right;
        }

        if(best != parent) {
            swap(A, parent, best);
            reBuildHeap(A, best, last);
        }
    }


    public int[] getChilds(int parent) {
        int[] childs = new int[2];
        int left = parent * 2 + 1;
        int right = left + 1;

        childs[0] = left;
        childs[1] = right;
        return childs;
    }


    public int getParent(int child) {
        int parent = (child - 1) / 2;
        return parent;
    }


    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

}
