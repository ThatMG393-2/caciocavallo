package net.java.openjdk.cacio.ctc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.List;

import javax.imageio.ImageIO;

import sun.awt.peer.cacio.WindowClippedGraphics;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;
import sun.awt.peer.cacio.managed.PlatformScreen;
import java.io.*;

public class CTCScreen implements PlatformScreen {

    private BufferedImage screenBuffer;

    private static CTCScreen instance;

    static CTCScreen getInstance() {
        if (instance == null) {
            instance = new CTCScreen();
        }
        return instance;
    }

    private CTCScreen() {
        Dimension d = FullScreenWindowFactory.getScreenDimension();
        screenBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		/*
		new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						FileOutputStream fos = new FileOutputStream("/sdcard/games/.minecraft/awtfb.png", false);
						while (true) {
							Thread.sleep(16);
							ImageIO.write(screenBuffer, "png", fos);
						}
					} catch (Throwable th) {
						throw new RuntimeException(th);
					}
				}
			}, "AWTFB").start();
        */
    }

    @Override
    public ColorModel getColorModel() {
        return screenBuffer.getColorModel();
    }

    @Override
    public GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    @Override
    public Rectangle getBounds() {
        Dimension d = FullScreenWindowFactory.getScreenDimension();
        return new Rectangle(0, 0, d.width, d.height);
    }

    @Override
    public Graphics2D getClippedGraphics(Color fg, Color bg, Font f,
            List<Rectangle> clipRects) {
        Graphics2D g2d = (Graphics2D) screenBuffer.getGraphics();
        if (clipRects != null && clipRects.size() > 0) {
            Area a = new Area(getBounds());
            for (Rectangle clip : clipRects) {
                a.subtract(new Area(clip));
            }
            g2d = new WindowClippedGraphics(g2d, a);
        }
		
        return g2d;
    }

    int[] getRGBPixels(Rectangle bounds) {
        return screenBuffer.getRGB(bounds.x, bounds.y, bounds.width, bounds.height, null, 0, bounds.width);
    }

    // private static Canvas mAndroidCanvas = new Canvas(-1);
    // private static int[] currentRgbArray;
    public static int[] getCurrentScreenRGB(/* long nativeCanvas, int width, int height */) {
      /*
        if (instance.screenBuffer.getWidth() != width || instance.screenBuffer.getHeight() != height) {
            
        }
      */
        // mAndroidCanvas.updateCanvas(nativeCanvas);
        // currentRgbArray = return instance.screenBuffer.getRGB(0, 0, width, height, null, 0, width);
        // mAndroidCanvas.drawBitmap(currentRgbArray, 0, width, 0, 0, width, height, true, null);
        
        if (instance.screenBuffer == null) {
            return null;
        } else {
            return instance.screenBuffer.getRGB(0, 0,
                (int) FullScreenWindowFactory.getScreenDimension().getWidth(),
                (int) FullScreenWindowFactory.getScreenDimension().getHeight(),
                null, 0, (int) FullScreenWindowFactory.getScreenDimension().getWidth());
        }
    }
    
    static {
        // Load it to get JavaVM instance
        // System.loadLibrary("pojavexec");
        
        File currLibFile;
        for (String ldLib : System.getenv("LD_LIBRARY_PATH").split(":")) {
            if (ldLib.isEmpty()) continue;
            currLibFile = new File(ldLib, "libpojavexec.so");
            if (currLibFile.exists()) {
                System.load(currLibFile.getAbsolutePath());
                break;
            }
		}
    }
}
