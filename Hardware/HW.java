package Hardware;
import Hardware.CPU.CPU;
import Hardware.Memory.Memory;

public class HW {
    public Memory mem;
    public CPU cpu;

    public HW(int tamMem, int tamPag) {
        mem = new Memory(tamMem);
        cpu = new CPU(mem, true); // true liga debug
    }
}
