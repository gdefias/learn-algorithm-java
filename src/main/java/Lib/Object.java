package Lib;

import java.util.Comparator;

/**
 * Created by Defias on 2020/06.
 * Description:  公用类
 */
public class Object {

    //学生类
    public static class Student {
        public static int number = 0; //number表示学生个数
        public int id;
        public String name;
        public int score;

        public Student(String name) {
            this.id = ++number;
            this.name = name;
        }

        public Student(String name, int score) {
            this.id = ++number;
            this.name = name;
            this.score = score;
        }


        public void takeTest(int score) {
            if (score >= 0 && score <= 100) {
                this.score = score;
            }
        }

        public String getName() {
            return this.name;
        }

        public int getScore() {
            return this.score;
        }

        public int getId() {
            return this.id;
        }

        public void print() {
            System.out.println(name + ": " + score);
        }

        @Override
        public String toString() {
            return name + ": " + score;
        }
    }


    //学生分数比较器
    public static class StudentScoreComparator implements Comparator<Student> {
        public int compare(Student o1, Student o2) {
            return o1.score > o2.score ? -1 : 1;
        }
    }

    //学生姓名比较器
    public static class StudentNameComparator  implements Comparator<Student> {
        public int compare(Student student1, Student student2) {
            // when student1 should be placed before student2, return -1;
            return 0 - student1.name.compareTo(student2.name); // -1 0 1
        }
    }
}
