package com.chat.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HardDriveImageSaver{
    private final String folderPath;
    private int counter;

    public HardDriveImageSaver(String folderPath) {
        this.folderPath = folderPath;
        this.counter = 0;
    }

    public void save(String clientName, String imageType, ByteArrayOutputStream imageData) throws IOException {
        File file = new File(String.format("./%s/%s%s%s", this.folderPath, clientName, this.counter, imageType));
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        imageData.writeTo(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        this.counter++;
    }
}
