package com.xrigau.renderscripting;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class ImageHelper {

    private static final String KEY_SELECTED_KIT_KAT_INDEX = "selected_kit_kat_index";

    private final String[] images = {
            "kitkat1.png",
            "kitkat2.jpg"
    };

    private int selectedImageIndex;

    public Bitmap getSelectedBitmapImage(Context context) {
        return getBitmapFromAsset(context, images[selectedImageIndex]);
    }

    public Bitmap getNextBitmap(Context context) {
        selectedImageIndex = ++selectedImageIndex % images.length;
        return getSelectedBitmapImage(context);
    }

    private Bitmap getBitmapFromAsset(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();

        try {
            final InputStream inputStream = assetManager.open(fileName);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("File " + fileName + " does not exist or is corrupt");
        }
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
