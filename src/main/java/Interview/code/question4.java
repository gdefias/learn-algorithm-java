package Interview.code;

/**
 * @author: Defias
 * @date: 2021/3/11
 * @description:
 */
public class question4 {
    public static void main(String[] args) {
        int[] A = new int[] {1, 1, 1, 2, 3, 4, 4, 5, 6};
        System.out.println(deldup(A));
    }

    public static int deldup(int[] A) {
        if(A==null || A.length==0) {
            return 0;
        }

        if(A.length==1){
            return 1;
        }

        int i = 0;
        int j = 1;
        while(j<A.length) {
            if(A[j]!=A[i]) {
                i++;
                swap(A, j, i);
            }
            j++;
        }

        return i+1;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
