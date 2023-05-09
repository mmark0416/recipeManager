package com.recipeManager.JSONDB;

import com.recipeManager.utilities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@lombok.Data
public class JSONDB {
    private final String path = "B:\\documents\\school\\6-4\\szoftverfejlesztés\\exercises\\demo2\\src\\main\\resources\\JSONData\\";
    private List<User> userLst;
    private ObjectMapper mapper = new ObjectMapper();

    public User readJSON(String username) {
        User user = null;
        try {
            user = mapper.readValue(Paths.get(path + username + ".json").toFile(), User.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void writeJSON(User user) {
        try {
            mapper.writeValue(Paths.get(path + user.getUsername() + ".json").toFile(), user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFileExists(String username) {
        File f = new File(path + username + ".json");
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

    public void deleteJSON(String username){
        try {
            Files.delete(Paths.get(path + username + ".json"));
        } catch (NoSuchFileException x) {
            System.out.println(x.getMessage());
        } catch (DirectoryNotEmptyException x) {
            System.out.println(x.getMessage());
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }
    }
}
