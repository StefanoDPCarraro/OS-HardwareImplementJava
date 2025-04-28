package Hardware.Memory;

import Hardware.CPU.Opcode;

public class Memory {
	public Word[] pos;

	public Memory(int size) {
		for (int i = 0; i < pos.length; i++){
			pos[i] = new Word(Opcode.___, -1, -1, -1);
		}
		; // cada posicao da memoria inicializada
	}
}
