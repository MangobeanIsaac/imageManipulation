package imageManip;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageManipulation extends PApplet {
	PImage picture;
	PImage barney;
	PImage beach;

	public static void main(String[] args) {
		PApplet.main("imageManip.ImageManipulation");

	}

	public void settings() {
		size(700, 700);

	}

	public void setup() {
		picture = loadImage("images/breakfast-secret.png");
		barney = loadImage("images/smaller-barny.jpg");
		beach = loadImage("images/beach.jpeg");

		// grayscale();
		// negate();
		// greenScreen();
		mirror();
		// mouseClicked();
	}

	public void draw() {
		image(picture, 0, 0);

	}

	public void grayscale() {
		// GOAL: gray scale filter
		// Note *3 main colors are: Red, green, and blue, also think of the numbers as
		// x, y, and z
		// Firstly, find the average of each of those colors and set them to a variable
		// Next, take the average amount of red, blue, and greens

		for (int x = 0; x < picture.width; x++) {
			for (int y = 0; y < picture.height; y++) {
				int pixel = picture.get(x, y);

				// Int newColor = color(blue(pixel),red(pixel), green(pixel));

				float blueComponent = blue(pixel);
				float redComponent = red(pixel);
				float greenComponent = green(pixel);

				float average = ((redComponent + blueComponent + greenComponent) / 3);// find the average and divide by
																						// 3
				int grayscale = color(average, average, average); // makes the filter for grayscale
				picture.set(x, y, grayscale);

			}
		}

	}

	public void negate() {
		// switch pixel to its opposite color
		// 255- red, 255 -blue, 255 -red
		for (int x = 0; x < picture.width; x++) {
			for (int y = 0; y < picture.height; y++) {
				int pixel = picture.get(x, y);

				float redComponent = 255 - red(pixel);
				float greenComponent = 255 - green(pixel);
				float blueComponent = 255 - blue(pixel);

				int colors = color(redComponent, greenComponent, blueComponent);

				picture.set(x, y, colors);
			}
		}
	}

	public void mirror() {
		// GOAL: flip half of the picture
		for (int x = 0; x < picture.width; x++) {
			for (int y = 0; y < picture.height; y++) {
				int pixel = picture.get(x, y);

				float redComponent = red(pixel);
				float greenComponent = green(pixel);
				float blueComponent = blue(pixel);

				int colors = color(redComponent, greenComponent, blueComponent);

				int newX = picture.width - x;
				picture.set(newX, y, colors);

			}
		}
	}

	public void greenScreen() {
		// load two images, background width and height, foreground has to have the same
		// color
		for (int x = 0; x < barney.width; x++) {

			for (int y = 0; y < barney.height; y++) {
				int compositeColor = barney.get(x, y);
//				float redComponent = red(compositeColor);

				int newColor = beach.get(x, y);

				if (barney.get(x, y) == -3997956) {

					barney.set(x, y, newColor);
					//
				}
			}

		}

	}

	public void mouseClicked() {
		int compositeColor = barney.get(mouseX, mouseY);
		float redComponent = red(compositeColor);
		float greenComponent = green(compositeColor);
		float blueComponent = blue(compositeColor);

		int backToComposite = color(redComponent, greenComponent, blueComponent);

		System.out.println("original composite: " + compositeColor);
		System.out.println("red:   " + redComponent);
		System.out.println("green: " + greenComponent);
		System.out.println("blue:  " + blueComponent);
		System.out.println("back to composite:  " + backToComposite);

		System.out.println();
	}
}
