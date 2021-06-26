package com.vvvxd.File_CRUD_Java.view;

import com.vvvxd.File_CRUD_Java.controller.SkillController;
import com.vvvxd.File_CRUD_Java.model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SkillView {
    private final SkillController skillController = new SkillController();
    Skill skill;
    List<Skill> skillList;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String data;

    public void save() {
        System.out.println("Enter the name of the skill: ");
        try {
            data = reader.readLine();
            if (!data.isEmpty()) {
                skill = skillController.save(data);
                System.out.println(skill.toString());
            }
        } catch (IOException e) {
            System.out.println("Enter the correct name.");
        }
    }

    public void getAll() {
        try {
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            for (Skill i : skillList) {
                System.out.println(i.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("There are no skills.");
        }

    }

    public void getById(Long id) {
        try {
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            skill = skillController.getById(id);
            System.out.println(skill.toString());
        } catch (NumberFormatException e) {
            System.out.println("There are no skills.");
        }
    }

    public void update(Long id) {
        try {
            System.out.println("Write new name of skill: ");
            skill = skillController.update(new Skill(id,reader.readLine()));
            System.out.println(skill.toString());
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void deleteById(Long id) {
        try {
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            skillController.deleteById(id);
        } catch (  NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
