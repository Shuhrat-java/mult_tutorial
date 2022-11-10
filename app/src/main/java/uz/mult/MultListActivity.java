package uz.mult;

import android.os.Bundle;


import com.google.gson.Gson;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MultListActivity extends AppCompatActivity {

    private JsonClasses.Item currentPlayingVideoITem;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        RecyclerView multList = findViewById(R.id.mainList);
        try {
            InputStream jsonInputStream = getAssets().open("youtube.json");
            byte[] buffer = new byte[jsonInputStream.available()];
            jsonInputStream.read(buffer, 0, jsonInputStream.available());
            String jsonContent = new String(buffer);
            JsonClasses.Root root = new Gson().fromJson(jsonContent, JsonClasses.Root.class);
            currentPlayingVideoITem = root.items.get(0);
            youTubePlayerView = findViewById(R.id.youtube_player_view);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    player = youTubePlayer;
                    youTubePlayer.loadVideo(currentPlayingVideoITem.snippet.resourceId.videoId, 0);
                }
            });
            MultfilmAdapter multfilmAdapter =
                    new MultfilmAdapter(root, new ClickListener() {
                        @Override
                        public void onClick(JsonClasses.Item item) {
                            player.loadVideo(item.snippet.resourceId.videoId, 0);
                        }
                    }, MultListActivity.this);

            multList.setAdapter(multfilmAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
