package com.miraval.mitostudents.service.impl;

import com.miraval.mitostudents.model.Course;
import com.miraval.mitostudents.repo.ICourseRepo;
import com.miraval.mitostudents.repo.IGenericRepo;
import com.miraval.mitostudents.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends CRUDImpl<Course,Integer> implements ICourseService {

    @Autowired
    private ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
