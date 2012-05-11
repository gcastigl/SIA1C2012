package utils;

import hopfield.HopfieldNet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	/**
	 *
	 * Loads an RGB image stored in the given path and converts it
	 * to black and white. 
	 * Returns an INT array containing a 0 if the pixel is white
	 * and 1 if the pixel is black.
	 *
	 * @path: Path of the image
	 *
	 */ 
	public static int[] loadBlackAndWhiteImage(String path) {
		int[] colorImage = loadImage(path);
		toBlackAndWhite(colorImage);
		return colorImage;
	}

	/**
	 *
	 * Loads an RGB image stored in the given path. 
	 * Returns an INT array containing the alpha, R, G, B channels
	 * for each pixel of the image.
	 *
	 * @path: Path of the image
	 *
	 */ 
	public static int[] loadImage(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			return getRGBArray(image);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	private static int[] getRGBArray(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		int[] rgb = new int[w * h];
//		System.out.println("width, height: " + w + ", " + h);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
//				System.out.println("x,y: " + j + ", " + i);
				int pixel = image.getRGB(j, i);
//				printPixelARGB(pixel);
				rgb[i * j] = pixel;
			}
		}
		return rgb;
	}

	public static void toBlackAndWhite(int[] array) {
		for (int i = 0; i < array.length; i++) {
			// POSSIBLE FIXME: the color is set according to red value only.
			if  (getRed(array[i]) > (255 / 2)) {
				array[i] = HopfieldNet.STATE_POSITIVE;
			} else {
				array[i] = HopfieldNet.STATE_NEGATIVE;
			}
			
		}
	}
	
	public static int getRed(int color) {
		return (color >> 16) & 0xff;
	}
	
	public static int getRGB(int alpha, int red, int green, int blue) {
		return alpha << 24 | red << 16 | green << 8 | blue; 
	}
	
	public static void printPixelARGB(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
//		System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
	}
	
}
