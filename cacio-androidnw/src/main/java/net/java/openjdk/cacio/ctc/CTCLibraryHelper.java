package net.java.openjdk.cacio.ctc;

public class CTCLibraryHelper {
    public static void loadAWTHeadlessLibrary() {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.loadLibrary("awt_headless");
        });
    }
}
