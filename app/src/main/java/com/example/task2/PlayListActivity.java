package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    private ListView listViewPlaylist;
    private List<String> playlist;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        listViewPlaylist = findViewById(R.id.listViewPlaylist);

        // 创建播放列表数据
        playlist = new ArrayList<>();
        playlist.addAll(Database.getCurrentUser().getPlaylist());
        // 可以根据需要从数据库或其他来源加载播放列表数据

        // 初始化适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playlist);

        // 设置适配器
        listViewPlaylist.setAdapter(adapter);

        listViewPlaylist.setOnItemClickListener((parent, view, position, id) -> {
            String youtubeUrl = playlist.get(position);
            Intent intent = new Intent(PlayListActivity.this, VideoActivity.class);
            intent.putExtra("youtubeUrl", youtubeUrl);
            startActivity(intent);
        });

    }
}
