package com.miraval.mitostudents.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Enrollment {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrollment;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false,foreignKey = @ForeignKey(name = "FK_Enrollment_Student"))
    private Student student;

    @Column(nullable = false)
    private boolean status;

    //Bidireccional
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EnrollmentDetail> details;


}
