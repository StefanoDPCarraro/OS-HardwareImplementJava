package Software;

import Hardware.CPU.Opcode;
import Hardware.Memory.Memory;
import Hardware.Memory.Word;

public class GM {
    public Memory memoria;
    public boolean[] pagLivres;
    public int tamPag;

    public GM(Memory memoria, int tamPag) {
        this.memoria = memoria;
        System.out.println("Memoria: " + memoria);
        this.tamPag = tamPag;
        System.out.println("TamPag: " + tamPag);
        int numPags = memoria.pos.length / tamPag;
        System.out.println("Mem length: " + memoria.pos.length);
        System.out.println("NumPags: " + numPags);
        pagLivres = new boolean[numPags];
        System.out.println("PagLivres: " + pagLivres);
        freePages();
    }

    public void freePages(){
        for(int i = 0; i < pagLivres.length; i++){
            pagLivres[i] = true;
        }
    }

    public boolean canAlloc(int numPalavras) {
        int pagNecessarias = (int) Math.ceil((double)numPalavras / tamPag);

        int livres = 0;

        for (int i = 0; i < pagLivres.length; i++) {
            System.out.println(pagLivres[i]);
            if (pagLivres[i] == true) {
                livres++;
            }
        }

        System.out.println("GM (DEBUG): Frames livres: " + livres);
        System.out.println("GM (DEBUG): Páginas necessárias: " + pagNecessarias);

        return livres >= pagNecessarias;
    }

    public void desaloca(int[] tabelaPags) {
        for (int i = 0; i < tabelaPags.length; i++) {
            pagLivres[tabelaPags[i]] = true;
        }
        for (int i = 0; i < tabelaPags.length; i++) {
            System.out.println("Página [" + i + "] -> Página Física: " + tabelaPags[i]);
            for (int offset = 0; offset < tamPag; offset++) {
                int index = (tabelaPags[i] * tamPag) + offset; // Índice da memoria lógica (indexPag * tamPag) + offset
                memoria.pos[index] = new Word(Opcode.___, -1, -1, -1);
                System.out.println("Índice físico: " + index);
            }
        }
    }

    public int[] alloc(Word[] instructions) {
        int numPalavras = instructions.length;
        int pagNecessarias = (int) Math.ceil((double)numPalavras / tamPag); // memoria.frames[0].words.length pega uma página e
                                                                    // ve o tamanho
        System.out.println("GM: Tentando alocar " + numPalavras + " palavras.");
        System.out.println("GM: Tamanho da página: " + tamPag);
        System.out.println("GM: Páginas necessárias: " + pagNecessarias);

        int[] tabelaPags = new int[pagNecessarias];

        int count = 0;
        if (canAlloc(pagNecessarias)) {
            System.out.println("GM: Há frames suficientes. Iniciando alocação...");

            for (int i = 0; i < pagLivres.length && count < pagNecessarias; i++) {
                System.out.println("GM: Verificando frame " + i + " - livre? " + pagLivres[i]);
                if (pagLivres[i]) { // Frame esta livre
                    pagLivres[i] = false;
                    tabelaPags[count] = i;
                    System.out.println("GM: → Alocando página " + count + " no frame " + i);
                    count++;
                }
            }
            System.out.println("GM: Alocação finalizada com sucesso. Tabela de páginas:");
            for (int i = 0; i < tabelaPags.length; i++) {
                System.out.println("  Página " + i + " → Frame " + tabelaPags[i]);
            }
            int intructionsCount = 0;
            for (int i = 0; i < tabelaPags.length; i++) {
                System.out.println("Página [" + i + "] -> Página Física: " + tabelaPags[i]);
                for (int offset = 0; offset < tamPag; offset++) {
                    int index = (tabelaPags[i] * tamPag) + offset;
                    memoria.pos[index] = instructions[intructionsCount];
                    System.out.println(
                            "Gravando instrução " + intructionsCount +
                                    " (valor: " + instructions[intructionsCount] +
                                    ") no índice físico " + index);
                    intructionsCount++;
                    if(intructionsCount >= instructions.length){
                        break;
                    }
                }
            }
            return tabelaPags;
        } else {
            System.out.println("GM: Não há frames suficientes disponíveis para alocar.");
            return null;
        }
    }

    public int traduzir(int[] tabelaPags, int pc){
        // TODO: Finalizar GM (Passo 1.4) ?? FOI ISSO??
        int pagLogica = pc/tamPag;
        int offset = pc%tamPag;
        int traducao = (tabelaPags[pagLogica] * tamPag) + offset;
        System.out.println("PC="+pc+" PagLogica="+pagLogica+" Traducao="+traducao);
        return traducao;
    }
    
}