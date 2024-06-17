package com.example.appstudents.init;

import com.example.appstudents.entities.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
@ConditionalOnProperty("app.students.create-students-on-start.enabled")
public class StudentInitializer {
    private final Map<UUID, Student> studentMap = new HashMap<>();

    @PostConstruct
    public void conditionalStudentCreate() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/students.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    UUID uuid = UUID.randomUUID();
                    Student student = new Student(uuid, firstName, lastName, age);
                    studentMap.put(uuid, student);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке студентов из файла: " + e.getMessage());
        }
    }

    public Map<UUID, Student> getStudentMap() {
        return studentMap;
    }
}
