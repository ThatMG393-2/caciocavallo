package net.java.openjdk.cacio.ctc;

import java.awt.AWTError;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

import sun.font.*;

class FontManagerUtil {

    // A way to force set a FontManager
    static void setFontManager(final String fmClassName) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {

                public Object run() {
                    try {
                        System.setProperty("sun.font.fontmanager", fmClassName);
                        ClassLoader cl = ClassLoader.getSystemClassLoader();
                        Class<?> fmClass = Class.forName(fmClassName, true, cl);
                        FontManager instance = (FontManager) fmClass.getDeclaredConstructor().newInstance();
                        
                        Field fmInstanceField = FontManagerFactory.class.getDeclaredField("instance");
                        fmInstanceField.setAccessible(true);
                        fmInstanceField.set(null, instance);
                    } catch (ReflectiveOperationException ex) {
                        throw new InternalError(ex);
                    }
                    return null;
                }
            });
    }
    
}
