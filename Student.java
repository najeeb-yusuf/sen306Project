import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Student{
    public ArrayList<String> classes;
    private String idno;
    private String name;
    private int age;
    private String gender;

    public Student(String n, int a, String g, String idno) {
        name = n;
        age = a;
        gender = g;
        this.idno = idno;
        classes = new ArrayList<String>();}

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public void addClass(Course course) {
        classes.add(course.getCourseTitle());
        course.students.add(idno);

    }

    public void removeClass(Course course) {
        classes.remove(course.getCourseTitle());
        course.students.remove(idno);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}