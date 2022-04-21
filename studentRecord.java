import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/*
 after I finished writing the nonsense code for file management, I read the documentation for the scanner
 object, and I could've just used a delimiter. Nonetheless, it works.
*/
public class studentRecord {
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public studentRecord() {

        // create all the student objects
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
                String fname = null;String lname = null; int age = 0; String gender = null; String id = null;
                // loop through each character until the last one
                for (int i = 0; i < data.length(); i++){
                    // when i reach the end add the value of property to id
                    if (i == data.length() - 1){
                        // I am hard coding at this point, if I restructure my code this will look better
                        // I do not feel like rewriting this as working with files in java is time-consuming
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
                                fname = property;
                                property = "";
                                counter += 1;
                            }
                            case 1 -> {
                                lname = property;
                                property = "";
                                counter += 1;
                            }
                            case 2 -> {
                                age = Integer.parseInt(property.stripLeading());
                                property = "";
                                counter += 1;
                            }
                            case 3 -> {
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
                Student studentInstance = new Student(fname,lname, age,gender,id);
                students.add(studentInstance);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // get the courses from the database
        try {
            //This function goes through the database and parses the string into a Course Object
            File courseFile = new File("courses.txt");
            Scanner courseReader = new Scanner(courseFile);
            // go through each line in our course file
            while (courseReader.hasNextLine()) {
                String data = courseReader.nextLine();
                int index = 0;
                //instantiate all the variables we need to construct our student class
                // loop through each character until the last one
                for (int i = 0; i < data.length(); i++){
                    if (data.charAt(i) == ','){
                        index = i;
                    }
                }
                Course courseInstance = new Course(data.substring(0,index), data.substring(index+1));
                // add all the students in the class
                try {
                    //This function goes through the database and parses the string into a Student Object
                    File relationshipsFile = new File("relationships.txt");
                    Scanner relationshipsReader = new Scanner(relationshipsFile);
                    // go through each line in our students file
                    while (relationshipsReader.hasNextLine()) {
                        String relationship = relationshipsReader.nextLine();
                        int indexRelationship = 0;
                        // loop through each character until the last one
                        for (int i = 0; i < relationship.length(); i++) {
                            if (relationship.charAt(i) == ',') {
                                indexRelationship = i;
                            }
                        }
                        // review this
                        if (relationship.substring(indexRelationship + 1).equals(data.substring(0, index))){
                            courseInstance.addStudent( findStudent(relationship.substring(0,indexRelationship)));}
                    }
                }catch (FileNotFoundException e) {
                    java.lang.System.out.println("An error occurred with 'relationship.txt' ");
                    e.printStackTrace();
                }
                courses.add(courseInstance);
            }
            courseReader.close();
        }catch (FileNotFoundException e) {
            java.lang.System.out.println("An error occurred with 'courses.txt'");
            e.printStackTrace();
        }

    }
    public void newStudent(Student student){
        try {
            FileWriter myWriter = new FileWriter("students.txt", true);

            // write all the details to the database 'students.txt'
            myWriter.write(student.getFirstName());
            myWriter.write(',');
            myWriter.write(student.getLastname());
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
    public void newCourse(Course course){
        try {
            FileWriter myWriter = new FileWriter("courses.txt", true);

            // write all the details to the database 'courses.txt'
            myWriter.write(course.getCourseTitle());
            myWriter.write(',');
            myWriter.write(course.getCourseName());
            myWriter.write('\n');

            myWriter.close();
            java.lang.System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static Student findStudent(String studentId){
        Student student = null;
        for (Student s : students){
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
        for (Course c : courses){
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
    public void assignStudent(String studentId, String courseTitle){
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
        * Not working please do not run to avoid stories that touch
        *
        *
        *
        * */
        try {
            File myFile = new File("relationships.txt");
            Scanner myReader = new Scanner(myFile);
            FileWriter myWriter = new FileWriter(myFile, true);

            //both student and course have to exist
            if (findStudent(studentId) == null || findCourse(courseTitle ) == null){
                System.out.println("Please check your parameters");
            }else{
                Course course = findCourse(courseTitle);
                Student student = findStudent(studentId);
                // remove the course from the student object
                // this automatically removes the course from the student object and also removes the student from the course object
                course.removeStudent(student);
                //generate the string equivalent to what is in my database
                String databaseEntry = studentId + ',' + courseTitle;
                while (myReader.hasNextLine()){
                    String data = myReader.nextLine();
                    if (!(data.equals(databaseEntry))){
                        // if the string does not my database entry, create a new file relationships.txt and append th
                        myWriter.write(data);
                    }else{
                        //otherwise replace the data in thesame relationships.txt file
                        // this seems highly inefficient because it is, i can instead write an empty string to the document when I find it.
                        // I tried looking for ways to do this but they also use thesame method
                        continue;
                    }
                }
            System.out.print(student.getFirstName());
            System.out.print(" has been removed from ");
            System.out.print(course.getCourseTitle());

            }
            myReader.close();
            myWriter.close();
        } catch (IOException e) {
            java.lang.System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void printSummary(){
        System.out.println("Total number of students: " + students.size());
        for (Course i : courses){
            System.out.println("Course:" + i.getCourseName());
            System.out.println("Enrolled students:" + i.students.size());


        }


    }

}
