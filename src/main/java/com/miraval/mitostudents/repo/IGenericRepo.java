package com.miraval.mitostudents.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean//para convertir en generico esta clase
public interface IGenericRepo<T,ID> extends JpaRepository<T,ID> {
}
