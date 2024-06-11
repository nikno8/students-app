package com.example.appstudents.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class RemoveStudentEvent extends ApplicationEvent {

    private final UUID studentId;

    public RemoveStudentEvent(Object source, UUID studentId) {
        super(source);
        this.studentId = studentId;
    }
}
