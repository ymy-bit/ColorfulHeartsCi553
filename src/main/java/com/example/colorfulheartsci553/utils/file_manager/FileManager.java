package com.example.colorfulheartsci553.utils.file_manager;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    final String file = "saveFile.txt";

    public void writeToFile(SaveFile saveFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(saveFile.getName() + ", " + saveFile.getScore());
        writer.newLine();
        writer.close();
    }

    public ArrayList<SaveFile> readFromFile() {
        ArrayList<SaveFile> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(", ");
                SaveFile saveFile = new SaveFile();
                saveFile.setName(split[0]);
                saveFile.setScore(Integer.parseInt(split[1]));
                list.add(saveFile);

            }
            reader.close();
        }  catch (IOException ignored) {

        }

        return list;

    }


}
