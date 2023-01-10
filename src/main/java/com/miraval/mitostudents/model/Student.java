package com.miraval.mitostudents.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Student {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudent;

    @Column(length = 100, nullable = false)
    private String firstNames;

    @Column(length = 100, nullable = false)
    private String lastNames;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private Integer age;

}
