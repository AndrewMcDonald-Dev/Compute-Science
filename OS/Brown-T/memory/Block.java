package memory;

import java.util.ArrayList;

/**
 * Block A block is a portion of memory. (an aggregatee of RAM) A block will
 * have a start and end memory address. Blocks can be subdivided into smaller
 * blocks given that there is enough free memory left over
 */

public class Block {
    private int start;
    private int end;
    private int spaceLeft;
    private ArrayList<Process> processes;

    public Block(int start, int end) {
        this.start = start;
        this.end = end;
        this.spaceLeft = size();
        this.processes = new ArrayList<>();
    }

    public boolean assign(Process p) {
        if (!processFits(p))
            return false;

        p.assign(this);
        spaceLeft -= p.size();

        processes.add(p);

        return true;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public int size() {
        return end - start;
    }

    public int spaceLeft() {
        return spaceLeft;
    }

    public boolean processFits(Process p) {
        return (p.size() < spaceLeft);
    }

    @Override
    public String toString() {
        return String.format("Start: %d, End: %d, Space Left: %d, NumProcesses: %d, Processes: %s", start, end,
                spaceLeft, processes.size(), processes.toString());
    }

}