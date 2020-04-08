package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Java8新特性：FunctionInteface
 * @FunctionInterface 的作用是限制接口只能有一个方法
 * 去掉这个注解也可以正常运行，只不过在给接口添加新的方法不会在接口报错，而是在Main这里报错
 */
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("A", 10,20);
        Student student2 = new Student("B", 20,10);
        Student student3 = new Student("C", 15,30);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.sort(Student::compare);
        list.forEach((student)-> System.out.println(student));

        testFuncationInterface(Main::testGoSchool, student1, student2);
        testFuncationInterface(Main::testGoWangBa, student1, student2);
    }

    public static void testFuncationInterface(IGoSchool iGoSchool,Student student1, Student student2){
        iGoSchool.goSchool(student1, student2);
    }

    public static void testGoSchool(Student studentA, Student studentB){
        System.out.println(studentA+" go school with "+ studentB);
    }

    public static String testGoWangBa(Student studentA, Student studentB){
        String str = studentA+" go wangba with "+ studentB;
        System.out.println(str);
        return str;
    }
}
