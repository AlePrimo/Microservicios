package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private void saveStundent(@RequestBody Student student){
        iStudentService.save(student);

    }


    @PutMapping("/update/{idStudent}")
    public ResponseEntity<?> updateStudent(@PathVariable Long idStudent, @RequestBody Student student) {
        Student student1 = iStudentService.findById(idStudent);
        student1.setName(student.getName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setCourseId(student.getCourseId());
        iStudentService.save(student1);

        return ResponseEntity.ok("Estudiante Actualizado");
    }


    @DeleteMapping ("/delete/{idStudent}")
    public ResponseEntity<?>  deleteStudent(@PathVariable Long idStudent){

        iStudentService.delete(idStudent);
        return ResponseEntity.ok("Estudiante Eliminado");

    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(iStudentService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(iStudentService.findAll());
    }


    @GetMapping("/findByCourse/{idCourse}")
    public ResponseEntity<?> findByIdCourse(@PathVariable Long idCourse){

        return ResponseEntity.ok(iStudentService.findByIdCourse(idCourse));

    }



}
