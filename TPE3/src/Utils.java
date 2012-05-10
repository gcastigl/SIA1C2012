import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {

	public static void loadImage(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			getRGBArray(image);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void printPixelARGB(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		System.out.println("argb: " + alpha + ", " + red + ", " + green + ", "
				+ blue);
	}
	
	public static int getRed(int color) {
		return (color >> 16) & 0xff;
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

	public static void toblackAndWhite(int[] array) {
		for (int i = 0; i < array.length; i++) {
			
		}
	}
}
