package Questions.Sorts;

import java.util.*;
import static Lib.Object.*;

/**
 * Created by Defias on 2017/9/16.
 * @author yzh
 *
 * Arrays.sort 与 Collections.sort
 */
public class SortObject {

    public static void main(String[] args) {
        int[] array = {6, 4, 5, 7, 2, 4, 3, 4, 7, 8};
        Arrays.sort(array);
        printArray(array);

        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(8);
        list.add(1);
        list.add(2);
        Collections.sort(list);
        System.out.println(list);

        javaSortExample();
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void javaSortExample() {
        Student[] students = new Student[5];
        students[0] = new Student("Jack", 100);
        students[1] = new Student("Tom", 80);
        students[2] = new Student("Pony", 90);
        students[3] = new Student("Andy", 98);
        students[4] = new Student("John", 56);

        Arrays.sort(students, new StudentScoreComparator());
        for (Student student : students) {
            System.out.println(student.name + ": " + student.score);
        }

        List<Student> studentList = Arrays.asList(students);
        Collections.sort(studentList, new StudentScoreComparator());
        System.out.println(studentList);
    }
}





