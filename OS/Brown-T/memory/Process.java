package memory;

public class Process {
    private int PID;
    private int size;
    private int start;
    private int end;
    private Block block;

    public Process(int PID, int size) {
        this.PID = PID;
        this.size = size;
        this.block = null;
    }

    public void assign(Block b) {
        this.block = b;
        this.start = (b.end() - b.spaceLeft());
        this.end = this.start + this.size;
    }

    public int size() {
        return size;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public boolean allocated() {
        return (this.block != null);
    }

    @Override
    public String toString() {
        return String.format("PID: %d, Start: %d, End %d, Allocated: %b", PID, start, end, allocated());
    }
}
