package com.vvvxd.File_CRUD_Java;


import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.repository.SkillRepository;
import com.vvvxd.File_CRUD_Java.view.SkillView;

public class Main {
    public static void main(String[] args) {
        Skill sk = new Skill( 100502L,"Java");
        SkillView sr = new SkillView();
//        sr.getAll();
//        sr.save();
//          sr.update(100501L);
//        sr.getById(100301L);

        sr.deleteById(100301L);
//        List<Skill> a = sr.getAll();
//        System.out.println(a.toString());

//        sr.deleteById(100501L);
//        System.out.println(sr.getAll().toString());
    }
}
