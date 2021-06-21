package com.vvvxd.File_CRUD_Java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {
    Skill getById(Long id) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> listSkill = new Gson().fromJson(String.valueOf(json), FoundListType);
        Optional<Skill> sk = listSkill.stream().filter(x -> id.equals(x.getId())).findFirst();
        return sk.isPresent() ? sk.get() : new Skill();

    }

    List<Skill> getAll() {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
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

    Skill save(Skill s) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> listSkill = new Gson().fromJson(String.valueOf(json), FoundListType);
        Comparator<Skill> comparator = Comparator.comparing(Skill::getId);
        Skill id = listSkill.stream().max(comparator).get();
        s.setId(id.getId() + 1);
        listSkill.add(s);
        String jsonString = new Gson().toJson(listSkill);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    Skill update(Skill s) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> listSkill = new Gson().fromJson(String.valueOf(json), FoundListType);
        listSkill.forEach(x -> {
            if (x.getId().equals(s.getId())) {
                x.setName(s.getName());
            }
        });
        String jsonString = new Gson().toJson(listSkill);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    void deleteById(Long id) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type FoundListType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> listSkill = new Gson().fromJson(String.valueOf(json), FoundListType);
        List<Skill> sf = listSkill.stream().filter(x -> !id.equals(x.getId())).collect(Collectors.toList());
        String jsonString = new Gson().toJson(sf);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/main/java/com/vvvxd/File_CRUD_Java/skills.json"))) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
