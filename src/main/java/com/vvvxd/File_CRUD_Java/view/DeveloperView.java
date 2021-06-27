package com.vvvxd.File_CRUD_Java.view;

import com.vvvxd.File_CRUD_Java.controller.DeveloperController;
import com.vvvxd.File_CRUD_Java.controller.SkillController;
import com.vvvxd.File_CRUD_Java.model.Developer;
import com.vvvxd.File_CRUD_Java.model.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController DeveloperController = new DeveloperController();
    Scanner scanner = new Scanner(System.in);

    public void save() {
        String firstName;
        String lastName;
        System.out.println("Enter the first name of the Developer: ");
        firstName = scanner.nextLine();
        System.out.println("Enter the last name of the Developer: ");
        lastName = scanner.nextLine();
        List<Skill> skills = enterSkills();
        if (!lastName.isEmpty() && !firstName.isEmpty()) {
            Developer developer = DeveloperController.save(firstName, lastName, skills);
            System.out.println(developer.toString());
        }
    }

    public void getAll() {
        try {
            List<Developer> DeveloperList;
            DeveloperList = DeveloperController.getAll();
            if (DeveloperList.isEmpty()) {
                System.out.println("There are no Developers.");
                return;
            }
            for (Developer i : DeveloperList) {
                System.out.println(i.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("There are no Developers.");
        }
    }

    public void getById(Long id) {
        try {
            List<Developer> DeveloperList;
            Developer Developer;
            DeveloperList = DeveloperController.getAll();
            if (DeveloperList.isEmpty()) {
                System.out.println("There are no Developers.");
                return;
            }
            Developer = DeveloperController.getById(id);
            System.out.println(Developer.toString());
        } catch (NumberFormatException e) {
            System.out.println("There are no Developers.");
        }
    }

    public void update(Long id) {
        try {
            String firstName;
            String lastName;
            System.out.println("Enter the first name of the Developer: ");
            firstName = scanner.nextLine();
            System.out.println("Enter the last name of the Developer: ");
            lastName = scanner.nextLine();
            List<Skill> skills = enterSkills();
            if (!lastName.isEmpty() && !firstName.isEmpty()) {
                Developer developer = DeveloperController.update(new Developer(id, firstName, lastName, skills));
                System.out.println(developer.toString());
            }
        } catch (NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void deleteById(Long id) {
        try {
            List<Developer> DeveloperList;
            DeveloperList = DeveloperController.getAll();
            if (DeveloperList.isEmpty()) {
                System.out.println("Write correct id.");
                return;
            }
            DeveloperController.deleteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    private List<Skill> enterSkills() {
        String result;
        List<Skill> skills = new ArrayList<>();
        System.out.println("Enter the skills of the Developer(Y - if the skill to be added; N - if not):");
        SkillController skillController = new SkillController();
        List<Skill> skillsList = skillController.getAll();
        for (Skill sk : skillsList) {
            System.out.println(sk.toString());
            result = scanner.nextLine();
            if (result.equals("Y")) {
                skills.add(sk);
            }
        }
        return skills;
    }
}
