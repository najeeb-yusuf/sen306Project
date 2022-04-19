import java.util.ArrayList;

public class Course {
    private String courseTitle;
    private String courseName;
    public ArrayList<String> students;

    public Course(){}

    public Course(String ct, String cn){
        courseTitle = ct;
        courseName = cn;
        students = new  ArrayList<String>();
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public boolean addStudent(Student s){
        if (students.contains(s.getIdno())){
            //System.out.println("This student is already enrolled in the class");
            // I can also print this in my main function
            return false;
        }
        else{
            students.add(s.getIdno());
            s.classes.add(courseTitle);
            // indicate to me that what I just did is successful
            return true;
        }

    }

    public boolean removeStudent(Student student){
        if (students.contains(student.getIdno())){
            students.remove(student.getIdno());
            student.classes.remove(courseTitle);
            return true;
        }
        else{
            //System.out.println("This student is not enrolled in the class");
            //I need to do this in my main function so for now just return false
            // if this result is none then I know to print that line
            return false;
        }

    }

}
