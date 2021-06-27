package com.vvvxd.File_CRUD_Java.controller;

import com.vvvxd.File_CRUD_Java.model.Developer;
import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.repository.DeveloperRepository;
import com.vvvxd.File_CRUD_Java.repository.implementations.JsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private final DeveloperRepository DeveloperRepository = new JsonDeveloperRepositoryImpl();

    public Developer getById(Long id) {
        return DeveloperRepository.getById(id);
    }

    public List<Developer> getAll() {
        return DeveloperRepository.getAll();
    }

    public Developer save(String firstName, String lastName, List<Skill> skills) {
        return DeveloperRepository.save(new Developer(firstName, lastName, skills));
    }

    public Developer update(Developer s) {
        return DeveloperRepository.update(s);
    }

    public void deleteById(Long id) {
        DeveloperRepository.deleteById(id);
    }
}
