package net.java.openjdk.cacio.ctc;

import java.io.File;

import java.lang.reflect.*;

import java.awt.*;
import java.awt.image.*;

import sun.font.FontConfigManager;
import static sun.font.FontConfigManager.*;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class CTCGraphicsEnvironment extends SunGraphicsEnvironment {
    static {
        android.os.OpenJDKNativeRegister.registerNatives();

        // Android: add a dummy font list
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
        
        try {
            /*
             * Make AWT use Caciocavallo and not load libawt_xawt.so
             * to prevent linking X11 libraries.
             */
            /*
             // Initialize headless mode first
             Class.forName("java.awt.Toolkit");

             // Set false it...
             System.setProperty("java.awt.headless", "false");

             // Set false to GraphicsEvnironment saved isHeadless
             Field headlessField = GraphicsEnvironment.class.getDeclaredField("headless");
             headlessField.setAccessible(true);
             headlessField.set(null, Boolean.FALSE);

             Field defaultHeadlessField = GraphicsEnvironment.class.getDeclaredField("defaultHeadless");
             defaultHeadlessField.setAccessible(true);
             defaultHeadlessField.set(null, Boolean.FALSE);
             */
            // System.setProperty("awt.toolkit", "net.java.openjdk.cacio.ctc.CTCToolkit");
            // System.setProperty("java.awt.graphicsenv", "net.java.openjdk.cacio.ctc.CTCGraphicsEnvironment");
        } catch (Throwable th) {
            System.err.println("Unable to unheadless:");
            th.printStackTrace();
        }
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

    // Headless override
    /*
     @Override
     public boolean isHeadlessInstance() {
     return false;
     }

     @Override
     public static boolean isHeadless() {
     return false;
     }
     */
}
