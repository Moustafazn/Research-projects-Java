/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pma_Entities.PosDataVals;

/**
 *
 * @author Moustafa
 */
public class FileUtils {

    public static void writeValuesInFile(List<Double> values, String fileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileName + ".txt"));
            int row = 1;
            for (Double value : values) {
                pw.println(row + " " + value);
                row++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }

    public static void writeValuesListInFile(List<PosDataVals> values, String fileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileName + ".txt"));
            int row = 1;
            for (PosDataVals value : values) {
                pw.println(row + " " + value.getX_val());
                row++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    
    public static List<Double> readAlistFromFile(String fileName)
    {
        List<Double> arr = new ArrayList<Double>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName+".txt")))
        {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(Double.parseDouble(sCurrentLine.split(" ")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    return arr;
    }
    
}
