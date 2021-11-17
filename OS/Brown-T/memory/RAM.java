package memory;

import java.util.ArrayList;

import strategy.BestFit;
import strategy.FirstFit;
import strategy.Fit;
import strategy.WorstFit;

/**
 * @class RAM RAM will be a collection of memory blocks. The ram will be
 *        constructed with a given number of process blocks. The blocks will
 *        have a start and end address. The blocks can be partitioned into
 *        smaller blocks recursively if need be. The ram will hold a strategy
 *        for how to allocate incoming processes,
 * 
 */
public class RAM {

    private void deserializeBlocks(String blocks) {
        String[] lines = blocks.split("\n");
        try {
            int numSlots = Integer.parseInt(lines[0]);
            for (int i = 1; i < lines.length; i++) {
                String[] nums = lines[i].split(" ");
                int start = Integer.parseInt(nums[0]);
                int end = Integer.parseInt(nums[1]);
                addBlock(new Block(start, end));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deserializeProcesses(String processes) {
        String[] lines = processes.split("\n");
        try {
            int numSlots = Integer.parseInt(lines[0]);
            for (int i = 1; i < lines.length; i++) {
                String[] nums = lines[i].split(" ");
                int pid = Integer.parseInt(nums[0]);
                int size = Integer.parseInt(nums[1]);
                addProcess(new Process(pid, size));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deserialize(String blocks, String processes) {
        deserializeBlocks(blocks);
        deserializeProcesses(processes);
    }

    private Fit strategy;
    private ArrayList<Block> blocks;
    private ArrayList<Process> processes;

    private Fit createFit(String fit) {
        switch (fit.toLowerCase()) {
        case "best":
            return new BestFit();
        case "worst":
            return new WorstFit();
        case "first":
            return new FirstFit();
        default:
            return new FirstFit();
        }
    }

    /**
     * 
     * @param fit       the algorithm type to use
     * @param blocks    a serialized string of memory blocks
     * @param processes a serialized string of processes
     */
    public RAM(String fit, String blocks, String processes) {
        this.blocks = new ArrayList<>();
        this.processes = new ArrayList<>();
        this.strategy = createFit(fit);
        deserialize(blocks, processes);

    }

    public void start() {
        for (Process process : processes) {
            this.strategy.allocate(process, blocks);
        }
    }

    public void addBlock(Block b) {
        this.blocks.add(b);
    }

    public void addProcess(Process b) {
        this.processes.add(b);
    }

    public String printProcesses() {
        StringBuilder sb = new StringBuilder();
        processes.forEach(p -> {
            sb.append(p + "\n");
            System.out.println(p);
        });
        return sb.toString();
    }

    public String printBlocks() {
        StringBuilder sb = new StringBuilder();
        blocks.forEach(b -> {
            sb.append(b + "\n");
            System.out.println(b);
        });
        return sb.toString();
    }

}