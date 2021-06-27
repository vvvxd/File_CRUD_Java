package com.vvvxd.File_CRUD_Java.view;

import com.vvvxd.File_CRUD_Java.controller.DeveloperController;
import com.vvvxd.File_CRUD_Java.controller.TeamController;
import com.vvvxd.File_CRUD_Java.controller.SkillController;
import com.vvvxd.File_CRUD_Java.model.Developer;
import com.vvvxd.File_CRUD_Java.model.Team;
import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.model.TeamStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamView {
    private final TeamController TeamController = new TeamController();
    Scanner scanner = new Scanner(System.in);

    public void save() {
        String name;
        System.out.println("Enter the name of the Team: ");
        name = scanner.nextLine();
        List<Developer> developers = enterDevelopers();
        TeamStatus ts =  enterTeamStatus();
        if (!name.isEmpty()) {
            Team team = TeamController.save(name, developers, ts);
            System.out.println(team.toString());
        }
    }

    public void getAll() {
        try {
            List<Team> TeamList;
            TeamList = TeamController.getAll();
            if (TeamList.isEmpty()) {
                System.out.println("There are no Teams.");
                return;
            }
            for (Team i : TeamList) {
                System.out.println(i.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("There are no Teams.");
        }
    }

    public void getById(Long id) {
        try {
            List<Team> TeamList;
            Team Team;
            TeamList = TeamController.getAll();
            if (TeamList.isEmpty()) {
                System.out.println("There are no Teams.");
                return;
            }
            Team = TeamController.getById(id);
            System.out.println(Team.toString());
        } catch (NumberFormatException e) {
            System.out.println("There are no Teams.");
        }
    }

    public void update(Long id) {
        try {
            String name;
            System.out.println("Enter the name of the Team: ");
            name = scanner.nextLine();
            List<Developer> developers = enterDevelopers();
            TeamStatus ts =  enterTeamStatus();
            if (!name.isEmpty()) {
                Team team = TeamController.update(new Team(id, name, developers, ts));
                System.out.println(team.toString());
            }
        } catch (NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void deleteById(Long id) {
        try {
            List<Team> TeamList;
            TeamList = TeamController.getAll();
            if (TeamList.isEmpty()) {
                System.out.println("No one Team.");
                return;
            }
            TeamController.deleteById(id);
        } catch (NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    private List<Developer> enterDevelopers() {
        String result;
        List<Developer> developers = new ArrayList<>();
        System.out.println("Enter the developer of the Team (Y - if the developer to be added; N - if not):");
        DeveloperController developerController = new DeveloperController();
        List<Developer> developerList = developerController.getAll();
        for (Developer dev : developerList) {
            System.out.println(dev.toString());
            result = scanner.nextLine();
            if (result.equals("Y")) {
                developers.add(dev);
            }
        }
        return developers;
    }

    private TeamStatus enterTeamStatus() {
        TeamStatus ts;
        System.out.println("Choose status of team: ");
        System.out.println("1: active");
        System.out.println("2: deleted");
        if (scanner.nextLine().equals("1")) {
            ts = TeamStatus.ACTIVE;
        } else {
            ts = TeamStatus.DELETED;
        }
        return ts;
    }
}
