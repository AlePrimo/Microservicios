package com.microservice.course.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class StudentDTO {



    private String name;
    private String lastName;
    private String email;
    private Long courseId;




}
