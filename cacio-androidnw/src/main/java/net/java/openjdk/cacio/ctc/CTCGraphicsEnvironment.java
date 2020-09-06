package net.java.openjdk.cacio.ctc;

import java.io.File;

import java.awt.*;
import java.awt.image.*;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class CTCGraphicsEnvironment extends SunGraphicsEnvironment {
	static {
		android.os.OpenJDKNativeRegister.registerNatives();
		
		try {
			// Make it init headless mode first
			Class.forName("java.awt.Toolkit");

			System.setProperty("java.awt.headless", "false");
		} catch (ClassNotFoundException e) {}
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
