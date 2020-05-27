package test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestMap {
    public static void main(String[] args) {
        Map<Student, String> map = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge()-o1.getAge();
            }
        });
        Student s1 = new Student(10, "xiaohai");
        Student s2 = new Student(15, "dahai");
        Student s3 = new Student(20, "zhuojh");
        map.put(s1, s1.getName());
        map.put(s2, s2.getName());
        map.put(s3, s3.getName());
        System.out.println(map);
    }
}
class Student /*implements Comparable<Student>*/{
    private int age;
    private String name;

    public Student(int age, String name){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*@Override
    public int compareTo(Student o) {
        return this.age-o.age;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
