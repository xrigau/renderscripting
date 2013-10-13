package com.xrigau.renderscripting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private final ImageHelper imageHelper = new ImageHelper();

    private BitmapBlurer bitmapBlurer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bitmapBlurer = new BitmapBlurer(this);

        imageHelper.restoreState(savedInstanceState);
        showSelectedImage();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_blur:
                blurrySelectedKitKat();
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

    private void blurrySelectedKitKat() {
        bitmapBlurer.blurryBitmap(imageHelper.getSelectedBitmapImage(this), new OnOperationFinishedListener() {
            @Override
            public void onBlurryFinished(Bitmap blurryBitmap) {
                showImage(blurryBitmap);
            }
        });
    }

    private void showNextImage() {
        showImage(imageHelper.getNextBitmap(this));
    }

    private void showSelectedImage() {
        showImage(imageHelper.getSelectedBitmapImage(this));
    }

    private void showImage(Bitmap bitmap) {
        ((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);
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
