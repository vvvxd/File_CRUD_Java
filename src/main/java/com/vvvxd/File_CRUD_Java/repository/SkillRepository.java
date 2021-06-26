package com.vvvxd.File_CRUD_Java.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vvvxd.File_CRUD_Java.model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {
    private static final String SKILL_FILE_PATH = "src/main/resources/skills.json";

    public Skill getById(Long id) {
        return getAllSkillsInternal().stream().filter(x -> id.equals(x.getId())).findFirst().orElse(new Skill());
    }

    public List<Skill> getAll() {
        return getAllSkillsInternal();
    }

    public Skill save(Skill s) {
        List<Skill> listSkill = getAllSkillsInternal();
        Long newId = generateNewId();
        s.setId(newId);
        listSkill.add(s);
        writeSkillToFile(listSkill);
        return s;
    }

    public Skill update(Skill s) {
        List<Skill> listSkill = getAllSkillsInternal();
        listSkill.forEach(x -> {
            if (x.getId().equals(s.getId())) {
                x.setName(s.getName());
            }
        });
        writeSkillToFile(listSkill);
        return s;
    }

    public void deleteById(Long id) {
        List<Skill> skills = getAllSkillsInternal();
        List<Skill> sf = skills.stream().filter(skill -> !id.equals(skill.getId())).collect(Collectors.toList());
        writeSkillToFile(sf);
    }

    private List<Skill> getAllSkillsInternal() {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(SKILL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(String.valueOf(json), FoundListType);
    }

    private Long generateNewId() {
        List<Skill> listSkill = getAllSkillsInternal();
        Skill skill = listSkill.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.isNull(skill)
                ? 1L : skill.getId() + 1;
    }

    private void writeSkillToFile(List<Skill> skills) {
        String jsonString = new Gson().toJson(skills);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SKILL_FILE_PATH))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
