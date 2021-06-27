package com.vvvxd.File_CRUD_Java.controller;

import com.vvvxd.File_CRUD_Java.model.Developer;
import com.vvvxd.File_CRUD_Java.model.Team;
import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.model.TeamStatus;
import com.vvvxd.File_CRUD_Java.repository.TeamRepository;
import com.vvvxd.File_CRUD_Java.repository.implementations.JsonTeamRepositoryImpl;

import java.util.List;

public class TeamController {
    private final com.vvvxd.File_CRUD_Java.repository.TeamRepository TeamRepository = new JsonTeamRepositoryImpl();

    public Team getById(Long id) {
        return TeamRepository.getById(id);
    }

    public List<Team> getAll() {
        return TeamRepository.getAll();
    }

    public Team save(String name, List<Developer> developers, TeamStatus teamStatus) {
        return TeamRepository.save(new Team(name, developers, teamStatus));
    }

    public Team update(Team s) {
        return TeamRepository.update(s);
    }

    public void deleteById(Long id) {
        TeamRepository.deleteById(id);
    }
}
