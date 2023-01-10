package com.miraval.mitostudents.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    @Min(value = 1)
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String firstNames;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String lastNames;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 8)
    private String dniStudent;

    @NotNull
    @Min(value = 1)
    private Integer ageStudent;



}
