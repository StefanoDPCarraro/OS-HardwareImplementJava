package Software;

import java.util.ArrayDeque;
import java.util.Queue;

import Hardware.Memory.Word;
import Programas.Programs;

public class GP {
    public Queue<Programs> processes;
    public GM gm;
    public GP(GM gm){
        processes = new ArrayDeque<Programs>();
        this.gm = gm;
    }
    public boolean createProcess(Word[] process){
        int tam = process.length;
        if(!gm.canAlloc(tam)){
            return false;
        }
        PCB pcb = new PCB();
        // TODO: Continuar método e fazer resto do GP
        return true;
    }
}
