package com.vvvxd.File_CRUD_Java.repository;

import com.vvvxd.File_CRUD_Java.model.Developer;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer,Long>{
    Developer getById(Long id);

    List<Developer> getAll();

    Developer save(Developer s);

    Developer update(Developer s);

    void deleteById(Long id);
}
