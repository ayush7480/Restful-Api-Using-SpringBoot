package com.springboot.api.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;
import static java.time.Month.MARCH;

@Configuration
public class StudentConfig
{

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository reporsitory)
    {
        return args -> {
             Student ayush = new Student(
                    "Ayush Sharma",
                    LocalDate.of(2002, MARCH, 10),
                    "ayushsharma7480@gmail.com"

            );

            Student kabir = new Student(
                    "Kabir",
                    LocalDate.of(2002, DECEMBER, 11),
                    "kabirsharma8823@gmail.com"

            );

            reporsitory.saveAll(
                    List.of(ayush, kabir)
            );
        };

    }

}
