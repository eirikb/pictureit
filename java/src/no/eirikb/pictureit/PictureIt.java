package no.eirikb.pictureit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.imageio.ImageIO;

public class PictureIt {

    private final static int CHARSINPIXEL = 3;

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("usage: java -jar PictureIt <text-file> [output-image].out.png");
            System.exit(1);
        }

        System.out.printf("Loading file %s ...\n", args[0]);
        File file = new File(args[0]);

        if (!file.isFile()) {
            System.out.printf("Not a file: %s. Aborting ...\n", file.getName());
            System.exit(1);
        }

        System.out.println("Reading pixels ...");
        byte[] bytes = new byte[(int) file.length()];
        RandomAccessFile in = new RandomAccessFile(file, "r");
        in.readFully(bytes);
        in.close();

        int length = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] >= 32) {
                bytes[length++] = bytes[i];
            }
        }

        length /= CHARSINPIXEL;
        int size = (int) Math.sqrt(length) + 1;
        System.out.printf("Bytes: %d\tPixels: %d\tSize: %d\n", bytes.length, length, size);

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < length; i++) {
            int[] pixel = new int[CHARSINPIXEL];
            for (int j = 0; j < pixel.length; j++) {
                pixel[j] = bytes[i * CHARSINPIXEL + j];
            }
            int x = i % size;
            int y = i / size;
            image.getRaster().setPixel(x, y, pixel);
        }

        File output;
        if (args.length >= 2) {
            output = new File(args[1] + ".png");
        } else {
            output = new File(args[0] + ".out.png");
        }
        System.out.printf("Writing image to %s ...\n", output.getName());
        ImageIO.write(image, "PNG", output);
        System.out.println("Done!");
    }
}
