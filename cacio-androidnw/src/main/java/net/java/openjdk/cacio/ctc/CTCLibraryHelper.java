package net.java.openjdk.cacio.ctc;

import java.security.*;

public class CTCLibraryHelper {
    public static void loadAWTHeadlessLibrary() {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            System.loadLibrary("awt_headless");
            return null;
        });
    }
}
