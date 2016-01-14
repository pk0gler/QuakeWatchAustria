package com.quakewatch.ekos.quakewatchaustria;

import android.test.AndroidTestCase;

import com.quakewatch.ekos.quakewatchaustria.Tablayout_Fragments.Erdbeben;

/**
 * Created by pkogler on 14.01.2016.
 */
public class testErdbeben extends AndroidTestCase {
    public void testErdbeben_Konstruktor_1_mag() {
        Erdbeben expected = new Erdbeben();
        assertEquals(expected.getMag(), 0.0);
    }

    public void testErdbeben_Konstruktor_1_region() {
        Erdbeben expected = new Erdbeben();
        assertEquals(expected.getRegion(), "");
    }

    public void testErdbeben_Konstruktor_1_timeWhole() {
        Erdbeben expected = new Erdbeben();
        assertEquals(expected.getTimeWhole(), "");
    }

    public void testErdbeben_Konstruktor_1_depth() {
        Erdbeben expected = new Erdbeben();
        assertEquals(expected.getDepth(), 0.0);
    }

    public void testFormatRegion() {
        String expected = "Frohnleiten /Steiermark";
        Erdbeben temp = new Erdbeben();
        temp.setRegion("FrohnLEITen / SteiERmaRk");
        String actual = temp.formatRegion(temp.getRegion());
        assertEquals(expected,actual);
    }
}
