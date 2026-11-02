package image;

import lib.Picture;

public interface Thresholder {
    Picture apply(Picture image, int tau);
}
