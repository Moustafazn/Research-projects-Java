/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pma_processes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import pma_Entities.Local_Mem;
import pma_Entities.PosDataVals;
import pma_Utilities.Algorithm_parameters;
import pma_processes.mem_processes.Watch_Jump_ProcessPerMem;

/**
 *
 * @author Moustafa
 */
public class Migration_Precedure {

    private static final int NTHREDS = 10;
    private List<Future<Local_Mem>> localMems;
    private Local_Mem lclMem;

    public Migration_Precedure(List<Future<Local_Mem>> localMems) {
        this.localMems = localMems;
    }

    public void performMigrationProcess() throws Exception {
        while (localMems.size() > 1) {
            ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
            List<Future<Local_Mem>> lclMems = new ArrayList<>();
            for (int i = 0; i < localMems.size(); i += 2) {
                Watch_Jump_ProcessPerMem watch_Jump = null;
                if (i + 1 == localMems.size()) {
                    watch_Jump = new Watch_Jump_ProcessPerMem(localMems.get(i).get());
                    lclMems.add(executor.submit(watch_Jump));
                } else {
                    Local_Mem local1 = localMems.get(i).get();
                    Local_Mem local2 = localMems.get(i + 1).get();
                    List<PosDataVals> tempList = new ArrayList<>(local1.getLcl_Monkey_positions());
                    tempList.addAll(local2.getLcl_Monkey_positions());
                    if (Algorithm_parameters.funcType.equalsIgnoreCase("min")) {
                        if (local1.getOptVal() <= local2.getOptVal()) {
                            local1.setLcl_Monkey_positions(tempList);
                            watch_Jump = new Watch_Jump_ProcessPerMem(local1);
                            lclMems.add(executor.submit(watch_Jump));
                        } else {
                            local2.setLcl_Monkey_positions(tempList);
                            watch_Jump = new Watch_Jump_ProcessPerMem(local2);
                            lclMems.add(executor.submit(watch_Jump));
                        }
                    } else {
                        if (local1.getOptVal() >= local2.getOptVal()) {
                            local1.setLcl_Monkey_positions(tempList);
                            watch_Jump = new Watch_Jump_ProcessPerMem(local1);
                            lclMems.add(executor.submit(watch_Jump));
                        } else {
                            local2.setLcl_Monkey_positions(tempList);
                            watch_Jump = new Watch_Jump_ProcessPerMem(local2);
                            lclMems.add(executor.submit(watch_Jump));
                        }
                    }

                }
            }
            localMems.clear();
            localMems.addAll(lclMems);
            executor.shutdown();

        }
        lclMem = localMems.get(0).get();
    }

    public Local_Mem getLclMem() {
        return lclMem;
    }
}
