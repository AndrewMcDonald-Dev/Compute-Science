import java.util.*;
import java.io.*;

public class Mallocator {
    ArrayList<Memory> mem;
    ArrayList<Process> proc;

    public static void main(String[] args) {
        Mallocator m1 = new Mallocator();
        Mallocator m2 = new Mallocator();
        Mallocator m3 = new Mallocator();
        m1.bestFit(m1.mem, m1.proc);
        m2.worstFit(m2.mem, m2.proc);
        m3.firstFit(m3.mem, m3.proc);
    }
    public Mallocator(){
        try {
            File minput = new File("Minput1.data");
            Scanner mReader = new Scanner(minput);
            mem = new ArrayList<Memory>(Integer.parseInt(mReader.nextLine()));
            while (mReader.hasNextLine()){
                String[] data = mReader.nextLine().split(" ");
                int memStart = Integer.parseInt(data[0]);
                int memEnd = Integer.parseInt(data[1]);
                mem.add(new Memory(memStart, memEnd));
            }
            mReader.close();


            File pinput = new File("Pinput1.data");
            Scanner pReader = new Scanner(pinput);
            proc = new ArrayList<Process>(Integer.parseInt(pReader.nextLine()));
            while (pReader.hasNextLine()){
                String[] data = pReader.nextLine().split(" ");
                int procStart = Integer.parseInt(data[0]);
                int procEnd = Integer.parseInt(data[1]);
                proc.add(new Process(procStart, procEnd));
            }
            pReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public class Memory {
        int startMem;
        int endMem;
        int size;
    
        public Memory(int startMem, int endMem) {
            this.startMem = startMem;
            this.endMem = endMem;
            this.size = endMem - startMem;
        }
    }

    public class Process implements Comparable<Process>{
        int ID;
        int size;
        int start;
        int end;

        public Process(int ID, int size){
            this.ID = ID;
            this.size = size;
        }

        public int compareTo(Process proc){
            return this.start - proc.start;
        }
    }

    public void bestFit(ArrayList<Memory> memory, ArrayList<Process> processes){
        for (int i = 0; i < processes.size(); i++){
            int bestIndex = -1;
            for (int j = 0; j < memory.size(); j++){
                if(memory.get(j).size >= processes.get(i).size){
                    if(bestIndex == -1)
                        bestIndex = j;
                    else if (memory.get(bestIndex).size > memory.get(j).size)
                        bestIndex = j;
                }
            }
            if(bestIndex != -1)
                updateData(memory, processes, i, bestIndex);
        }
        printResult(processes, "BFoutput1.data");
    }

    public void worstFit(ArrayList<Memory> memory, ArrayList<Process> processes){
        for (int i = 0; i < processes.size(); i++ ){
            int worstIndex = -1;
            for(int j = 0; j < memory.size(); j++){
                if(memory.get(j).size >= processes.get(i).size){
                    if(worstIndex == -1)
                        worstIndex = j;
                    else if(memory.get(worstIndex).size < memory.get(j).size)
                        worstIndex =j;
                }
            }
            if (worstIndex != -1)
                updateData(memory, processes, i, worstIndex);
        }
        printResult(processes, "WFoutput1.data");
    }

    
    public void firstFit(ArrayList<Memory> memory, ArrayList<Process> processes){
        for(int i = 0; i < processes.size(); i++){
            for(int j = 0; j < memory.size(); j++){
                if(memory.get(j).size >= processes.get(i).size){
                    updateData(memory, processes, i, j);
                    break;
                }
            }
        }
        printResult(processes, "FFoutput1.data");
    }

    private void updateData(ArrayList<Memory> memory, ArrayList<Process> processes, int procLoc, int memLoc){
        int start = memory.get(memLoc).startMem;
        int end = start + processes.get(procLoc).size;
        processes.get(procLoc).start = start;
        processes.get(procLoc).end = end;
        memory.get(memLoc).size -= processes.get(procLoc).size;
        memory.get(memLoc).startMem += processes.get(procLoc).size;
    }


    private void printResult(ArrayList<Process> processes, String location){
        Collections.sort(processes);
        ArrayList<String> out = new ArrayList<>();
        ArrayList<String> alt = new ArrayList<>();
        for(int i = 0; i < processes.size(); i++){
            Process p = processes.get(i);
            if(p.start != 0 || p.end != 0)
                out.add(p.start + " " + p.end + " " + p.ID);
            else 
                alt.add(String.valueOf(p.ID));
        }
        try {
            FileWriter outWriter = new FileWriter(new File(location));
            for(String line : out)
                outWriter.write(line + "\n");
            outWriter.write("-");
            if(alt.size() == 0)
                outWriter.write("0");
            for(String id : alt)
                outWriter.write(id + " ");
            outWriter.close();
        }catch (Exception e){
            System.out.println("An error bas occured");
            e.printStackTrace();
        }
    }
}
