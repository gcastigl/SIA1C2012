package utils;

import hopfield.HopfieldNet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	/**
	 * Sets an RGB image to HopField states valid array. 
	 * Sets an int[] to a STATE_POSITIVE or STATE_NEGATIVE
	 * depending on the color of the pixel.
	 * 
	 * @image: int[] to convert
	 */ 
	public static void convertColorImageToState(int[] image) {
		for (int i = 0; i < image.length; i++) {
			// POSSIBLE FIXME: the color is set according to red value only.
			if  (getRed(image[i]) > (255 / 2)) {
				image[i] = HopfieldNet.STATE_POSITIVE;
			} else {
				image[i] = HopfieldNet.STATE_NEGATIVE;
			}
		}
	}
	/**
	 * Loads an RGB image stored in the given path. 
	 * Returns an INT array containing the alpha, R, G, B channels
	 * for each pixel of the image.
	 * 
	 * @path: Path of the image
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
		System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
	}
	
}
