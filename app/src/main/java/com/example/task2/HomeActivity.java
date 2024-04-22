package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import android.widget.Toast;
public class HomeActivity extends AppCompatActivity {

    private EditText editTextYouTubeUrl;
    private Button buttonPlay, buttonAddToPlaylist, buttonMyPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextYouTubeUrl = findViewById(R.id.editTextYouTubeUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonAddToPlaylist = findViewById(R.id.buttonAddToPlaylist);
        buttonMyPlaylist = findViewById(R.id.buttonMyPlaylist);

        // Play 按钮点击事件
        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VideoActivity.class);
            intent.putExtra("youtubeUrl", editTextYouTubeUrl.getText().toString().trim());

            startActivity(intent);
        });

        // Add To Playlist 按钮点击事件
        buttonAddToPlaylist.setOnClickListener(v -> {
            String youtubeUrl = editTextYouTubeUrl.getText().toString().trim();
            if (!TextUtils.isEmpty(youtubeUrl)) {
                // 获取当前用户
                User currentUser = Database.getCurrentUser();
                currentUser.addPlaylist(youtubeUrl);
                Database.updateCurrentUser(currentUser);
                Toast.makeText(HomeActivity.this, "Video added to playlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HomeActivity.this, "Please enter Youtube URL", Toast.LENGTH_SHORT).show();
            }
        });

        // My Playlist 按钮点击事件
        buttonMyPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlayListActivity.class);
            startActivity(intent);
        });
    }
}