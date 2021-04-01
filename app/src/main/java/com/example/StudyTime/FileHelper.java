package com.example.StudyTime;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
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
                System.out.println("File: " + dir.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Could not create file: " + dir.getName());
            e.printStackTrace();
        }
    }

    public void writeToFile(String contents) {
        try {
            FileWriter myWriter = new FileWriter(dir);
            myWriter.write(contents);
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + dir.getName());
        } catch (IOException e) {
            System.out.println("An error occurred. Could not write to file: " + dir.getName());
            e.printStackTrace();
        }
    }

    public String readFromFile() {
        String contents = "";
        try {
            Scanner myReader = new Scanner(dir);
            if (myReader.hasNextLine()) { contents = myReader.nextLine(); }
            myReader.close();
            System.out.println("Successfully read from file: " + dir.getName());
        } catch (IOException e) {
            System.out.println("An error occurred. Could not read from file: " + dir.getName());
            e.printStackTrace();
        }

        return contents;
    }

    public boolean fileExists() {
        Scanner sc = null;
        try {
            sc = new Scanner(dir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (sc.hasNextLine()) {
            return true;
        } else {
            return false;
        }
    }
}