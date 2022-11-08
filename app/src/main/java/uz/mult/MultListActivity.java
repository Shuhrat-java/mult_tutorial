package uz.mult;

import android.os.Bundle;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MultListActivity extends AppCompatActivity {
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
            MultfilmAdapter multfilmAdapter = new MultfilmAdapter(root, MultListActivity.this);
            multList.setAdapter(multfilmAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
