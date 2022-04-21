import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Student{
    public ArrayList<String> classes;
    private String idno;
    private String fname;

    private String lname;

    private int age;
    private String gender;

    public Student(String fn, String ln, int a, String g, String idno) {
        fname = fn;
        lname = ln;
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

    public String getFirstName() {
        return fname;
    }

    public String getLastname() {
        return lname;
    }

    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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