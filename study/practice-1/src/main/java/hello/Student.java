package hello;

import java.util.Random;

/**
 * Created by asxat on 2/11/17.
 */
public class Student {

    private long id;
    private String fullname;
    private int age;
    private String group;

    public Student(long id, String name, String surname, int age, String group) {
        this.id = id;
        this.fullname = name + " " + surname;
        this.age = age;
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

class Group {

    private static String[] items = {"A03", "B03", "C03", "D03"};

    public static String getGroup() {
        Random r = new Random();
        return items[r.nextInt(items.length)];
    }

}
