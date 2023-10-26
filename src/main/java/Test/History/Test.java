package Test.History;

/**
 * @author: Felix
 * @date: 2023/8/3
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        Test O = new Test();
        int[] A =  {1, 3, 6, 4, 1, 2};

        int res = O.solution(A);
        System.out.println(res);
    }


    public int solution(int[] A) {
        int[] index = new int[1000001];

        boolean isExit = false;
        for(int num : A) {
            if(num > 0) {
                index[num]++;
                if(!isExit) {
                    isExit = true;
                }
            }
        }

        if(!isExit) {
            return 1;
        }

        for(int i=1; i<1000001; i++) {
            if(index[i] == 0) {
                return i;
            }
        }

        return 0;
    }

}
