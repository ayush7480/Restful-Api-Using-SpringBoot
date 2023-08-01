package com.springboot.api.student;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //this is bean also we can use Component

public class StudentService
{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents()
    {
          return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws IllegalAccessException {
        Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());
        studentRepository.save(student);

        if(studentByEmail.isPresent())
        {
            {
                throw new IllegalAccessException("email taken");
            }
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId)
    {
        studentRepository.findById(studentId);
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("student with id: " + studentId + " does not exists.");
        }

        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists."));

        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) //Objects.equals keyword is used for if the name is not the same as the previous one.
        {
            student.setName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals((student.getEmail()), email))
        {
           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

           if(studentOptional.isPresent())
           {
               throw new IllegalStateException("email taken");
           }
            student.setEmail(email);
        }
    }
}
