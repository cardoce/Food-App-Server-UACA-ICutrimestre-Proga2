/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Interfaces.CSVReaderInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author fernandocardoce
 */
public class CSVReader implements CSVReaderInterface {
    
    
    @Override
    public ArrayList<String[]> readCSVData(String csvFile) {
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String[]> dataContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                dataContent.add(data);

            }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
        return dataContent;
    }
    
    private String fileChooser() {
        String fileName = "";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            fileName = selectedFile.getAbsolutePath();
        }
        return fileName;
    }

    
}
