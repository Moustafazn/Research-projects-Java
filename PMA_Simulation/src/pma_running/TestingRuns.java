/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_running;

import java.util.List;
import java.util.OptionalDouble;
import static pma_Utilities.FileUtils.readAlistFromFile;

/**
 *
 * @author Moustafa
 */
public class TestingRuns {
    public static void main(String[] args) {
    List<Double> results = readAlistFromFile("fitnessAfter50Run func5");
    OptionalDouble average = results.stream()
            .mapToDouble(a -> a)
            .average();
    double mean = average.getAsDouble(); 
    System.out.println("Mean is :"+ mean);
    double temp = 0;
    temp = results.stream().map((a) -> (a-mean)*(a-mean)).reduce(temp, (accumulator, _item) -> accumulator + _item);
    double  variance = temp/results.size();
    System.out.println("Variance is :"+ variance);
    System.out.println("Std is :"+ Math.sqrt(variance));
    }
}
