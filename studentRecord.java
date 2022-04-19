import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/*
 after I finished writing the nonsense code for file management, I read the documentation for the scanner
 object and I could've just used a delimiter. Nonetheless, it works.
*/
public class studentRecord {

    public static void newStudent(Student student){
        try {
            FileWriter myWriter = new FileWriter("students.txt", true);

            // write all the details to the database 'students.txt'
            myWriter.write(student.getName());
            myWriter.write(',');
            myWriter.write(Integer.toString(student.getAge()));
            myWriter.write(',');
            myWriter.write(student.getGender());
            myWriter.write(',');
            myWriter.write(student.getIdno());
            myWriter.write('\n');

            myWriter.close();
            java.lang.System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void newCourse(Course course){
        try {
            FileWriter myWriter = new FileWriter("courses.txt", true);

            // write all the details to the database 'courses.txt'
            myWriter.write(course.getCourseTitle());
            myWriter.write(',');;
            myWriter.write(course.getCourseName());
            myWriter.write('\n');

            myWriter.close();
            java.lang.System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            //This function goes through the database and parses the string into a Student Object
            File myObj = new File("courses.txt");
            Scanner myReader = new Scanner(myObj);
            // go through each line in our students file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int index = 0;
                //instantiate all the variables we need to construct our student class
                String name = null; int age = 0; String gender = null; String id = null;
                // loop through each character until the last one
                for (int i = 0; i < data.length(); i++){
                    if (data.charAt(i) == ','){
                        index = i;
                    }
                }
                courses.add(new Course(data.substring(0,index), data.substring(index+1)));
        }
            myReader.close();
        }catch (FileNotFoundException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return courses;
    }
    public static ArrayList<Course> getRelationships(){
        ArrayList<Course> relationships = new ArrayList<>();
        try {
            //This function goes through the database and parses the string into a Student Object
            File myObj = new File("relationships.txt");
            Scanner myReader = new Scanner(myObj);
            // go through each line in our students file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int index = 0;
                //instantiate all the variables we need to construct our student class
                String name = null; int age = 0; String gender = null; String id = null;
                // loop through each character until the last one
                for (int i = 0; i < data.length(); i++){
                    if (data.charAt(i) == ','){
                        index = i;
                    }
                }
                relationships.add(new Course(data.substring(0,index), data.substring(index+1)));
        }
            myReader.close();
        }catch (FileNotFoundException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return relationships;
    }
    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();

        try {
            //This function goes through the database and parses the string into a Student Object
            File myObj = new File("students.txt");
            Scanner myReader = new Scanner(myObj);
            // go through each line in our students file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // this variable will store the values of the properties
                String property = "";
                //this will count how many variables we have stored currently
                int counter = 0;
                //instantiate all the variables we need to construct our student class
                String name = null; int age = 0; String gender = null; String id = null;
                // loop through each character until the last one
                for (int i = 0; i < data.length(); i++){
                    // when i reach the end add the value of property to id
                    if (i == data.length() - 1){
                        // I am hard coding at this point, if I restructure my code this will look better
                        // I do not feel like rewriting this as working with files in java is time consuming
                        // It feels like i am remaking the wheel
                        // I miss python

                        id = property += data.charAt(data.length()-1);
                    }
                    else if (data.charAt(i) == ','){
                        // I need to know when I'm dealing with the name, the age,
                        // the gender and the id number so everytime I come across a ','
                        // The first case should be a name
                        // the second case should be an integer number
                        // the third case should be a string gender
                        // the last case is covered in the if statement above
                        switch (counter) {
                            case 0 -> {
                                name = property;
                                property = "";
                                counter += 1;
                            }
                            case 1 -> {
                                age = Integer.parseInt(property.stripLeading());
                                property = "";
                                counter += 1;
                            }
                            case 2 -> {
                                gender = property;
                                property = "";
                                counter += 1;
                            }

                        }
                    }
                    else{
                        // if I do not have a comma then append the character to property
                        property += data.charAt(i);
                    }

                }
                Student studentInstance = new Student(name, age,gender,id);
                students.add(studentInstance);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return students;
    }


    public static Student findStudent(String studentId){
        Student student = null;
        for (Student s : getStudents()){
            if (s.getIdno().equals(studentId)) {
                student = s;
            }
        }
        if(student == null){
            System.out.print("Student with ID:");
            System.out.print(studentId);
            System.out.print(" not found");}
        return student;
    }
    public static Course findCourse(String courseTitle){
        Course course = null;
        for (Course c : getCourses()){
            if (c.getCourseTitle().equals(courseTitle)) {
                course = c;
            }
        }
        if(course == null){
            System.out.print("Course with title:");
            System.out.print(courseTitle);
            System.out.print(" not found");}
        return course;
    }


    public static void assignStudent(String studentId, String courseTitle){
        try {
            FileWriter myWriter = new FileWriter("relationships.txt", true);

            // write all the details to the database 'relationships.txt'
            myWriter.write(studentId);
            myWriter.write(',');
            myWriter.write(courseTitle);
            myWriter.write('\n');

            myWriter.close();
            System.out.print("Successfully assigned student with ID: ");
            System.out.print(studentId);
            System.out.print(" to the class ");
            System.out.print(courseTitle);

            // make the assignments within the Course object
            // This automatically makes the assignments within the Student object as well
            // This course uses search for students so I might as well write the code for it
            if (findStudent(studentId) == null || findCourse(courseTitle ) == null){
                System.out.println("Please check your parameters");
            }else{
                Course course = findCourse(courseTitle);
                Student student = findStudent(studentId);
                course.addStudent(student);
            }
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void removeStudent(String studentId, String courseTitle){
        // seems like I may need to create a new relationships.txt file and replace the other one
        // I will look through the documentation to avoid reinventing the wheel
        /*
        *
        *
        *
        * incomplete
        *
        *
        *
        * */
        try {
            File myFile = new File("relationships.txt");
            Scanner myReader = new Scanner(myFile);
            //both student and course have to exist
            if (findStudent(studentId) == null || findCourse(courseTitle ) == null){
                System.out.println("Please check your parameters");
            }else{
                Course course = findCourse(courseTitle);
                Student student = findStudent(studentId);
                course.removeStudent(student);
                String databaseEntry = studentId + ',' + courseTitle;
                while (myReader.hasNextLine()){
                    String data = myReader.nextLine();
                    if (data.equals(databaseEntry)){
                        myReader.;
                    }
                }
            }
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
