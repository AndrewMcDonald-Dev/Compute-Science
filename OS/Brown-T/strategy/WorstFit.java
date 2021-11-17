package strategy;

import java.util.ArrayList;

import memory.Block;
import memory.Process;

public class WorstFit implements Fit {
    @Override
    public void allocate(Process p, ArrayList<Block> blocks) {
        Block largestFit = null;
        for (Block block : blocks) {
            if (block.processFits(p)) {
                if (largestFit == null || block.size() > largestFit.size())
                    largestFit = block;
            }
        }
        if (largestFit != null) {
            largestFit.assign(p);
        }
    }
}
