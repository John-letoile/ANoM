package image;

import lib.Picture;

public class FilesystemImageLoader implements ImageLoader {
    public Picture load(String name) {
        return new Picture(name);
    }
}
