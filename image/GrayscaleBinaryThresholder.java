package image;

import lib.Picture;
import java.awt.Color;

public class GrayscaleBinaryThresholder implements Thresholder {
    public Picture apply(Picture image, int tau) {
        Picture result = new Picture(image.width(), image.height());

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Color pixel = image.get(x, y);
                int intensity = (int) Math.round(
                    0.299 * pixel.getRed() +
                    0.587 * pixel.getGreen() +
                    0.114 * pixel.getBlue()
                );

                result.set(x, y, intensity > tau ? Color.WHITE : Color.BLACK);
            }
        }

        return result;
    }
}
