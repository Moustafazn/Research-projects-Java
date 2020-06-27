/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spg_Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Moustafa
 */
public class RandomGeneration {
    
    public static List<Double> generateTwoRandNums()
    {
        List<Double> twoRand = new ArrayList<>();
        twoRand.add(ThreadLocalRandom.current().nextDouble(0, 1 + 1));
        twoRand.add(ThreadLocalRandom.current().nextDouble(0, 1 + 1));
        return twoRand;
    }
}
