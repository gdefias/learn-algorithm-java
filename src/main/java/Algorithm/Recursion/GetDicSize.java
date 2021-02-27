package Algorithm.Recursion;
import java.io.File;
import java.util.Scanner;
/**
 * Created by Defias on 2017/2/25.

 递归应用：获得目录的大小
 */

public class GetDicSize {
    public static void main(String[] args) {
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    public static long getSize(File file) {
        long size = 0; // Store the total size of all files

        if (file.isDirectory()) {
            File[] files = file.listFiles(); // All files and subdirectories
            for (int i = 0; i < files.length; i++) {
                size += getSize(files[i]); // Recursive call
            }
        } else { // Basic case
            size += file.length();
        }
        return size;
    }
}
