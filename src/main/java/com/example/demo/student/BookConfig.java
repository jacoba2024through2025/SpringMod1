package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Book book1 = new Book(
                    "The Great Gatsby",
                    "F. Scott Fitzgerald",
                    LocalDate.of(1925, 4, 10),
                    18
            );

            Book book2 = new Book(
                    "To Kill a Mockingbird",
                    "Harper Lee",
                    LocalDate.of(1960, 7, 11),
                    14
            );

            repository.saveAll(List.of(book1, book2));
        };
    }
}

