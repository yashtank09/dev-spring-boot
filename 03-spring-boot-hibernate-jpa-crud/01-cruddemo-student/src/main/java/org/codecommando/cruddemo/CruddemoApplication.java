package org.codecommando.cruddemo;

import org.codecommando.cruddemo.dao.StudentDao;
import org.codecommando.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    // Commandline runner to display things in Commandline
    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            // createStudent(studentDao);
            createMultipleStudent(studentDao);
            // readStudent(studentDao);
            // queryForStudents(studentDao);
            // queryForStudentsByLastName(studentDao);
            // updateStudent(studentDao);
            // deleteStudent(studentDao);
            // deleteAllStudents(studentDao);
        };
    }

    private void deleteAllStudents(StudentDao studentDao) {
        System.out.println("Deleting all students");
        int numberOfDeletedStudents = studentDao.deleteAll();
        System.out.println("Deleted " + numberOfDeletedStudents + " students");
    }

    private void deleteStudent(StudentDao studentDao) {
        int studentId = 4;
        System.out.println("Deleting student: " + studentId);
        studentDao.delete(studentId);
    }

    private void updateStudent(StudentDao studentDao) {
        // retrieve student based on the ID: primary key
        int studentId = 2;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDao.findById(studentId);
        // change first name to "Scooby"
        System.out.println("Updating student...");
        myStudent.setFirstName("Scooby");
        // update the student
        studentDao.update(myStudent);
        // display the update student
        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByLastName(StudentDao studentDao) {
        System.out.println("List of student: ");
        // get list of students
        List<Student> students = studentDao.findByLastName("Sumera");
        // display list of students
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDao studentDao) {
        System.out.println("List of students: ");
        // get list of students
        List<Student> students = studentDao.findAll();
        // display list of students
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDao studentDao) {
        // create a student object\
        System.out.println("Creating new student");
        Student newStudent1 = new Student("Peter", "Parker", "spiderman@gmail.com");
        // save the student
        System.out.println("Saving student");
        studentDao.save(newStudent1);
        // display id fo the saved student
        int studentId = newStudent1.getId();
        System.out.println("Retrieving student with id: " + studentId);
        // retrive student based on the id: primary key
        System.out.println("Retrieving student by id: " + studentId);
        Student outStudent = studentDao.findById(studentId);
        // display student
        System.out.println("Found student: " + outStudent);
    }

    private void createMultipleStudent(StudentDao studentDao) {
        // create multiple student
        System.out.println("Creating new student");
        Student newStudent1 = new Student("John", "Deao", "jd@gmail.com");
        Student newStudent2 = new Student("Baby", "Don", "babydon@gmail.com");
        Student newStudent3 = new Student("Virat", "Sumera", "virat@gmail.com");
        // saving students
        studentDao.save(newStudent1);
        studentDao.save(newStudent2);
        studentDao.save(newStudent3);
        // Note: we can ALTER table for changing auto increment values for resetting it we can use TRUNCATE

        //
    }

    private void createStudent(StudentDao studentDao) {
        // create the student object
        System.out.println("Creating new student");
        Student newStudent = new Student("Yash", "Tank", "yash@gmail.com");
        // save the student object
        System.out.println("Saving new student");
        studentDao.save(newStudent);
        // display id of the saved student
        System.out.println("Student saved. ID: " + newStudent.getId());

    }
}
