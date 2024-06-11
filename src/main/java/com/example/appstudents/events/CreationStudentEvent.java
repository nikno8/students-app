package com.example.appstudents.events;


import com.example.appstudents.entities.Student;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreationStudentEvent extends ApplicationEvent {

    private final Student student;

    public CreationStudentEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }
}