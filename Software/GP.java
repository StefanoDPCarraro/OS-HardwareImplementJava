package Software;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Hardware.Memory.Word;

public class GP {
    public List<PCB> processes;
    public Queue<PCB> ready;
    public PCB running;
    public GM gm;
    public int currentID;
    public GP(GM gm){
        currentID = 0;
        processes = new ArrayList<>();
        running = null;
        this.gm = gm;
        ready = new LinkedList<>();
    }
    public boolean createProcess(Word[] process){
        int tam = process.length;
        if(!gm.canAlloc(tam)){
            return false;
        }
        int[] tabelaPags = gm.alloc(process);

        PCB pcb = new PCB(currentID, tabelaPags, process);
        currentID++;
        processes.add(pcb);
        ready.add(pcb);
        // TODO: Continuar método e fazer resto do GP - Fila de ready
        return true;
    }
    public void desalocaProcesso(int id){
        if(running != null){
            if(running.id == id){
                gm.desaloca(running.tabelaPags);
                processes.remove(running);
                running = null;
                return;
            }
        }
     
        for(PCB proc: processes){
            if(proc.id == id){
                gm.desaloca(proc.tabelaPags);
                if(proc.status == Status.READY){
                    ready.remove(proc);
                }
                processes.remove(proc);
                return;
            }
        }
    }
    public void printAllProcesses(){ //Função adicional para interface
        for(PCB proc: processes){
            System.out.println(proc);
            System.out.println();
        }
    }
}
