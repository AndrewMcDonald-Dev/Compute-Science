package strategy;

import java.util.ArrayList;
import memory.Block;
import memory.Process;

public interface Fit {
    public void allocate(Process p, ArrayList<Block> blocks);
}
