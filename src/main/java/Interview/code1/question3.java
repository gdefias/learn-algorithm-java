package Interview.code1;

/**
 * @author: Defias
 * @date: 2021/3/5
 * @description:
 */
public class question3 {

    public static void main(String[] args) {
        int[] A = new int[] {12, 23, 34, 35, 55, 66};
        System.out.println(Search(A, 34));
    }

    public static int Search(int[] A, int target) {
        if(A==null || A.length==0 || target<A[0] || target>A[A.length-1]) {
            return -1;
        }

        int i = 0;
        int j = A.length-1;

        while(i<=j) {
            int mid = i + (j - i)/2;
            if(A[mid]==target) {
                return mid;
            } else if(A[mid]>target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return -1;
    }


}
