package com.quakewatch.ekos.quakewatchaustria.Custom_Adapter_Listener;

import android.graphics.drawable.Drawable;

/**
 * Created by philippkogler on 02.01.16.
 */
public class ContactInfo {
    public String placeName;
    public int img;

    public ContactInfo(int img, String placeName) {
        this.img = img;
        this.placeName = placeName;
    }
}
