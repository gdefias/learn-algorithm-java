package Algorithm.Recursion;
import java.util.Scanner;
/**
 * Created by Defias on 2017/2/25.

 递归应用：汉诺塔问题

 https://leetcode-cn.com/problems/hanota-lcci/
 */
public class TowerofHanoi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = input.nextInt();

        System.out.println("The moves are:");  //A塔 --> C塔   B塔为辅助塔
        moveDisks(n, 'A', 'C', 'B');
    }

    public static void moveDisks(int n, char A, char C, char B) {
        if (n == 1) // Stopping condition
            System.out.println("Move disk " + n + " from " + A + " to " + C);
        else {
            moveDisks(n-1, A, B, C);
            System.out.println("Move disk " + n + " from " + A + " to " + C);
            moveDisks(n-1, B, C, A);
        }
    }
}
