package com.microservice.student.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String email;
    @Column(name = "course_id")
    private Long courseId;






}
