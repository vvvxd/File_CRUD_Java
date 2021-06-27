package com.vvvxd.File_CRUD_Java.controller;

import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.repository.SkillRepository;
import com.vvvxd.File_CRUD_Java.repository.implementations.JsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final SkillRepository skillRepository= new JsonSkillRepositoryImpl();

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill save(String name) {
        return skillRepository.save(new Skill(name));
    }

    public Skill update(Skill s) {
        return skillRepository.update(s);
    }

    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
