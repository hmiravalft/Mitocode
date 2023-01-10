package com.miraval.mitostudents.service;

import com.miraval.mitostudents.model.Enrollment;
import com.miraval.mitostudents.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IEnrollmentService extends ICRUD<Enrollment,Integer>{

    Map<String, Set<String>> getCoursesByStudent();

}
