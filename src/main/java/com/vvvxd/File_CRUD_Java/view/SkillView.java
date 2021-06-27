package com.vvvxd.File_CRUD_Java.view;

import com.vvvxd.File_CRUD_Java.controller.SkillController;
import com.vvvxd.File_CRUD_Java.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private final SkillController skillController = new SkillController();
    Scanner scanner = new Scanner(System.in);

    public void save() {
        System.out.println("Enter the name of the skill: ");
        Skill skill;
        String data;
        data = scanner.nextLine();
        if (!data.isEmpty()) {
            skill = skillController.save(data);
            System.out.println(skill.toString());
        }
    }

    public void getAll() {
        try {
            List<Skill> skillList;
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
            List<Skill> skillList;
            Skill skill;
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
            Skill skill;
            System.out.println("Write new name of skill: ");
            skill = skillController.update(new Skill(id, scanner.nextLine()));
            System.out.println(skill.toString());
        } catch (NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void deleteById(Long id) {
        try {
            List<Skill> skillList;
            skillList = skillController.getAll();
            if (skillList.isEmpty()) {
                return;
            }
            skillController.deleteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
