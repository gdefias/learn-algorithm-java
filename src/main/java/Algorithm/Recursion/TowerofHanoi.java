package Questions.Recursion;
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

        System.out.println("The moves are:");
        moveDisks(n, 'A', 'B', 'C');
    }

    /** The method for finding the solution to move n disks from fromTower to toTower with auxTower */
    public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        if (n == 1) // Stopping condition
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
        else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }
}
