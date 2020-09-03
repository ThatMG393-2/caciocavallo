package net.java.openjdk.cacio.ctc;

import java.io.File;

import java.awt.*;
import java.awt.image.*;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class CTCGraphicsEnvironment extends SunGraphicsEnvironment {
	static {
		OpenJDKNativeRegister.registerNatives();
		
		// FIXME a better way to get window graphics output
		new Thread(new Runnable(){
				@Override
				public void run() {
					File fbFile = new File("/sdcard/awtOutFb.png");
					
					try {
						Thread.sleep(200);

						Robot robot = new Robot();
						while (true) {
							// Window[] windowList = Window.getWindows();
							BufferedImage capture = robot.createScreenCapture(new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,  Toolkit.getDefaultToolkit().getScreenSize().height));
							
							javax.imageio.ImageIO.write(capture, "png", fbFile);
							
							/*
							android.graphics.Canvas androidCanvas = Surface.ANDROID_SURFACE_BRIDGE.lockCanvas(new Rect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
							androidCanvas.drawBitmap(BitmapFactory.decodeBufferedImage(capture), 0, 0, null);
							Surface.ANDROID_SURFACE_BRIDGE.unlockCanvasAndPost(androidCanvas);
							*/
							Thread.sleep(16);
						}
					} catch (Throwable th) {
						System.err.println("AWTAndroid Thread stopped due to an error");
						th.printStackTrace();
					}
				}
			}, "AWTAndroidRender").start();
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
