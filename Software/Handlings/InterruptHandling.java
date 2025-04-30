package Software.Handlings;
import Hardware.HW;
import Hardware.CPU.Interrupts;
import Software.Context;
import Software.Status;

public class InterruptHandling {
    private HW hw; // referencia ao hw se tiver que setar algo

    public InterruptHandling(HW _hw) {
        hw = _hw;
    }

    public void handle(Interrupts irpt) {
        if(irpt == Interrupts.intEnderecoInvalido){
            hw.cpu.gp.running.status = Status.BLOCKED;
            hw.cpu.gp.running = null;
        }
        // apenas avisa - todas interrupcoes neste momento finalizam o programa
        System.out.println(
                "                                               Interrupcao " + irpt + "   pc: " + hw.cpu.pc);
    }

    public void handleTimer(Interrupts irpt, int pc, int id){
        System.out.println("Timer Interrupt -  PC=" + pc + " - ID="+id);
        Context ctx = new Context(hw.cpu.pc, hw.cpu.reg);
        hw.cpu.gp.running.context = ctx;
        hw.cpu.gp.ready.add(hw.cpu.gp.running);
        hw.cpu.gp.running = null;
    }
}