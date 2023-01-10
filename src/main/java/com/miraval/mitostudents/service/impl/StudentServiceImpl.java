package com.miraval.mitostudents.service.impl;

import com.miraval.mitostudents.model.Student;
import com.miraval.mitostudents.repo.IGenericRepo;
import com.miraval.mitostudents.repo.IStudentRepo;
import com.miraval.mitostudents.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends CRUDImpl<Student,Integer> implements IStudentService {

    @Autowired
    private IStudentRepo repo;


    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }


    //Ordenar por edad de forma descendiente
    @Override
    public List<Student> findAllOrderDesc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());
    }
}
