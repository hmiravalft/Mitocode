package com.miraval.mitostudents.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//si hay respuestas que sean null, se van a ignorar
public class CourseDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nameCourse;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    private String acronymCourse;

    @NotNull
    private boolean statusCourse;

}
