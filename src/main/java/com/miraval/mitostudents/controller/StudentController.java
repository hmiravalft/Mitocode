package com.miraval.mitostudents.controller;

import com.miraval.mitostudents.dto.StudentDTO;
import com.miraval.mitostudents.exception.ModelNotFoundException;
import com.miraval.mitostudents.model.Student;
import com.miraval.mitostudents.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService service;

    @Autowired
    @Qualifier("studentMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(student -> mapper.map(student, StudentDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        StudentDTO obj = mapper.map(service.readById(id),StudentDTO.class);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(mapper.map(dto,Student.class));
        return new ResponseEntity<>(mapper.map(obj,StudentDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.readById(dto.getId());

        if(obj==null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }

        Student Student = service.update(mapper.map(dto,Student.class));

        return new ResponseEntity<>(mapper.map(Student,StudentDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Student obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Ordenar por edad de forma descendiente
    @GetMapping("/agedesc")
    public ResponseEntity<List<StudentDTO>> findAllOrderDesc(){

        List<StudentDTO> listdto = service.findAllOrderDesc().stream().map(student -> mapper.map(student, StudentDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(listdto,HttpStatus.OK);

    }



}
