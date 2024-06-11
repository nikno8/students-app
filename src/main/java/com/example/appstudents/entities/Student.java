package com.example.appstudents.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;


@Data
@AllArgsConstructor
@ToString
public class Student {
    private UUID id;
    private String firstName;
    private String lastname;
    private int age;

}
