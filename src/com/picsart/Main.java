package com.picsart;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static long fileCount;
    static long foldrCount;
    static long fileSize;

    public static void main(String[] args) throws InterruptedException {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the directory path");
        String path = scanner.nextLine();

        //String path = "C:\\Windows";


        final File folder = new File(path);
        System.out.println(folder.getName());

        //listFilesForFolder(folder);
        //2 method
        Thread thread = new Thread(() -> { listFilesForFolderNull(folder);
        });
        thread.start();

        while (thread.isAlive()) {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println("-------INFO-------");
            System.out.println("File count  = " + fileCount);
            System.out.println("Folder count = " + foldrCount);
            System.out.println("Folder size = " + fileSize / 1_000_000 + " MB");
            System.out.println("----------------------");
        }

        System.out.println("End");

    }

    public static void listFilesForFolder(final File folder) {
        try {
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                    foldrCount++;
                } else {

                    fileSize += fileEntry.length();
                    fileCount++;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("File name "+fileEntry.getName());
            }
        } catch (Exception ignored) {
        }
    }

    public static void listFilesForFolderNull(final File folder) {
        if (folder.listFiles() != null) {
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolderNull(fileEntry);
                    foldrCount++;
                } else {
                    fileSize += fileEntry.length();
                    fileCount++;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("File name "+fileEntry.getName());
            }
        }
    }
}
