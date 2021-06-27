package com.vvvxd.File_CRUD_Java.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vvvxd.File_CRUD_Java.model.Team;
import com.vvvxd.File_CRUD_Java.repository.TeamRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonTeamRepositoryImpl implements TeamRepository {
    private static final String Team_FILE_PATH = "src/main/resources/Teams.json";

    public Team getById(Long id) {
        return getAllTeamsInternal().stream().filter(x -> id.equals(x.getId())).findFirst().orElse(new Team());
    }

    public List<Team> getAll() {
        return getAllTeamsInternal();
    }

    public Team save(Team s) {
        List<Team> listTeam = getAllTeamsInternal();
        Long newId = generateNewId();
        s.setId(newId);
        listTeam.add(s);
        writeTeamToFile(listTeam);
        return s;
    }

    public Team update(Team s) {
        List<Team> listTeam = getAllTeamsInternal();
        listTeam.forEach(x -> {
            if (x.getId().equals(s.getId())) {
                x.setName(s.getName());
                x.setTeamStatus(s.getTeamStatus());
                x.setDevelopers(s.getDevelopers());
            }
        });
        writeTeamToFile(listTeam);
        return s;
    }

    public void deleteById(Long id) {
        List<Team> Teams = getAllTeamsInternal();
        List<Team> sf = Teams.stream().filter(Team -> !id.equals(Team.getId())).collect(Collectors.toList());
        writeTeamToFile(sf);
    }

    private List<Team> getAllTeamsInternal() {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(Team_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Team>>() {
        }.getType();
        return new Gson().fromJson(String.valueOf(json), FoundListType);
    }

    private Long generateNewId() {
        List<Team> listTeam = getAllTeamsInternal();
        Team team = listTeam.stream().max(Comparator.comparing(Team::getId)).orElse(null);
        return Objects.isNull(team)
                ? 1L : team.getId() + 1;
    }

    private void writeTeamToFile(List<Team> Teams) {
        String jsonString = new Gson().toJson(Teams);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(Team_FILE_PATH))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
