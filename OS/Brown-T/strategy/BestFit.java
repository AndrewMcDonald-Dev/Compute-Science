package strategy;

import java.util.ArrayList;

import memory.Block;
import memory.Process;

public class BestFit implements Fit {
    @Override
    public void allocate(Process p, ArrayList<Block> blocks) {
        Block smallestFit = null;
        for (Block block : blocks) {
            if (block.processFits(p)) {
                if (smallestFit == null || block.size() < smallestFit.size())
                    smallestFit = block;
            }
        }
        if (smallestFit != null) {
            smallestFit.assign(p);
        }
    }
}
