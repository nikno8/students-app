package com.example.appstudents.listeners;

import com.example.appstudents.entities.Student;
import com.example.appstudents.events.CreationStudentEvent;
import com.example.appstudents.events.RemoveStudentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.UUID;

@Component
public class StudentEventListener {

    @EventListener
    public void listenCreateStudent(CreationStudentEvent creationStudentEvent) {
        Student student = creationStudentEvent.getStudent();
        System.out.println(MessageFormat.format("Created new student with id {0}, firstName: {1}, " +
                        "lastName: {2}, age: {3}", student.getId(), student.getFirstName(),
                student.getLastname(), student.getAge()));
    }

    @EventListener
    public void listenRemoveStudent(RemoveStudentEvent removeStudentEvent) {
        UUID id = removeStudentEvent.getStudentId();
        System.out.println(MessageFormat.format("Student with id {0} deleted", id));
    }
}
