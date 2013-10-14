package com.xrigau.renderscripting.effects;

import android.renderscript.RenderScript;

public class BitmapEffects {

    public static BitmapEffect getBlurryEffect(float blurRadius, RenderScript renderScript) {
        return new BlurEffect(renderScript, blurRadius);
    }

    public static BitmapEffect getGrayscaleEffect(RenderScript renderScript) {
        return new GrayscaleEffect(renderScript);
    }
}
