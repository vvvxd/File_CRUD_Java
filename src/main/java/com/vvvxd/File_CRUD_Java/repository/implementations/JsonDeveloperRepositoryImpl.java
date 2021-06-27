package com.vvvxd.File_CRUD_Java.repository.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vvvxd.File_CRUD_Java.model.Developer;
import com.vvvxd.File_CRUD_Java.model.Skill;
import com.vvvxd.File_CRUD_Java.repository.DeveloperRepository;

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

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {
    private static final String SKILL_FILE_PATH = "src/main/resources/developers.json";

    public Developer getById(Long id) {
        return getAllDevelopersInternal().stream().filter(x -> id.equals(x.getId())).findFirst().orElse(new Developer());
    }

    public List<Developer> getAll() {
        return getAllDevelopersInternal();
    }

    public Developer save(Developer s) {
        List<Developer> listDevelopers = getAllDevelopersInternal();
        Long newId = generateNewId();
        s.setId(newId);
        listDevelopers.add(s);
        writeDeveloperToFile(listDevelopers);
        return s;
    }

    public Developer update(Developer s) {
        List<Developer> listSkill = getAllDevelopersInternal();
        listSkill.forEach(x -> {
            if (x.getId().equals(s.getId())) {
                x.setFirstName(s.getFirstName());
                x.setLastName(s.getLastName());
                x.setSkills(s.getSkills());
            }
        });
        writeDeveloperToFile(listSkill);
        return s;
    }

    public void deleteById(Long id) {
        List<Developer> developers = getAllDevelopersInternal();
        List<Developer> sf = developers.stream().filter(developer -> !id.equals(developer.getId())).collect(Collectors.toList());
        writeDeveloperToFile(sf);
    }

    private List<Developer> getAllDevelopersInternal() {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(SKILL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(String.valueOf(json), FoundListType);
    }

    private Long generateNewId() {
        List<Developer> listDevelopers = getAllDevelopersInternal();
        Developer developer = listDevelopers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.isNull(developer)
                ? 1L : developer.getId() + 1;
    }

    private void writeDeveloperToFile(List<Developer> developers) {
        String jsonString = new Gson().toJson(developers);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SKILL_FILE_PATH))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
