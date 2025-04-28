package Software;
import Hardware.HW;
import Software.Handlings.InterruptHandling;
import Software.Handlings.SysCallHandling;

public class SO {
    public InterruptHandling ih;
    public SysCallHandling sc;
    public Utilities utils;
    public GM gm;
    public GP gp;

    public SO(HW hw, int tamPag) {
        ih = new InterruptHandling(hw); // rotinas de tratamento de int
        sc = new SysCallHandling(hw); // chamadas de sistema
        hw.cpu.setAddressOfHandlers(ih, sc);
        utils = new Utilities(hw);

        gm = new GM(hw.mem, tamPag); //Parte 1
        gp = new GP(gm); //Parte 2
    }
}