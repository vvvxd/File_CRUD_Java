package com.vvvxd.File_CRUD_Java.repository;
;
import com.vvvxd.File_CRUD_Java.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long> {
    Skill getById(Long id);

    List<Skill> getAll();

    Skill save(Skill s);

    Skill update(Skill s);

    void deleteById(Long id);
}
