package Software;

import Hardware.Memory.Memory;
import Hardware.Memory.Word;

public class GM {
    public Memory memoria;
    public boolean[] pagLivres;
    public int tamPag;

    public GM(Memory memoria) {
        this.memoria = memoria;
        int numPags = memoria.pos.length / tamPag;
        pagLivres = new boolean[numPags];
    }

    public boolean canAlloc(int pagNecessarias) {
        int livres = 0;

        for (int i = 0; i < pagLivres.length; i++) {
            if (pagLivres[i]) {
                livres++;
            }
        }

        System.out.println("GM (DEBUG): Frames livres: " + livres);
        System.out.println("GM (DEBUG): Páginas necessárias: " + pagNecessarias);

        return livres >= pagNecessarias;
    }

    public void desaloca(int[] tabelaPags) {
        // TODO: Limpar oq ta dentro
        for (int i = 0; i < tabelaPags.length; i++) {
            pagLivres[tabelaPags[i]] = true;
        }
    }

    public int[] alloc(Word[] instructions, int[] tabelaPags) {
        int numPalavras = instructions.length;
        int pagNecessarias = (int) Math.ceil(numPalavras / tamPag); // memoria.frames[0].words.length pega uma página e
                                                                    // ve o tamanho
        System.out.println("GM: Tentando alocar " + numPalavras + " palavras.");
        System.out.println("GM: Tamanho da página: " + tamPag);
        System.out.println("GM: Páginas necessárias: " + pagNecessarias);

        // int[] framesAlocados = new int[pagNecessarias];

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
            for (int i = 0; intructionsCount < instructions.length; i++) {
                System.out.println("Página [" + i + "] -> Página Física: " + tabelaPags[i]);
                for (int offset = 0; offset < tamPag; offset++) {
                    int index = (tabelaPags[i] * tamPag) + offset;
                    memoria.pos[index] = instructions[intructionsCount];
                    System.out.println(
                            "Gravando instrução " + intructionsCount +
                                    " (valor: " + instructions[intructionsCount] +
                                    ") no índice físico " + index);
                    intructionsCount++;
                }
            }
            return tabelaPags;
        } else {
            System.out.println("GM: Não há frames suficientes disponíveis para alocar.");
            return null;
        }
    }
}
