package net.java.openjdk.cacio.ctc;

import java.security.*;

public class CTCLibraryHelper {
    public static void loadAWTHeadlessLibrary() {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.loadLibrary("awt_headless");
        });
    }
}
