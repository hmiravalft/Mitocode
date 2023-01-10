package com.miraval.mitostudents.controller;

import com.miraval.mitostudents.dto.EnrollmentDTO;
import com.miraval.mitostudents.exception.ModelNotFoundException;
import com.miraval.mitostudents.model.Enrollment;
import com.miraval.mitostudents.service.IEnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService service;

    @Autowired
    @Qualifier("enrollmentMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception{
        List<EnrollmentDTO> list = service.readAll().stream().map(enroll -> mapper.map(enroll, EnrollmentDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        EnrollmentDTO obj = mapper.map(service.readById(id),EnrollmentDTO.class);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@Valid @RequestBody EnrollmentDTO dto) throws Exception{
        Enrollment obj = service.save(mapper.map(dto,Enrollment.class));
        return new ResponseEntity<>(mapper.map(obj,EnrollmentDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnrollmentDTO> update(@Valid @RequestBody EnrollmentDTO dto) throws Exception{
        Enrollment obj = service.readById(dto.getIdEnrollment());

        if(obj==null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdEnrollment());
        }

        Enrollment Enrollment = service.update(mapper.map(dto,Enrollment.class));

        return new ResponseEntity<>(mapper.map(Enrollment,EnrollmentDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Enrollment obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/coursesByStudent")
    public ResponseEntity<Map<String, Set<String>>> getCoursesByStudent(){
        Map<String, Set<String>> map = service.getCoursesByStudent();
        return new ResponseEntity<>(map,HttpStatus.OK);
    }



}
