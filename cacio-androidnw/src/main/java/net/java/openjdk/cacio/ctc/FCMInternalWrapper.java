package net.java.openjdk.cacio.ctc;

import sun.font.*;
import sun.font.FontConfigManager.*;

public class FCMInternalWrapper {
    public static FontConfigInfo getFontConfigInfo(FontConfigManager instance) {
        return (FontConfigInfo) invokeMethod(instance, "getFontConfigInfo");
    }

    public static FcCompFont[] loadFontConfig(FontConfigManager instance) {
        return (FcCompFont[]) invokeMethod(instance, "loadFontConfig");
    }

    public static void populateFontConfig(FontConfigManager instance, FcCompFont[] arr) {
        invokeMethod(instance, "populateFontConfig", arr);
    }
    
    private static Object invokeMethod(FontConfigManager instance, String method, Object... objs) {
        try {
            Class[] clsArr = new Class[objs.length];
            for (int i = 0; i < objs.length; i++) {
                clsArr = objs[i].getClass();
            }

            Method m = instance.getClass().getDeclaredMethod(method, clsArr);
            m.setAccessible(true);
            return m.invoke(objs);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }
}

