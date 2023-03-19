package com.example.a_assmr.CommonDir;

import android.content.ClipData;
import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageProcesess {
    public static List<MultipartBody.Part> ImageToLists(Context context, Uri uri) {
        List<MultipartBody.Part> imageLists = new ArrayList<>();
        String path = RealPathUtil.getRealPath(context, uri);
        File file = new File(path);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        imageLists.add(body);

        return imageLists;
    }
    public static List<MultipartBody.Part> ImageToLists(Context context, ClipData clipData) {
        List <MultipartBody.Part> imageLists = new ArrayList<>();
        final int size = clipData.getItemCount();
        for(int i = 0; i < size; i++) {
            Uri uri = clipData.getItemAt(i).getUri();
            String path = RealPathUtil.getRealPath(context, uri);
            File file = new File(path);

            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
            imageLists.add(body);
        }
        return imageLists;
    }
}
