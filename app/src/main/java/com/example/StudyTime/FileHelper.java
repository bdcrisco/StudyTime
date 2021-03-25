package com.example.StudyTime;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
    private Context context;
    String filename;
    File dir;

    public FileHelper (Context context, String filename) {
        this.context = context;
        dir = new File(context.getApplicationContext().getFilesDir(), filename + ".txt");
    }

    public void createFile() {
        try {
            if (dir.createNewFile()) {
                System.out.println("File created: " + dir.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filename, String contents) {
//        try {
//            FileWriter myWriter = new FileWriter(filename + ".txt");
//            myWriter.write(contents);
//            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }

    public static String readFromFile(String filename) {
//        String contents = new String();
//        try {
//            File myObj = new File(filename + ".txt");
//            Scanner myReader = new Scanner(myObj);
//            contents = myReader.nextLine();
//            myReader.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//        return contents;
        return new String();
    }
}