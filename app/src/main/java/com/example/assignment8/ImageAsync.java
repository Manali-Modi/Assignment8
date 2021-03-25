package com.example.assignment8;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class ImageAsync extends AsyncTask<Void, Void, ArrayList<String>> {

    ArrayList<String> allImages;
    private final getImageList imageList;

    public ImageAsync(getImageList imageList) {
        this.imageList = imageList;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        allImages = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory() + "/Camera");
        File[] files = file.listFiles();
        if(files!=null){
            Log.d("image", String.valueOf(files.length));
            for (int i=0; i<files.length; i++){
                File f = files[i];
                String filePath = f.getPath();
                if(filePath.endsWith(".jpg") || filePath.endsWith(".jpeg"))
                    allImages.add(filePath);
            }
        }
        return allImages;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        Log.d("images",strings.toString());
        imageList.getImages(strings);
    }
}
