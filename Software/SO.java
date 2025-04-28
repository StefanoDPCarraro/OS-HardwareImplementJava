package Software;
import Hardware.HW;
import Software.Handlings.InterruptHandling;
import Software.Handlings.SysCallHandling;

public class SO {
    public InterruptHandling ih;
    public SysCallHandling sc;
    public Utilities utils;

    public SO(HW hw) {
        ih = new InterruptHandling(hw); // rotinas de tratamento de int
        sc = new SysCallHandling(hw); // chamadas de sistema
        hw.cpu.setAddressOfHandlers(ih, sc);
        utils = new Utilities(hw);
    }
}