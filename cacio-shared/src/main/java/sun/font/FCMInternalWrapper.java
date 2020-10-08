package sun.font;

import sun.font.FontConfigManager.*;

public class FCMInternalWrapper {
    public static FontConfigInfo getFontConfigInfo(FontConfigManager instance) {
        return instance.getFontConfigInfo();
    }
    
    public static FcCompFont[] loadFontConfig(FontConfigManager instance) {
        return instance.loadFontConfig();
    }
    
    public static void populateFontConfig(FontConfigManager instance, FcCompFont[] arr) {
        instance.populateFontConfig(arr);
    }
}

