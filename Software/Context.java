package Software;

public class Context {
    public int pc;
    public int[] registers;

    public Context(int pc, int[] regs){
        this.pc = pc;
        registers = regs;
    }
}
