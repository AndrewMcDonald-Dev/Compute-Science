import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import memory.RAM;

/**
 * Mallocator This will hold the main method
 */

public class Mallocator {

    public static String stringify(String file) {
        String s = "";
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNextLine()) {
                s += scan.nextLine() + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void write(String s, String file) {
        try {
            FileWriter fileWriter = new FileWriter(new File(file));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(s);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String blocks = stringify("Minput.data");
        String processes = stringify("Pinput.data");
        RAM ram;

        ram = new RAM("best", blocks, processes);
        ram.start();
        write(ram.printProcesses() + "\n" + ram.printBlocks(), "BFOutput.data");

        ram = new RAM("worst", blocks, processes);
        ram.start();
        write(ram.printProcesses() + "\n" + ram.printBlocks(), "WFOutput.data");

        ram = new RAM("first", blocks, processes);
        ram.start();
        write(ram.printProcesses() + "\n" + ram.printBlocks(), "FFOutput.data");
    }

}