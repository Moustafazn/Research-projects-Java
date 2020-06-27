/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 *
 * @author Moustafa
 */
public class SortFunction {

    public static void sort_and_index() {
        Map<Integer, Double> sortedMap
                = Algorithm_parameters.fitness_vals.entrySet().stream()
                .sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                (e1, e2) -> e1, HashMap::new));
        if (Algorithm_parameters.funcType.equals("min")) {
            Algorithm_parameters.alpha_Score = sortedMap.get(0);
            ObjFunctions.fitness_History.add(Algorithm_parameters.alpha_Score);
            Algorithm_parameters.beta_Score = sortedMap.get(1);
            Algorithm_parameters.delta_Score = sortedMap.get(2);
        } else {
            Algorithm_parameters.alpha_Score = sortedMap.get(sortedMap.size() - 1);
            Algorithm_parameters.beta_Score = sortedMap.get(sortedMap.size() - 2);
            Algorithm_parameters.delta_Score = sortedMap.get(sortedMap.size() - 3);
        }
    }
}
