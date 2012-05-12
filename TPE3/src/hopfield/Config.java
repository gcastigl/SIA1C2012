package hopfield;

import java.io.File;
import java.util.Arrays;

import utils.ImageUtils;

public class Config {
	
	public static final String RESOURCES_PATH = "./TPE3/resources/";
	public static final int NUMBER_OF_PATTERNS = 2;
	public static final String[] pictures = new String[] {
		"a.png",
		"bad-egg.png",
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
		"windows.png"};
	public static int TOTAL_PICTURES = pictures.length;
	
	
	public static void listImages() {
		File f = new File(RESOURCES_PATH);
		if (f.isDirectory()) {
			System.out.println(Arrays.toString(f.list()));
		}
	}
	
	public static int[] getImage(int index) {
		int[] image = ImageUtils.loadImage(RESOURCES_PATH + pictures[index]);
		ImageUtils.convertColorImageToState(image);
		return image;
	}
}
