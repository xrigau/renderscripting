package com.xrigau.renderscripting;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BitmapBlurer {

    private static final float BLUR_RADIUS = 20f;

    private final RenderScript renderScript;

    public BitmapBlurer(Context context) {
        renderScript = RenderScript.create(context);
    }

    public void blurryBitmap(Bitmap originalBitmap, OnOperationFinishedListener onOperationFinishedListener) {
        new AsyncTask<Bitmap, Void, Bitmap>() {
            private OnOperationFinishedListener onOperationFinishedListener;

            @Override
            protected Bitmap doInBackground(Bitmap... params) {
                Bitmap originalBitmap = params[0];
                Allocation inputAllocation = Allocation.createFromBitmap(renderScript, originalBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SHARED | Allocation.USAGE_GRAPHICS_TEXTURE | Allocation.USAGE_SCRIPT);

                Bitmap blurryBitmap = Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), originalBitmap.getConfig());
                Allocation outputAllocation = Allocation.createFromBitmap(renderScript, blurryBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SHARED | Allocation.USAGE_SCRIPT);

                ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
                intrinsicBlur.setRadius(BLUR_RADIUS);
                intrinsicBlur.setInput(inputAllocation);

                intrinsicBlur.forEach(outputAllocation);
                outputAllocation.copyTo(blurryBitmap);

                return blurryBitmap;
            }

            @Override
            protected void onPostExecute(Bitmap blurryBitmap) {
                onOperationFinishedListener.onBlurryFinished(blurryBitmap);
            }

            public AsyncTask<Bitmap, Void, Bitmap> withListener(OnOperationFinishedListener onOperationFinishedListener) {
                this.onOperationFinishedListener = onOperationFinishedListener;
                return this;
            }
        }.withListener(onOperationFinishedListener).execute(originalBitmap);
    }
}
