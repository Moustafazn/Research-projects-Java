/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_Utilities;

import java.util.List;

/**
 *
 * @author Moustafa
 */
public class LocalMinimaUtils {

    public static double findLocalMinima(List<Double> valuesList) {
        return findLocalMinima(valuesList, 0, valuesList.size());
    }

    private static double findLocalMinima(List<Double> valuesList, int start, int end) {
        int mid = (start + end) / 2;
        if (mid - 2 < 0 && mid + 1 >= valuesList.size()) {
            return -1;
        }
        if (valuesList.get(mid - 2) > valuesList.get(mid - 1) && valuesList.get(mid - 1) < valuesList.get(mid)) {
            return valuesList.get(mid - 1);
        }
        if (valuesList.get(mid - 1) > valuesList.get(mid - 2)) {
            return findLocalMinima(valuesList, start, mid);
        } else {
            return findLocalMinima(valuesList, mid, end);
        }
    }

}
