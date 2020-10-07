package net.java.openjdk.cacio.ctc;

import java.awt.GraphicsDevice;

import sun.font.FontConfigManager;
import static sun.font.FontConfigManager.*;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class CTCGraphicsEnvironment extends SunGraphicsEnvironment {
    static {
        // Android: add a dummy font
        FontConfigFont[] fcFont = new FontConfigFont[2];
        
        fcFont[0] = new FontConfigFont();
        fcFont[0].familyName = "Android Roboto Sans";
        fcFont[0].styleStr = "Regular";
        fcFont[0].fullName = "Android Roboto Sans Regular";
        fcFont[0].fontFile = "/system/fonts/DroidSans.ttf";

        fcFont[1] = new FontConfigFont();
        fcFont[1].familyName = "Android Roboto Sans";
        fcFont[1].styleStr = "Bold";
        fcFont[1].fullName = "Android Roboto Sans Bold";
        fcFont[1].fontFile = "/system/fonts/DroidSans-Bold.ttf";
        
        FcCompFont font = new FcCompFont();
        font.fcName = "sans"; //monospace
        font.fcFamily = "sans"; // eg sans
        font.jdkName = "sansserif"; // eg sansserif
        font.style = 0; // eg 0=PLAIN
        font.allFonts = fcFont;
        font.firstFont = fcFont[0];
        // font.compFont = ...; 
        FontConfigManager.FcCompFont[] fontArr = new FcCompFont[]{font};
        FontConfigManager.populateFontConfig(fontArr);
    }
    
    public CTCGraphicsEnvironment() {
        SurfaceManagerFactory.setInstance(new CTCSurfaceManagerFactory());
    }

    @Override
    protected int getNumScreens() {
        return 1;
    }

    @Override
    protected GraphicsDevice makeScreenDevice(int screennum) {
        return new CTCGraphicsDevice();
    }

    @Override
    public boolean isDisplayLocal() {
        return true;
    }

}
