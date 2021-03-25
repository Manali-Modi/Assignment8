package com.example.assignment8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements getImageList{

    ImageAdapter imageAdapter;
    RecyclerView recImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recImages = findViewById(R.id.rec_images);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},11);
        }
        new ImageAsync(this).execute();

        /*imageAdapter = new ImageAdapter(this);

        recImages.setLayoutManager(new GridLayoutManager(this,4));
        recImages.setAdapter(imageAdapter);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnu_setting){
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==11 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            new ImageAsync(this).execute();
    }

    @Override
    public void getImages(ArrayList<String> imageList) {
        Log.d("images2",imageList.toString());
        imageAdapter = new ImageAdapter(this,imageList);

        recImages.setLayoutManager(new GridLayoutManager(this,4));
        recImages.setAdapter(imageAdapter);
    }
}