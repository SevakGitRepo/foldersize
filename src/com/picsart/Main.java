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

        //listFilesForFolderNull(folder);
        //2 method
        Thread thread = new Thread(() -> listFilesForFolder(folder));
        thread.start();

        while (thread.isAlive()) {
            TimeUnit.MILLISECONDS.sleep(500);
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
//                System.out.println(fileEntry.getName());
                    fileSize += fileEntry.length();
                    fileCount++;
                }
            }
        } catch (Exception ignored) {
        }
    }

    public static void listFilesForFolderNull(final File folder) {
        if (folder != null) {
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                    foldrCount++;
                } else {
                    fileSize += fileEntry.length();
                    fileCount++;
                }
            }
        }
    }

}
