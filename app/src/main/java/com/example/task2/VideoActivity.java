package com.example.task2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String youtubeUrl = getIntent().getStringExtra("youtubeUrl");
        String videoId = extractVideoIdFromUrl(youtubeUrl);
        Log.i("VideoActivity", "videoId: " + videoId);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
            youTubePlayer.loadVideo(videoId, 0);
        });
//        getLifecycle().addObserver(youTubePlayerView);
//
//        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                // 从intent读取
//                String youtubeUrl = getIntent().getStringExtra("youtubeUrl");
//                String videoId = extractVideoIdFromUrl(youtubeUrl);
//                Log.i("VideoActivity", "videoId: " + videoId);
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//        });
    }

    public String extractVideoIdFromUrl(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0) {
            String query = youtubeUrl.substring(youtubeUrl.indexOf("?") + 1);
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && keyValue[0].equals("v")) {
                    videoId = keyValue[1];
                    break;
                }
            }
        }
        return videoId;
    }

}