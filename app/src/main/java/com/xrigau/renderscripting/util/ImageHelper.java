package com.xrigau.renderscripting.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class ImageHelper {

    private static final String KEY_SELECTED_KIT_KAT_INDEX = "selected_kit_kat_index";
    private static final String IMAGES_FOLDER_NAME = "kitkats";

    private int selectedImageIndex;

    public Bitmap getSelectedBitmapImage(Context context) {
        return getBitmapFromAsset(context);
    }

    private Bitmap getBitmapFromAsset(Context context) {
        AssetManager assetManager = context.getAssets();

        try {
            final String fileName = getImagesList(assetManager)[selectedImageIndex];
            final InputStream inputStream = assetManager.open(filePath(fileName));
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Can't open file with index " + selectedImageIndex);
        }
    }

    public Bitmap getNextBitmap(Context context) {
        selectedImageIndex = ++selectedImageIndex % getImagesList(context.getAssets()).length;
        return getSelectedBitmapImage(context);
    }

    private String[] getImagesList(AssetManager assetManager) {
        try {
            return assetManager.list(IMAGES_FOLDER_NAME);
        } catch (IOException e) {
            throw new RuntimeException("The folder " + IMAGES_FOLDER_NAME + " does not exist in the assets folder");
        }
    }

    private String filePath(String fileName) {
        return IMAGES_FOLDER_NAME + "/" + fileName;
    }

    public void saveState(Bundle outState) {
        outState.putInt(KEY_SELECTED_KIT_KAT_INDEX, selectedImageIndex);
    }

    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            selectedImageIndex = savedInstanceState.getInt(KEY_SELECTED_KIT_KAT_INDEX);
        }
    }
}
