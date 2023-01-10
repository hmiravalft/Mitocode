package com.miraval.mitostudents.service;

import com.miraval.mitostudents.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student,Integer>{

    //Ordenar por edad de forma descendiente
    List<Student> findAllOrderDesc();

}
