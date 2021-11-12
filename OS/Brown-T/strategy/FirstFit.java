package strategy;

import java.util.ArrayList;

import memory.Block;
import memory.Process;

public class FirstFit implements Fit {
    @Override
    public void allocate(Process p, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (block.processFits(p)) {
                block.assign(p);
                return;
            }
        }
    }
}
