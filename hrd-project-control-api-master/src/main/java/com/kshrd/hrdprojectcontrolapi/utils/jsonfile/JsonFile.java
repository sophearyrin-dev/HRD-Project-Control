package com.kshrd.hrdprojectcontrolapi.utils.jsonfile;

import java.io.*;

public class JsonFile {

    public void writeFile(String book) {
        boolean append;
        File file = new File("jsonFile.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,append=true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bw.write(book);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleted(){
        File file = new File("jsonFile.txt");
        file.delete();
    }

}
