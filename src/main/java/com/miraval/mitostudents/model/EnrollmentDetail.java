package com.miraval.mitostudents.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EnrollmentDetail {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrollmentDetail;

    //Bidireccional
    @ManyToOne
    @JoinColumn(name = "id_enrollment",nullable = false,foreignKey = @ForeignKey(name = "FK_EnrollmentDetail_Enrollment"))
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_EnrollmentDetail_Course"))
    private Course course;

    @Column(length = 50, nullable = false)
    private String classroom;

}
