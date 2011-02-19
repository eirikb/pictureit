package no.eirikb.pictureit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.imageio.ImageIO;

public class PictureIt {

	private final static int CHARSINPIXEL = 3;

	public static void main(String[] args) throws IOException {

		if (args.length > 0) {
			System.out.println("Loading file " + args[0] + "...");
			File file = new File(args[0]);

			if (file.isFile()) {
				System.out.println("Reading pixels...");

				byte[] bytes = new byte[(int) file.length()];

				RandomAccessFile in = new RandomAccessFile(file, "r");
				in.readFully(bytes);
				in.close();

				int length = 0;
				for (int i = 0; i < bytes.length; i++) {
					if (bytes[i] >= 32) {
						length++;
					}
				}

				int j = 0;
				for (int i = 0; i < bytes.length; i++) {
					if (bytes[i] >= 32) {
						bytes[j++] = bytes[i];
					}
				}

				length /= CHARSINPIXEL;

				int size = (int) (Math.sqrt(length)) + 1;

				System.out.println("Bytes: " + bytes.length + ". pixels: "
						+ length + ". size: " + size);

				BufferedImage image = new BufferedImage(size, size,
						BufferedImage.TYPE_INT_BGR);
				for (int i = 0; i < length; i++) {
					int[] pixel = new int[CHARSINPIXEL];
					for (j = 0; j < CHARSINPIXEL; j++) {
						pixel[j] = bytes[i * CHARSINPIXEL + j];
					}
					int x = i % size;
					int y = (int) (i / size);
					image.getRaster().setPixel(x, y, pixel);
				}

				System.out.println("Writing image...");
				ImageIO.write(image, "png", new File("image.png"));
				System.out.println("Done!");
			} else {
				System.out.println("Not a file!");
			}
		} else {
			System.out.println("Must specify a file as argument");
		}
	}
}
