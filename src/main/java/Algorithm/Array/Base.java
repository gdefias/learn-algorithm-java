package Algorithm.Array;

/**
 * @author: Defias
 * @date: 2020/12/1
 * @description:
 */
public class Base {
    public static void main(String[] args) {
        base1();
    }

    public static void findArray() {
        //max  sum
        int[] scoress = new int[] {90, 95, 92, 89, 100, 98};
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i=0; i < scoress.length; ++i) {
            if (scoress[i] > max) {
                max = scoress[i];
            }
            sum += scoress[i];
        }
        System.out.println(max);
        System.out.println(sum);

        //find target
        int[] scores = new int[] {90, 95, 92, 89, 100, 98};
        int target = 100;
        boolean foundFullMark = false;
        for (int i=0; i<scoress.length; ++i) {
            if (scores[i] == target) {
                foundFullMark = true;
                break;
            }
        }

        //find  passNumber
        int passNumber = 0;
        for (int i=0; i<scoress.length; ++i) {
            if (scoress[i]<60)
                continue;
            passNumber++;
        }
        System.out.println(passNumber);
        System.out.println("---------");
    }

    public static void base1() {
        // 与, 或, 非
        // and
        boolean isTodaySunny = true;
        boolean isWeekend = true;
        boolean shopping = isTodaySunny && isWeekend;
        System.out.println(shopping);

        // or
        boolean isWeekday = false;
        boolean isBusy = true;
        boolean working = isWeekday || isBusy;
        System.out.println(working);

        // not
        boolean handsome = false;
        handsome = !handsome;
        System.out.println(handsome);

        //优先级 not > and > or
        boolean female = true;
        int interviewScore = 1;
        boolean interviewPass1 = female && interviewScore >= 3 || !female && interviewScore >= 4;
        boolean interviewPass2 = interviewScore >= 4 || female && interviewScore >= 3;
        System.out.println(interviewPass1);
        System.out.println(interviewPass2);
        System.out.println("---------boolean");

        //char
        char character = '9';
        char prevCharacter = (char)(character - 1);
        System.out.println(prevCharacter);
        boolean larger = 'a' > 'A';
        System.out.println(larger);

        int delta = 'a' - 'A';
        System.out.println(delta);

        char ch = '字';
        int code = ch;
        System.out.println(code);

        char chr = 'a';
        char capitalCh = (char) (chr - 'a' + 'A');
        System.out.println(capitalCh);

        System.out.println((int) character);  //中文或英文字符的unicode码 十进制
        System.out.println((char) 21494);  //将十进制的unicode码转换为字符
        System.out.println(Integer.toHexString(21494));  //将十进制的unicode码转换为十六进制的字符串
        System.out.println("---------char");


        //array
        int[] scores = new int[] {90, 95, 92, 89, 100, 98};
        System.out.println(scores[0]);
        System.out.println(scores[3]);
        System.out.println(scores[scores.length - 1]);

        scores[3] = 90;
        scores[scores.length - 1] = 97;
        System.out.println(scores[3]);
        System.out.println(scores[scores.length - 1]);
        System.out.println("---------array");

        //2-d array
        int[][] matrix = new int[][] {
                {1, 4, 7, 10},
                {2, 6, 12, 15},
                {6, 8, 13, 20}
        };
        for (int i=0; i<matrix.length; ++i) {
            for (int j=0; j<matrix[0].length; ++j) {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
            //System.out.println();
        }

        int[][] matrixx = new int[][] {
                {1, 4, 7, 10, 1},
                {2, 6, 12, 15, 3},
                {6, 8, 13, 20, 4},
                {6, 8, 13, 20, 4}
        };
        System.out.println(matrixx[1][2]);
        System.out.println(matrixx[2][1]);

        matrixx[0][2] = 17;
        System.out.println(matrixx[0][2]); // 17
        System.out.println("---------2-d array");
    }
}
