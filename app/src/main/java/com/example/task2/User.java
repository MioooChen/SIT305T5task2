package com.example.task2;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String fullName;
    private String username;
    private String password;
    private List<String> playlist;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        playlist = new ArrayList<>();
    }

    public User(String fullName, String username, String password, List<String> playlist) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.playlist = playlist;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<String> playlist) {
        this.playlist = playlist;
    }

    public void addPlaylist(String videoUrl) {
        playlist.add(videoUrl);
    }


}