package com.example.StudyTime;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/* A helper class to facilitate the reading and writing to internal storage */
public class FileHelper {
    Context context;
    String filename;
    File dir;

    // constructor that sets the context and creates a File for saving/loading with
    public FileHelper(Context context, String filename) {
        this.context = context;
        this.filename = filename + ".txt";
        dir = new File(this.context.getApplicationContext().getFilesDir(), this.filename);
    }

    // creates the file (if possible), else displays errors
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

    // writes data to the file
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

    // reads data from the file
    public String readFromFile() {
        String contents = "";
        try {
            Scanner myReader = new Scanner(dir);
            while (myReader.hasNextLine()) { contents += myReader.nextLine(); }
            myReader.close();
            System.out.println("Successfully read from file: " + dir.getName());
        } catch (IOException e) {
            System.out.println("An error occurred. Could not read from file: " + dir.getName());
            e.printStackTrace();
        }

        return contents;
    }

    // checks to see if a file exists and has data inside of it
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