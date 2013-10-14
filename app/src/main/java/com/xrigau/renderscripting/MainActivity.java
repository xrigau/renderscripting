package com.xrigau.renderscripting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.xrigau.renderscripting.effects.BitmapEffects;
import com.xrigau.renderscripting.util.ImageHelper;

public class MainActivity extends Activity {

    private static final float BLUR_RADIUS = 20f;

    private final ImageHelper imageHelper = new ImageHelper();

    private ImageView imageView;
    private Bitmap visibleBitmap;
    private RenderScript renderScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViews();

        imageHelper.restoreState(savedInstanceState);
        showSelectedImage();

        renderScript = RenderScript.create(this);
    }

    private void findViews() {
        imageView = ((ImageView) findViewById(R.id.image));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_grayscale:
                grayscaleSelectedBitmap();
                break;
            case R.id.action_blur:
                blurrySelectedImage();
                break;
            case R.id.action_next:
                showNextImage();
                break;
            case R.id.action_reset:
                showSelectedImage();
                break;
        }
        return true;
    }

    private void grayscaleSelectedBitmap() {
        final Bitmap grayscaledBitmap = BitmapEffects.getGrayscaleEffect(renderScript).apply(visibleBitmap);
        showImage(grayscaledBitmap);
    }

    private void blurrySelectedImage() {
        final Bitmap blurryBitmap = BitmapEffects.getBlurryEffect(BLUR_RADIUS, renderScript).apply(visibleBitmap);
        showImage(blurryBitmap);
    }

    private void showNextImage() {
        showImage(imageHelper.getNextBitmap(this));
    }

    private void showSelectedImage() {
        showImage(imageHelper.getSelectedBitmapImage(this));
    }

    private void showImage(Bitmap bitmap) {
        visibleBitmap = bitmap;
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        imageHelper.saveState(outState);
    }

}
