/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import pma_Entities.PosDataVals;

/**
 *
 * @author Moustafa
 */
public class ObjFunctions {

    public static List<PosDataVals> convergence_Curve = new ArrayList<>();
    public static List<Double> fitness_History = new ArrayList<>();

    private static double func1(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 0; i < valList.size() - 1; i++) 
            sum += Math.pow(valList.get(i).getX_val(), 2);
    return sum;
    }

    private static double func2(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 0; i < valList.size() - 1; i++) {
            sum += (100 * Math.pow((Math.pow(valList.get(i).getX_val(), 2)) - valList.get(i + 1).getX_val(),2)) + Math.pow(valList.get(i).getX_val() - 1, 2);
        }
        return sum;
    }

    private static double func3(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 1; i < valList.size(); i++) {
            double Temsum = 0;
            for (int j = 0; j < i; j++) {
                Temsum += valList.get(j).getX_val();
            }
           sum += Math.pow(Temsum,2); 
        }
        return sum;
    }

    private static double func4(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 1; i < valList.size(); i++) {
            sum += Math.pow((Math.pow(valList.get(i).getX_val(), 2) + 0.5), 2);
        }
        return sum;
    }

    private static double func5(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 1; i < valList.size(); i++) {
            sum += i * Math.pow(valList.get(i).getX_val(), 4) + ThreadLocalRandom.current().nextDouble(0, 1 + 1);
        }
        return sum;
    }

    private static double func6(List<PosDataVals> valList) {
        double sum = 0;
        for (int i = 1; i < valList.size(); i++) {
            sum += -valList.get(i).getX_val() * Math.sin(Math.sqrt(Math.abs(valList.get(i).getX_val())));
        }
        return sum;
    }

    public static double applyFunction(List<PosDataVals> valList, String funcName) {
        double resVal;
        switch (funcName) {
            case "func1":
                resVal = func1(valList);
                break;
            case "func2":
                resVal = func2(valList);
                break;
            case "func3":
                resVal = func3(valList);
                break;
            case "func4":
                resVal = func4(valList);
                break;
            case "func5":
                resVal = func5(valList);
                break;
            case "func6":
                resVal = func6(valList);
                break;
            default:
                resVal = func1(valList);
        }
        return resVal;
    }

    public static Map<String, Double> getMinMaxValues(String funcName) {
        Map<String, Double> minMaxValues = new HashMap<>();
        switch (funcName) {
            case "func1":
                minMaxValues.put("min", -100d);
                minMaxValues.put("max", 100d);
                break;
            case "func2":
                minMaxValues.put("min", -5d);
                minMaxValues.put("max", 10d);
                break;
            case "func3":
                minMaxValues.put("min", -100d);
                minMaxValues.put("max", 100d);
                break;
            case "func4":
                minMaxValues.put("min", -100d);
                minMaxValues.put("max", 100d);
                break;
            case "func5":
                minMaxValues.put("min", -1.28d);
                minMaxValues.put("max", 1.28d);
                break;
            case "func6":
                minMaxValues.put("min", -500d);
                minMaxValues.put("max", 500d);
                break;
        }
        return minMaxValues;
    }
}
