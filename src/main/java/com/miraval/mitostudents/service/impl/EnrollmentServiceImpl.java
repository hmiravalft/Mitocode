package com.miraval.mitostudents.service.impl;

import com.miraval.mitostudents.model.Enrollment;
import com.miraval.mitostudents.model.EnrollmentDetail;
import com.miraval.mitostudents.model.Student;
import com.miraval.mitostudents.repo.IEnrollmentRepo;
import com.miraval.mitostudents.repo.IGenericRepo;
import com.miraval.mitostudents.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Service
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment,Integer> implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepo repo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return repo;
    }


    @Override
    public Map<String, Set<String>> getCoursesByStudent() {

        //Creamos un stream conteniendo el detalle de la matricula
        Stream<List<EnrollmentDetail>> stream = repo.findAll().stream().map(Enrollment::getDetails);

        //Creamos otro stream tipo EnrollmentDetail para tener una collecion de detalles de la matricula
        Stream<EnrollmentDetail> streamDetail = stream.flatMap(Collection::stream);

        //Creamos un map para tener la colección de la matricula con sus estudiantes
        Map<String,Set<String>> byEnrollment = streamDetail.collect(Collectors.groupingBy(c->c.getCourse().getName(),Collectors.mapping(s->s.getEnrollment().getStudent().getFirstNames(),Collectors.toSet())));


        return byEnrollment.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,e-> new HashSet<>(e.getValue())));
    }


}
