package Software;

import Hardware.Memory.Word;

public class PCB {
    public int id;
    public int[] tabelaPags;
    public Word[] program;

    public Context context;
    public Status status;

    public PCB(int id, int[] tabelaPags, Word[] program){
        this.id = id;
        this.tabelaPags = tabelaPags;
        this.status = Status.READY;
        this.program = program;
        this.context = new Context(0, new int[10]);
    }

    @Override
    public String toString() {

        StringBuilder tabPagsStringBuilder = new StringBuilder();
        tabPagsStringBuilder.append("[");
        for(int i = 0; i < tabelaPags.length; i++){
            tabPagsStringBuilder.append(tabelaPags[i] + ", ");
        }
        tabPagsStringBuilder.append("]");

        String tabPagsToString = tabPagsStringBuilder.toString();
        
        StringBuilder registradoresStringBuilder = new StringBuilder();
        registradoresStringBuilder.append("[");
        for(int i = 0; i < context.registers.length; i++){
            registradoresStringBuilder.append(context.registers[i] + ", ");
        }
        registradoresStringBuilder.append("]");

        String registradoresToString = registradoresStringBuilder.toString();

        return "Id: " + id  + "      Pc: " + context.pc + "\n"
        + "Tabela pags: " + tabPagsToString + "\n"
        + "Status: " + status.name() + "\n"
        + "Registradores: " + registradoresToString;
    }
}
