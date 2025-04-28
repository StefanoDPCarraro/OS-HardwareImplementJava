package Hardware.Memory;

import Hardware.CPU.Opcode;

public class Memory {
	public Word[] pos; // pos[i] é a posição i da memória. cada posição é uma palavra.
	private int tamPag;

	public Memory(int size, int pag) {
		pos = new Word[size];
		for (int i = 0; i < pos.length; i++) {
			pos[i] = new Word(Opcode.___, -1, -1, -1);
		}
		tamPag = pag;
		; // cada posicao da memoria inicializada
	}
}
