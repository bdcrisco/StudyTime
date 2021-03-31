package com.example.StudyTime;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {
    Context context;
    String filename;
    File dir;

    public FileHelper(Context context, String filename) {
        this.context = context;
        this.filename = filename + ".txt";
        dir = new File(this.context.getApplicationContext().getFilesDir(), this.filename);
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

    public void writeToFile(String contents) {
        try {
            FileWriter myWriter = new FileWriter(dir);
            myWriter.write(contents);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write to file");
            e.printStackTrace();
        }
    }

    public String readFromFile() {
        String contents = "";
        try {
            Scanner myReader = new Scanner(dir);
            contents = myReader.nextLine();
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not read from file");
            e.printStackTrace();
        }

        return contents;
    }
}