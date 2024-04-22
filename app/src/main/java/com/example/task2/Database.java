package com.example.task2;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Database {
    private static List<User> userList;
    private static Context context;

    private static User currentUser;

    private Database() {
        // 私有构造函数，防止实例化
    }

    public static void initialize(Context ctx) {
        context = ctx;
        init();
    }

    private static void init() {
        // 从 JSON 文件中加载用户数据
        userList = JsonHelper.loadUsers(context);

        for (User user : userList) {
            Log.i("User", "FullName: " + user.getFullName() + ", Username: " + user.getUsername() + ", Password: " + user.getPassword());
        }
    }

    public static void addUser(User user) {
        userList.add(user);
        // 保存用户数据到 JSON 文件
        JsonHelper.saveUsers(context, userList);
    }

    public static boolean checkUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user; // 设置当前用户
                return true;
            }
        }
        return false;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void updateCurrentUser(User updatedUser) {
        // 在数据库中查找当前用户并更新
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(currentUser.getUsername())) {
                userList.set(i, updatedUser);
                break;
            }
        }
        // 更新当前用户对象
        currentUser = updatedUser;
        // 保存用户数据到 JSON 文件
        JsonHelper.saveUsers(context, userList);
    }
}