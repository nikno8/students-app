package com.example.appstudents.services;


import com.example.appstudents.entities.Student;
import com.example.appstudents.events.CreationStudentEvent;
import com.example.appstudents.events.RemoveStudentEvent;
import com.example.appstudents.init.StudentInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ShellComponent
public class StudentService {

    private Map<UUID, Student> studentMap;

    private final ApplicationEventPublisher eventPublisher;



    public StudentService(ApplicationEventPublisher eventPublisher, @Autowired(required = false) StudentInitializer initializer) {
        this.eventPublisher = eventPublisher;
        this.studentMap = (initializer != null) ? initializer.getStudentMap() : new
                HashMap<>();
    }

    @ShellMethod(key = "a")
    public void createStudent(String firstName, String lastName, int age){
        UUID id = UUID.randomUUID();
        Student newStudent = new Student(id, firstName, lastName, age);
        studentMap.put(id, newStudent);
        eventPublisher.publishEvent(new CreationStudentEvent(this, newStudent));

    }
    @ShellMethod(key = "r")
    public void removeStudentById(UUID id){
        if (studentMap.containsKey(id)){
            studentMap.remove(id);
            eventPublisher.publishEvent(new RemoveStudentEvent(this, id));
        } else{
            System.out.println(MessageFormat.format("Студент с id: {0} не был найден", id));
        }

    }

    @ShellMethod(key = "pa")
    public void printAllStudents(){
        studentMap.values().forEach(System.out::println);
    }

    @ShellMethod(key = "ra")
    public void removeAllStudents(){
        studentMap.clear();
    }



}
