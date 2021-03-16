package com.example.StudyTime;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {

    // Part 3
    public static void createFile(String filename) {
        getFilesDir();
//        try {
//            File myObj = new File(mcoContext.getFilesDir(), filename + ".txt");
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }

    public static void writeToFile(String filename, String contents) {
        try {
            FileWriter myWriter = new FileWriter(filename + ".txt");
            myWriter.write(contents);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Part 4
    public static String readFromFile(String filename) {
        String contents = new String();
        try {
            File myObj = new File(filename + ".txt");
            Scanner myReader = new Scanner(myObj);
            contents = myReader.nextLine();
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return contents;
    }

}
