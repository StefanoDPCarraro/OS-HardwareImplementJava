package Software;

import Hardware.Memory.Word;

public class PCB {
    public int id;
    public int[] tabelaPags;
    public Word[] program;
    public int pc; // TODO: Usa registrador?? Ou só pc?? Outra, PC começa do 0 e é interno ou do inicio do programa na memoria e é externo??
    public Status status;

    public PCB(int id, int[] tabelaPags, Word[] program){
        this.id = id;
        this.tabelaPags = tabelaPags;
        this.status = status.READY;
        this.program = program;
        this.pc = 0; // TODO: Sempre começa como 0?? Parece que sim
    }
}
