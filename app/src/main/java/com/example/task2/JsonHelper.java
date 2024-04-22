package com.example.task2;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private static final String FILENAME = "users.json";

    public static List<User> loadUsers(Context context) {
        List<User> userList = new ArrayList<>();
        FileInputStream fis = null;

        try {
            fis = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String fullName = jsonObject.getString("fullName");
                String username = jsonObject.getString("username");
                String password = jsonObject.getString("password");
                JSONArray playlistArray = jsonObject.getJSONArray("playlist");
                List<String> playlist = new ArrayList<>();
                for (int j = 0; j < playlistArray.length(); j++) {
                    playlist.add(playlistArray.getString(j));
                }

                userList.add(new User(fullName, username, password, playlist));
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static void saveUsers(Context context, List<User> userList) {
        JSONArray jsonArray = new JSONArray();

        for (User user : userList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("fullName", user.getFullName());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("password", user.getPassword());

                JSONArray playlistArray = new JSONArray();
                for (String video : user.getPlaylist()) {
                    playlistArray.put(video);
                }
                jsonObject.put("playlist", playlistArray);

                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(jsonArray.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}