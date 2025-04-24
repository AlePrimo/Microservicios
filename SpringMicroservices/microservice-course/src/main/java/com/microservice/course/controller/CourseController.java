package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Course course){
        courseService.save(course);
    }


    @PutMapping("/update/{idCourse}")
    public ResponseEntity<?> updateCourse(@PathVariable Long idCourse, @RequestBody Course course) {
        Course course1 = courseService.findById(idCourse);
        course1.setName(course.getName());
        course1.setTeacher(course.getTeacher());
        courseService.save(course1);
        return ResponseEntity.ok("Curso actualizado");
    }




    @DeleteMapping ("/delete/{idCourse}")
    public ResponseEntity<?>  deleteCourse(@PathVariable Long idCourse){

        courseService.delete(idCourse);
        return ResponseEntity.ok("Registro Eliminado");

    }






    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(courseService.findAll());
    }


    @GetMapping("/findStudentsByCourse/{idCourse}")
    public ResponseEntity<?> findStudentsByIdCourse(@PathVariable Long idCourse){
        return ResponseEntity.ok(courseService.findStudentsByIdCourse(idCourse));
    }








}


