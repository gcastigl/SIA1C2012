package hopfield;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import utils.ImageUtils;

public class Config {
	
	public static final String RESOURCES_PATH = "./TPE3/resources/";
	public static final int NUMBER_OF_PATTERNS = 4;
	public static final String[] pictures = new String[] {
		"a.png",
		"circle-union.png",
		"circle1.png",
		"circle2.png",
		"crouched.png",
		"donatello.png",
		"f.png",
		"footprint.png",
		"green-knot.png",
		"h.png",
		"line1.png",
		"line2.png",
		"line3.png",
		"line4.png",
		"mac.png",
		"midnight-bsd.png",
		"ninja.png",
		"no-clan-avatar.png",
		"pacman.png",
		"phone.png",
		"picture.png",
		"rss.png",
		"sonic.png",
		"windows.png",
		"zbad-egg.png"};
	public static int TOTAL_PICTURES = pictures.length;
	
	
	public static void listImages() {
		File f = new File(RESOURCES_PATH);
		if (f.isDirectory()) {
			System.out.println(Arrays.toString(f.list()));
		}
	}
	
	public static int[] getImageAsState(int index) {
		return getImageAsState(pictures[index]);
	}
	
	public static int[] getImageAsState(String imageName) {
		int[] image = ImageUtils.loadBlackAndWhiteImage(RESOURCES_PATH + imageName);
		ImageUtils.convertBlackAndWhiteImageToState(image);
		return image;
	}
	
	public static void saveStateToImage(int[] outputState) {
		ImageUtils.convertStateToBlackAndWhiteRGB(outputState);
		Image image = ImageUtils.getImageFromArray(outputState, 64, 64);
		// Save as PNG
	    try {
	    	File imageFile = new File(Config.RESOURCES_PATH + "newImage.png");
			ImageIO.write((RenderedImage) image, "png", imageFile);
	    } catch (IOException e) {
			System.out.println("Error saving the image to disk: " + e.getMessage());
		}
	}
}
