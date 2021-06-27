package com.vvvxd.File_CRUD_Java;


import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.model.Team;
import com.vvvxd.File_CRUD_Java.view.DeveloperView;
import com.vvvxd.File_CRUD_Java.view.SkillView;
import com.vvvxd.File_CRUD_Java.view.TeamView;

public class Main {
    public static void main(String[] args) {
        Skill sk = new Skill( 100502L,"Java");
        SkillView sr = new SkillView();
//       sr.getAll();
      //  sr.save();
//          sr.update(100501L);
//        sr.getById(100301L);

//        sr.deleteById(100301L);


//        DeveloperView dv= new DeveloperView();
//        dv.save();
        TeamView dv= new TeamView();
        dv.save();
    }
}
