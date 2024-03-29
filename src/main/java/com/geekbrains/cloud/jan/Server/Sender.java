package com.geekbrains.cloud.jan.Server;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Sender {

    private Sender () {

    }
    public static void getFile(DataInputStream is, Path clientDir, int size2, byte[] buffer) throws IOException {
        String fileName = is.readUTF();
        System.out.println("Received: " + fileName);
        long size = is.readLong();

        try (OutputStream fos = new FileOutputStream(clientDir.resolve(fileName).toFile())){

            for (int i = 0; i < (size + size2 - 1) / size2; i++) {
                int readBytes = is.read(buffer);
                fos.write(buffer,0,readBytes);
            }
        }
    }

    public static void sendFile(String fileName, DataOutputStream os, Path clientDir) throws IOException {
        os.writeUTF("#file#");
        os.writeUTF(fileName);
        Path file = clientDir.resolve(fileName);
        long size = Files.size(file);
        byte[] bytes = Files.readAllBytes(file);
        os.writeLong(size);
        os.write(bytes);
        os.flush();
    }
}