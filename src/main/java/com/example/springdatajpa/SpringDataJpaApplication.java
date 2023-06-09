package com.example.springdatajpa;

import com.example.springdatajpa.student.Student;
import com.example.springdatajpa.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student student1 = new Student(
                    "jarin",
                    "anjum",
                    "jarinanjum@gmail.com",
                    22);
            Student student2 = new Student(
                    "sifat",
                    "nur",
                    "sifatnur@gmail.com",
                    20);
            List studentList = new ArrayList();
            studentList.add(student1);
            studentList.add(student2);
            studentRepository.saveAll(studentList);
            System.out.println("Student count:" + studentRepository.count());

            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(student -> {
                        System.out.println(student);
                    }, () -> {
                        System.out.println("Student with id 2 not found");
                    });

            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(System.out::println, () -> System.out.println("Student with id 3 not found"));

            List<Student> students = studentRepository.findAll();
            for (Student student :
                    students) {
                System.out.println(student);
            }

            studentRepository.deleteById(1L);

            System.out.println("Student count:" + studentRepository.count());
        };
    }

}
