package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;


    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);

    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {

        //1 : CONSULTAR EL CURSO

        Course course = courseRepository.findById(idCourse).orElseThrow();

        //2: OBTENER LOS ESTUDIANTES DE ESE CURSO COMUNICANDO LOS MICROSERVICIOS A TRAVES DE STUDENT CLIENT

        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);

        //3 : RETORNAMOS UNA RESPUESTA PERSONALIZADA


        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentList(studentDTOList)
                .build();
    }
}
