package com.example.colorfulheartsci553.utils.file_manager;

import java.io.*;

public class FileManager {

    final BufferedReader reader;
    final BufferedWriter writer;
    final String file = "saveFile.txt";


    FileManager(){
        try {
            writer = new BufferedWriter(new FileWriter(file));
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(SaveFile saveFile) throws IOException {
        writer.write(saveFile.getName() + ", " + saveFile.getScore());
        writer.newLine();
        writer.close();

    }

    public SaveFile readFromFile(){

    }


}
